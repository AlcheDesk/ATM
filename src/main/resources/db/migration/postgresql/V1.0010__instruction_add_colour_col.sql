ALTER TABLE "instruction" ADD COLUMN color_id INTEGER;
ALTER TABLE "instruction" ALTER COLUMN color_id SET DEFAULT NULL;

CREATE TABLE instruction_type
    (
        id bigserial,
        PRIMARY KEY (id),
        name text,
        CONSTRAINT instructiontype_uk_name UNIQUE (name)
    );
    
ALTER TABLE "instruction" ADD COLUMN instruction_type_id bigint;
ALTER TABLE "instruction" ALTER COLUMN instruction_type_id SET DEFAULT NULL;
ALTER TABLE "instruction" ADD CONSTRAINT instruction_fk_type FOREIGN KEY (instruction_type_id) REFERENCES "instruction_type" ("id");

INSERT INTO instruction_type (name) VALUES ('FUNCTIONAL') ON CONFLICT DO NOTHING;
INSERT INTO instruction_type (name) VALUES ('PERFORMANCE') ON CONFLICT DO NOTHING;
INSERT INTO instruction_type (name) VALUES ('API') ON CONFLICT DO NOTHING;
INSERT INTO instruction_type (name) VALUES ('MANUAL') ON CONFLICT DO NOTHING;
INSERT INTO instruction_type (name) VALUES ('APP') ON CONFLICT DO NOTHING;