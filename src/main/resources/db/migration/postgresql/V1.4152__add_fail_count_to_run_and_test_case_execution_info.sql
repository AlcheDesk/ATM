ALTER TABLE "run_execution_info" ADD COLUMN instruction_fail_count INTEGER;
UPDATE "run_execution_info" SET instruction_fail_count = instruction_executed_count - instruction_pass_count WHERE instruction_fail_count IS NULL;
ALTER TABLE "run_execution_info" ALTER COLUMN instruction_fail_count SET NOT NULL;
ALTER TABLE "run_execution_info" ALTER COLUMN instruction_fail_count SET DEFAULT 0;
--------------------------------------------------------------------------------------------------------
ALTER TABLE "test_case_execution_info" ADD COLUMN latest_run_instruction_fail_count INTEGER;
UPDATE "test_case_execution_info" SET latest_run_instruction_fail_count = latest_run_instruction_executed_count - latest_run_instruction_pass_count WHERE latest_run_instruction_fail_count IS NULL;
UPDATE "test_case_execution_info" SET latest_run_instruction_fail_count = 0 WHERE latest_run_instruction_fail_count IS NULL;
ALTER TABLE "test_case_execution_info" ALTER COLUMN latest_run_instruction_fail_count SET NOT NULL;
ALTER TABLE "test_case_execution_info" ALTER COLUMN latest_run_instruction_fail_count SET DEFAULT 0;
--------------------------------------------------------------------------------------------------------
ALTER TABLE "test_case_execution_info" ADD COLUMN latest_dev_run_instruction_fail_count INTEGER;
UPDATE "test_case_execution_info" SET latest_dev_run_instruction_fail_count = latest_dev_run_instruction_executed_count - latest_dev_run_instruction_pass_count WHERE latest_dev_run_instruction_fail_count IS NULL;
UPDATE "test_case_execution_info" SET latest_dev_run_instruction_fail_count = 0 WHERE latest_dev_run_instruction_fail_count IS NULL;
ALTER TABLE "test_case_execution_info" ALTER COLUMN latest_dev_run_instruction_fail_count SET NOT NULL;
ALTER TABLE "test_case_execution_info" ALTER COLUMN latest_dev_run_instruction_fail_count SET DEFAULT 0;
--------------------------------------------------------------------------------------------------------
DROP TRIGGER "run_execution_info_after_update_update_rei" ON "run_execution_info";
DROP FUNCTION "run_execution_info_update_update_tcei" ( );
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
		latest_run_instruction_fail_count = 0,
		latest_run_trigger_source = NEW.trigger_source,
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
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_execution_info_update_test_case_execution_info" ()  RETURNS trigger
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
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "dev_ir_update_update_run_execution_info" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.status_id = 3 AND NEW.status_id <> OLD.status_id THEN
		update run_execution_info set
		instruction_pass_count = instruction_pass_count + 1
		where run_id = NEW.run_id;
    ELSIF NEW.status_id IN (4,5,8,9) AND NEW.status_id <> OLD.status_id THEN
    	update run_execution_info set
		instruction_fail_count = instruction_fail_count + 1
		where run_id = NEW.run_id;
	ELSIF NEW.status_id = 3 AND NEW.result_overwritten THEN
    	update run_execution_info set
		instruction_fail_count = instruction_fail_count + 1
		where run_id = NEW.run_id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "prod_ir_update_update_run_execution_info" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.status_id = 3 AND NEW.status_id <> OLD.status_id THEN
		update run_execution_info set
		instruction_pass_count = instruction_pass_count + 1
		where run_id = NEW.run_id;
    ELSIF NEW.status_id IN (4,5,8,9) AND NEW.status_id <> OLD.status_id THEN
    	update run_execution_info set
		instruction_fail_count = instruction_fail_count + 1
		where run_id = NEW.run_id;
    ELSIF NEW.status_id = 3 AND NEW.result_overwritten THEN
    	update run_execution_info set
		instruction_fail_count = instruction_fail_count + 1
		where run_id = NEW.run_id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------