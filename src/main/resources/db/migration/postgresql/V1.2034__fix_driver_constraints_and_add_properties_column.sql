ALTER TABLE "driver" DROP CONSTRAINT "driver_ix_type_and_default";

ALTER TABLE "driver" ADD COLUMN properties json;
UPDATE "driver" SET properties = '{}'::json WHERE properties IS NULL;
ALTER TABLE "driver" ALTER COLUMN properties SET NOT NULL;
ALTER TABLE "driver" ALTER COLUMN properties SET DEFAULT '{}'::json;