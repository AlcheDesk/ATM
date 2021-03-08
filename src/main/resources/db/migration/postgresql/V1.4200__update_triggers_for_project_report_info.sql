ALTER TABLE "project_report_info" RENAME COLUMN "totalexecutiontime" TO total_execution_time ;
ALTER TABLE "project_report_info" RENAME COLUMN "executedtestcasenumber" TO executed_test_case_number;
ALTER TABLE "project_report_info" RENAME COLUMN "failedtestcasenumber" TO failed_test_case_number;
ALTER TABLE "project_report_info" RENAME COLUMN "passedtestcasenumber" TO passed_test_case_number;
ALTER TABLE "project_report_info" RENAME COLUMN "passrate" TO pass_rate;
ALTER TABLE "project_report_info" RENAME COLUMN "failrate" TO fail_rate;
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "project_report_info_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	-- is_finished and end_at
	IF TG_OP = 'UPDATE' THEN
		NEW.project_created_at = OLD.project_created_at;
	END IF;
	
	IF TG_OP = 'INSERT' THEN
		NEW.total_test_case_number = 0;
		NEW.total_run_number = 0;
		NEW.total_execution_time = 0;
		NEW.failed_test_case_number = 0;
		NEW.executed_test_case_number = 0;
		NEW.passed_test_case_number = 0;
		NEW.pass_rate = 0;
		NEW.fail_rate = 0;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "test_case_insert_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	------------------------------------
	-- test_case_execution_info
	insert into test_case_execution_info (
	    test_case_id,
	    test_case_name,
	    test_case_created_at,
	    tenant_id
	) values (
	    NEW.id,
	    NEW.name,
	    NEW.created_at,
	    NEW.tenant_id
	);
	
	-- test_case_driver_alias_map
	INSERT INTO test_case_driver_alias_map (test_case_id, driver_aliases) 
	VALUES (NEW.id, 
	        '{}'::text[]
	       );
    
    -- test_case_instruction_types_map
	insert into test_case_instruction_type_map (test_case_id, instruction_types) 
	values (NEW.id, '{}'::bigint[]);
	
	-- project_execution_info
	UPDATE project_execution_info SET total_test_case_number = total_test_case_number + 1, test_case_ids = array_append(test_case_ids, NEW.id) WHERE project_id = NEW.project_id;
	------------------------------------  
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
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
	ELSEIF (NEW.id <> OLD.id) THEN
		update test_case_execution_info set
	    test_case_name = NEW.name,
	    test_case_created_at = NEW.created_at,
	    test_case_id = NEW.id,
	    total_run_count = (select count(id) from run where test_case_id = NEW.id )
	    where test_case_id = OLD.id;
	END IF;
	
	IF NEW.is_deleted IS TRUE AND OLD.is_deleted IS FALSE THEN
		DELETE FROM run_set_test_case_link WHERE test_case_id = NEW.id;
		DELETE FROM test_case_module_link WHERE test_case_id = NEW.id;
		DELETE FROM test_case_share_folder_test_case_link WHERE test_case_id = NEW.id;
		DELETE FROM test_case_tag_link WHERE test_case_id = NEW.id;
		UPDATE project_execution_info SET total_test_case_number = total_test_case_number - 1, test_case_ids = array_remove(test_case_ids, NEW.id) WHERE project_id = NEW.project_id;
	END IF;
	
	IF NEW.is_deleted IS FALSE AND OLD.is_deleted IS TRUE THEN
	    UPDATE project_execution_info SET total_test_case_number = total_test_case_number + 1, test_case_ids = array_append(test_case_ids, NEW.id) WHERE project_id = NEW.project_id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_insert_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	insert into run_execution_info (
	    run_id,
	    run_name,
	    run_type_id,
	    run_status_id,
	    run_created_at,
	    run_updated_at,
	    test_case_id,
	    test_case_md5,
	    run_set_result_id,
	    executable_instruction_number,
	    instruction_executed_count,
	    instruction_pass_count,
	    trigger_source,
	    driver_pack_md5,
	    test_case_overwrite_md5,
	    run_group_id,
	    driver_pack_name,
	    test_case_name,
	    test_case_overwrite_name,
	    run_priority,
	    run_result_overwritten,
	    run_project_id,
	    is_finished
	) values (  
	    NEW.id,
		NEW.name,
		NEW.run_type_id,
		NEW.status_id,
		NEW.created_at,
		NEW.updated_at,
		NEW.test_case_id,
		md5(NEW.test_case::text)::uuid,
		NEW.run_set_result_id,
		NEW.executable_instruction_number,
	    0,
		0,
		NEW.trigger_source,
		md5(NEW.driver_pack::text)::uuid,
		md5(NEW.test_case_overwrite::text)::uuid,
		NEW.group_id,
		NEW.driver_pack->>'name',
		NEW.test_case->>'name',
		NEW.test_case_overwrite->>'name',
		NEW.priority,
		NEW.result_overwritten,
		NEW.project_id,
		false
	);
	
	-- project_execution_info
	UPDATE project_execution_info SET 
	total_test_case_number = total_test_case_number + 1, 
	executed_test_case_ids = ARRAY(SELECT DISTINCT UNNEST(executed_test_case_ids || NEW.test_case_id) ORDER BY 1) 
	WHERE project_id = NEW.project_id;
	
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "project_execution_info_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF TG_OP = 'UPDATE' THEN
		NEW.project_created_at = OLD.project_created_at;
		NEW.total_test_case_number = array_length(NEW.test_case_ids, 1);
		NEW.executed_test_case_number = array_length(NEW.executed_test_case_ids, 1);
	END IF;
	
	IF TG_OP = 'INSERT' THEN
		NEW.total_test_case_number = 0;
		NEW.total_run_number = 0;
		NEW.totalExecutionTime = 0;
		NEW.failedTestCaseNumber = 0;
		NEW.executedTestCaseNumber = 0;
		NEW.passedTestCaseNumber = 0;
		NEW.passRate = 0;
		NEW.failRate = 0;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
CREATE TRIGGER "project_execution_info_generate_columns"
  BEFORE INSERT OR UPDATE ON project_execution_info
  FOR EACH ROW
EXECUTE PROCEDURE project_execution_info_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "project_execution_info_insert_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN	
	INSERT INTO project_report_info (
		project_id, 
		project_name, 
		project_created_at, 
		total_test_case_number, 
		total_run_number, 
		total_execution_time, 
		executed_test_case_number, 
		failed_test_case_number, 
		passed_test_case_number, 
		pass_rate, 
		fail_rate
	) VALUES (
		NEW.project_id,
	    NEW.project_name,
	    NEW.project_created_at,
	    0, 
	    0, 
	    0, 
	    0, 
	    0, 
	    0, 
	    0, 
	    0
	);

	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
CREATE TRIGGER "project_execution_info_after_insert_change_others"
  AFTER INSERT ON project_execution_info
  FOR EACH ROW
EXECUTE PROCEDURE project_execution_info_insert_change_others();
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "project_execution_info_update_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.project_name IS NOT NULL AND NEW.project_name <> OLD.project_name THEN
		UPDATE project_report_info SET project_name = NEW.project_name WHERE project_id = NEW.project_id;
	ELSIF TG_OP = 'UPDATE' AND NEW.project_name IS NULL THEN
	    UPDATE project_report_info SET project_name = NEW.project_name WHERE project_id = NEW.project_id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
CREATE TRIGGER "project_execution_info_after_update_change_others"
  AFTER UPDATE ON project_execution_info
  FOR EACH ROW
EXECUTE PROCEDURE project_execution_info_update_change_others();
----------------------------------------------------------------------------------------------------------------------------------