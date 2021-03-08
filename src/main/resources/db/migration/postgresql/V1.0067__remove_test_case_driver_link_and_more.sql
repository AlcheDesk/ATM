DROP TABLE "test_case_engine_link";

CREATE TABLE storage_entry
    (
        url text,
        test_case_id bigint NOT NULL,
        parameter json,
        name bigserial NOT NULL,
        id bigserial NOT NULL,
        CONSTRAINT storage_entry_fk_test_case FOREIGN KEY (test_case_id) REFERENCES "test_case" ("id")
    );
    