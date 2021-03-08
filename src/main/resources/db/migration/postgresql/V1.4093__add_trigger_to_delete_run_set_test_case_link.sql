--clean up
DELETE FROM "run_set_test_case_link" WHERE run_set_id IN (SELECT id FROM run_set WHERE is_deleted IS TRUE);
DELETE FROM "run_set_test_case_link" WHERE test_case_id IN (SELECT id FROM test_case WHERE is_deleted IS TRUE);
-- add trigger
CREATE OR REPLACE FUNCTION "run_set_delete_delete_rstcl" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.is_deleted IS TRUE THEN
		DELETE FROM run_set_test_case_link
		WHERE run_set_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "run_set_after_update_delete_rstcl"
  AFTER UPDATE ON run_set
  FOR EACH ROW
EXECUTE PROCEDURE run_set_delete_delete_rstcl();
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "test_case_delete_delete_rstcl" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.is_deleted IS TRUE THEN
		DELETE FROM run_set_test_case_link
		WHERE test_case_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "test_case_after_update_delete_rstcl"
  AFTER UPDATE ON test_case
  FOR EACH ROW
EXECUTE PROCEDURE test_case_delete_delete_rstcl();
------------------------------------------------------------------------------