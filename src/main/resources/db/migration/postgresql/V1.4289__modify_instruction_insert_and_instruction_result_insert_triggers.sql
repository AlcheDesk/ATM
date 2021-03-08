ALTER TABLE "instruction" disable TRIGGER "instruction_generate_columns";
UPDATE instruction SET instruction_action_id = 19 WHERE instruction_type_id IN (9, 10, 11, 12, 13, 15, 19, 20);
ALTER TABLE "instruction" enable TRIGGER "instruction_generate_columns";
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "instruction_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
      NEW.target = null;
      NEW.ref_test_case_overwrite_name = null;  
      ----------------
      IF NEW.log IS NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created.';
      ELSIF NEW.log IS NOT NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created with initial log:' || NEW.log;
      END IF;
      ----------------
      IF NEW.instruction_type_id IS NOT NULL THEN
        NEW.driver_type_id = (SELECT driver_type_id FROM driver_type_instruction_type_link WHERE instruction_type_id = NEW.instruction_type_id);
        ----------------
        IF NEW.instruction_type_id = 9 OR 
           NEW.instruction_type_id = 10 OR 
           NEW.instruction_type_id = 11 OR 
           NEW.instruction_type_id = 12 OR 
           NEW.instruction_type_id = 13 OR 
           NEW.instruction_type_id = 15 OR 
           NEW.instruction_type_id = 19 OR 
           NEW.instruction_type_id = 20 THEN
          NEW.instruction_action_id = 19; 
        END IF;
      ELSIF NEW.instruction_type_id IS NULL THEN
        NEW.driver_type_id = NULL; 
      END IF;
      ----------------
      IF NEW.element_id IS NOT NULL THEN
        NEW.element_type_id = (SELECT element_type_id FROM element WHERE id = NEW.element_id);
      ELSIF NEW.element_id IS NULL THEN
        NEW.element_type_id = NULL;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
      NEW.copy_from_id = OLD.copy_from_id;
      NEW.instruction_type_id = OLD.instruction_type_id;
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
      ----------------
      IF NEW.ref_test_case_overwrite_id IS DISTINCT FROM OLD.ref_test_case_overwrite_id AND NEW.ref_test_case_overwrite_id IS NULL THEN
        NEW.ref_test_case_overwrite_name = NULL;
      ELSIF NEW.ref_test_case_overwrite_id IS DISTINCT FROM OLD.ref_test_case_overwrite_id THEN
        NEW.ref_test_case_overwrite_name = (SELECT name FROM test_case_overwrite WHERE id = NEW.ref_test_case_overwrite_id);
      END IF;
      ----------------
      IF NEW.element_id IS NOT NULL AND NEW.element_id IS DISTINCT FROM OLD.element_id THEN
        NEW.element_type_id = (SELECT element_type_id FROM element WHERE id = NEW.element_id);
      ELSIF NEW.element_id IS NULL THEN
        NEW.element_type_id = NULL;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------