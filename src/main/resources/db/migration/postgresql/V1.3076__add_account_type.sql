-- create account type table
CREATE TABLE account_type
(
    id bigserial,
    name text NOT NULL,
    is_predefined boolean NOT NULL,
    is_active boolean NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE account_type ALTER COLUMN is_predefined SET DEFAULT true;
ALTER TABLE account_type ALTER COLUMN is_active SET DEFAULT true;

INSERT INTO account_type(id, name) VALUES (1, 'admin');
INSERT INTO account_type(id, name) VALUES (2, 'user');
INSERT INTO account_type(id, name) VALUES (3, 'view');

-- fix account table
ALTER TABLE "account" ADD COLUMN account_type_id bigint;

UPDATE "account" SET account_type_id=1 WHERE type='admin';
UPDATE "account" SET account_type_id=2 WHERE type='user';
UPDATE "account" SET account_type_id=3 WHERE type='view';

ALTER TABLE account ALTER COLUMN account_type_id SET NOT NULL;

ALTER TABLE "account"
    ADD CONSTRAINT account_fk_account_type FOREIGN KEY (account_type_id)
    REFERENCES account_type (id);

ALTER TABLE "account" DROP COLUMN "type";