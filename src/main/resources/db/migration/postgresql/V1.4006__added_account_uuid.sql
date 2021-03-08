ALTER TABLE "tenant" ADD COLUMN account_uuid uuid;
UPDATE "tenant" SET account_uuid = '00000000-0000-0000-0000-000000000000' WHERE account_uuid IS NULL;
ALTER TABLE "tenant" ALTER COLUMN account_uuid SET NOT NULL;