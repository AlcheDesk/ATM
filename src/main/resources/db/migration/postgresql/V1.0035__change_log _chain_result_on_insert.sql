DROP TRIGGER IF EXISTS "trigger_instruction_result_status_update" ON "step_log";
DROP TRIGGER IF EXISTS "trigger_run_status_update" ON "instruction_result";

CREATE TRIGGER trigger_instruction_result_status_update 
BEFORE INSERT 
ON step_log
FOR EACH ROW
EXECUTE PROCEDURE step_log_update_instruction_result_status();
    
    
CREATE TRIGGER trigger_run_status_update
BEFORE INSERT 
ON instruction_result
FOR EACH ROW
EXECUTE PROCEDURE instruction_result_update_run_status();