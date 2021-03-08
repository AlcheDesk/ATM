CREATE OR REPLACE FUNCTION "instruction_result_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
      NEW.status_id = 1;
      NEW.end_at = NULL;
      NEW.is_finished = FALSE;
      NEW.start_at = NULL;
      ----------------
      IF NEW.log IS NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created.';
      ELSIF NEW.log IS NOT NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created with initial log:' || NEW.log;
      END IF;
      ----------------
      IF NEW.instruction IS NOT NULL THEN
        NEW.target = NEW.instruction->>'target';
      ELSIF NEW.instruction IS NULL THEN
        NEW.target = NULL;
      END IF;
      ----------------
      IF NEW.instruction IS NOT NULL THEN
          NEW.action = CASE WHEN NEW.instruction->>'action' = 'null' THEN NULL ELSE NEW.instruction->>'action' END;
      ELSIF NEW.instruction IS NULL THEN
          NEW.action = NULL;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
      NEW.run_id = OLD.run_id;
      NEW.action = OLD.action;
      ----------------
      IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL THEN
        NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL THEN
        NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      END IF;
      ----------------
      IF NEW.instruction IS NOT NULL AND NEW.instruction <> OLD.instruction THEN
        NEW.target = NEW.instruction->>'target';
      ELSIF NEW.instruction IS NULL THEN
        NEW.target = NULL;
      END IF;
      ----------------
      IF OLD.instruction_option_log IS NOT NULL AND NEW.instruction_option_log IS NOT NULL THEN
        NEW.instruction_option_log = OLD.instruction_option_log || chr(10) || NEW.instruction_option_log;        
      END IF;
      ----------------	  
	  IF NEW.is_finished IS TRUE AND OLD.is_finished IS FALSE AND NEW.start_at IS NOT NULL THEN
        NEW.end_at = now();
      ELSIF OLD.is_finished IS TRUE THEN
        NEW.end_at = OLD.end_at;
        NEW.is_finished = OLD.is_finished;
        NEW.start_at = OLD.start_at;
      END IF;
      ----------------
      IF OLD.status_id = 1 AND NEW.status_id <> 1 AND OLD.start_at IS NULL THEN
        NEW.start_at = now();
      ELSIF OLD.start_at IS NOT NULL THEN
        NEW.start_at = OLD.start_at;
      END IF;
      ---------------- [EXECUTION LOGIC] set the finished log and check if we need to set the status to PASS
      IF NEW.is_finished IS TRUE THEN        
        IF NEW.status_id = 2 AND OLD.status_id = 2 THEN
            NEW.status_id = 3;
        	NEW.log = NEW.log || chr(10) || trim(both '"' from to_json(now())::text) || ' this instruction result set to PASS from WIP by finished signal.';
        ELSIF NEW.status_id = 3 THEN
            NEW.log = NEW.log || chr(10) || trim(both '"' from to_json(now())::text) || ' this instruction result set to PASS.';
        END IF;
        NEW.log = NEW.log || chr(10) || trim(both '"' from to_json(now())::text) || ' this instruction result is finished.';
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
      NEW.status_id = 1;
      NEW.end_at = NULL;
      NEW.is_finished = FALSE;
      NEW.start_at = NULL;
      NEW.test_case_id = (NEW.test_case->>'id')::bigint;
      NEW.project_id = (NEW.test_case->>'projectId')::bigint;
      ----------------
      IF NEW.log IS NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created.';
      ELSIF NEW.log IS NOT NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created with initial log:' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
      NEW.test_case_id = OLD.test_case_id;
      NEW.project_id = OLD.project_id;
      ----------------
      IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL THEN
        NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL THEN
        NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      END IF;
      ----------------
      IF NEW.is_finished IS TRUE AND OLD.is_finished IS FALSE AND NEW.start_at IS NOT NULL THEN
        NEW.end_at = now();
      ELSIF OLD.is_finished IS TRUE THEN
        NEW.end_at = OLD.end_at;
        NEW.is_finished = OLD.is_finished;
        NEW.start_at = OLD.start_at;
      END IF;
      ----------------
      IF OLD.status_id = 1 AND NEW.status_id <> 1 AND OLD.start_at IS NULL THEN
        NEW.start_at = now();
      ELSIF OLD.start_at IS NOT NULL THEN
        NEW.start_at = OLD.start_at;
      END IF;
      ----------------
      IF OLD.start_at IS NULL AND OLD.end_at IS NULL AND OLD.is_finished IS FALSE AND NEW.is_finished IS TRUE THEN -- special case when the run start with failed condition
      	NEW.start_at = now();
      	NEW.end_at = now();
      	NEW.log = trim(both '"' from to_json(now())::text) || ' Special condition, the RUN directly entered into end status.' || chr(10) || NEW.log;
      END IF;
      ---------------- [EXECUTION LOGIC] set the finished log and check if we need to set the status to PASS
      IF NEW.is_finished IS TRUE THEN        
        IF NEW.status_id = 2 AND OLD.status_id = 2 THEN
            NEW.status_id = 3;
        	NEW.log = NEW.log || chr(10) || trim(both '"' from to_json(now())::text) || ' this run set to PASS from WIP by finished signal.';
        ELSIF NEW.status_id = 3 THEN
            NEW.log = NEW.log || chr(10) || trim(both '"' from to_json(now())::text) || ' this run set to PASS.';
        END IF;
        NEW.log = NEW.log || chr(10) || trim(both '"' from to_json(now())::text) || ' this run is finished.';
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_set_result_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
      NEW.end_at = NULL;
      NEW.is_finished = FALSE;
      NEW.start_at = NULL;
      ----------------
      IF NEW.log IS NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created.';
      ELSIF NEW.log IS NOT NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created with initial log:' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
      NEW.uuid = OLD.uuid;
      ----------------
      IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL THEN
        NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL THEN
        NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      END IF;
      ----------------
      IF NEW.is_finished IS TRUE AND OLD.is_finished IS FALSE AND NEW.start_at IS NOT NULL THEN
        NEW.end_at = now();
      ELSIF OLD.is_finished IS TRUE THEN
        NEW.end_at = OLD.end_at;
        NEW.is_finished = OLD.is_finished;
        NEW.start_at = OLD.start_at;
      END IF;
      ----------------
      IF OLD.status_id = 1 AND NEW.status_id <> 1 AND OLD.start_at IS NULL THEN
        NEW.start_at = now();
      ELSIF OLD.start_at IS NOT NULL THEN
        NEW.start_at = OLD.start_at;
      END IF;
      ----------------
      IF OLD.start_at IS NULL AND OLD.end_at IS NULL AND OLD.is_finished IS FALSE AND NEW.is_finished IS TRUE THEN -- special case when the run set result start with failed condition
      	NEW.start_at = now();
      	NEW.end_at = now();
      	NEW.log = trim(both '"' from to_json(now())::text) || ' Special condition, the run set result directly entered into end status.' || chr(10) || NEW.log;
      END IF;
      ---------------- [EXECUTION LOGIC] set the finished log and check if we need to set the status to PASS
      IF NEW.is_finished IS TRUE THEN        
        IF NEW.status_id = 2 AND OLD.status_id = 2 THEN
            NEW.status_id = 3;
        	NEW.log = NEW.log || chr(10) || trim(both '"' from to_json(now())::text) || ' this run set result set to PASS from WIP by finished signal.';
        ELSIF NEW.status_id = 3 THEN
            NEW.log = NEW.log || chr(10) || trim(both '"' from to_json(now())::text) || ' this run set result set to PASS.';
        END IF;
        NEW.log = NEW.log || chr(10) || trim(both '"' from to_json(now())::text) || ' this run set result is finished.';
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
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
	-- [EXECUTION LOGIC] update run set result status
	IF NEW.is_finished IS DISTINCT FROM  OLD.is_finished AND NEW.is_finished IS TRUE THEN
	  IF NEW.run_set_result_id IS NOT NULL THEN
	    -- check if all run is finished
	    IF (SELECT COUNT(id) FROM run WHERE is_finished IS FALSE AND run_set_result_id = NEW.run_set_result_id) = 0 THEN
	      UPDATE run_set_result SET is_finished = TRUE WHERE id = NEW.run_set_result_id;
	    END IF;
	  END IF;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;