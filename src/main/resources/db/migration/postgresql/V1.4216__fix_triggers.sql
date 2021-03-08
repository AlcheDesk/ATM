CREATE OR REPLACE FUNCTION "project_execution_info_update_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.project_name IS NOT NULL AND NEW.project_name <> OLD.project_name THEN
		UPDATE project_report_info SET project_name = NEW.project_name WHERE project_id = NEW.project_id;
	ELSIF TG_OP = 'UPDATE' THEN
	    UPDATE project_report_info SET 
	    project_name = NEW.project_name,
	    executed_test_case_number = case when array_length(NEW.executed_test_case_ids, 1) is null then 0 else array_length(NEW.executed_test_case_ids, 1) end,
	    total_test_case_number = case when array_length(NEW.test_case_ids, 1) is null then 0 else array_length(NEW.test_case_ids, 1) end,
	    passed_test_case_number = case when array_length(NEW.passed_test_case_ids, 1) is null then 0 else array_length(NEW.passed_test_case_ids, 1) end
	    WHERE project_id = NEW.project_id;		
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;