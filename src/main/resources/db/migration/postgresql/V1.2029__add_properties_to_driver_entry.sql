ALTER TABLE "driver_entry" ADD COLUMN properties json;
UPDATE "driver_entry" SET properties = '{}'::json WHERE properties IS NULL;
ALTER TABLE "driver_entry" ALTER COLUMN properties SET NOT NULL;