--------------------
--step log
--------------------
CREATE OR REPLACE FUNCTION "dev_step_log_insert_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	------------------------------------
	-- dev instruction result
    IF  NEW.step_log_type_id IN (1, 2) THEN -- [EXECUTION LOGIC] update instruction result from NEW to WIP
        UPDATE dev_instruction_result SET 
        status_id = 2, 
        log = 'Update status to WIP by step log :' || NEW.id || ' type : ' || (SELECT name from step_log_type WHERE id = NEW.step_log_type_id)
        WHERE id = NEW.instruction_result_id AND status_id = 1;
    ELSIF NEW.step_log_type_id IN (8) THEN  -- [EXECUTION LOGIC] update instruction result to ERROR
        UPDATE dev_instruction_result SET 
        status_id = 5, 
        log = 'Update status to ERROR by step log :' || NEW.id  || ' type : ' || (SELECT name from step_log_type WHERE id = NEW.step_log_type_id)
        WHERE id = NEW.instruction_result_id;
    ELSIF NEW.step_log_type_id IN (4, 6, 7) THEN  -- [EXECUTION LOGIC] update instruction result to ERROR
        UPDATE dev_instruction_result SET 
        status_id = 4, 
        log = 'Update status to FAIL by step log :' || NEW.id  || ' type : ' || (SELECT name from step_log_type WHERE id = NEW.step_log_type_id)
        WHERE id = NEW.instruction_result_id AND status_id <> 5;
    END IF;
	------------------------------------  
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "dev_step_log_insert_change_others"
  AFTER INSERT ON dev_step_log
  FOR EACH ROW
EXECUTE PROCEDURE dev_step_log_insert_change_others();
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "prod_step_log_insert_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	------------------------------------
	-- dev instruction result
    IF  NEW.step_log_type_id IN (1, 2) THEN -- [EXECUTION LOGIC] update instruction result from NEW to WIP
        UPDATE prod_instruction_result SET 
        status_id = 2, 
        log = 'Update status to WIP by step log :' || NEW.id || ' type : ' || (SELECT name from step_log_type WHERE id = NEW.step_log_type_id)
        WHERE id = NEW.instruction_result_id AND status_id = 1;
    ELSIF NEW.step_log_type_id IN (8) THEN  -- [EXECUTION LOGIC] update instruction result to ERROR
        UPDATE prod_instruction_result SET 
        status_id = 5, 
        log = 'Update status to ERROR by step log :' || NEW.id  || ' type : ' || (SELECT name from step_log_type WHERE id = NEW.step_log_type_id)
        WHERE id = NEW.instruction_result_id;
    ELSIF NEW.step_log_type_id IN (4, 6, 7) THEN  -- [EXECUTION LOGIC] update instruction result to ERROR
        UPDATE prod_instruction_result SET 
        status_id = 4, 
        log = 'Update status to FAIL by step log :' || NEW.id  || ' type : ' || (SELECT name from step_log_type WHERE id = NEW.step_log_type_id)
        WHERE id = NEW.instruction_result_id;
    END IF;
	------------------------------------  
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "prod_step_log_insert_change_others"
  AFTER INSERT ON prod_step_log
  FOR EACH ROW
EXECUTE PROCEDURE prod_step_log_insert_change_others();
----------------------------------------------------------------------------------------------------------------------------------