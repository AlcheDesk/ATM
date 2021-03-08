--clean up
DELETE FROM "test_case_share_folder_test_case_link" WHERE test_case_share_folder_id IN (SELECT id FROM test_case_share_folder WHERE is_deleted IS TRUE);
DELETE FROM "test_case_share_folder_test_case_link" WHERE test_case_id IN (SELECT id FROM test_case WHERE is_deleted IS TRUE);
-- add trigger
CREATE OR REPLACE FUNCTION "test_case_share_folder_delete_delete_tcsftcl" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.is_deleted IS TRUE THEN
		DELETE FROM test_case_share_folder_test_case_link
		WHERE test_case_share_folder_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "test_case_share_folder_after_update_delete_tcsftcl"
  AFTER UPDATE ON test_case_share_folder
  FOR EACH ROW
EXECUTE PROCEDURE test_case_share_folder_delete_delete_tcsftcl();
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "test_case_delete_delete_tcsftcl" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.is_deleted IS TRUE THEN
		DELETE FROM test_case_share_folder_test_case_link
		WHERE test_case_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "test_case_after_update_delete_tcsftcl"
  AFTER UPDATE ON test_case
  FOR EACH ROW
EXECUTE PROCEDURE test_case_delete_delete_tcsftcl();
------------------------------------------------------------------------------