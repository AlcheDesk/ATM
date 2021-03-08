CREATE OR REPLACE FUNCTION "test_case_overwrite_update_itm" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.name <> OLD.name THEN
		update instruction_target_map set ref_test_case_overwrite_name = NEW.name where ref_test_case_overwrite_id = NEW.id;
	END IF;
	
	IF NEW.is_deleted = true then
      UPDATE run_set_test_case_link SET  test_case_overwrite_id = null WHERE test_case_overwrite_id = NEW.id;  
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

DROP TRIGGER "test_case_overwrite_before_update_run_set_test_case_link" ON "test_case_overwrite";
DROP FUNCTION "test_case_overwrite_update_update_run_set_test_case_link" ( );
ALTER FUNCTION "test_case_overwrite_update_itm" ( ) RENAME TO test_case_overwrite_update_change_others;
ALTER TRIGGER "test_case_overwrite_update_update_itm" ON "test_case_overwrite" RENAME TO test_case_overwrite_after_update_change_others;
