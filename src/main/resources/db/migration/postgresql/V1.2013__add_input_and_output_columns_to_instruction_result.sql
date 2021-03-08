ALTER TABLE "prod_instruction_result" ADD COLUMN input_data text;
ALTER TABLE "prod_instruction_result" ADD COLUMN input_type VARCHAR(50);
ALTER TABLE "prod_instruction_result" ADD COLUMN input_parameter json;

ALTER TABLE "prod_instruction_result" ADD COLUMN output_data text;
ALTER TABLE "prod_instruction_result" ADD COLUMN output_type VARCHAR(50);
ALTER TABLE "prod_instruction_result" ADD COLUMN output_parameter json;

ALTER TABLE "dev_instruction_result" ADD COLUMN input_data text;
ALTER TABLE "dev_instruction_result" ADD COLUMN input_type VARCHAR(50);
ALTER TABLE "dev_instruction_result" ADD COLUMN input_parameter json;

ALTER TABLE "dev_instruction_result" ADD COLUMN output_data text;
ALTER TABLE "dev_instruction_result" ADD COLUMN output_type VARCHAR(50);
ALTER TABLE "dev_instruction_result" ADD COLUMN output_parameter json;