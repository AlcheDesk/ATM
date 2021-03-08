CREATE OR REPLACE FUNCTION generate_instrcution_target()  RETURNS trigger
  VOLATILE
AS $dbvis$
DECLARE
    appication_name text;
    section_name text;
    element_name text;
BEGIN
	select name into appication_name from application where id = NEW.application_id;
	select name into section_name from section where id = NEW.section_id;
	select name into element_name from element where id = NEW.element_id;
    NEW.target := appication_name||'.'||section_name||'.'||element_name;
    RETURN NEW;   
END;
$dbvis$ LANGUAGE plpgsql