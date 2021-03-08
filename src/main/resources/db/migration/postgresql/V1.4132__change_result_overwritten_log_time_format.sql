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
CREATE OR REPLACE FUNCTION "keep_log" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF OLD.log IS NOT NULL  AND NEW.log IS NOT NULL THEN
		NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;        
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
------------------------------------------------------------------------------