ALTER TABLE "driver" ADD COLUMN execution_count bigint;
UPDATE "driver" SET execution_count = 0 WHERE execution_count IS NULL;
ALTER TABLE "driver" ALTER COLUMN execution_count SET NOT NULL;
ALTER TABLE "driver" ALTER COLUMN execution_count SET DEFAULT 0;

ALTER TABLE "driver_pack" RENAME COLUMN "execution_number" TO execution_count;