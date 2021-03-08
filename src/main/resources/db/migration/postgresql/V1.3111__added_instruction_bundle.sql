CREATE TABLE instruction_bundle
    (
        id bigserial NOT NULL,
        name text NOT NULL,
        created_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        updated_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        comment text NOT NULL,
        PRIMARY KEY (id)
    );
    
CREATE TRIGGER instruction_bundle_insert_created_at BEFORE INSERT ON "instruction_bundle" 
FOR EACH ROW EXECUTE PROCEDURE "insert_created_at_column"();
CREATE TRIGGER instruction_bundle_update_created_at BEFORE UPDATE ON "instruction_bundle" 
FOR EACH ROW EXECUTE PROCEDURE "update_created_at_column"();

CREATE TABLE instruction_bundle_entry
    (
        id bigserial NOT NULL,
        comment text NOT NULL,
        instruction_bundle_id bigint,
        PRIMARY KEY (id),
        CONSTRAINT instruction_bundle_entry_fk_instruction_bundle FOREIGN KEY (instruction_bundle_id) REFERENCES "instruction_bundle" ("id")
    );