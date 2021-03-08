ALTER TABLE "test_case" disable TRIGGER "test_case_before_update_timestamp_with_uuid";
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
	------------------------------------  
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
ALTER TABLE "test_case" enable TRIGGER "test_case_before_update_timestamp_with_uuid";
----------------------------------------------------------------------------------------------------------------------------------
insert into test_case_instruction_type_map (test_case_id, instruction_types)
select ins.test_case_id, array_agg(distinct(ins.instruction_type_id)) as ins_types from instruction ins
full outer join test_case_instruction_type_map tmap on tmap.test_case_id = ins.test_case_id
where is_deleted is false and tmap.test_case_id IS NULL group by ins.test_case_id;
