ALTER TABLE "dev_instruction_result" disable TRIGGER "dev_instruction_result_after_update_change_others";
ALTER TABLE "dev_instruction_result" disable TRIGGER "dev_instruction_result_generate_columns";
ALTER TABLE "prod_instruction_result" disable TRIGGER "prod_instruction_result_after_update_change_others";
ALTER TABLE "prod_instruction_result" disable TRIGGER "prod_instruction_result_generate_columns";


ALTER TABLE "dev_instruction_result" ADD COLUMN instruction_id bigint;
UPDATE "dev_instruction_result" SET instruction_id = (instruction->>'id')::bigint WHERE instruction IS NOT NULL;
ALTER TABLE "dev_instruction_result" ALTER COLUMN instruction_id SET NOT NULL;
ALTER TABLE "dev_instruction_result" ADD COLUMN comment text;
UPDATE "dev_instruction_result" SET comment = (SELECT comment FROM instruction WHERE id = instruction_id) WHERE instruction_id IS NOT NULL;
ALTER TABLE "dev_instruction_result" ADD CONSTRAINT dev_instruction_result_fk_instruction FOREIGN KEY (instruction_id) REFERENCES "instruction" ("id");
ALTER TABLE "dev_instruction_result" RENAME CONSTRAINT "devinstruction_result_fk_run_type" TO dev_instruction_result_fk_run_type;
ALTER TABLE "dev_instruction_result" RENAME CONSTRAINT "ext_instruction_result_fk_run" TO dev_instruction_result_fk_run;
ALTER TABLE "dev_instruction_result" RENAME CONSTRAINT "ext_instruction_result_fk_status" TO dev_instruction_result_fk_status;

ALTER TABLE "prod_instruction_result" ADD COLUMN instruction_id bigint;
UPDATE "prod_instruction_result" SET instruction_id = (instruction->>'id')::bigint WHERE instruction IS NOT NULL;
ALTER TABLE "prod_instruction_result" ALTER COLUMN instruction_id SET NOT NULL;
ALTER TABLE "prod_instruction_result" ADD COLUMN comment text;
UPDATE "prod_instruction_result" SET comment = (SELECT comment FROM instruction WHERE id = instruction_id) WHERE instruction_id IS NOT NULL;
ALTER TABLE "prod_instruction_result" ADD CONSTRAINT prod_instruction_result_fk_instruction FOREIGN KEY (instruction_id) REFERENCES "instruction" ("id");

ALTER TABLE "dev_instruction_result" enable TRIGGER "dev_instruction_result_after_update_change_others";
ALTER TABLE "dev_instruction_result" enable TRIGGER "dev_instruction_result_generate_columns";
ALTER TABLE "prod_instruction_result" enable TRIGGER "prod_instruction_result_after_update_change_others";
ALTER TABLE "prod_instruction_result" enable TRIGGER "prod_instruction_result_generate_columns";