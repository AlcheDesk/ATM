-- drop driver proeprty link
DROP TABLE "driver_driver_property_link";
-- drop driver property
DROP TABLE "driver_property";

-- create driver_type table
CREATE TABLE driver_type
(
    id bigserial NOT NULL,
    name text NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT driver_type_ix_name UNIQUE (name)
);


INSERT INTO driver_type (id,name) VALUES (1,'Browser') ON CONFLICT DO NOTHING;

-- driver modify
ALTER TABLE "driver" DROP COLUMN "log";
ALTER TABLE "driver" DROP COLUMN "owner_id";
ALTER TABLE "driver" ADD COLUMN driver_type_id bigint;
UPDATE "driver" SET "driver_type_id" = 1 WHERE driver_type_id IS NULL;
ALTER TABLE "driver" ALTER COLUMN driver_type_id SET NOT NULL;
ALTER TABLE "driver" ADD CONSTRAINT driver_fk_type FOREIGN KEY ("driver_type_id") REFERENCES "driver_type" ("id");
ALTER TABLE "driver" ADD COLUMN parameter json;

-- modify driver entry
ALTER TABLE "driver_entry" DROP COLUMN "proerties";
ALTER TABLE "driver_entry" ADD COLUMN parameter json;
ALTER TABLE "driver_entry" ADD COLUMN driver_type_id bigint;
UPDATE "driver_entry" SET "driver_type_id" = 1 WHERE driver_type_id IS NULL;
ALTER TABLE "driver_entry" ALTER COLUMN driver_type_id SET NOT NULL;
ALTER TABLE "driver_entry" ADD CONSTRAINT driver_entry_fk_type FOREIGN KEY ("driver_type_id") REFERENCES "driver_type" ("id");
