ALTER TABLE "run_set_test_case_link" ALTER COLUMN tenant_id SET DEFAULT 1000;

ALTER TABLE "driver_pack_driver_link" ADD COLUMN tenant_id bigint;
UPDATE "driver_pack_driver_link" SET tenant_id = 1000 WHERE tenant_id IS NULL;
ALTER TABLE "driver_pack_driver_link" ALTER COLUMN tenant_id SET NOT NULL;
ALTER TABLE "driver_pack_driver_link" ALTER COLUMN tenant_id SET DEFAULT 1000;

ALTER TABLE "test_case_task_link" ADD COLUMN tenant_id bigint;
UPDATE "test_case_task_link" SET tenant_id = 1000 WHERE tenant_id IS NULL;
ALTER TABLE "test_case_task_link" ALTER COLUMN tenant_id SET NOT NULL;
ALTER TABLE "test_case_task_link" ALTER COLUMN tenant_id SET DEFAULT 1000;

ALTER TABLE "run_task_link" ADD COLUMN tenant_id bigint;
UPDATE "run_task_link" SET tenant_id = 1000 WHERE tenant_id IS NULL;
ALTER TABLE "run_task_link" ALTER COLUMN tenant_id SET NOT NULL;
ALTER TABLE "run_task_link" ALTER COLUMN tenant_id SET DEFAULT 1000;

ALTER TABLE "run_set_result_job_link" ADD COLUMN tenant_id bigint;
UPDATE "run_set_result_job_link" SET tenant_id = 1000 WHERE tenant_id IS NULL;
ALTER TABLE "run_set_result_job_link" ALTER COLUMN tenant_id SET NOT NULL;
ALTER TABLE "run_set_result_job_link" ALTER COLUMN tenant_id SET DEFAULT 1000;

ALTER TABLE "run_set_job_link" ADD COLUMN tenant_id bigint;
UPDATE "run_set_job_link" SET tenant_id = 1000 WHERE tenant_id IS NULL;
ALTER TABLE "run_set_job_link" ALTER COLUMN tenant_id SET NOT NULL;
ALTER TABLE "run_set_job_link" ALTER COLUMN tenant_id SET DEFAULT 1000;