----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "instruction_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF TG_OP = 'UPDATE' AND NEW.ref_test_case_overwrite_id IS DISTINCT FROM OLD.ref_test_case_overwrite_id AND NEW.ref_test_case_overwrite_id IS NULL THEN
		NEW.ref_test_case_overwrite_name = NULL;
	ELSIF TG_OP = 'UPDATE' AND NEW.ref_test_case_overwrite_id IS DISTINCT FROM OLD.ref_test_case_overwrite_id THEN
	    NEW.ref_test_case_overwrite_name = (SELECT name FROM test_case_overwrite WHERE id = NEW.ref_test_case_overwrite_id);
	END IF;
	--------------------------------------------------------------------------------------------------------
	IF TG_OP = 'UPDATE' AND NEW.element_id IS NOT NULL AND NEW.element_id IS DISTINCT FROM OLD.element_id THEN
		NEW.element_type_id = (SELECT element_type_id FROM element WHERE id = NEW.element_id);
	ELSIF TG_OP = 'UPDATE' AND NEW.element_id IS NULL THEN
	    NEW.element_type_id = NULL;
	END IF;
	--------------------------------------------------------------------------------------------------------	
	IF TG_OP = 'INSERT' AND NEW.instruction_type_id IS NOT NULL THEN
		NEW.driver_type_id = (SELECT driver_type_id FROM driver_type_instruction_type_link WHERE instruction_type_id = NEW.instruction_type_id);
	ELSIF TG_OP = 'INSERT' AND NEW.instruction_type_id IS NULL THEN
	    NEW.driver_type_id = NULL; 
	END IF;
	--------------------------------------------------------------------------------------------------------
	IF TG_OP = 'INSERT' THEN
		NEW.target = null;
        NEW.ref_test_case_overwrite_name = null;  
	END IF;
	--------------------------------------------------------------------------------------------------------
	IF TG_OP = 'INSERT' AND NEW.element_id IS NOT NULL THEN
		NEW.element_type_id = (SELECT element_type_id FROM element WHERE id = NEW.element_id);
	ELSIF TG_OP = 'INSERT' AND NEW.element_id IS NULL THEN
	    NEW.element_type_id = NULL;
	END IF;
	--------------------------------------------------------------------------------------------------------
	IF TG_OP = 'INSERT' THEN
	  IF NEW.log IS NULL THEN
	      NEW.log = trim(both '"' from to_json(now())::text) || ' Created.';
      ELSIF NEW.log IS NOT NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created with initial log:' || NEW.log;
      END IF;
	END IF;
	--------------------------------------------------------------------------------------------------------
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "keep_log" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL THEN
		NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
	ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL THEN
	    NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;