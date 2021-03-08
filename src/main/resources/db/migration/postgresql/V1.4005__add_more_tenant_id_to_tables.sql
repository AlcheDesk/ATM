ALTER TABLE "driver_pack" ADD COLUMN tenant_id bigint;
ALTER TABLE "instruction_bundle_entry" ADD COLUMN tenant_id bigint;
ALTER TABLE "prod_step_log" ADD COLUMN tenant_id bigint;
ALTER TABLE "test_case_option_entry" ADD COLUMN tenant_id bigint;

UPDATE "driver_pack" SET tenant_id = 1 WHERE tenant_id IS NULL;
UPDATE "instruction_bundle_entry" SET tenant_id = 1 WHERE tenant_id IS NULL;
UPDATE "prod_step_log" SET tenant_id = 1 WHERE tenant_id IS NULL;
UPDATE "test_case_option_entry" SET tenant_id = 1 WHERE tenant_id IS NULL;

ALTER TABLE "driver_pack" ALTER COLUMN tenant_id SET NOT NULL;
ALTER TABLE "instruction_bundle_entry" ALTER COLUMN tenant_id SET NOT NULL;
ALTER TABLE "prod_step_log" ALTER COLUMN tenant_id SET NOT NULL;
ALTER TABLE "test_case_option_entry" ALTER COLUMN tenant_id SET NOT NULL;