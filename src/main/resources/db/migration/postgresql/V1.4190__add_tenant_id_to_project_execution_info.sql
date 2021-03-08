ALTER TABLE "project_execution_info" ADD COLUMN tenant_id bigint;
UPDATE "project_execution_info" SET
tenant_id = i.ti
FROM (select id as pi, tenant_id as ti FROM project) i
WHERE i.pi = project_id;
ALTER TABLE "project_execution_info" ALTER COLUMN tenant_id SET NOT NULL;
ALTER TABLE "project_execution_info" ALTER COLUMN tenant_id SET DEFAULT 1000;