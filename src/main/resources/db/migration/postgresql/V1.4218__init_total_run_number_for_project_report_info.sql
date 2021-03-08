----------------------------------------------------------------------------------------------------------------------------------
ALTER TABLE "project_report_info" ALTER COLUMN "total_run_number" TYPE bigint;
UPDATE "project_report_info" SET
total_run_number = i.trn
FROM 
(
    select project_id as pi, 
    count(DISTINCT(id)) as trn
    FROM run group by project_id
) i
WHERE i.pi = project_id;
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

	    -- project_report_info
	    UPDATE project_report_info SET 
	    total_run_number = total_run_number + 1 
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