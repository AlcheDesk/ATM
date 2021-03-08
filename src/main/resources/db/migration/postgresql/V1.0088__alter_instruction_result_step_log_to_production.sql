--run type table
CREATE TABLE run_type
    (
        id bigserial NOT NULL,
        name text NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT run_type_uk_name UNIQUE (name)
    );

INSERT INTO run_type (id,name) VALUES (1,'PRODUCTION') ON CONFLICT DO NOTHING;
INSERT INTO run_type (id,name) VALUES (2,'DEVELOPMENT') ON CONFLICT DO NOTHING;

--update run table
ALTER TABLE "run" disable TRIGGER "update_run_created_at_updated_at";
ALTER TABLE "run" ADD COLUMN run_type_id bigint;
UPDATE "run" SET run_type_id = 1 WHERE run_type_id IS NULL;
ALTER TABLE "run" ADD CONSTRAINT run_set_type_fk_type FOREIGN KEY (run_type_id) REFERENCES "run_type" ("id");
ALTER TABLE "run" ALTER COLUMN run_type_id SET NOT NULL;
ALTER TABLE "run" enable TRIGGER "update_run_created_at_updated_at";
-- rename current isntruction result table
ALTER TABLE "instruction_result" RENAME CONSTRAINT "instruction_result_pkey" TO prod_instruction_result_pkey;
ALTER TABLE "instruction_result" RENAME CONSTRAINT "instruction_result_fk_run" TO prod_instruction_result_fk_run;
ALTER TABLE "instruction_result" RENAME CONSTRAINT "instruction_result_fkey_status" TO prod_instruction_result_fk_status;
ALTER TABLE "instruction_result" RENAME TO prod_instruction_result;
ALTER TRIGGER "finished_status_update" ON "prod_instruction_result" RENAME TO prod_instruction_result_finished_status_update;
ALTER TRIGGER "trigger_run_status_update" ON "prod_instruction_result" RENAME TO prod_instruction_result_trigger_run_status_update;
ALTER TRIGGER "update_instruction_result_created_at_updated_at" ON "prod_instruction_result" RENAME TO prod_instruction_result_update_created_at_updated_at;
ALTER TRIGGER "update_run_status" ON "prod_instruction_result" RENAME TO prod_instruction_result_update_run_status;

-- rename current step log table
ALTER TABLE "step_log" RENAME CONSTRAINT "step_log_pkey" TO prod_step_log_pkey;
ALTER TABLE "step_log" RENAME CONSTRAINT "step_log_fk_instruction_result" TO prod_step_log_fk_prod_instruction_result;
ALTER TABLE "step_log" RENAME CONSTRAINT "step_log_fk_type" TO prod_step_log_fk_type;
ALTER TABLE "step_log" RENAME TO prod_step_log;
ALTER TRIGGER "trigger_instruction_result_status_update" ON "prod_step_log" RENAME TO prod_step_log_trigger_prod_instruction_result_status_update;
ALTER TRIGGER "update_step_log_created_at_updated_at" ON "prod_step_log" RENAME TO prod_step_log_update_step_log_created_at_updated_at;


-- rename current execution log table
ALTER TABLE "execution_log" RENAME CONSTRAINT "execution_log_pkey" TO prod_execution_log_pkey;
ALTER TABLE "execution_log" RENAME CONSTRAINT "execution_log_fk_run" TO prod_execution_log_fk_run;
ALTER TABLE "execution_log" RENAME CONSTRAINT "execution_log_fk_log_level" TO prod_execution_log_fk_log_level;
ALTER TABLE "execution_log" RENAME TO prod_execution_log;
ALTER TRIGGER "update_execution_log_created_at_updated_at" ON "prod_execution_log" RENAME TO prod_execution_log_update_execution_log_created_at_updated_at;

-- rename current file table
ALTER TABLE "file" RENAME CONSTRAINT "file_pkey" TO prod_file_pkey;
ALTER TABLE "file" RENAME CONSTRAINT "file_fk_instruction_result" TO prod_file_fk_prod_instruction_result;
ALTER TABLE "file" RENAME CONSTRAINT "file_fk_type" TO prod_file_fk_type;
ALTER TABLE "file" RENAME TO prod_file;
ALTER TRIGGER "update_file_created_at_updated_at" ON "prod_file" RENAME TO prod_file_update_execution_log_created_at_updated_at;
