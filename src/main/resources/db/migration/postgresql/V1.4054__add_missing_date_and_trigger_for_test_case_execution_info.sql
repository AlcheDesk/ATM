insert into test_case_execution_info (
	test_case_id,
	test_case_name,
	test_case_created_at,
	total_run_count
	)
select 
id as test_case_id, 
name as test_case_name, 
created_at as test_case_created_at, 
(select count(id) from run where test_case_id = tc.id ) as total_run_count 
from test_case tc
where tc.id not in (select test_case_id from test_case_execution_info);

--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "update_test_case_execution_info_before_test_case_update" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF (NEW.id = OLD.id) AND (NEW.name <> OLD.name) THEN
	    update test_case_execution_info set
	    test_case_name = NEW.name
	    where test_case_id = NEW.id;
	ELSEIF (NEW.id <> OLD.id) THEN
		update test_case_execution_info set
	    test_case_name = NEW.name,
	    test_case_created_at = NEW.created_at,
	    test_case_id = NEW.id,
	    total_run_count = (select count(id) from run where test_case_id = NEW.id )
	    where test_case_id = OLD.id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "test_case_z_before_update_test_case_execution_info"
  BEFORE UPDATE ON test_case
  FOR EACH ROW
EXECUTE PROCEDURE update_test_case_execution_info_before_test_case_update();