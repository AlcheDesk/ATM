CREATE OR REPLACE FUNCTION "prod_ir_update_update_test_case_execution_info" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.status_id = 3 AND NEW.status_id <> OLD.status_id THEN
		update test_case_execution_info set
		latest_run_instruction_pass_count = latest_run_instruction_pass_count + 1
		where latest_run_id = NEW.run_id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "dev_ir_update_update_test_case_execution_info" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.status_id = 3 AND NEW.status_id <> OLD.status_id THEN
		update test_case_execution_info set
		latest_dev_run_instruction_pass_count = latest_dev_run_instruction_pass_count + 1
		where latest_dev_run_id = NEW.run_id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
------------------------------------------------------------------------------
ALTER TABLE "prod_instruction_result" ADD COLUMN result_overwritten INTEGER;
UPDATE "prod_instruction_result" SET result_overwritten = 0 WHERE result_overwritten IS NULL;
ALTER TABLE "prod_instruction_result" ALTER COLUMN result_overwritten SET NOT NULL;
ALTER TABLE "prod_instruction_result" ALTER COLUMN result_overwritten SET DEFAULT 0;
------------------------------------------------------------------------------
ALTER TABLE "dev_instruction_result" ADD COLUMN result_overwritten INTEGER;
UPDATE "dev_instruction_result" SET result_overwritten = 0 WHERE result_overwritten IS NULL;
ALTER TABLE "dev_instruction_result" ALTER COLUMN result_overwritten SET NOT NULL;
ALTER TABLE "dev_instruction_result" ALTER COLUMN result_overwritten SET DEFAULT 0;
------------------------------------------------------------------------------
ALTER TABLE "run" ADD COLUMN result_overwritten INTEGER;
ALTER TABLE "run" disable TRIGGER "run_before_update_log";
ALTER TABLE "run" disable TRIGGER "run_before_update_timestamp_with_uuid";
UPDATE "run" SET result_overwritten = 0 WHERE result_overwritten IS NULL;
ALTER TABLE "run" enable TRIGGER "run_before_update_log";
ALTER TABLE "run" enable TRIGGER "run_before_update_timestamp_with_uuid";
ALTER TABLE "run" ALTER COLUMN result_overwritten SET NOT NULL;
ALTER TABLE "run" ALTER COLUMN result_overwritten SET DEFAULT 0;
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "instruction_result_resul_overwritten_update_run" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    IF NEW.result_overwritten <> OLD.result_overwritten THEN
    	UPDATE run SET result_overwritten = NEW.result_overwritten, log = 'changed result overwritten to ' || 
		NEW.result_overwritten || ' by instruction result ' || NEW.id
		WHERE id = NEW.run_id;
    END IF;
    RETURN NEW;   
END;
$body$ LANGUAGE plpgsql;
------------------------------------------------------------------------------
CREATE TRIGGER "prod_instruction_result_after_update_update_run"
  AFTER UPDATE ON prod_instruction_result
  FOR EACH ROW
EXECUTE PROCEDURE instruction_result_resul_overwritten_update_run();
------------------------------------------------------------------------------
CREATE TRIGGER "dev_instruction_result_after_update_update_run"
  AFTER UPDATE ON dev_instruction_result
  FOR EACH ROW
EXECUTE PROCEDURE instruction_result_resul_overwritten_update_run();
------------------------------------------------------------------------------