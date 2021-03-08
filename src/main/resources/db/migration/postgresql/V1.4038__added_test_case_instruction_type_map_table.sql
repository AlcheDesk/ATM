CREATE TABLE test_case_instruction_type_map
    (
        id bigserial NOT NULL,
        test_case_id bigint NOT NULL,
        instruction_types text[] NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT test_case_instruction_type_map_fk_test_case FOREIGN KEY (test_case_id)
        REFERENCES "test_case" ("id"),
        CONSTRAINT test_case_instruction_type_map_ix_test_case UNIQUE (test_case_id)
    );