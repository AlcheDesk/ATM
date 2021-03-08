ALTER TABLE "instruction" DROP CONSTRAINT IF EXISTS "instruction_ck_application_id_and_section_id_and_element_id";
ALTER TABLE "instruction" ADD CONSTRAINT instruction_ck_application_id_and_section_id_and_element_id 
CHECK (
(application_id IS NOT NULL AND section_id IS NOT NULL)
OR 
(application_id IS NULL AND section_id IS NULL AND element_id IS NULL)
);


CREATE OR REPLACE FUNCTION "generate_instrcution_target" ()  RETURNS trigger
  VOLATILE
AS $$
DECLARE
    application_name text;
    section_name text;
    element_name text;
BEGIN
	IF NEW.application_id IS NULL AND NEW.section_id IS NULL AND NEW.element_id IS NULL THEN
	    NEW.target := NULL;	
	ELSIF NEW.application_id IS NOT NULL AND NEW.section_id IS NOT NULL AND NEW.element_id IS NOT NULL THEN
		select name into application_name from application where id = NEW.application_id;
		select name into section_name from section where id = NEW.section_id;
		select name into element_name from element where id = NEW.element_id;
	    NEW.target := application_name||'.'||section_name||'.'||element_name;
    ELSIF NEW.application_id IS NOT NULL AND NEW.section_id IS NOT NULL AND NEW.element_id IS NULL THEN
		select name into application_name from application where id = NEW.application_id;
		select name into section_name from section where id = NEW.section_id;
	    NEW.target := application_name||'.'||section_name;
	ELSE
	    NEW.target := NULL;	
	END IF;
    RETURN NEW;   
END;
$$ LANGUAGE plpgsql;

-----------------------------------------------------------------
DROP TRIGGER IF EXISTS "insert_target_trigger" ON "instruction";
CREATE TRIGGER "insert_target_trigger" BEFORE INSERT ON "instruction" FOR EACH ROW EXECUTE PROCEDURE generate_instrcution_target();
DROP TRIGGER IF EXISTS "update_target_trigger" ON "instruction";
CREATE TRIGGER "update_target_trigger" BEFORE UPDATE ON "instruction" FOR EACH ROW EXECUTE PROCEDURE generate_instrcution_target();