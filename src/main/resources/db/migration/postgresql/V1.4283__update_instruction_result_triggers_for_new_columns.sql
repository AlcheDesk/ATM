ALTER TABLE "dev_instruction_result" ALTER COLUMN "instruction_id" DROP NOT NULL;
ALTER TABLE "prod_instruction_result" ALTER COLUMN "instruction_id" DROP NOT NULL;

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
          NEW.log = trim(both '"' from to_json(now())::text) || ' [NEW] Created.';
      ELSIF NEW.log IS NOT NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' [NEW] Created with initial log:' || NEW.log;
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
          NEW.instruction_id = CASE WHEN NEW.instruction->>'id' = 'null' THEN NULL ELSE (NEW.instruction->>'id')::bigint END;
          NEW.comment = CASE WHEN NEW.instruction->>'comment' = 'null' THEN NULL ELSE NEW.instruction->>'comment' END;
      ELSIF NEW.instruction IS NULL THEN
          NEW.action = NULL;
          NEW.instruction_id = null;
          NEW.comment = null;
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
      IF NEW.instruction IS NOT NULL AND NEW.instruction <> OLD.instruction THEN
        NEW.action = CASE WHEN NEW.instruction->>'action' = 'null' THEN NULL ELSE NEW.instruction->>'action' END;
        NEW.instruction_id = CASE WHEN NEW.instruction->>'id' = 'null' THEN NULL ELSE (NEW.instruction->>'id')::bigint END;
        NEW.comment = CASE WHEN NEW.instruction->>'comment' = 'null' THEN NULL ELSE NEW.instruction->>'comment' END;
        NEW.target = NEW.instruction->>'target';
      ELSIF NEW.instruction IS NULL THEN
        NEW.action = NULL;
        NEW.instruction_id = null;
        NEW.comment = null;
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
      ----------------
      -- get the clean new log first
      IF NEW.log IS DISTINCT FROM OLD.log THEN
        NEW.log = replace(NEW.log, OLD.log, '');
        IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log <> '' THEN
          NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
        ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log <> '' THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
        END IF;
      END IF;
      ---------------- [EXECUTION LOGIC] set the finished log and check if we need to set the status to PASS
      IF NEW.is_finished IS TRUE THEN        
        IF NEW.status_id = 2 AND OLD.status_id = 2 THEN
           NEW.status_id = 3;
           NEW.log = CASE 
        	           WHEN NEW.log IS NULL THEN 
        	             trim(both '"' from to_json(now())::text) || ' [PASS] this instruction result set to PASS from WIP by finished signal.'
        	           ELSE 
        	             NEW.log || chr(10) || trim(both '"' from to_json(now())::text) || ' [PASS] this instruction result set to PASS from WIP by finished signal.' 
        	         END;
        ELSIF NEW.status_id = 3 THEN
          NEW.log = CASE 
        	          WHEN NEW.log IS NULL THEN 
        	            trim(both '"' from to_json(now())::text) || ' [PASS] this instruction result set to PASS.'
        	          ELSE 
        	            NEW.log || chr(10) || trim(both '"' from to_json(now())::text) || ' [PASS] this instruction result set to PASS.' 
        	        END;
        END IF;
        NEW.log = NEW.log || chr(10) || trim(both '"' from to_json(now())::text) || ' [FINISHED] this instruction result is finished.';
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;