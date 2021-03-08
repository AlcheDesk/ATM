CREATE OR REPLACE FUNCTION "application_modify_trigger_instruction_target_udpate" ()  RETURNS trigger
    LANGUAGE 'plpgsql'
AS $BODY$

DECLARE
    appication_name text;
    section_name text;
    element_name text;
    target text;
BEGIN       
    UPDATE instruction SET 
    target = (select name from application where id = instruction.application_id)||'.'||(select name from section where id = instruction.section_id)||'.'||(select name from application where id = instruction.application_id) 
    WHERE instruction.application_id = NEW.id;  
    
    RETURN NEW;
END;

$BODY$;

DROP TRIGGER IF EXISTS "application_udpate_trigger_instruction_target_update" ON "application";
CREATE TRIGGER application_udpate_trigger_instruction_target_update AFTER UPDATE ON "application" FOR EACH ROW EXECUTE PROCEDURE "application_modify_trigger_instruction_target_udpate"();


CREATE OR REPLACE FUNCTION "section_modify_trigger_instruction_target_udpate" ()  RETURNS trigger
    LANGUAGE 'plpgsql'
AS $BODY$

DECLARE
    appication_name text;
    section_name text;
    element_name text;
    target text;
BEGIN       
    UPDATE instruction SET 
    target = (select name from section where id = instruction.section_id)||'.'||(select name from section where id = instruction.section_id)||'.'||(select name from section where id = instruction.section_id) 
    WHERE instruction.section_id = NEW.id;  
    
    RETURN NEW;
END;

$BODY$;

DROP TRIGGER IF EXISTS "section_udpate_trigger_instruction_target_update" ON "section";
CREATE TRIGGER section_udpate_trigger_instruction_target_update AFTER UPDATE ON "section" FOR EACH ROW EXECUTE PROCEDURE "section_modify_trigger_instruction_target_udpate"();