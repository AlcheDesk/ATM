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
	ELSIF NEW.status_id = 3 AND NEW.result_overwritten <> 0 THEN
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
    ELSIF NEW.status_id = 3 AND NEW.result_overwritten <> 0 THEN
    	update run_execution_info set
		instruction_fail_count = instruction_fail_count + 1
		where run_id = NEW.run_id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------