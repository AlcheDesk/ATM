CREATE TABLE account
    (
        id bigserial,
        username text NOT NULL,
        password text,
        created_at timestamp with time zone NOT NULL,
        updated_at timestamp with time zone NOT NULL,
        is_deleted boolean NOT NULL,
        PRIMARY KEY (id)
    );

ALTER TABLE account ALTER COLUMN is_deleted SET DEFAULT False;

CREATE TRIGGER account_insert_create_at_update_at BEFORE INSERT ON "account" 
FOR EACH ROW EXECUTE PROCEDURE "insert_updated_at_created_at_column"();
CREATE TRIGGER account_update_created_at_updated_at BEFORE UPDATE ON "account" 
FOR EACH ROW EXECUTE PROCEDURE "update_created_at_updated_at_column"();