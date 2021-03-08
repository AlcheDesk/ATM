ALTER TRIGGER "run_after_insert_insert_run_execution_info" ON "run" RENAME TO run_after_insert_change_others;
ALTER FUNCTION "run_insert_insert_run_execution_info" ( ) RENAME TO run_insert_change_others;
ALTER TRIGGER "run_after_update_update_run_execution_info" ON "run" RENAME TO run_after_update_change_others;
ALTER FUNCTION "run_update_update_run_execution_info" ( ) RENAME TO run_update_change_others;