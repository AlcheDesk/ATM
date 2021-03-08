CREATE OR REPLACE FUNCTION "update_test_case_execution_info_after_run_insert" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	update test_case_execution_info set
	total_run_count = total_run_count + 1,
	latest_run_status_id = NEW.status_id,
	latest_run_updated_at = NEW.updated_at,
	latest_run_instruction_executed_count = 0,
	latest_run_instruction_pass_count = 0,
	latest_run_source_ip = NEW.trigger_source,
	latest_run_created_at = NEW.created_at,
	latest_run_id = NEW.id,
	latest_run_executable_instruction_number = NEW.executable_instruction_number
	where test_case_id = NEW.test_case_id;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;