ALTER TABLE "driver_driver_property_link" DROP CONSTRAINT IF EXISTS "driver_driver_property_fk_driver_property";
ALTER TABLE "driver_driver_property_link" ADD CONSTRAINT driver_driver_property_fk_driver_property FOREIGN KEY (driver_id) REFERENCES "driver_property" ("id");
ALTER TABLE "instruction_option" RENAME CONSTRAINT "stepoption_uk_name" TO instruction_option_uk_name;
    
CREATE TABLE driver_entry
    (
        id bigserial NOT NULL,
        name text NOT NULL,
        comment text,
        proerties jsonb,
        test_case_id bigint NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT driver_entry_fk_test_case FOREIGN KEY (test_case_id) REFERENCES "test_case" ("id")
    );
    