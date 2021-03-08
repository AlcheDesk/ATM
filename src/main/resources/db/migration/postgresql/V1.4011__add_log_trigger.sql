CREATE OR REPLACE FUNCTION "update_log_column" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF OLD.log IS NOT NULL THEN
		NEW.log = OLD.log || chr(10) || NEW.log;
	END IF;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "application_update_log"
  BEFORE UPDATE ON application
  FOR EACH ROW
EXECUTE PROCEDURE update_log_column();

CREATE TRIGGER "dev_instruction_result_update_log"
  BEFORE UPDATE ON dev_instruction_result
  FOR EACH ROW
EXECUTE PROCEDURE update_log_column();

CREATE TRIGGER "driver_update_log"
  BEFORE UPDATE ON driver
  FOR EACH ROW
EXECUTE PROCEDURE update_log_column();

CREATE TRIGGER "driver_entry_update_log"
  BEFORE UPDATE ON driver_entry
  FOR EACH ROW
EXECUTE PROCEDURE update_log_column();

CREATE TRIGGER "driver_pack_update_log"
  BEFORE UPDATE ON driver_pack
  FOR EACH ROW
EXECUTE PROCEDURE update_log_column();

CREATE TRIGGER "element_update_log"
  BEFORE UPDATE ON element
  FOR EACH ROW
EXECUTE PROCEDURE update_log_column();

CREATE TRIGGER "instruction_update_log"
  BEFORE UPDATE ON instruction
  FOR EACH ROW
EXECUTE PROCEDURE update_log_column();

CREATE TRIGGER "instruction_overwrite_update_log"
  BEFORE UPDATE ON instruction_overwrite
  FOR EACH ROW
EXECUTE PROCEDURE update_log_column();

CREATE TRIGGER "module_update_log"
  BEFORE UPDATE ON module
  FOR EACH ROW
EXECUTE PROCEDURE update_log_column();

CREATE TRIGGER "notification_update_log"
  BEFORE UPDATE ON notification
  FOR EACH ROW
EXECUTE PROCEDURE update_log_column();

CREATE TRIGGER "prod_instruction_result_update_log"
  BEFORE UPDATE ON prod_instruction_result
  FOR EACH ROW
EXECUTE PROCEDURE update_log_column();

CREATE TRIGGER "project_update_log"
  BEFORE UPDATE ON project
  FOR EACH ROW
EXECUTE PROCEDURE update_log_column();

CREATE TRIGGER "run_update_log"
  BEFORE UPDATE ON run
  FOR EACH ROW
EXECUTE PROCEDURE update_log_column();

CREATE TRIGGER "run_set_update_log"
  BEFORE UPDATE ON run_set
  FOR EACH ROW
EXECUTE PROCEDURE update_log_column();

CREATE TRIGGER "run_set_result_update_log"
  BEFORE UPDATE ON run_set_result
  FOR EACH ROW
EXECUTE PROCEDURE update_log_column();

CREATE TRIGGER "section_update_log"
  BEFORE UPDATE ON section
  FOR EACH ROW
EXECUTE PROCEDURE update_log_column();

CREATE TRIGGER "template_update_log"
  BEFORE UPDATE ON template
  FOR EACH ROW
EXECUTE PROCEDURE update_log_column();

CREATE TRIGGER "test_case_update_log"
  BEFORE UPDATE ON test_case
  FOR EACH ROW
EXECUTE PROCEDURE update_log_column();

CREATE TRIGGER "test_case_share_folder_update_log"
  BEFORE UPDATE ON test_case_share_folder
  FOR EACH ROW
EXECUTE PROCEDURE update_log_column();

CREATE TRIGGER "test_case_overwrite_update_log"
  BEFORE UPDATE ON test_case_overwrite
  FOR EACH ROW
EXECUTE PROCEDURE update_log_column();