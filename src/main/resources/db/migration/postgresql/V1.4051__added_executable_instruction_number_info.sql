ALTER TABLE "run" ADD COLUMN executable_instruction_number bigint;
ALTER TABLE "run" ALTER COLUMN executable_instruction_number SET DEFAULT 0;

ALTER TABLE "test_case_execution_info" ADD COLUMN latest_run_executable_instruction_number bigint;