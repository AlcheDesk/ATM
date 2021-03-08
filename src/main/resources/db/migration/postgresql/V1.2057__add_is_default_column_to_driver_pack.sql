--##########driver_pack
-- drop constraints
ALTER TABLE "driver_pack_driver_link" DROP CONSTRAINT "driver_pack_driver_link_fk_driver_pack";
ALTER TABLE "driver_pack" DROP CONSTRAINT "driver_pack_pkey";

-- increase id
UPDATE driver_pack SET id = id + 999 WHERE id < 1000;
UPDATE driver_pack_driver_link SET driver_pack_id = driver_pack_id + 999 WHERE driver_pack_id < 1000;

-- increase sequence
SELECT setval(pg_get_serial_sequence('driver_pack', 'id'), max(id)) FROM driver_pack;

-- recreate constraints
ALTER TABLE "driver_pack" ADD PRIMARY KEY ("id");
ALTER TABLE ONLY driver_pack_driver_link ADD CONSTRAINT driver_pack_driver_link_fk_driver_pack FOREIGN KEY (driver_pack_id) REFERENCES driver_pack (id);


-- add default column to the driver pack table
ALTER TABLE "driver_pack" ADD COLUMN is_default BOOLEAN;
UPDATE "driver_pack" SET is_default = false WHERE is_default IS NULL;
ALTER TABLE "driver_pack" ALTER COLUMN is_default SET NOT NULL;
ALTER TABLE "driver_pack" ALTER COLUMN is_default SET DEFAULT false;
INSERT INTO "driver_pack" (id, name, comment, is_deleted, is_default) VALUES (1, 'Default', 'Default driver pack by system.', false, true);

-- add check to check driver has only one default for each driver type
CREATE UNIQUE INDEX driver_ix_default_driver_type ON "driver" (driver_type_id) WHERE (is_default IS TRUE);
ALTER TABLE "driver" RENAME CONSTRAINT "driver_uk_name" TO driver_ix_name;

-- add the default storage driver
INSERT INTO "driver" (id, name, comment, is_active, is_default, driver_type_id, vendor_name, version, is_predefined) VALUES 
                     (3,'Samba Share Drive','Default storage settings', true, true, (SELECT id FROM driver_type WHERE name = 'Storage'), 'Samba', null, true);
                  
-- link the default driver pack with the default driver
-- for now, this will be done in the java code.