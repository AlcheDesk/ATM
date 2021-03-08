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
        --update number for run set result
        IF NEW.run_set_result_id IS NOT NULL THEN
          IF NEW.status_id = 3 THEN
    	    UPDATE run_set_result SET passed_run_number = passed_run_number + 1 WHERE id = NEW.run_set_result_id;
    	  ELSE
    	    UPDATE run_set_result SET failed_run_number = failed_run_number + 1 WHERE id = NEW.run_set_result_id;
    	  END IF;
        END IF;
        ----------------------------------
      END IF;
    END IF;

    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------