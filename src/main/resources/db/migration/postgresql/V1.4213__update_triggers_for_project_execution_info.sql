CREATE OR REPLACE FUNCTION "project_execution_info_update_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.project_name IS NOT NULL AND NEW.project_name <> OLD.project_name THEN
		UPDATE project_report_info SET project_name = NEW.project_name WHERE project_id = NEW.project_id;
	ELSIF TG_OP = 'UPDATE' THEN
	    UPDATE project_report_info SET 
	    project_name = NEW.project_name,
	    executed_test_case_number = array_length(NEW.executed_test_case_ids, 1),
	    total_test_case_number = array_length(NEW.test_case_ids, 1),
	    passed_test_case_number = array_length(NEW.passed_test_case_ids, 1)
	    WHERE project_id = NEW.project_id;		
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "project_report_info_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	-- is_finished and end_at
	IF TG_OP = 'UPDATE' THEN
		NEW.project_created_at = OLD.project_created_at;
		NEw.failed_test_case_number = NEw.total_test_case_number - NEW.failed_test_case_number;
	END IF;
	
	IF TG_OP = 'INSERT' THEN
		NEW.total_test_case_number = 0;
		NEW.total_run_number = 0;
		NEW.total_execution_time = 0;
		NEW.failed_test_case_number = 0;
		NEW.executed_test_case_number = 0;
		NEW.passed_test_case_number = 0;
		NEW.pass_rate = 0;
		NEW.fail_rate = 0;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;