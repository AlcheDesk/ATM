ALTER TABLE "driver_vendor" ADD COLUMN active boolean;
UPDATE "driver_vendor" SET active = true WHERE active IS NULL;
ALTER TABLE "driver_vendor" ALTER COLUMN active SET NOT NULL;

UPDATE "driver_vendor" SET active = false WHERE id > 14 AND id < 30;