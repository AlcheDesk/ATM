ALTER TABLE "driver" ADD COLUMN is_deleted BOOLEAN;
UPDATE "driver" SET is_deleted = false WHERE is_deleted IS NULL;
ALTER TABLE "driver" ALTER COLUMN is_deleted SET NOT NULL;
ALTER TABLE "driver" ALTER COLUMN is_deleted SET DEFAULT false;