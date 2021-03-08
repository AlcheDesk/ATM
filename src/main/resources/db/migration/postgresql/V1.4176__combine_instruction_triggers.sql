CREATE OR REPLACE FUNCTION "instruction_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF TG_OP = 'UPDATE' AND NEW.element_id IS NOT NULL AND NEW.element_id <> OLD.element_id THEN
		NEW.element_type_id = (SELECT element_type_id FROM element WHERE id = NEW.element_id);
	ELSIF TG_OP = 'UPDATE' AND NEW.element_id IS NULL THEN
	    NEW.element_type_id = NULL;
	END IF;
	--------------------------------------------------------------------------------------------------------
	IF TG_OP = 'INSERT' AND NEW.element_id IS NOT NULL THEN
		NEW.element_type_id = (SELECT element_type_id FROM element WHERE id = NEW.element_id);
	ELSIF TG_OP = 'INSERT' AND NEW.element_id IS NULL THEN
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
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
DROP TRIGGER "instruction_before_insert_fields" ON "instruction";
DROP FUNCTION "instruction_insert_fields" ( );
ALTER TRIGGER "section_after_update_update_others" ON "section" RENAME TO section_after_update_change_others;
ALTER FUNCTION "section_update_others" ( ) RENAME TO section_update_change_others;