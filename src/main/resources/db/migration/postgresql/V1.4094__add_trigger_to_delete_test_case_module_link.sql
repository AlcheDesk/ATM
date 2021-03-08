--clean up
DELETE FROM "test_case_module_link" WHERE test_case_id IN (SELECT id FROM test_case WHERE is_deleted IS TRUE);
DELETE FROM "test_case_module_link" WHERE module_id IN (SELECT id FROM module WHERE is_deleted IS TRUE);
-- add trigger
CREATE OR REPLACE FUNCTION "test_case_delete_delete_tcml" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.is_deleted IS TRUE THEN
		DELETE FROM test_case_module_link
		WHERE test_case_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "test_case_after_update_delete_tcml"
  AFTER UPDATE ON test_case
  FOR EACH ROW
EXECUTE PROCEDURE test_case_delete_delete_tcml();
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "module_delete_delete_tcml" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.is_deleted IS TRUE THEN
		DELETE FROM test_case_module_link
		WHERE module_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "module_after_update_delete_tcml"
  AFTER UPDATE ON module
  FOR EACH ROW
EXECUTE PROCEDURE module_delete_delete_tcml();
------------------------------------------------------------------------------