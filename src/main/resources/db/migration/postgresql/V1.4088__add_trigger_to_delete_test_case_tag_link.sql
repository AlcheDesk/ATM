--clean up
DELETE FROM "test_case_tag_link" WHERE test_case_id IN (SELECT id FROM test_case WHERE is_deleted IS TRUE);
DELETE FROM "test_case_tag_link" WHERE tag_id IN (SELECT id FROM tag WHERE is_deleted IS TRUE);
-- add trigger
CREATE OR REPLACE FUNCTION "test_case_delete_delete_tctl" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.is_deleted IS TRUE THEN
		DELETE FROM test_case_tag_link
		WHERE test_case_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "test_case_after_update_delete_tctl"
  AFTER UPDATE ON test_case
  FOR EACH ROW
EXECUTE PROCEDURE test_case_delete_delete_tctl();
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "tag_delete_delete_tctl" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.is_deleted IS TRUE THEN
		DELETE FROM test_case_tag_link
		WHERE tag_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "tag_after_update_delete_tctl"
  AFTER UPDATE ON tag
  FOR EACH ROW
EXECUTE PROCEDURE tag_delete_delete_tctl();
------------------------------------------------------------------------------