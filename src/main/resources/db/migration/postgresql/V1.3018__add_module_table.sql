CREATE TABLE module
    (
        id bigserial,
        name text NOT NULL,
        version text NOT NULL,
        COMMENT text,
        updated_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        created_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        log text,
        is_deleted BOOLEAN DEFAULT false NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT module_ix_name_version UNIQUE (name, version)
    );

CREATE TRIGGER module_insert_create_at_update_at BEFORE INSERT ON "module" 
FOR EACH ROW EXECUTE PROCEDURE "insert_updated_at_created_at_column"();
CREATE TRIGGER module_update_created_at_updated_at BEFORE UPDATE ON "module" 
FOR EACH ROW EXECUTE PROCEDURE "update_created_at_updated_at_column"();
    
CREATE TABLE test_case_module_link
    (
        id bigserial,
        test_case_id bigint,
        module_id bigint,
        PRIMARY KEY (id),
        CONSTRAINT test_case_module_link_fk_test_case FOREIGN KEY (test_case_id) REFERENCES "test_case" ("id"),
        CONSTRAINT test_case_module_link_fk_module FOREIGN KEY (module_id) REFERENCES "module" ("id"),
        CONSTRAINT test_case_module_link_ix_test_case_module UNIQUE (test_case_id, module_id)
    );