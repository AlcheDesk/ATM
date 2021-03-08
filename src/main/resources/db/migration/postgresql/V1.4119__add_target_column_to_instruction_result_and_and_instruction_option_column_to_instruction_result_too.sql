ALTER TABLE "dev_instruction_result" ADD COLUMN target text;
ALTER TABLE "prod_instruction_result" ADD COLUMN target text;
ALTER TABLE "dev_instruction_result" ADD COLUMN instruction_options text[];
ALTER TABLE "prod_instruction_result" ADD COLUMN instruction_options text[];
ALTER TABLE "dev_instruction_result" ADD COLUMN instruction_option_log text;
ALTER TABLE "prod_instruction_result" ADD COLUMN instruction_option_log text;