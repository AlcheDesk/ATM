--clean up
DELETE FROM "system_requirement_pack_system_requirement_link" WHERE system_requirement_pack_id IN (SELECT id FROM system_requirement_pack WHERE is_deleted IS TRUE);
-- add trigger
CREATE OR REPLACE FUNCTION "system_requirement_pack_delete_delete_srpsrl" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.is_deleted IS TRUE THEN
		DELETE FROM system_requirement_pack_system_requirement_link
		WHERE system_requirement_pack_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "system_requirement_pack_after_update_delete_srpsrl"
  AFTER UPDATE ON system_requirement_pack
  FOR EACH ROW
EXECUTE PROCEDURE system_requirement_pack_delete_delete_srpsrl();
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "system_requirement_delete_delete_srpsrl" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	DELETE FROM system_requirement_pack_system_requirement_link
	WHERE system_requirement_id = OLD.id;
	RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "system_requirement_before_delete_delete_srpsrl"
  BEFORE DELETE ON system_requirement
  FOR EACH ROW
EXECUTE PROCEDURE system_requirement_delete_delete_srpsrl();
------------------------------------------------------------------------------