DROP TRIGGER "instruction_before_update_instruction_type" ON "system_requirement_pack_system_requirement_link";

CREATE OR REPLACE FUNCTION "insert_system_requirement_type_id" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    NEW.system_requirement_type_id = (SELECT system_requirement_type_id FROM system_requirement WHERE id = NEW.system_requirement_id);
    RETURN NEW;   
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "srpsrl_before_update_system_requirement_type"
  BEFORE INSERT ON system_requirement_pack_system_requirement_link
  FOR EACH ROW
EXECUTE PROCEDURE insert_system_requirement_type_id();