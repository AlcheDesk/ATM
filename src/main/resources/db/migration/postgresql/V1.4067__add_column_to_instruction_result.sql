ALTER TABLE "prod_instruction_result" ADD COLUMN is_overwrite boolean;
ALTER TABLE "dev_instruction_result" ADD COLUMN is_overwrite boolean;
ALTER TABLE "prod_instruction_result" ALTER COLUMN is_overwrite SET DEFAULT null;
ALTER TABLE "dev_instruction_result" ALTER COLUMN is_overwrite SET DEFAULT null;