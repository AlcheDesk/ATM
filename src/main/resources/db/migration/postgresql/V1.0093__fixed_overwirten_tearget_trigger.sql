CREATE OR REPLACE FUNCTION "generate_instrcution_target" ()  RETURNS trigger
  VOLATILE
AS $$
DECLARE
    application_name text;
    section_name text;
    element_name text;
    test_case_name text;
BEGIN
	IF NEW.ref_test_case_id IS NOT NULL THEN
		select name into test_case_name from test_case where id = NEW.ref_test_case_id;        
	    NEW.target := test_case_name;
	ELSIF NEW.application_id IS NULL AND NEW.section_id IS NULL AND NEW.element_id IS NULL THEN
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