ALTER TABLE "driver_driver_property_link" DROP CONSTRAINT IF EXISTS "driver_driver_property_fk_driver_property";

ALTER TABLE "driver_driver_property_link"
    ADD CONSTRAINT driver_driver_property_fk_driver_property FOREIGN KEY ("driver_property_id")
    REFERENCES "driver_property" ("id");