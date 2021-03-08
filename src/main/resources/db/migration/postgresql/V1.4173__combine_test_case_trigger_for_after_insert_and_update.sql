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
	RETURN NEW;
    
    -- test_case_instruction_types_map
	insert into test_case_instruction_type_map (test_case_id, instruction_types) 
	values (NEW.id, 
	         (select 
	             case 
	                when array_agg(distinct(instruction_type_id)) IS NULL THEN '{}'::bigint[] 
	                else array_agg(distinct(instruction_type_id))
	             end
	          from instruction where test_case_id = NEW.id)
	       );
	------------------------------------  
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------
CREATE TRIGGER "test_case_after_insert_change_others"
  AFTER INSERT ON test_case
  FOR EACH ROW
EXECUTE PROCEDURE test_case_insert_change_others();
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "test_case_update_update_others" ()  RETURNS trigger
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
	
	IF NEW.is_deleted IS TRUE THEN
		DELETE FROM run_set_test_case_link
		WHERE test_case_id = NEW.id;
		DELETE FROM test_case_module_link
		WHERE test_case_id = NEW.id;
		DELETE FROM test_case_share_folder_test_case_link
		WHERE test_case_id = NEW.id;
		DELETE FROM test_case_tag_link
		WHERE test_case_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
ALTER TRIGGER "test_case_after_update_update_others" ON "test_case" RENAME TO test_case_after_update_change_others;
ALTER FUNCTION "test_case_update_update_others" ( ) RENAME TO test_case_update_change_others;
--------------------------------------------------------------------------------------------------------
DROP TRIGGER "test_case_after_insert_tcdam" ON "test_case";
DROP FUNCTION "test_case_insert_test_case_driver_alias_map" ( );
DROP FUNCTION "test_case_insert_tcdam" ( );
DROP TRIGGER "test_case_after_insert_tcei" ON "test_case";
DROP FUNCTION "test_case_insert_insert_test_case_execution_info" ( );
DROP TRIGGER "test_case_after_insert_tcitm" ON "test_case";
DROP FUNCTION "test_case_insert_update_test_case_instruction_type_map" ( );
DROP TRIGGER "test_case_update_update_itm" ON "test_case";
DROP FUNCTION "test_case_update_instruction_target_map" ( );
DROP TRIGGER "test_case_after_update_delete_rstcl" ON "test_case";
DROP TRIGGER "test_case_after_update_delete_tcml" ON "test_case";
DROP TRIGGER "test_case_after_update_delete_tcsftcl" ON "test_case";
DROP TRIGGER "test_case_after_update_delete_tctl" ON "test_case";
DROP FUNCTION "test_case_delete_delete_rstcl" ( );
DROP FUNCTION "test_case_delete_delete_tcml" ( );
DROP FUNCTION "test_case_delete_delete_tcsftcl" ( );
DROP FUNCTION "test_case_delete_delete_tctl" ( );