ALTER TABLE "dev_step_log" disable TRIGGER "dev_step_log_update_created_at_updated_at";
ALTER TABLE "dev_file" disable TRIGGER "dev_file_update_created_at_updated_at";
ALTER TABLE "dev_execution_log" disable TRIGGER "dev_execution_log_update_created_at_updated_at";
ALTER TABLE "prod_step_log" disable TRIGGER "prod_step_log_update_created_at_updated_at";
ALTER TABLE "prod_file" disable TRIGGER "prod_file_update_created_at_updated_at";
ALTER TABLE "prod_execution_log" disable TRIGGER "prod_execution_log_update_created_at_updated_at";

ALTER TABLE "dev_instruction_result" ALTER COLUMN "created_at" DROP NOT NULL;
ALTER TABLE "dev_instruction_result" ALTER COLUMN "updated_at" DROP NOT NULL;
ALTER TABLE "dev_instruction_result" ALTER COLUMN "input" DROP NOT NULL;

UPDATE prod_instruction_result SET target = 'N/A' WHERE target IS NULL;
ALTER TABLE "prod_instruction_result" ALTER COLUMN target SET NOT NULL;
UPDATE prod_instruction_result SET action = 'N/A' WHERE action IS NULL;
ALTER TABLE "prod_instruction_result" ALTER COLUMN action SET NOT NULL;
UPDATE prod_instruction_result SET instruction = '{}'::json WHERE instruction IS NULL;
ALTER TABLE "prod_instruction_result" ALTER COLUMN instruction SET NOT NULL;
DELETE FROM prod_instruction_result WHERE run_id IS NULL;
ALTER TABLE "prod_instruction_result" ALTER COLUMN run_id SET NOT NULL;

ALTER TABLE "dev_file" ALTER COLUMN file_type_id SET NOT NULL;
ALTER TABLE "dev_file" ALTER COLUMN instruction_result_id SET NOT NULL;
ALTER TABLE "prod_file" ALTER COLUMN file_type_id SET NOT NULL;
ALTER TABLE "prod_file" ALTER COLUMN instruction_result_id SET NOT NULL;

ALTER TABLE "dev_step_log" ALTER COLUMN instruction_result_id SET NOT NULL;
ALTER TABLE "prod_step_log" ALTER COLUMN instruction_result_id SET NOT NULL;

ALTER TABLE "dev_step_log" enable TRIGGER "dev_step_log_update_created_at_updated_at";
ALTER TABLE "dev_file" enable TRIGGER "dev_file_update_created_at_updated_at";
ALTER TABLE "dev_execution_log" enable TRIGGER "dev_execution_log_update_created_at_updated_at";
ALTER TABLE "prod_step_log" enable TRIGGER "prod_step_log_update_created_at_updated_at";
ALTER TABLE "prod_file" enable TRIGGER "prod_file_update_created_at_updated_at";
ALTER TABLE "prod_execution_log" enable TRIGGER "prod_execution_log_update_created_at_updated_at";