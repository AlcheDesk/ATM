ALTER TABLE "instruction" DROP CONSTRAINT "instruction_fk_element_element_type";


CREATE OR REPLACE FUNCTION "before_element_update" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.element_type_id != OLD.element_type_id THEN
		UPDATE instruction SET element_type_id = NEW.element_type_id WHERE element_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "element_before_update"
  BEFORE UPDATE ON element
  FOR EACH ROW
EXECUTE PROCEDURE before_element_update();