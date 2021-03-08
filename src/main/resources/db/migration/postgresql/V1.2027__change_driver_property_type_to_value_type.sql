ALTER TABLE "driver_property" RENAME COLUMN "type" TO value_type;
ALTER TABLE "driver_property_entry" RENAME COLUMN "type" TO value_type;
ALTER TABLE "driver_property_entry" RENAME COLUMN "driver_id" TO driver_entry_id;
ALTER TABLE "driver_property_entry" DROP COLUMN "driver_vendor_id";