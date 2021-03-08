-- create the instruction type instruction option table
CREATE TABLE instruction_type_instruction_option_link
    (
        id bigserial NOT NULL,
        instruction_type_id bigint NOT NULL,
        instruction_option_id bigint NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT instruction_type_instruction_option_link_ix_ins_type_ins_options UNIQUE
        (instruction_type_id, instruction_option_id),
        CONSTRAINT instruction_type_instruction_option_link_fk_instruction_type FOREIGN KEY
        (instruction_type_id) REFERENCES "instruction_type" ("id"),
        CONSTRAINT instruction_type_instruction_option_link_fk_instruction_option FOREIGN KEY
        (instruction_option_id) REFERENCES "instruction_option" ("id")
    );