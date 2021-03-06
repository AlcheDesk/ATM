----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "instruction_result_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF TG_OP = 'UPDATE' AND NEW.instruction IS NOT NULL AND NEW.instruction <> OLD.instruction THEN
		NEW.target = NEW.instruction->>'target';
	ELSIF TG_OP = 'UPDATE' AND NEW.instruction IS NULL THEN
	    NEW.target = NULL;
	END IF;
	
	IF TG_OP = 'INSERT' AND NEW.instruction IS NOT NULL THEN
		NEW.target = NEW.instruction->>'target';
	ELSIF TG_OP = 'INSERT' AND NEW.instruction IS NULL THEN
	    NEW.target = NULL;
	END IF;
	
	IF TG_OP = 'INSERT' THEN
	  IF NEW.log IS NULL THEN
	      NEW.log = trim(both '"' from to_json(now())::text) || ' Created.';
      ELSIF NEW.log IS NOT NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created with initial log:' || NEW.log;
      END IF;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------