CREATE TABLE user_content
    (
        id bigserial,
        content_uuid uuid DEFAULT uuid_generate_v4(),
        sha256 text,
        original_name text NOT NULL,
        modified_name text,
        content_type bigint DEFAULT 0 NOT NULL,
        version text,
        description text DEFAULT '' NOT NULL,
        created_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        updated_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        tenant_id bigint NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT user_content_fk_tenant_id FOREIGN KEY (tenant_id) REFERENCES "tenant" ("id"),
        CONSTRAINT user_content_ix_uuid UNIQUE (content_uuid)
    );
    
    
CREATE TRIGGER "user_content_insert_created_at_updated_at"
  BEFORE INSERT ON user_content
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_column();

CREATE TRIGGER "user_content_update_created_at_updated_at"
  BEFORE UPDATE ON user_content
  FOR EACH ROW
EXECUTE PROCEDURE update_created_at_updated_at_column();


CREATE OR REPLACE FUNCTION modified_name_default()
  RETURNS trigger AS
$$
BEGIN
    NEW.modified_name = NEW.original_name;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER modified_name_default
BEFORE INSERT ON user_content
FOR EACH ROW
WHEN (NEW.modified_name IS NULL AND NEW.original_name IS NOT NULL)
EXECUTE PROCEDURE modified_name_default();