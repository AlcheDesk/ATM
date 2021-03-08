CREATE EXTENSION IF NOT EXISTS pgcrypto;
------------------------------------------------------------------------------
CREATE TABLE content_archive
    (
        id bigserial NOT NULL,
        content_sha2 text NOT NULL,
        content_json jsonb NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT content_archive_ix_sha2 UNIQUE (content_sha2),
        CONSTRAINT content_archive_ix_json UNIQUE (content_json)
    );
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "insert_content_archive_sha2" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF TG_OP = 'INSERT' THEN 
        NEW.content_sha2 = digest(NEW.content_json::text, 'sha256');
    ELSIF TG_OP = 'UPDATE' AND OLD.content_json <> NEW.content_json THEN
        NEW.content_sha2 = digest(NEW.content_json::text, 'sha256');
    END IF;
    RETURN NEW;   
END;
$body$ LANGUAGE plpgsql;
------------------------------------------------------------------------------
CREATE TRIGGER "content_archive_before_insert_sha2"
  BEFORE INSERT OR UPDATE ON content_archive
  FOR EACH ROW
EXECUTE PROCEDURE insert_content_archive_sha2();
------------------------------------------------------------------------------    
