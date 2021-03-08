DELETE FROM step_option WHERE name = 'IGNORE';
INSERT INTO step_option (name,with_value) VALUES ('IGNORE',false) ON CONFLICT DO NOTHING;

ALTER TABLE "step_option" ADD CONSTRAINT stepoption_uk_name UNIQUE ("name");

CREATE TABLE step_option_entry
    (
        id bigserial NOT NULL,
        name text NOT NULL,
        value text,
        with_value BOOLEAN DEFAULT false NOT NULL,
        instruction_id bigint NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT stepoptionentry_fk_name FOREIGN KEY (name) REFERENCES "step_option" ("name"),
        CONSTRAINT stepoptionentry_fk_instruction FOREIGN KEY (instruction_id) REFERENCES "instruction" ("id")
    );
    
DROP TABLE "instruction_step_option_link" CASCADE;