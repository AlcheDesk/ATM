ALTER TABLE "test_case" ADD COLUMN uuid uuid;
UPDATE "test_case" SET uuid = uuid_generate_v4() WHERE uuid IS NULL;
ALTER TABLE "test_case" ALTER COLUMN uuid SET NOT NULL;
ALTER TABLE "test_case" ALTER COLUMN uuid SET DEFAULT uuid_generate_v4();
