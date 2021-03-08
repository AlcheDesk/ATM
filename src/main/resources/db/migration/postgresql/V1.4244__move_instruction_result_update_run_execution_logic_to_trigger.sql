--------------------
--instruction result
--------------------
--clean up
DROP TRIGGER "dev_instruction_result_after_update_change_others" ON "dev_instruction_result";
DROP TRIGGER "prod_instruction_result_after_update_change_others" ON "prod_instruction_result";
DROP FUNCTION "dev_instruction_result_update_change_others" ( );
DROP FUNCTION "prod_instruction_result_update_change_others" ( );

-- new trigger function
CREATE OR REPLACE FUNCTION "instruction_result_update_change_others" ()  RETURNS trigger
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
    
    -- if the isntruction result update to result overwritten, change the run too
    IF NEW.result_overwritten IS DISTINCT FROM OLD.result_overwritten THEN
        UPDATE run SET result_overwritten = NEW.result_overwritten, log = 'changed result overwritten to ' || 
        NEW.result_overwritten || ' by instruction result ' || NEW.id
        WHERE id = NEW.run_id;
    END IF;
    
    ------------------------------------
    -- [EXECUTION LOGIC] 
    IF NEW.status_id IS NOT NULL AND NEW.status_id NOT IN (4, 5) THEN -- [EXECUTION LOGIC] update run from NEW to WIP
        UPDATE run SET 
        status_id = 2, 
        log = 'Update status to WIP by instruction result :' || NEW.id || ' status : ' || (SELECT name from status WHERE id = NEW.status_id)
        WHERE id = NEW.run_id AND status_id = 1 AND run_type_id = NEW.run_type_id;
    END IF;
    IF NEW.status_id IS NOT NULL AND NEW.status_id = 5 THEN -- [EXECUTION LOGIC] update run to ERROR
        UPDATE run SET 
        status_id = 5,         
        log = 'Update status to ERROR by instruction result :' || NEW.id || ' status : ERROR'
        WHERE id = NEW.run_id AND run_type_id = NEW.run_type_id;
    END IF;
    IF NEW.status_id IS NOT NULL AND NEW.status_id = 4 THEN -- [EXECUTION LOGIC] update run to FAIL
        UPDATE run SET 
        status_id = 4, 
        log = 'Update status to FAIL by instruction result :' || NEW.id || ' status : FAIL'
        WHERE id = NEW.run_id AND status_id <> 5 AND run_type_id = NEW.run_type_id;
    END IF;
    ------------------------------------ 
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

-- create triggers again
CREATE TRIGGER "dev_instruction_result_after_update_change_others"
  AFTER UPDATE ON dev_instruction_result
  FOR EACH ROW
EXECUTE PROCEDURE instruction_result_update_change_others();

CREATE TRIGGER "prod_instruction_result_after_update_change_others"
  AFTER UPDATE ON prod_instruction_result
  FOR EACH ROW
EXECUTE PROCEDURE instruction_result_update_change_others();