CREATE OR REPLACE FUNCTION "run_execution_info_update_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.run_type_id = 1 AND 
	   (NEW.run_status_id <> OLD.run_status_id OR 
	    NEW.instruction_executed_count <> OLD.instruction_executed_count OR 
	    NEW.instruction_pass_count <> OLD.instruction_pass_count OR
	    NEW.instruction_fail_count <> OLD.instruction_fail_count) THEN
	        ---------------------
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
	---------------------		
	ELSIF NEW.run_status_id <> OLD.run_status_id OR 
	    NEW.instruction_executed_count <> OLD.instruction_executed_count OR 
	    NEW.instruction_pass_count <> OLD.instruction_pass_count OR 
	    NEW.instruction_fail_count <> OLD.instruction_fail_count THEN
    ---------------------
		    update test_case_execution_info set
			latest_dev_run_status_id = NEW.run_status_id,
			latest_dev_run_updated_at = NEW.run_updated_at,
			latest_dev_run_instruction_executed_count = NEW.instruction_executed_count,
			latest_dev_run_instruction_pass_count = NEW.instruction_pass_count,
			latest_dev_run_instruction_fail_count = NEW.instruction_fail_count
			where test_case_id = NEW.test_case_id and latest_dev_run_id = NEW.run_id;
    ---------------------
	END IF;
	
	--update number for run set result
    IF NEW.run_set_result_id IS NOT NULL AND NEW.is_finished IS DISTINCT FROM OLD.is_finished AND NEW.is_finished IS TRUE THEN
      IF NEW.run_status_id = 3 THEN
	    UPDATE run_set_result SET 
	    passed_run_ids = ARRAY(SELECT DISTINCT UNNEST(passed_run_ids || NEW.id) ORDER BY 1) 
	    WHERE id = NEW.run_set_result_id AND run_type_id = NEW.run_type_id;
      ELSE
    	UPDATE run_set_result SET 
	    failed_run_ids = ARRAY(SELECT DISTINCT UNNEST(failed_run_ids || NEW.id) ORDER BY 1) 
	    WHERE id = NEW.run_set_result_id AND run_type_id = NEW.run_type_id;
      END IF;
    END IF;
    ----------------------------------
	
	-- update project chart info
	IF NEW.run_type_id = 1 THEN
	    IF NEW.is_finished IS DISTINCT FROM OLD.is_finished AND NEW.is_finished IS TRUE AND NEW.run_start_at IS NOT NULL AND NEW.run_end_at IS NOT NULL THEN
	        UPDATE "project_report_info" SET total_execution_time = total_execution_time + EXTRACT(EPOCH FROM (NEW.run_end_at - NEW.run_start_at)) WHERE project_id = NEW.run_project_id;
	    END IF;
	END IF;
	
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;