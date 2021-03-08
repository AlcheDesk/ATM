ALTER TABLE "test_case_execution_info" ADD COLUMN latest_run_created_at TIMESTAMP WITH TIME zone;
ALTER TABLE "test_case_execution_info" ADD COLUMN latest_run_id bigint;
ALTER TABLE "test_case_execution_info" RENAME COLUMN "latest_run_update_at" TO latest_run_updated_at;
ALTER TABLE "test_case_execution_info" ADD CONSTRAINT test_case_execution_info_fk_latest_run_id FOREIGN KEY (latest_run_id) REFERENCES "run" ("id");