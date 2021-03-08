ALTER TABLE "step_option_entry" RENAME CONSTRAINT "stepoptionentry_fk_name" TO step_option_entry_fk_name;
ALTER TABLE "step_option_entry" RENAME CONSTRAINT "stepoptionentry_fk_instruction" TO step_option_entry_fk_instruction;
ALTER TABLE "step_option" ADD COLUMN COMMENT text;

ALTER TABLE "test_case_option" ADD COLUMN with_value BOOLEAN;
UPDATE "test_case_option" SET with_value = false WHERE with_value IS NULL;
ALTER TABLE "test_case_option" ALTER COLUMN with_value SET NOT NULL;
ALTER TABLE "test_case_option" ALTER COLUMN with_value SET DEFAULT false;
ALTER TABLE "test_case_option" ADD CONSTRAINT test_case_option_uk_name UNIQUE ("name");
DELETE FROM "test_case_option" WHERE name IS NULL;
ALTER TABLE "test_case_option" ALTER COLUMN name SET NOT NULL;

CREATE TABLE test_case_option_entry
    (
        id bigserial NOT NULL,
        name text NOT NULL,
        value text,
        with_value BOOLEAN DEFAULT false NOT NULL,
        test_case_id bigint NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT test_case_option_entry_fk_name FOREIGN KEY (name) REFERENCES "test_case_option" ("name"),
        CONSTRAINT test_case_option_entry_fk_test_case FOREIGN KEY (test_case_id) REFERENCES "test_case" ("id")
    );
    
DROP TABLE "instruction_test_case_option_link" CASCADE;