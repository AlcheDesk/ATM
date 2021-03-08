ALTER TABLE "instruction" DROP CONSTRAINT "instruction_fk_driver_type_instruction_type";
ALTER TABLE "instruction" DROP CONSTRAINT "instruction_fk_element_type";
ALTER TABLE "instruction" ADD CONSTRAINT instruction_fk_driver_type_instruction_type FOREIGN KEY (driver_type_id, instruction_type_id)
REFERENCES driver_type_instruction_type_link (driver_type_id, instruction_type_id) DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE "instruction" ADD CONSTRAINT instruction_fk_element_type FOREIGN KEY (element_type_id) REFERENCES "element_type" ("id") DEFERRABLE INITIALLY DEFERRED;
--------------------------------------------------------------------------------------------------------
ALTER FUNCTION "insert_instruction_fields" ( ) RENAME TO instruction_insert_fields;
ALTER TABLE "driver_type_instruction_type_link" ADD CONSTRAINT driver_type_instruction_type_link_ix_instruction_type UNIQUE ("instruction_type_id");
--------------------------------------------------------------------------------------------------------
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
END;
$body$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------
CREATE TRIGGER "instruction_generate_columns"
  BEFORE INSERT OR UPDATE ON instruction
  FOR EACH ROW
EXECUTE PROCEDURE instruction_generate_columns();