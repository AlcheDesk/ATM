CREATE OR REPLACE FUNCTION "test_case_execution_info_update_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN	
	IF NEW.test_case_is_deleted IS TRUE AND OLD.test_case_is_deleted IS FALSE THEN
		UPDATE project_execution_info SET 
		total_test_case_number = total_test_case_number - 1, 
		test_case_ids = array_remove(test_case_ids, NEW.test_case_id),
		executed_test_case_ids = array_remove(executed_test_case_ids, NEW.test_case_id),
		passed_test_case_ids = array_remove(passed_test_case_ids, NEW.test_case_id)
		WHERE project_id = NEW.test_case_project_id;
	END IF;
	
	IF NEW.test_case_is_deleted IS FALSE AND OLD.test_case_is_deleted IS TRUE THEN
	    UPDATE project_execution_info SET 
	    total_test_case_number = total_test_case_number + 1, 
	    test_case_ids = array_append(test_case_ids, NEW.test_case_id) 
	    WHERE project_id = NEW.test_case_project_id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;