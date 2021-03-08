CREATE OR REPLACE FUNCTION "project_insert_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	insert into project_execution_info (
	    project_id,
	    project_name,
	    project_created_at,
	    project_updated_at,
	    total_test_case_number,
	    executed_test_case_number,
	    test_case_ids,
	    executed_test_case_ids
	) values (
	    NEW.id,
	    NEW.name,
	    NEW.created_at,
	    NEW.updated_at,
	    0,
	    0,
	    ARRAY[]::bigint[],
	    ARRAY[]::bigint[]
	);
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "keep_is_finished_start_at_end_at" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.is_finished IS TRUE THEN
		NEW.end_at = now();
	END IF;
	
	IF NEW.status_id <> 1 THEN
		NEW.start_at = now();
	END IF;
	
    RETURN NEW;   
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "run_before_update_finished"
  BEFORE UPDATE ON run
  FOR EACH ROW
EXECUTE PROCEDURE keep_is_finished_start_at_end_at();

CREATE TRIGGER "run_set_result_before_update_finished"
  BEFORE UPDATE ON run_set_result
  FOR EACH ROW
EXECUTE PROCEDURE keep_is_finished_start_at_end_at();

CREATE TRIGGER "dev_instruction_result_before_update_finished"
  BEFORE UPDATE ON dev_instruction_result
  FOR EACH ROW
EXECUTE PROCEDURE keep_is_finished_start_at_end_at();

CREATE TRIGGER "prod_instruction_result_before_update_finished"
  BEFORE UPDATE ON prod_instruction_result
  FOR EACH ROW
EXECUTE PROCEDURE keep_is_finished_start_at_end_at();