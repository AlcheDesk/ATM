CREATE FUNCTION test_case_overwrite_update_update_run_set_test_case_link()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF 
AS $BODY$
BEGIN			  
	IF NEW.is_deleted = true then
      UPDATE run_set_test_case_link SET 
      test_case_overwrite_id = null
      WHERE test_case_overwrite_id = NEW.id;  
	END IF;
	RETURN NEW;
END;
$BODY$;

CREATE TRIGGER test_case_overwrite_before_update_run_set_test_case_link
    BEFORE UPDATE
    ON test_case_overwrite
    FOR EACH ROW
    EXECUTE PROCEDURE test_case_overwrite_update_update_run_set_test_case_link();