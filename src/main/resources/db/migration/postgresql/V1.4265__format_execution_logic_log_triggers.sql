
CREATE OR REPLACE FUNCTION "prod_step_log_insert_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	------------------------------------
	-- [EXECUTION LOGIC] 
	-- dev instruction result
    IF  NEW.step_log_type_id IN (1, 2) THEN -- [EXECUTION LOGIC] update instruction result from NEW to WIP
        UPDATE prod_instruction_result SET 
        status_id = 2, 
        log = '[WIP] Update status to WIP by step log :' || NEW.id || ' type : ' || (SELECT name from step_log_type WHERE id = NEW.step_log_type_id)
        WHERE id = NEW.instruction_result_id AND status_id = 1;
    ELSIF NEW.step_log_type_id IN (8) THEN  -- [EXECUTION LOGIC] update instruction result to ERROR
        UPDATE prod_instruction_result SET 
        status_id = 5, 
        log = '[ERROR] Update status to ERROR by step log :' || NEW.id  || ' type : ' || (SELECT name from step_log_type WHERE id = NEW.step_log_type_id)
        WHERE id = NEW.instruction_result_id;
    ELSIF NEW.step_log_type_id IN (4, 6, 7) THEN  -- [EXECUTION LOGIC] update instruction result to ERROR
        UPDATE prod_instruction_result SET 
        status_id = 4, 
        log = '[FAIL] Update status to FAIL by step log :' || NEW.id  || ' type : ' || (SELECT name from step_log_type WHERE id = NEW.step_log_type_id)
        WHERE id = NEW.instruction_result_id;
    ELSE
        UPDATE prod_instruction_result SET 
        log = '[NO RESULT CHANGE] Received new corresponding step log :' || NEW.id  || ' type : ' || (SELECT name from step_log_type WHERE id = NEW.step_log_type_id)
        WHERE id = NEW.instruction_result_id;
    END IF;
	------------------------------------  
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "dev_step_log_insert_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	------------------------------------
	-- [EXECUTION LOGIC] 
	-- dev instruction result
    IF  NEW.step_log_type_id IN (1, 2) THEN -- [EXECUTION LOGIC] update instruction result from NEW to WIP
        UPDATE dev_instruction_result SET 
        status_id = 2, 
        log = '[WIP] Update status to WIP by step log :' || NEW.id || ' type : ' || (SELECT name from step_log_type WHERE id = NEW.step_log_type_id)
        WHERE id = NEW.instruction_result_id AND status_id = 1;
    ELSIF NEW.step_log_type_id IN (8) THEN  -- [EXECUTION LOGIC] update instruction result to ERROR
        UPDATE dev_instruction_result SET 
        status_id = 5, 
        log = '[ERROR] Update status to ERROR by step log :' || NEW.id  || ' type : ' || (SELECT name from step_log_type WHERE id = NEW.step_log_type_id)
        WHERE id = NEW.instruction_result_id;
    ELSIF NEW.step_log_type_id IN (4, 6, 7) THEN  -- [EXECUTION LOGIC] update instruction result to ERROR
        UPDATE dev_instruction_result SET 
        status_id = 4, 
        log = '[FAIL] Update status to FAIL by step log :' || NEW.id  || ' type : ' || (SELECT name from step_log_type WHERE id = NEW.step_log_type_id)
        WHERE id = NEW.instruction_result_id AND status_id <> 5;
    ELSE
        UPDATE dev_instruction_result SET 
        log = '[NO RESULT CHANGE] Received new corresponding step log :' || NEW.id  || ' type : ' || (SELECT name from step_log_type WHERE id = NEW.step_log_type_id)
        WHERE id = NEW.instruction_result_id;
    END IF;
	------------------------------------  
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
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
        log = '[WIP] Update status to WIP by instruction result :' || NEW.id || ' status : ' || (SELECT name from status WHERE id = NEW.status_id)
        WHERE id = NEW.run_id AND status_id = 1 AND run_type_id = NEW.run_type_id;
    END IF;
    IF NEW.status_id IS NOT NULL AND NEW.status_id = 5 THEN -- [EXECUTION LOGIC] update run to ERROR
        UPDATE run SET 
        status_id = 5,         
        log = '[ERROR] Update status to ERROR by instruction result :' || NEW.id || ' status : ERROR'
        WHERE id = NEW.run_id AND run_type_id = NEW.run_type_id;
    END IF;
    IF NEW.status_id IS NOT NULL AND NEW.status_id = 4 THEN -- [EXECUTION LOGIC] update run to FAIL
        UPDATE run SET 
        status_id = 4, 
        log = '[FAIL] Update status to FAIL by instruction result :' || NEW.id || ' status : FAIL'
        WHERE id = NEW.run_id AND status_id <> 5 AND run_type_id = NEW.run_type_id;
    ELSE
        UPDATE run SET 
        log = '[NO RESULT CHANGE] Received instruction result update signal from instruction result :' || NEW.id  || 
              ' new status : ' || (SELECT name from status WHERE id = NEW.status_id) || 
              ' old status : ' || (SELECT name from status WHERE id = OLD.status_id) ||
              ' finished : ' || NEW.is_finished
        WHERE id = NEW.run_id;
    END IF;
    ------------------------------------ 
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_update_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    -- update run_execution_info, sync with the run
    IF NEW.status_id <> OLD.status_id OR 
       NEW.is_finished <> OLD.is_finished OR 
       NEW.result_overwritten <> OLD.result_overwritten OR
       NEW.start_at <> OLD.start_at OR
       NEw.end_at <> OLD.end_at THEN
            update run_execution_info set
            run_status_id = NEW.status_id,
            run_updated_at = NEW.updated_at,
            run_result_overwritten = NEW.result_overwritten,
            is_finished = NEW.is_finished,
            run_start_at = NEW.start_at,
            run_end_at = NEW.end_at
            where test_case_id = NEW.test_case_id and run_id = NEW.id;
    END IF;
    
    -- [EXECUTION LOGIC]
    IF NEW.run_set_result_id IS NOT NULL THEN
      -- update run set result status
      IF NEW.status_id IS NOT NULL AND NEW.status_id = 2 THEN -- update run set result from NEW to WIP
        UPDATE run_set_result SET 
        status_id = 2, 
        log = '[WIP] Update status to WIP by run :' || NEW.id || ' status : WIP'
        WHERE id = NEW.run_set_result_id AND status_id = 1 AND run_type_id = NEW.run_type_id;
      ----==========
      ELSIF NEW.status_id IS NOT NULL AND NEW.status_id = 5 THEN -- update run set result to ERROR
        UPDATE run_set_result SET 
        status_id = 5, 
        log = '[ERROR] Update status to ERROR by run :' || NEW.id || ' status : ERROR'
        WHERE id = NEW.run_set_result_id AND run_type_id = NEW.run_type_id;
      ----==========
      ELSIF NEW.status_id IS NOT NULL AND NEW.status_id = 4 THEN -- update run set result from to FAIL or just log the FAIL signal
        UPDATE run_set_result SET 
        log = '[ERROR_FAIL] Keep status as ERROR but got a FAIL run :' || NEW.id || ' status : FAIL'
        WHERE id = NEW.run_set_result_id AND status_id = 5 AND run_type_id = NEW.run_type_id;
        
        UPDATE run_set_result SET 
        status_id = 4, 
        log = '[FAIL] Update status to FAIL by run :' || NEW.id || ' status : FAIL'
        WHERE id = NEW.run_set_result_id AND status_id <> 5 AND run_type_id = NEW.run_type_id;
      ----==========
      ELSIF NEW.status_id IS NOT NULL AND NEW.status_id = 8 THEN -- update run set result from to FAIL by run TIMEOUT
        UPDATE run_set_result SET 
        log = '[ERROR_TIMEOUT] Keep status as ERROR but got a TIMEOUT run :' || NEW.id || ' status : TIMEOUT'
        WHERE id = NEW.run_set_result_id AND status_id = 5 AND run_type_id = NEW.run_type_id;
        
        UPDATE run_set_result SET 
        status_id = 4, 
        log = '[FAIL] Update status to FAIL by run :' || NEW.id || ' status : TIMEOUT'
        WHERE id = NEW.run_set_result_id AND status_id <> 5 AND run_type_id = NEW.run_type_id;
      ----==========
      ELSIF NEW.status_id IS NOT NULL AND NEW.status_id = 9 THEN -- update run set result from to FAIL by run TIMEOUT
        UPDATE run_set_result SET 
        log = '[ERROR_TERMINATED] Keep status as ERROR but got a TERMINATED run :' || NEW.id || ' status : TERMINATED'
        WHERE id = NEW.run_set_result_id AND status_id = 5 AND run_type_id = NEW.run_type_id;
        
        UPDATE run_set_result SET 
        status_id = 9, 
        log = '[TERMINATED] Update status to TERMINATED by run :' || NEW.id || ' status : TERMINATED'
        WHERE id = NEW.run_set_result_id AND status_id <> 5 AND run_type_id = NEW.run_type_id;
      ELSE
        UPDATE run_set_result SET 
        log = '[NO RESULT CHANGE] Received run update signal from run :' || NEW.id  || 
              ' new status : ' || (SELECT name from status WHERE id = NEW.status_id) || 
              ' old status : ' || (SELECT name from status WHERE id = OLD.status_id) ||
              ' finished : ' || NEW.is_finished
        WHERE id = NEW.run_set_result_id;
      END IF;
      ----==========
      
      -- update run set result to finished
      IF NEW.is_finished IS DISTINCT FROM  OLD.is_finished AND NEW.is_finished IS TRUE THEN
        -- check if all run is finished
        IF (SELECT COUNT(id) FROM run WHERE is_finished IS FALSE AND run_set_result_id = NEW.run_set_result_id) = 0 THEN
          UPDATE run_set_result SET is_finished = TRUE WHERE id = NEW.run_set_result_id;
        END IF;
      END IF;
    END IF;
    

    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
