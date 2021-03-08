ALTER TABLE "instruction" ADD COLUMN expected_value text;
ALTER TABLE "dev_instruction_result" ADD COLUMN expected_value text;
ALTER TABLE "prod_instruction_result" ADD COLUMN expected_value text;
ALTER TABLE "dev_instruction_result" ADD COLUMN return_value text;
ALTER TABLE "prod_instruction_result" ADD COLUMN return_value text;
