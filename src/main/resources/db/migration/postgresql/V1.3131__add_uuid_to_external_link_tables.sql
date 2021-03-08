ALTER TABLE "run" ADD CONSTRAINT run_ix_id_uuid UNIQUE ("id", "uuid");
ALTER TABLE "run_set" ADD CONSTRAINT run_set_ix_id_uuid UNIQUE ("id", "uuid");
ALTER TABLE "run_set_result" ADD CONSTRAINT run_set_result_ix_id_uuid UNIQUE ("id", "uuid");

ALTER TABLE "test_case_task_link" ADD COLUMN test_case_uuid uuid;
UPDATE "test_case_task_link" SET test_case_uuid = tc.uuid FROM (SELECT * FROM test_case) tc WHERE tc.id = test_case_id;
ALTER TABLE "test_case_task_link" DROP CONSTRAINT "test_case_task_fk_test_case";
ALTER TABLE "test_case_task_link" ADD CONSTRAINT test_case_task_link_fk_test_case FOREIGN KEY ("test_case_id", "test_case_uuid") REFERENCES "test_case" ("id", "uuid");

ALTER TABLE "run_task_link" ADD COLUMN run_uuid uuid;
UPDATE "run_task_link" SET run_uuid = sel.uuid FROM (SELECT * FROM run) sel WHERE sel.id = run_id;
ALTER TABLE "run_task_link" DROP CONSTRAINT "run_task_link_fk_run_set";
ALTER TABLE "run_task_link" ADD CONSTRAINT run_task_link_fk_run FOREIGN KEY ("run_id", "run_uuid") REFERENCES "run" ("id", "uuid");

ALTER TABLE "run_set_job_link" ADD COLUMN run_set_uuid uuid;
UPDATE "run_set_job_link" SET run_set_uuid = tc.uuid FROM (SELECT * FROM run_set) tc WHERE tc.id = run_set_id;
ALTER TABLE "run_set_job_link" DROP CONSTRAINT "run_set_job_link_fk_run_set";
ALTER TABLE "run_set_job_link" ADD CONSTRAINT run_set_job_link_fk_run_set FOREIGN KEY ("run_set_id", "run_set_uuid") REFERENCES "run_set" ("id", "uuid");

ALTER TABLE "run_set_result_job_link" ADD COLUMN run_set_result_uuid uuid;
UPDATE "run_set_result_job_link" SET run_set_result_uuid = tc.uuid FROM (SELECT * FROM run_set_result) tc WHERE tc.id = run_set_result_id;
ALTER TABLE "run_set_result_job_link" DROP CONSTRAINT "run_set_result_job_link_fk_run_set";
ALTER TABLE "run_set_result_job_link" ADD CONSTRAINT run_set_result_job_link_fk_run_set_result FOREIGN KEY ("run_set_result_id", "run_set_result_uuid") REFERENCES "run_set_result" ("id", "uuid");
