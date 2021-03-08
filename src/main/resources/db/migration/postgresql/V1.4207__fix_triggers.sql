CREATE OR REPLACE FUNCTION "project_execution_info_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF TG_OP = 'UPDATE' THEN
		NEW.project_created_at = OLD.project_created_at;
		NEW.total_test_case_number = case when array_length(NEW.test_case_ids, 1) is null then 0 else array_length(NEW.test_case_ids, 1) end;
		NEW.executed_test_case_number = case when array_length(NEW.executed_test_case_ids, 1) is null then 0 else array_length(NEW.executed_test_case_ids, 1) end;
	END IF;
	
	IF TG_OP = 'INSERT' THEN
	    IF NEW.project_id IS NOT NULL THEN
	      NEW.project_name = (SELECT name FROM project WHERE id = NEW.project_id);
	    END IF;
		NEW.total_test_case_number = 0;
		NEW.executed_test_case_number = 0;
		NEW.test_case_ids = ARRAY[]::bigint[];
		NEW.executed_test_case_ids =  ARRAY[]::bigint[];
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------  
ALTER TABLE "project_report_info" DROP CONSTRAINT "project_report_info_fk_project";
ALTER TABLE "project_report_info" ADD CONSTRAINT project_report_info_fk_project FOREIGN KEY (project_id, project_name) REFERENCES "project" ("id", "name") DEFERRABLE INITIALLY DEFERRED;
CREATE OR REPLACE FUNCTION "project_update_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.name IS NOT NULL AND NEW.name IS DISTINCT FROM OLD.name THEN
		UPDATE test_case SET project_name = NEW.name WHERE project_id = NEW.id;
		UPDATE project_execution_info SET project_name = NEW.name WHERE project_id = NEW.id;
	ELSIF TG_OP = 'UPDATE' AND NEW.name IS NULL THEN
	    UPDATE test_case SET project_name = NULL WHERE project_id = NEW.id;
	    UPDATE project_execution_info SET project_name = NULL WHERE project_id = NEW.id;
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
		UPDATE test_case_execution_info SET test_case_is_deleted = NEW.is_deleted WHERE test_case_id = NEW.id;
	END IF;
	
	IF NEW.is_deleted IS FALSE AND OLD.is_deleted IS TRUE THEN
	    UPDATE project_execution_info SET total_test_case_number = total_test_case_number + 1, test_case_ids = array_append(test_case_ids, NEW.id) WHERE project_id = NEW.project_id;
	    UPDATE test_case_execution_info SET test_case_is_deleted = NEW.is_deleted WHERE test_case_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
