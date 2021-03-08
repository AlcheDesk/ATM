DROP TRIGGER IF EXISTS "dev_instruction_result_trigger_run_status_update" ON "dev_instruction_result";
DROP TRIGGER IF EXISTS "dev_instruction_result_trigger_run_status_insert" ON "dev_instruction_result";

DROP TRIGGER IF EXISTS "prod_instruction_result_trigger_run_status_update" ON "prod_instruction_result";
DROP TRIGGER IF EXISTS "prod_instruction_result_trigger_run_status_insert" ON "prod_instruction_result";

CREATE TRIGGER dev_instruction_result_insert_trigger_run_status
  AFTER INSERT ON dev_instruction_result
  FOR EACH ROW
EXECUTE PROCEDURE dev_instruction_result_update_run_status();

CREATE TRIGGER prod_instruction_result_insert_trigger_run_status
  AFTER INSERT ON prod_instruction_result
  FOR EACH ROW
EXECUTE PROCEDURE prod_instruction_result_update_run_status();

CREATE TRIGGER dev_instruction_result_update_trigger_run_status
  AFTER UPDATE ON dev_instruction_result
  FOR EACH ROW
EXECUTE PROCEDURE dev_instruction_result_update_run_status();

CREATE TRIGGER prod_instruction_result_update_trigger_run_status
  AFTER UPDATE ON prod_instruction_result
  FOR EACH ROW
EXECUTE PROCEDURE prod_instruction_result_update_run_status();
