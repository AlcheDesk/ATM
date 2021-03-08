CREATE OR REPLACE FUNCTION "element_modify_trigger_instruction_target_udpate" ()  RETURNS trigger
    LANGUAGE 'plpgsql'
AS $BODY$

DECLARE
    appication_name text;
    section_name text;
    element_name text;
    target text;
BEGIN       
    UPDATE instruction SET 
    target = (select name from application where id = instruction.application_id)||'.'||(select name from section where id = instruction.section_id)||'.'||(select name from element where id = instruction.element_id) 
    WHERE instruction.element_id = NEW.id;  
    
    RETURN NEW;
END;

$BODY$;

DROP TRIGGER IF EXISTS "element_udpate_trigger_instruction_target_update" ON "element";
CREATE TRIGGER element_udpate_trigger_instruction_target_update AFTER UPDATE ON "element" FOR EACH ROW EXECUTE PROCEDURE "element_modify_trigger_instruction_target_udpate"();