ALTER TABLE "driver_type_element_type_link" ADD CONSTRAINT driver_type_element_type_link_ix_element_type UNIQUE ("element_type_id");

ALTER TABLE "element" ADD COLUMN driver_type_id bigint;
UPDATE "element" ele SET driver_type_id = (SELECT link.driver_type_id FROM driver_type_element_type_link link WHERE link.element_type_id = ele.element_type_id);
ALTER TABLE "element" ALTER COLUMN driver_type_id SET NOT NULL;
ALTER TABLE "element" DROP CONSTRAINT "element_fk_element_type";
ALTER TABLE "element" ADD CONSTRAINT element_fk_driver_type_element_type FOREIGN KEY ("element_type_id", driver_type_id) REFERENCES "driver_type_element_type_link" ("element_type_id", "driver_type_id");