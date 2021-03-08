ALTER TABLE "application" ADD CONSTRAINT application_ix_id_project UNIQUE ("id", "project_id");
ALTER TABLE "section" ADD CONSTRAINT section_fk_project_application FOREIGN KEY ("application_id", "project_id") REFERENCES "application" ("id", "project_id");

CREATE OR REPLACE FUNCTION "section_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF TG_OP = 'UPDATE' AND NEW.project_id IS NOT NULL AND NEW.application_id <> OLD.application_id THEN
		NEW.project_id = (SELECT project_id FROM application WHERE id = NEW.application_id);
	END IF;
	
	IF TG_OP = 'INSERT' AND NEW.application_id IS NOT NULL THEN
		NEW.project_id = (SELECT project_id FROM application WHERE id = NEW.application_id);
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "section_generate_columns"
  BEFORE INSERT OR UPDATE ON section
  FOR EACH ROW
EXECUTE PROCEDURE section_generate_columns();