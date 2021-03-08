CREATE TABLE source_type
(
    id bigserial,
    name text NOT NULL,
    is_predefined boolean DEFAULT false NOT NULL,
    is_active boolean DEFAULT true NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO source_type(id, name, is_predefined, is_active) VALUES (1, 'ATM', true, true);
INSERT INTO source_type(id, name, is_predefined, is_active) VALUES (2, 'Jenkins', true, true);

ALTER TABLE run_set_result ADD COLUMN source_type_id bigint NOT NULL DEFAULT 1;
UPDATE run_set_result SET source_type_id=2 WHERE from_jenkins=true;

ALTER TABLE run_set_result
    ADD CONSTRAINT run_set_result_fk_source_type FOREIGN KEY (source_type_id)
    REFERENCES source_type (id);

ALTER TABLE run_set_result DROP COLUMN from_jenkins;