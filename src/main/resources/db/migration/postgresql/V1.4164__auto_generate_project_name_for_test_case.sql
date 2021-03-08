UPDATE "test_case" SET project_id = (SELECT MIN(id) FROM project) WHERE project_id IS NULL;
ALTER TABLE "test_case" ALTER COLUMN project_id SET NOT NULL;
ALTER TABLE "test_case" ADD COLUMN project_name text;
UPDATE "test_case" tc SET project_name = (SELECT name FROM project WHERE id = tc.project_id LIMIT 1);
ALTER TABLE "test_case" ALTER COLUMN project_name SET NOT NULL;
ALTER TABLE "test_case" DROP CONSTRAINT "test_case_fk_project";
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "test_case_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF TG_OP = 'UPDATE' AND NEW.project_id IS NOT NULL AND NEW.project_id <> OLD.project_id THEN
		NEW.project_name = (SELECT name FROM project WHERE id = NEW.project_id);
	ELSIF TG_OP = 'UPDATE' AND NEW.project_id IS NULL THEN
	    NEW.project_name = NULL;
	END IF;
	
	IF TG_OP = 'INSERT' AND NEW.project_id IS NOT NULL THEN
		NEW.project_name = (SELECT name FROM project WHERE id = NEW.project_id);
	ELSIF TG_OP = 'INSERT' AND NEW.project_id IS NULL THEN
	    NEW.project_name = NULL;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------
CREATE TRIGGER "test_case_generate_columns"
  BEFORE INSERT OR UPDATE ON test_case
  FOR EACH ROW
EXECUTE PROCEDURE test_case_generate_columns();
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "project_update_update_test_case" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.name IS NOT NULL AND NEW.name <> OLD.name THEN
		UPDATE test_case SET project_name = NEW.name WHERE project_id = NEW.id;
	ELSIF TG_OP = 'UPDATE' AND NEW.name IS NULL THEN
	    UPDATE test_case SET project_name = NULL WHERE project_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------
CREATE TRIGGER "project_after_update_update_test_case"
  AFTER UPDATE ON project
  FOR EACH ROW
EXECUTE PROCEDURE project_update_update_test_case();
--------------------------------------------------------------------------------------------------------
ALTER TABLE "project" ADD CONSTRAINT project_ix_id_name UNIQUE ("id", "name");
ALTER TABLE "test_case" ADD CONSTRAINT test_case_fk_project FOREIGN KEY ("project_id", "project_name") REFERENCES 
"project" ("id", "name") DEFERRABLE INITIALLY DEFERRED;
--------------------------------------------------------------------------------------------------------