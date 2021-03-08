--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "update_test_case_execution_info_after_dev_instruction_result_insert" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	update test_case_execution_info set
	latest_run_instruction_executed_count = latest_run_instruction_executed_count + 1
	where latest_run_id = NEW.run_id;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "update_test_case_execution_info_after_prod_instruction_result_insert" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	update test_case_execution_info set
	latest_run_instruction_executed_count = latest_run_instruction_executed_count + 1
	where latest_run_id = NEW.run_id;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;