DELETE FROM "test_case_tag_link" WHERE tag_id in (SELECT id FROM tag WHERE is_deleted IS TRUE);
ALTER TABLE "tag" DROP COLUMN "is_deleted";
DROP TRIGGER "tag_after_update_delete_tctl" ON "tag";
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "tag_delete_delete_tctl" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	DELETE FROM test_case_tag_link
	WHERE tag_id = OLD.id;
	RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "tag_before_delete_delete_tctl"
  BEFORE DELETE ON tag
  FOR EACH ROW
EXECUTE PROCEDURE tag_delete_delete_tctl();
------------------------------------------------------------------------------