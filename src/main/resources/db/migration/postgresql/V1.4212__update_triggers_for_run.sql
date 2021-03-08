CREATE OR REPLACE FUNCTION "run_execution_info_update_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.run_type_id = 1 AND 
	   (NEW.run_status_id <> OLD.run_status_id OR 
	    NEW.instruction_executed_count <> OLD.instruction_executed_count OR 
	    NEW.instruction_pass_count <> OLD.instruction_pass_count OR
	    NEW.instruction_fail_count <> OLD.instruction_fail_count) THEN
			update test_case_execution_info set
			latest_run_status_id = NEW.run_status_id,
			latest_run_updated_at = NEW.run_updated_at,
			latest_run_instruction_executed_count = NEW.instruction_executed_count,
			latest_run_instruction_pass_count = NEW.instruction_pass_count,
			latest_run_instruction_fail_count = NEW.instruction_fail_count
			where test_case_id = NEW.test_case_id and latest_run_id = NEW.run_id;
			
			-- project_execution_info
			IF NEW.run_status_id = 3 AND OLD.run_status_id = 2 THEN
	            UPDATE project_execution_info SET 
	            passed_test_case_ids = ARRAY(SELECT DISTINCT UNNEST(passed_test_case_ids || NEW.test_case_id) ORDER BY 1) 
	            WHERE project_id = NEW.run_project_id;
		    END IF;
			
	ELSIF NEW.run_status_id <> OLD.run_status_id OR 
	    NEW.instruction_executed_count <> OLD.instruction_executed_count OR 
	    NEW.instruction_pass_count <> OLD.instruction_pass_count OR 
	    NEW.instruction_fail_count <> OLD.instruction_fail_count THEN
		    update test_case_execution_info set
			latest_dev_run_status_id = NEW.run_status_id,
			latest_dev_run_updated_at = NEW.run_updated_at,
			latest_dev_run_instruction_executed_count = NEW.instruction_executed_count,
			latest_dev_run_instruction_pass_count = NEW.instruction_pass_count,
			latest_dev_run_instruction_fail_count = NEW.instruction_fail_count
			where test_case_id = NEW.test_case_id and latest_dev_run_id = NEW.run_id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_execution_info_insert_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.run_type_id = 1 THEN
		update test_case_execution_info set
		total_run_count = total_run_count + 1,
		latest_run_status_id = NEW.run_status_id,
		latest_run_updated_at = NEW.run_updated_at,
		latest_run_instruction_executed_count = 0,
		latest_run_instruction_pass_count = 0,
		latest_run_instruction_fail_count = 0,
		latest_run_trigger_source = NEW.trigger_source,
		latest_run_created_at = NEW.run_created_at,
		latest_run_id = NEW.run_id,
		latest_run_executable_instruction_number = NEW.executable_instruction_number
		where test_case_id = NEW.test_case_id;
		
		-- project_execution_info
	    UPDATE project_execution_info SET 
	    executed_test_case_ids = ARRAY(SELECT DISTINCT UNNEST(executed_test_case_ids || NEW.test_case_id) ORDER BY 1) 
	    WHERE project_id = NEW.run_project_id;

	ELSE
		update test_case_execution_info set
		total_dev_run_count = total_dev_run_count + 1,
		latest_dev_run_status_id = NEW.run_status_id,
		latest_dev_run_updated_at = NEW.run_updated_at,
		latest_dev_run_instruction_executed_count = 0,
		latest_dev_run_instruction_pass_count = 0,
		latest_dev_run_instruction_fail_count = 0,
		latest_dev_run_trigger_source = NEW.trigger_source,
		latest_dev_run_created_at = NEW.run_created_at,
		latest_dev_run_id = NEW.run_id,
		latest_dev_run_executable_instruction_number = NEW.executable_instruction_number
		where test_case_id = NEW.test_case_id;
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