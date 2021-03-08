--clean up
DELETE FROM "run_set_notification_link" WHERE run_set_id IN (SELECT id FROM run_set WHERE is_deleted IS TRUE);
DELETE FROM "run_set_notification_link" WHERE notification_id IN (SELECT id FROM notification WHERE is_deleted IS TRUE);
-- add trigger
CREATE OR REPLACE FUNCTION "run_set_delete_delete_rsnl" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.is_deleted IS TRUE THEN
		DELETE FROM run_set_notification_link
		WHERE run_set_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "run_set_after_update_delete_rsnl"
  AFTER UPDATE ON run_set
  FOR EACH ROW
EXECUTE PROCEDURE run_set_delete_delete_rsnl();
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "notification_delete_delete_rsnl" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.is_deleted IS TRUE THEN
		DELETE FROM run_set_notification_link
		WHERE notification_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "notification_after_update_delete_rsnl"
  AFTER UPDATE ON notification
  FOR EACH ROW
EXECUTE PROCEDURE notification_delete_delete_rsnl();
------------------------------------------------------------------------------