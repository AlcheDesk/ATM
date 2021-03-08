CREATE OR REPLACE FUNCTION "insert_instruction_fields" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    NEW.target = null;
    NEW.ref_test_case_overwrite_name = null;
    RETURN NEW;   
END;
$body$ LANGUAGE plpgsql;
------------------------------------------------------------------------------
CREATE TRIGGER "instruction_before_insert_fields"
  BEFORE INSERT ON instruction
  FOR EACH ROW
EXECUTE PROCEDURE insert_instruction_fields();
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "insert_run_set_fields" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    NEW.aliases = ARRAY[]::text[];
    RETURN NEW;   
END;
$body$ LANGUAGE plpgsql;
------------------------------------------------------------------------------
CREATE TRIGGER "run_set_before_insert_fields"
  BEFORE INSERT ON run_set
  FOR EACH ROW
EXECUTE PROCEDURE insert_run_set_fields();
------------------------------------------------------------------------------