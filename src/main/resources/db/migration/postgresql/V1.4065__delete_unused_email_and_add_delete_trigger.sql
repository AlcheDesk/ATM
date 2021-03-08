delete from "email_notification_target" where is_deleted is true;
ALTER TABLE "email_notification_target" DROP COLUMN "is_deleted";

CREATE FUNCTION delete_before_email_notification_target() RETURNS TRIGGER
  VOLATILE
AS $$
BEGIN
    DELETE FROM notification_email_notification_target_link WHERE email_notification_target_id = OLD.id;
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER delete_email_notification_target 
BEFORE DELETE ON email_notification_target 
FOR EACH ROW 
EXECUTE PROCEDURE delete_before_email_notification_target();