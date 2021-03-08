ALTER TABLE "test_case_execution_info" ADD COLUMN total_dev_run_count bigint;
ALTER TABLE "test_case_execution_info" ALTER COLUMN total_dev_run_count SET DEFAULT 0;
ALTER TABLE "test_case_execution_info" ADD COLUMN latest_dev_run_status_id bigint;
ALTER TABLE "test_case_execution_info" ALTER COLUMN latest_dev_run_status_id SET DEFAULT 7;
ALTER TABLE "test_case_execution_info" ADD COLUMN latest_dev_run_updated_at TIMESTAMP WITH TIME zone;
ALTER TABLE "test_case_execution_info" ADD COLUMN latest_dev_run_instruction_executed_count bigint;
ALTER TABLE "test_case_execution_info" ADD COLUMN latest_dev_run_instruction_pass_count bigint;
ALTER TABLE "test_case_execution_info" ADD COLUMN latest_dev_run_source_ip text;
ALTER TABLE "test_case_execution_info" ADD COLUMN latest_dev_run_created_at TIMESTAMP WITH TIME zone;
ALTER TABLE "test_case_execution_info" ADD COLUMN latest_dev_run_id bigint;
ALTER TABLE "test_case_execution_info" ADD COLUMN latest_dev_run_executable_instruction_number bigint;
ALTER TABLE "test_case_execution_info" ADD CONSTRAINT test_case_execution_info_fk_latest_dev_run_id FOREIGN KEY (latest_dev_run_id) REFERENCES "run" ("id");

--------------------------------------------------------------------------------------------------------
UPDATE test_case_execution_info tcei SET 
total_dev_run_count = (select count(id) from run where run_type_id = 2 and test_case_id = tcei.test_case_id),
total_run_count = (select count(id) from run where run_type_id = 1 and test_case_id = tcei.test_case_id);
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "update_test_case_execution_info_after_dev_instruction_result_insert" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	update test_case_execution_info set
	latest_dev_run_instruction_executed_count = latest_dev_run_instruction_executed_count + 1
	where latest_dev_run_id = NEW.run_id;
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
		latest_dev_run_instruction_pass_count = latest_dev_run_instruction_pass_count + 1
		where latest_dev_run_id = NEW.run_id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "update_test_case_execution_info_after_run_insert" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.run_type_id = 1 THEN
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
	ELSE
		update test_case_execution_info set
		total_dev_run_count = total_dev_run_count + 1,
		latest_dev_run_status_id = NEW.status_id,
		latest_dev_run_updated_at = NEW.updated_at,
		latest_dev_run_instruction_executed_count = 0,
		latest_dev_run_instruction_pass_count = 0,
		latest_dev_run_source_ip = NEW.trigger_source,
		latest_dev_run_created_at = NEW.created_at,
		latest_dev_run_id = NEW.id,
		latest_dev_run_executable_instruction_number = NEW.executable_instruction_number
		where test_case_id = NEW.test_case_id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "update_test_case_execution_info_after_run_update" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.run_type_id = 1 THEN
		update test_case_execution_info set
		latest_run_status_id = NEW.status_id,
		latest_run_updated_at = NEW.updated_at
		where test_case_id = NEW.test_case_id and latest_run_id = NEW.id;
	ELSE
	    update test_case_execution_info set
		latest_dev_run_status_id = NEW.status_id,
		latest_dev_run_updated_at = NEW.updated_at
		where test_case_id = NEW.test_case_id and latest_dev_run_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;