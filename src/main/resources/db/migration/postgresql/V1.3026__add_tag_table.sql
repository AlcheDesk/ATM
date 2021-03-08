CREATE TABLE tag
    (
        id bigserial,
        name text NOT NULL,
        COMMENT text,
        updated_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        created_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        is_deleted BOOLEAN DEFAULT false NOT NULL,
        PRIMARY KEY (id)
    );

CREATE TABLE test_case_tag_link
    (
        id bigserial,
        test_case_id bigint,
        tag_id bigint,
        PRIMARY KEY (id),
        CONSTRAINT test_case_tag_link_fk_test_case FOREIGN KEY (test_case_id) REFERENCES "test_case" ("id"),
        CONSTRAINT test_case_tag_link_fk_module FOREIGN KEY (tag_id) REFERENCES "tag" ("id"),
        CONSTRAINT test_case_module_link_ix_tag_module UNIQUE (test_case_id, tag_id)
    );