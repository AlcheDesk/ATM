--clean up
DELETE FROM "notification_email_notification_target_link" WHERE notification_id IN (SELECT id FROM notification WHERE is_deleted IS TRUE);
-- add trigger
CREATE OR REPLACE FUNCTION "notification_delete_delete_nentl" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.is_deleted IS TRUE THEN
		DELETE FROM notification_email_notification_target_link
		WHERE notification_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "notification_after_update_delete_nentl"
  AFTER UPDATE ON notification
  FOR EACH ROW
EXECUTE PROCEDURE notification_delete_delete_nentl();
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "email_notification_target_delete_delete_nentl" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	DELETE FROM notification_email_notification_target_link
	WHERE email_notification_target_id = OLD.id;
	RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "email_notification_target_before_delete_delete_nentl"
  BEFORE DELETE ON email_notification_target
  FOR EACH ROW
EXECUTE PROCEDURE email_notification_target_delete_delete_nentl();
------------------------------------------------------------------------------