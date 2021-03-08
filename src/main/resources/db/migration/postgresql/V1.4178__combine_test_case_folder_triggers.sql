ALTER TRIGGER "test_case_share_folder_update_update_itm" ON "test_case_share_folder" RENAME TO test_case_share_folder_after_update_change_others;
DROP TRIGGER "test_case_share_folder_after_update_delete_tcsftcl" ON "test_case_share_folder";
DROP FUNCTION "test_case_share_folder_delete_delete_tcsftcl" ( );
CREATE OR REPLACE FUNCTION "test_case_share_folder_update_itm" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.name <> OLD.name THEN
		update instruction_target_map set test_case_share_folder_name = NEW.name where test_case_share_folder_id = NEW.id;
	END IF;
	
	IF NEW.is_deleted IS TRUE THEN
		DELETE FROM test_case_share_folder_test_case_link WHERE test_case_share_folder_id = NEW.id;
	END IF;
	
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
ALTER
FUNCTION "test_case_share_folder_update_itm" ( ) RENAME TO test_case_share_folder_update_change_others;