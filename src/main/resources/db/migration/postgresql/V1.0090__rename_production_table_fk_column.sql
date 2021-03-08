ALTER TABLE "prod_file" RENAME COLUMN "instruction_result_id" TO prod_instruction_result_id;
ALTER TABLE "prod_step_log" RENAME COLUMN "instruction_result_id" TO prod_instruction_result_id;
ALTER TABLE "prod_execution_log" RENAME COLUMN "instruction_result_id" TO prod_instruction_result_id;
ALTER TABLE "prod_execution_log" RENAME CONSTRAINT "execution_log_fk_instruction_result" TO prod_execution_log_fk_prod_instruction_result;
    