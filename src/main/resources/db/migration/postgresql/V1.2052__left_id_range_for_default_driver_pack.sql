-- drop constraints
ALTER TABLE "driver_pack_driver_link" DROP CONSTRAINT "driver_pack_driver_link_fk_driver_pack";
ALTER TABLE "driver_pack" DROP CONSTRAINT "driver_pack_pkey";

-- increase id
UPDATE driver_pack SET id = id + 999;
UPDATE driver_pack_driver_link SET driver_pack_id = driver_pack_id + 999;

-- increase sequence
SELECT setval(pg_get_serial_sequence('driver_pack', 'id'), max(id)) FROM driver_pack;

-- recreate constraints
ALTER TABLE driver_pack ADD PRIMARY KEY (id);
ALTER TABLE ONLY driver_pack_driver_link ADD CONSTRAINT driver_pack_driver_link_fk_driver_pack FOREIGN KEY (driver_pack_id) REFERENCES driver_pack(id);