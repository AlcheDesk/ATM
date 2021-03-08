ALTER TABLE "dev_step_log" disable TRIGGER "dev_step_log_update_created_at_updated_at";
ALTER TABLE "dev_file" disable TRIGGER "dev_file_update_created_at_updated_at";
ALTER TABLE "dev_execution_log" disable TRIGGER "dev_execution_log_update_created_at_updated_at";

ALTER TABLE "dev_instruction_result" ADD COLUMN run_type_id bigint;
UPDATE "dev_instruction_result" SET run_type_id = 2 WHERE run_type_id IS NULL;
ALTER TABLE "dev_instruction_result" ALTER COLUMN run_type_id SET NOT NULL;
ALTER TABLE "dev_instruction_result" ALTER COLUMN run_type_id SET DEFAULT 2;
ALTER TABLE "dev_instruction_result" ADD CONSTRAINT devinstruction_result_fk_run_type FOREIGN KEY (run_type_id) REFERENCES "run_type" ("id");

ALTER TABLE "dev_execution_log" ADD COLUMN run_type_id bigint;
UPDATE "dev_execution_log" SET run_type_id = 2 WHERE run_type_id IS NULL;
ALTER TABLE "dev_execution_log" ALTER COLUMN run_type_id SET NOT NULL;
ALTER TABLE "dev_execution_log" ALTER COLUMN run_type_id SET DEFAULT 2;
ALTER TABLE "dev_execution_log" ADD CONSTRAINT devexecution_log_fk_run_type FOREIGN KEY (run_type_id) REFERENCES "run_type" ("id");

ALTER TABLE "dev_step_log" ADD COLUMN run_type_id bigint;
UPDATE "dev_step_log" SET run_type_id = 2 WHERE run_type_id IS NULL;
ALTER TABLE "dev_step_log" ALTER COLUMN run_type_id SET NOT NULL;
ALTER TABLE "dev_step_log" ALTER COLUMN run_type_id SET DEFAULT 2;
ALTER TABLE "dev_step_log" ADD CONSTRAINT devstep_log_fk_run_type FOREIGN KEY (run_type_id) REFERENCES "run_type" ("id");

ALTER TABLE "dev_file" ADD COLUMN run_type_id bigint;
UPDATE "dev_file" SET run_type_id = 2 WHERE run_type_id IS NULL;
ALTER TABLE "dev_file" ALTER COLUMN run_type_id SET NOT NULL;
ALTER TABLE "dev_file" ALTER COLUMN run_type_id SET DEFAULT 2;
ALTER TABLE "dev_file" ADD CONSTRAINT devfile_fk_run_type FOREIGN KEY (run_type_id) REFERENCES "run_type" ("id");

ALTER TABLE "dev_step_log" enable TRIGGER "dev_step_log_update_created_at_updated_at";
ALTER TABLE "dev_file" enable TRIGGER "dev_file_update_created_at_updated_at";
ALTER TABLE "dev_execution_log" enable TRIGGER "dev_execution_log_update_created_at_updated_at";

--production
ALTER TABLE "prod_step_log" disable TRIGGER "prod_step_log_update_created_at_updated_at";
ALTER TABLE "prod_file" disable TRIGGER "prod_file_update_created_at_updated_at";
ALTER TABLE "prod_execution_log" disable TRIGGER "prod_execution_log_update_created_at_updated_at";

ALTER TABLE "prod_instruction_result" ADD COLUMN run_type_id bigint;
UPDATE "prod_instruction_result" SET run_type_id = 1 WHERE run_type_id IS NULL;
ALTER TABLE "prod_instruction_result" ALTER COLUMN run_type_id SET NOT NULL;
ALTER TABLE "prod_instruction_result" ALTER COLUMN run_type_id SET DEFAULT 1;
ALTER TABLE "prod_instruction_result" ADD CONSTRAINT prod_instruction_result_fk_run_type FOREIGN KEY (run_type_id) REFERENCES "run_type" ("id");

ALTER TABLE "prod_execution_log" ADD COLUMN run_type_id bigint;
UPDATE "prod_execution_log" SET run_type_id = 1 WHERE run_type_id IS NULL;
ALTER TABLE "prod_execution_log" ALTER COLUMN run_type_id SET NOT NULL;
ALTER TABLE "prod_execution_log" ALTER COLUMN run_type_id SET DEFAULT 1;
ALTER TABLE "prod_execution_log" ADD CONSTRAINT prod_execution_log_fk_run_type FOREIGN KEY (run_type_id) REFERENCES "run_type" ("id");

ALTER TABLE "prod_step_log" ADD COLUMN run_type_id bigint;
UPDATE "prod_step_log" SET run_type_id = 1 WHERE run_type_id IS NULL;
ALTER TABLE "prod_step_log" ALTER COLUMN run_type_id SET NOT NULL;
ALTER TABLE "prod_step_log" ALTER COLUMN run_type_id SET DEFAULT 1;
ALTER TABLE "prod_step_log" ADD CONSTRAINT prod_step_log_fk_run_type FOREIGN KEY (run_type_id) REFERENCES "run_type" ("id");

ALTER TABLE "prod_file" ADD COLUMN run_type_id bigint;
UPDATE "prod_file" SET run_type_id = 1 WHERE run_type_id IS NULL;
ALTER TABLE "prod_file" ALTER COLUMN run_type_id SET NOT NULL;
ALTER TABLE "prod_file" ALTER COLUMN run_type_id SET DEFAULT 1;
ALTER TABLE "prod_file" ADD CONSTRAINT prod_file_fk_run_type FOREIGN KEY (run_type_id) REFERENCES "run_type" ("id");

ALTER TABLE "prod_step_log" enable TRIGGER "prod_step_log_update_created_at_updated_at";
ALTER TABLE "prod_file" enable TRIGGER "prod_file_update_created_at_updated_at";
ALTER TABLE "prod_execution_log" enable TRIGGER "prod_execution_log_update_created_at_updated_at";