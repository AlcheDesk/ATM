--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "update_test_case_execution_info_after_dev_instruction_result_insert" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	update test_case_execution_info set
	latest_run_instruction_total_count = latest_run_instruction_total_count + 1
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
	latest_run_instruction_total_count = latest_run_instruction_total_count + 1
	where latest_run_id = NEW.run_id;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "update_test_case_execution_info_after_dev_instruction_result_update" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.status_id = 3 THEN
		update test_case_execution_info set
		latest_run_instruction_pass_count = latest_run_instruction_pass_count + 1
		where latest_run_id = NEW.run_id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "update_test_case_execution_info_after_prod_instruction_result_update" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.status_id = 3 THEN
		update test_case_execution_info set
		latest_run_instruction_pass_count = latest_run_instruction_pass_count + 1
		where latest_run_id = NEW.run_id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;