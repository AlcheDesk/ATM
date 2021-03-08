CREATE OR REPLACE FUNCTION "test_case_execution_info_insert_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	------------------------------------
	-- project_execution_info
	UPDATE project_execution_info SET total_test_case_number = total_test_case_number + 1, test_case_ids = array_append(test_case_ids, NEW.id) WHERE project_id = NEW.project_id;
	------------------------------------  
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
CREATE TRIGGER "test_case_execution_info_after_insert_change_others"
  AFTER INSERT ON test_case_execution_info
  FOR EACH ROW
EXECUTE PROCEDURE test_case_execution_info_insert_change_others();
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "test_case_execution_info_update_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN	
	IF NEW.test_case_is_deleted IS TRUE AND OLD.test_case_is_deleted IS FALSE THEN
		UPDATE project_execution_info SET total_test_case_number = total_test_case_number - 1, test_case_ids = array_remove(test_case_ids, NEW.id) WHERE project_id = NEW.project_id;
	END IF;
	
	IF NEW.test_case_is_deleted IS FALSE AND OLD.test_case_is_deleted IS TRUE THEN
	    UPDATE project_execution_info SET total_test_case_number = total_test_case_number + 1, test_case_ids = array_append(test_case_ids, NEW.id) WHERE project_id = NEW.project_id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
CREATE TRIGGER "test_case_execution_info_update_change_others"
  AFTER UPDATE ON test_case_execution_info
  FOR EACH ROW
EXECUTE PROCEDURE test_case_execution_info_update_change_others();
----------------------------------------------------------------------------------------------------------------------------------