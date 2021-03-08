CREATE OR REPLACE FUNCTION "test_case_execution_info_insert_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	------------------------------------
	-- project_execution_info
	UPDATE project_execution_info SET 
	total_test_case_number = total_test_case_number + 1, 
	test_case_ids = array_append(test_case_ids, NEW.test_case_id) 
	WHERE project_id = NEW.test_case_project_id;
	------------------------------------  
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;