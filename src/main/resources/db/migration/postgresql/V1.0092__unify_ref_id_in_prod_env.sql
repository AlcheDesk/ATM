ALTER TABLE "prod_file" RENAME COLUMN "prod_instruction_result_id" TO instruction_result_id;
ALTER TABLE "prod_step_log" RENAME COLUMN "prod_instruction_result_id" TO instruction_result_id;
ALTER TABLE "prod_execution_log" RENAME COLUMN "prod_instruction_result_id" TO instruction_result_id;