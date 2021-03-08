----------------------------------------------------------------------------------------------------------------------------------
ALTER TABLE "test_case_execution_info" ADD COLUMN test_case_project_id bigint;
UPDATE "test_case_execution_info" SET
test_case_project_id = i.pi
FROM 
(
    select id as tid, project_id as pi
    FROM test_case
) i
WHERE i.tid = test_case_id;
ALTER TABLE "test_case_execution_info" ALTER COLUMN test_case_project_id SET NOT NULL;
CREATE OR REPLACE FUNCTION "test_case_execution_info_insert_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	------------------------------------
	-- project_execution_info
	UPDATE project_execution_info SET 
	total_test_case_number = total_test_case_number + 1, 
	test_case_ids = array_append(test_case_ids, NEW.id) 
	WHERE project_id = NEW.test_case_project_id;
	------------------------------------  
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
	    test_case_project_id,
	    tenant_id
	) values (
	    NEW.id,
	    NEW.name,
	    NEW.created_at,
	    NEw.is_deleted,
	    NEW.project_id,
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