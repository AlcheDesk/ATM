-- create driver vendor table
CREATE TABLE
    driver_vendor
    (
        id bigserial NOT NULL,
        name text NOT NULL,
        driver_type_id bigint NOT NULL,
        version text,
        PRIMARY KEY (id),
        CONSTRAINT driver_vendor_fk_driver_type FOREIGN KEY (driver_type_id) REFERENCES "driver_type" ("id")
    );
    
ALTER SEQUENCE driver_vendor_id_seq START WITH 10000;
ALTER TABLE "driver_vendor" ADD CONSTRAINT driver_vendor_ix_name_type_version UNIQUE ("name", "driver_type_id", "version");
INSERT INTO driver_vendor (id,name,driver_type_id) VALUES (1,'Firefox',1);
INSERT INTO driver_vendor (id,name,driver_type_id) VALUES (2,'Chrome',1);
INSERT INTO driver_vendor (id,name,driver_type_id) VALUES (3,'IE',1);
INSERT INTO driver_vendor (id,name,driver_type_id) VALUES (4,'Safari',1);
INSERT INTO driver_vendor (id,name,driver_type_id) VALUES (5,'Opera',1);

-- create driver property table
CREATE TABLE
    driver_property
    (
        id bigserial NOT NULL,
        name text NOT NULL,
        driver_vendor_id bigint NOT NULL,
        default_value text NOT NULL,
        default_action text,
        comment text,
        PRIMARY KEY (id),
        CONSTRAINT driver_property_ix_name UNIQUE (name,driver_vendor_id),
        CONSTRAINT driver_property_fk_driver_vendor FOREIGN KEY (driver_vendor_id) REFERENCES "driver_vendor" ("id")
    );
    
ALTER SEQUENCE driver_property_id_seq START WITH 10000;

-- create driver vendor version table
CREATE TABLE
    driver_property_default_value
    (
        id bigserial NOT NULL,
        value text NOT NULL,
        driver_property_id bigint NOT NULL,
        comment text,
        PRIMARY KEY (id),
        CONSTRAINT driver_property_default_value_fk_driver_property FOREIGN KEY (driver_property_id) REFERENCES "driver_property" ("id")
    );

ALTER SEQUENCE driver_property_default_value_id_seq START WITH 10000;
-- create driver property entry table
CREATE TABLE
    driver_property_entry
    (
        id bigserial NOT NULL,
        updated_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        created_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        name text NOT NULL,
        driver_vendor_id bigint NOT NULL,
        value text NOT NULL,
        comment text,
        PRIMARY KEY (id),
        CONSTRAINT driver_property_entry_ix_name UNIQUE (name,driver_vendor_id),
        CONSTRAINT driver_property_entry_fk_driver_vendor FOREIGN KEY (driver_vendor_id) REFERENCES "driver_vendor" ("id")
    );
    
ALTER SEQUENCE driver_property_entry_id_seq START WITH 10000;

CREATE TRIGGER "driver_property_entry_update_created_at_updated_at"
  BEFORE UPDATE ON driver_property_entry
  FOR EACH ROW
EXECUTE PROCEDURE update_created_at_updated_at_column();


-- add driver version
ALTER TABLE "driver" RENAME COLUMN "default_flag" TO is_default;
ALTER TABLE "driver" ALTER COLUMN is_default SET NOT NULL;

ALTER TABLE "driver" ADD COLUMN vendor text;
UPDATE driver SET vendor = 'Firefox' WHERE name LIKE 'Firefox';
UPDATE driver SET vendor = 'Chrome' WHERE name LIKE 'Chrome';
ALTER TABLE "driver" ADD COLUMN version text;
ALTER TABLE "driver" ALTER COLUMN vendor SET NOT NULL;
ALTER TABLE "driver" ADD CONSTRAINT driver_ix_name_vendor_version UNIQUE ("name", "vendor", "version");
ALTER TABLE "driver" ADD CONSTRAINT driver_fk_vendor_type_version FOREIGN KEY 
(vendor, driver_type_id, version) REFERENCES "driver_vendor" ("name", "driver_type_id", "version");

ALTER TABLE "driver_entry" ADD COLUMN vendor text;
UPDATE driver_entry SET vendor = 'Firefox' WHERE name LIKE 'Firefox';
UPDATE driver_entry SET vendor = 'Chrome' WHERE name LIKE 'Chrome';
ALTER TABLE "driver_entry" ADD COLUMN version text;
ALTER TABLE "driver_entry" ALTER COLUMN vendor SET NOT NULL;
ALTER TABLE "driver_entry" ADD CONSTRAINT driver_entry_ix_name_vendor_version UNIQUE ("name", "vendor", "version");
ALTER TABLE "driver_entry" ADD CONSTRAINT driver_entry_fk_vendor_type_version FOREIGN KEY 
(vendor, driver_type_id, version) REFERENCES "driver_vendor" ("name", "driver_type_id", "version");
ALTER TABLE "driver_entry" ADD CONSTRAINT driver_entry_ix_name_vendor_version_run UNIQUE ("name", vendor, version, run_id);
ALTER TABLE "driver_entry" ADD CONSTRAINT driver_entry_ix_name_vendor_version_run_set UNIQUE ("name", vendor, version, run_set_id);