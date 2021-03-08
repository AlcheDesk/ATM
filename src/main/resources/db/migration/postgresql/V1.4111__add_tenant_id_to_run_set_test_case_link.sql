ALTER TABLE "run_set_test_case_link" ADD COLUMN tenant_id bigint;
UPDATE "run_set_test_case_link" SET tenant_id = 1000 WHERE tenant_id IS NULL;
ALTER TABLE "run_set_test_case_link" ALTER COLUMN tenant_id SET NOT NULL;