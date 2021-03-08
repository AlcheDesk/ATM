ALTER TABLE "driver_property" ADD COLUMN active BOOLEAN;
UPDATE "driver_property" SET active = true WHERE active IS NULL;
ALTER TABLE "driver_property" ALTER COLUMN active SET NOT NULL;