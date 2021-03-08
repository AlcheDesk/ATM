ALTER TABLE "driver_property_entry" ADD COLUMN driver_id bigint;
ALTER TABLE "driver_property_entry" ALTER COLUMN driver_id SET NOT NULL;
ALTER TABLE "driver_property_entry" ADD CONSTRAINT driver_property_entry_fk_driver FOREIGN KEY (driver_id) REFERENCES "driver_entry" ("id");

UPDATE driver_property SET default_action = 'required' WHERE name = 'dataSourceClassName';
UPDATE driver_property SET default_action = 'required' WHERE name = 'jdbcUrl';
UPDATE driver_property SET default_action = 'required' WHERE name = 'username';
UPDATE driver_property SET default_action = 'required' WHERE name = 'password';
