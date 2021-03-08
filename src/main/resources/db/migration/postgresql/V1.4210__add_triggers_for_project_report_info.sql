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
	    test_case_is_deleted,
	    tenant_id
	) values (
	    NEW.id,
	    NEW.name,
	    NEW.created_at,
	    NEw.is_deleted,
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
	END IF;
	
	IF NEW.is_deleted IS TRUE AND OLD.is_deleted IS FALSE THEN
		DELETE FROM run_set_test_case_link WHERE test_case_id = NEW.id;
		DELETE FROM test_case_module_link WHERE test_case_id = NEW.id;
		DELETE FROM test_case_share_folder_test_case_link WHERE test_case_id = NEW.id;
		DELETE FROM test_case_tag_link WHERE test_case_id = NEW.id;
		UPDATE test_case_execution_info SET test_case_is_deleted = NEW.is_deleted WHERE test_case_id = NEW.id;
	END IF;
	
	IF NEW.is_deleted IS FALSE AND OLD.is_deleted IS TRUE THEN
	    UPDATE test_case_execution_info SET test_case_is_deleted = NEW.is_deleted WHERE test_case_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "test_case_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF TG_OP = 'UPDATE' THEN
	  NEW.id = OLD.id;
	  IF NEW.project_id IS NOT NULL AND NEW.project_id IS DISTINCT FROM OLD.project_id THEN
		NEW.project_name = (SELECT name FROM project WHERE id = NEW.project_id);
	  ELSIF NEW.project_id IS NULL THEN
	    NEW.project_name = NULL;
	  END IF;
	END IF;
	 
	IF TG_OP = 'INSERT' THEN
	    NEW.is_deleted = FALSE;
		IF NEW.project_id IS NOT NULL THEN
			NEW.project_name = (SELECT name FROM project WHERE id = NEW.project_id);
		ELSE
		    NEW.project_name = NULL;
		END IF;
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
	
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------