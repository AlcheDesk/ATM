DROP TABLE "module_module_property_link";
DROP TABLE "module";

ALTER SEQUENCE "engine_id_seq" RENAME TO driver_id_seq;
ALTER TABLE "engine" RENAME TO driver;

ALTER SEQUENCE "run_module_property_id_seq" RENAME TO driver_property_id_seq;
ALTER TABLE "module_property" RENAME TO driver_property;

--drop constraints and rename the columns
ALTER TABLE "engine_module_property_link" DROP CONSTRAINT IF EXISTS "engine_fkey";
ALTER TABLE "engine_module_property_link" DROP CONSTRAINT IF EXISTS "module_property_fkey";
ALTER TABLE "engine_module_property_link" RENAME COLUMN "module_property_id" TO driver_property_id;
ALTER TABLE "engine_module_property_link" RENAME COLUMN "engine_id" TO driver_id;
--rename the sequence and the database
ALTER SEQUENCE "engine_module_property_link_id_seq" RENAME TO driver_driver_property_link_id_seq;
ALTER TABLE "engine_module_property_link" RENAME TO driver_driver_property_link;
--add the constraints
ALTER TABLE "driver_driver_property_link" ADD CONSTRAINT driver_driver_property_fk_driver FOREIGN KEY (driver_id) REFERENCES "driver" ("id");
ALTER TABLE "driver_driver_property_link" ADD CONSTRAINT driver_driver_property_fk_driver_property FOREIGN KEY (driver_id) REFERENCES "driver" ("id");
