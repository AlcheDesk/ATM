ALTER TABLE "prod_instruction_result" disable TRIGGER "prod_instruction_result_before_update_timestamp";
ALTER TABLE "dev_instruction_result" disable TRIGGER "dev_instruction_result_before_update_timestamp";
--------------------------------------------------------------------------------------------------------
UPDATE "dev_instruction_result" SET target = instruction->>'target';
UPDATE "prod_instruction_result" SET target = instruction->>'target';
--------------------------------------------------------------------------------------------------------
ALTER TABLE "prod_instruction_result" enable TRIGGER "prod_instruction_result_before_update_timestamp";
ALTER TABLE "dev_instruction_result" enable TRIGGER "dev_instruction_result_before_update_timestamp";
--------------------------------------------------------------------------------------------------------