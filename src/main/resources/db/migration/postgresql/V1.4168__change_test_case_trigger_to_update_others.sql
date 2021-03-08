CREATE OR REPLACE FUNCTION "test_case_update_update_test_case_execution_info" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF (NEW.id = OLD.id) AND (NEW.name <> OLD.name) THEN
	    update test_case_execution_info set
	    test_case_name = NEW.name
	    where test_case_id = NEW.id;
	    ---------------------------
	    update run_execution_info set
	    test_case_name = NEW.name
	    where test_case_id = NEW.id;
	    ---------------------------
	    update instruction_target_map set
	    ref_test_case_name = NEW.name
	    where ref_test_case_id = NEW.id;
	ELSEIF (NEW.id <> OLD.id) THEN
		update test_case_execution_info set
	    test_case_name = NEW.name,
	    test_case_created_at = NEW.created_at,
	    test_case_id = NEW.id,
	    total_run_count = (select count(id) from run where test_case_id = NEW.id )
	    where test_case_id = OLD.id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------
ALTER FUNCTION "test_case_update_update_test_case_execution_info" ( ) RENAME TO test_case_update_update_others;
DROP TRIGGER "test_case_before_update_tcei" ON "test_case";
CREATE TRIGGER "test_case_after_update_update_others"
  AFTER UPDATE ON test_case
  FOR EACH ROW
EXECUTE PROCEDURE test_case_update_update_others();
--------------------------------------------------------------------------------------------------------