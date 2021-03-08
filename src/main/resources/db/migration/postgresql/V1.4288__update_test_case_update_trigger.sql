UPDATE instruction SET is_deleted = TRUE WHERE is_deleted = FALSE AND ref_test_case_id IN (SELECT id FROM test_case WHERE is_deleted = TRUE);

CREATE OR REPLACE FUNCTION "test_case_update_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF (NEW.id = OLD.id) AND (NEW.name <> OLD.name) THEN
	    update test_case_execution_info set
	    test_case_name = NEW.name
	    where test_case_id = NEW.id;
	    ---------------------------
	    update run_execution_info set
	    test_case_name = NEW.name
	    where test_case_id = NEW.id;
	    ---------------------------
	    update instruction_target_map set
	    ref_test_case_name = NEW.name
	    where ref_test_case_id = NEW.id;
	END IF;
	
	IF NEW.is_deleted IS TRUE AND OLD.is_deleted IS FALSE THEN
		DELETE FROM run_set_test_case_link WHERE test_case_id = NEW.id;
		DELETE FROM test_case_module_link WHERE test_case_id = NEW.id;
		DELETE FROM test_case_share_folder_test_case_link WHERE test_case_id = NEW.id;
		DELETE FROM test_case_tag_link WHERE test_case_id = NEW.id;
		UPDATE test_case_execution_info SET test_case_is_deleted = NEW.is_deleted WHERE test_case_id = NEW.id;
        UPDATE instruction SET is_deleted = TRUE WHERE ref_test_case_id = NEW.id AND is_deleted = FALSE;
	END IF;
	
	IF NEW.is_deleted IS FALSE AND OLD.is_deleted IS TRUE THEN
	    UPDATE test_case_execution_info SET test_case_is_deleted = NEW.is_deleted WHERE test_case_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;