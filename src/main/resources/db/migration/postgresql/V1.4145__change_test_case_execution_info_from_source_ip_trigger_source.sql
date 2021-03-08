ALTER TABLE "test_case_execution_info" RENAME COLUMN "latest_run_source_ip" TO latest_run_trigger_source;
ALTER TABLE "test_case_execution_info" RENAME COLUMN "latest_dev_run_source_ip" TO latest_dev_run_trigger_source;
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_execution_info_insert_update_test_case_execution_info" ()  RETURNS trigger
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
		latest_run_trigger_source = NEW.source_ip,
		latest_run_created_at = NEW.run_created_at,
		latest_run_id = NEW.run_id,
		latest_run_executable_instruction_number = NEW.executable_instruction_number
		where test_case_id = NEW.test_case_id;
	ELSE
		update test_case_execution_info set
		total_dev_run_count = total_dev_run_count + 1,
		latest_dev_run_status_id = NEW.run_status_id,
		latest_dev_run_updated_at = NEW.run_updated_at,
		latest_dev_run_instruction_executed_count = 0,
		latest_dev_run_instruction_pass_count = 0,
		latest_dev_run_trigger_source = NEW.source_ip,
		latest_dev_run_created_at = NEW.run_created_at,
		latest_dev_run_id = NEW.run_id,
		latest_dev_run_executable_instruction_number = NEW.executable_instruction_number
		where test_case_id = NEW.test_case_id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;