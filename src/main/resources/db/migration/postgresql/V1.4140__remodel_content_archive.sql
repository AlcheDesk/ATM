DROP TRIGGER "content_archive_before_insert_sha2" ON "content_archive";
DROP FUNCTION "insert_content_archive_sha2" ( );
DROP TABLE "content_archive";
------------------------------------------------------------------------------
CREATE TABLE content_archive
    (
        content_type text NOT NULL,
        content_md5 text NOT NULL,
        content_json jsonb NOT NULL,
        PRIMARY KEY (content_md5),
        CONSTRAINT content_archive_ix_json UNIQUE (content_json)
    );
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "insert_content_archive_md5" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF TG_OP = 'INSERT' THEN 
        NEW.content_sha2 = md5(NEW.content_json::text)::uuid;
    ELSIF TG_OP = 'UPDATE' AND OLD.content_json <> NEW.content_json THEN
        NEW.content_sha2 = md5(NEW.content_json::text)::uuid;
    END IF;
    RETURN NEW;   
END;
$body$ LANGUAGE plpgsql;
------------------------------------------------------------------------------
CREATE TRIGGER "content_archive_before_insert_md5"
  BEFORE INSERT OR UPDATE ON content_archive
  FOR EACH ROW
EXECUTE PROCEDURE insert_content_archive_md5();
------------------------------------------------------------------------------