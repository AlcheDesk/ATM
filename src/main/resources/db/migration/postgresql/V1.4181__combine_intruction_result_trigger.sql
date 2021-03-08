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
	
	IF NEW.result_overwritten <> OLD.result_overwritten THEN
    	UPDATE run SET result_overwritten = NEW.result_overwritten, log = 'changed result overwritten to ' || 
		NEW.result_overwritten || ' by instruction result ' || NEW.id
		WHERE id = NEW.run_id;
    END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

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
	
	IF NEW.result_overwritten <> OLD.result_overwritten THEN
    	UPDATE run SET result_overwritten = NEW.result_overwritten, log = 'changed result overwritten to ' || 
		NEW.result_overwritten || ' by instruction result ' || NEW.id
		WHERE id = NEW.run_id;
    END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

ALTER TRIGGER "dev_instruction_result_after_insert_rei" ON "dev_instruction_result" RENAME TO dev_instruction_result_after_insert_change_others;
ALTER FUNCTION "dev_ir_insert_update_run_execution_info" ( ) RENAME TO dev_instruction_result_insert_change_others;
ALTER TRIGGER "dev_instruction_result_after_update_rei" ON "dev_instruction_result" RENAME TO dev_instruction_result_after_update_change_others;    
ALTER FUNCTION "dev_ir_update_update_run_execution_info" ( ) RENAME TO dev_instruction_result_update_change_others;

ALTER TRIGGER "prod_instruction_result_after_insert_rei" ON "prod_instruction_result" RENAME TO prod_instruction_result_after_insert_change_others;
ALTER FUNCTION "prod_ir_insert_update_run_execution_info" ( ) RENAME TO prod_instruction_result_insert_change_others;
ALTER TRIGGER "prod_instruction_result_after_update_rei" ON "prod_instruction_result" RENAME TO prod_instruction_result_after_update_change_others;    
ALTER FUNCTION "prod_ir_update_update_run_execution_info" ( ) RENAME TO prod_instruction_result_update_change_others;

DROP TRIGGER "dev_instruction_result_after_update_update_run" ON "dev_instruction_result";
DROP TRIGGER "prod_instruction_result_after_update_update_run" ON "prod_instruction_result";
DROP FUNCTION "instruction_result_resul_overwritten_update_run" ( );
DROP TRIGGER "dev_instruction_result_generate_columns" ON "dev_instruction_result";
DROP FUNCTION "dev_instruction_result_generate_columns" ( );
DROP TRIGGER "prod_instruction_result_generate_columns" ON "prod_instruction_result";
DROP FUNCTION "prod_instruction_result_generate_columns" ( );
ALTER TRIGGER "dev_instruction_generate_columns" ON "dev_instruction_result" RENAME TO dev_instruction_result_generate_columns;
ALTER TRIGGER "prod_instruction_generate_columns" ON "prod_instruction_result" RENAME TO prod_instruction_result_generate_columns;