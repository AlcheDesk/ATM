CREATE OR REPLACE FUNCTION "keep_instruction_option_log" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF OLD.instruction_option_log IS NOT NULL AND NEW.instruction_option_log IS NOT NULL THEN
		NEW.instruction_option_log = OLD.instruction_option_log || chr(10) || NEW.instruction_option_log;        
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;
------------------------------------------------------------------------------
CREATE TRIGGER "prod_instruction_result_before_update_instruction_option_log"
  BEFORE UPDATE ON prod_instruction_result
  FOR EACH ROW
EXECUTE PROCEDURE keep_instruction_option_log();
------------------------------------------------------------------------------
CREATE TRIGGER "dev_instruction_result_before_update_instruction_option_log"
  BEFORE UPDATE ON dev_instruction_result
  FOR EACH ROW
EXECUTE PROCEDURE keep_instruction_option_log();
------------------------------------------------------------------------------