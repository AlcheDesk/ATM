ALTER TABLE "test_case_execution_info" ADD COLUMN tenant_id bigint;
UPDATE "test_case_execution_info" SET tenant_id = 1 WHERE tenant_id IS NULL;
ALTER TABLE "test_case_execution_info" ALTER COLUMN tenant_id SET NOT NULL;

DELETE FROM "multi_tenancy_ignorance" WHERE table_name = 'test_case_execution_info';