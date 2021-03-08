UPDATE "file" SET uuid = uuid_generate_v4() WHERE uuid IS NULL;
ALTER TABLE "file" ALTER COLUMN uuid SET DEFAULT uuid_generate_v4();