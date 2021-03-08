CREATE OR REPLACE FUNCTION "instruction_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF TG_OP = 'UPDATE' AND NEW.element_id IS NOT NULL AND NEW.element_id <> OLD.element_id THEN
		NEW.element_type_id = (SELECT element_type_id FROM element WHERE id = NEW.element_id);
	ELSIF TG_OP = 'UPDATE' AND NEW.element_id IS NULL THEN
	    NEW.element_type_id = NULL;
	END IF;
	
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
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;