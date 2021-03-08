-- create the test case override table
CREATE TABLE test_case_override
    (
        id bigserial NOT NULL,
        name text NOT NULL,
        created_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        updated_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        test_case_id bigint NOT NULL,
        is_deleted BOOLEAN DEFAULT FALSE NOT NULL,
        COMMENT text,
        PRIMARY KEY (id),
        CONSTRAINT test_case_input_fk_test_case FOREIGN KEY (test_case_id) REFERENCES "test_case" ("id")
    );

-- triggers for the test case override table
CREATE TRIGGER test_case_override_insert_create_at_update_at BEFORE INSERT ON "test_case_override" 
FOR EACH ROW EXECUTE PROCEDURE "insert_updated_at_created_at_column"();
CREATE TRIGGER test_case_override_update_created_at_updated_at BEFORE UPDATE ON "test_case_override" 
FOR EACH ROW EXECUTE PROCEDURE "update_created_at_updated_at_column"();

-- create the unique constraint in the instruction
ALTER TABLE "instruction" ADD CONSTRAINT instruction_ix_test_case_element_self UNIQUE ("id","element_id", "test_case_id");

-- create the instructinon override table
CREATE TABLE instruction_override
    (
        id bigserial NOT NULL,
        test_case_override_id bigint NOT NULL,
        test_case_id bigint NOT NULL,
        instruction_id bigint NOT NULL,
        element_id bigint NOT NULL,
        is_deleted BOOLEAN DEFAULT FALSE NOT NULL,
        override_fields text NOT NULL,
        data json DEFAULT '{}'::json NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT instruction_override_fk_test_case_override FOREIGN KEY (test_case_override_id) REFERENCES "test_case_override" ("id"),
        CONSTRAINT instruction_override_fk_test_case FOREIGN KEY (test_case_id) REFERENCES "test_case" ("id"),
        CONSTRAINT instruction_override_fk_instruction FOREIGN KEY (instruction_id) REFERENCES "instruction" ("id"),
        CONSTRAINT instruction_override_fk_element_id FOREIGN KEY (element_id) REFERENCES "element" ("id"),
        CONSTRAINT instruction_override_fk_test_case_instruction_element FOREIGN KEY (test_case_id, instruction_id, element_id) REFERENCES "instruction" ("test_case_id", "id", "element_id"),
        CONSTRAINT instruction_override_ix_test_case_overide UNIQUE (test_case_override_id, test_case_id, instruction_id, element_id)
    );