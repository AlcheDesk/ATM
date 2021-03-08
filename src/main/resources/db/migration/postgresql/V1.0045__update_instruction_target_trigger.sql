CREATE OR REPLACE FUNCTION "generate_instrcution_target" ()  RETURNS trigger
  VOLATILE
AS $$
DECLARE
    application_name text;
    section_name text;
    element_name text;
BEGIN
	select name into application_name from application where id = NEW.application_id;
--    RAISE NOTICE 'application name %', application_name;
	select name into section_name from section where id = NEW.section_id;
--    RAISE NOTICE 'section name %', section_name;
	select name into element_name from element where id = NEW.element_id;
--    RAISE NOTICE 'element name %', element_name;
    NEW.target := application_name||'.'||section_name||'.'||element_name;
    RETURN NEW;   
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS "insert_target_trigger" ON "instruction";
DROP TRIGGER IF EXISTS "update_target_trigger" ON "instruction";

CREATE TRIGGER insert_target_trigger BEFORE INSERT ON instruction FOR EACH ROW EXECUTE PROCEDURE generate_instrcution_target();
CREATE TRIGGER update_target_trigger BEFORE UPDATE ON instruction FOR EACH ROW EXECUTE PROCEDURE generate_instrcution_target();