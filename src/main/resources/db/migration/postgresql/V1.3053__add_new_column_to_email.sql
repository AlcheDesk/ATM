CREATE TABLE email_type
(
    id bigint NOT NULL,
    name text NOT NULL,
    is_active boolean NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE email_type ADD CONSTRAINT email_type_ix_name UNIQUE (name);
INSERT INTO email_type (id, name, is_active) VALUES (1, 'ATM', true);

ALTER TABLE email ADD COLUMN email_type_id bigint NOT NULL DEFAULT 1;
ALTER TABLE email ADD CONSTRAINT email_fk_type FOREIGN KEY (email_type_id) REFERENCES email_type (id);