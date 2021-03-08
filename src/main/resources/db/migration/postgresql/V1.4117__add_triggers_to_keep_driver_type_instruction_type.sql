CREATE OR REPLACE FUNCTION "keep_driver_type" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    NEW.driver_type_id = OLD.driver_type_id;
    RETURN NEW;   
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "driver_before_update_driver_type"
  BEFORE UPDATE ON driver
  FOR EACH ROW
EXECUTE PROCEDURE keep_driver_type();
-----------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "keep_instruction_type" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    NEW.instruction_type_id = OLD.instruction_type_id;
    RETURN NEW;   
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "instruction_before_update_instruction_type"
  BEFORE UPDATE ON instruction
  FOR EACH ROW
EXECUTE PROCEDURE keep_instruction_type();
-----------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "insert_system_requirement_type_id" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    NEW.system_requirement_type_id = (SELECT system_requirement_type_id FROM system_requirement WHERE id = NEW.system_requirement_id);
    RETURN NEW;   
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "instruction_before_update_instruction_type"
  BEFORE INSERT ON system_requirement_pack_system_requirement_link
  FOR EACH ROW
EXECUTE PROCEDURE insert_system_requirement_type_id();
-----------------------------------------------------------------------------------
