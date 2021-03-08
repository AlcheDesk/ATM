CREATE OR REPLACE FUNCTION "instruction_insert_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	------------------------------------
	-- instruction_target_map
	insert into instruction_target_map
	(	 
	 application_id, 
	 application_name, 
	 section_id, 
	 section_name, 
	 element_id, 
	 element_name, 
	 test_case_share_folder_id, 
	 test_case_share_folder_name, 
	 ref_test_case_id, 
	 ref_test_case_name,
	 ref_test_case_overwrite_id,
	 ref_test_case_overwrite_name,
	 instruction_id
	 ) 
	values 
	(
		 NEW.application_id, 
		 (select name from application where id = NEW.application_id), 
		 NEW.section_id, 
		 (select name from section where id = NEW.section_id),  
		 NEW.element_id, 
		 (select name from element where id = NEW.element_id),  
		 NEW.test_case_share_folder_id, 
		 (select name from test_case_share_folder where id = NEW.test_case_share_folder_id), 
		 NEW.ref_test_case_id, 
		 (select name from test_case where id = NEW.ref_test_case_id), 
		 NEW.ref_test_case_overwrite_id, 
		 (select name from test_case_overwrite where id = NEW.ref_test_case_overwrite_id), 
		 NEW.id
	 );
	 
	 -- test_case_driver_alias_map
	 IF NEW.driver_alias IS NOT NULL THEN
		UPDATE test_case_driver_alias_map
		SET 
		  driver_aliases = names.aliases
		FROM
		  (SELECT 
		  	CASE
				WHEN array_agg(distinct(driver_alias)) = '{null}'::text[] THEN '{}'::text[]
				WHEN array_agg(distinct(driver_alias)) IS NULL THEN '{}'::text[]
				ELSE array_agg(distinct(driver_alias))
			END
		  AS aliases FROM instruction ins
		  WHERE ins.driver_alias IS NOT NULL AND ins.is_deleted = FALSE AND ins.test_case_id = NEW.test_case_id) names
		WHERE 
		  test_case_id = NEW.test_case_id;
    END IF;
    
    -- test_case_instruction_types_map
    UPDATE test_case_instruction_type_map
	SET 
	  instruction_types = ARRAY(SELECT DISTINCT UNNEST(instruction_types || NEW.instruction_type_id) ORDER BY 1)
	WHERE 
	  test_case_id = NEW.test_case_id;
	------------------------------------  
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------
CREATE TRIGGER "instruction_after_insert_update_others"
  AFTER INSERT ON instruction
  FOR EACH ROW
EXECUTE PROCEDURE instruction_insert_change_others();
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "instruction_update_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	------------------------------------
	-- instruction_target_map
	IF NEW.application_id IS DISTINCT FROM OLD.application_id OR
	  NEW.section_id IS DISTINCT FROM OLD.section_id OR
	  NEW.element_id IS DISTINCT FROM OLD.element_id OR
	  NEW.test_case_share_folder_id IS DISTINCT FROM OLD.test_case_share_folder_id OR
	  NEW.ref_test_case_overwrite_id IS DISTINCT FROM OLD.ref_test_case_overwrite_id OR
	  NEW.ref_test_case_id IS DISTINCT FROM OLD.ref_test_case_id THEN
	  
	  update "instruction_target_map" set
		 application_id = NEW.application_id,
		 application_name =  (select name from application where id = NEW.application_id), 
		 section_id = NEW.section_id, 
		 section_name = (select name from section where id = NEW.section_id),  
		 element_id = NEW.element_id, 
		 element_name =  (select name from element where id = NEW.element_id),  
		 test_case_share_folder_id =  NEW.test_case_share_folder_id, 
		 test_case_share_folder_name =  (select name from test_case_share_folder where id = NEW.test_case_share_folder_id), 
		 ref_test_case_id = NEW.ref_test_case_id, 
		 ref_test_case_name = (select name from test_case where id = NEW.ref_test_case_id), 
		 ref_test_case_overwrite_id = NEW.ref_test_case_overwrite_id, 
		 ref_test_case_overwrite_name = (select name from test_case_overwrite where id = NEW.ref_test_case_overwrite_id)
    where instruction_id = NEW.id;
	END IF;
	
	-- test_case_driver_alias_map
	IF NEW.driver_alias IS DISTINCT FROM OLD.driver_alias THEN
		UPDATE test_case_driver_alias_map
		SET 
	    driver_aliases = names.aliases
		FROM
			  (SELECT 
			  	CASE
					WHEN array_agg(distinct(driver_alias)) = '{null}'::text[] THEN '{}'::text[]
					WHEN array_agg(distinct(driver_alias)) IS NULL THEN '{}'::text[]
					ELSE array_agg(distinct(driver_alias))
				END
			  AS aliases FROM instruction ins
			  WHERE ins.driver_alias IS NOT NULL AND ins.is_deleted = FALSE AND ins.test_case_id = NEW.test_case_id) names
		WHERE 
			  test_case_id = NEW.test_case_id;
    END IF;
    
    -- test_case_instruction_types_map
    IF NEW.is_deleted IS DISTINCT FROM OLD.is_deleted THEN
		UPDATE test_case_instruction_type_map
		SET 
		  instruction_types = types.ins_types
		FROM
		  (SELECT 
		    CASE
				WHEN array_agg(DISTINCT(instruction_type_id)) IS NULL THEN '{}'::bigint[]
				ELSE array_agg(DISTINCT(instruction_type_id))
		    END
		  AS ins_types FROM instruction ins
		  WHERE ins.is_deleted IS FALSE AND ins.test_case_id = NEW.test_case_id) types
		WHERE 
		  test_case_id = NEW.test_case_id;
	END IF;
	------------------------------------  
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------
CREATE TRIGGER "instruction_after_update_update_others"
  AFTER UPDATE ON instruction
  FOR EACH ROW
EXECUTE PROCEDURE instruction_update_change_others();
--------------------------------------------------------------------------------------------------------
DROP TRIGGER "instruction_after_insert_insert_itm" ON "instruction";
DROP TRIGGER "instruction_after_insert_or_update_test_case_instruction_type_m" ON "instruction";
DROP TRIGGER "instruction_after_insert_update_tcdam" ON "instruction";
DROP TRIGGER "instruction_after_update_update_itm" ON "instruction";
DROP TRIGGER "instruction_after_update_update_tcdam" ON "instruction";
DROP TRIGGER "instruction_before_update_tcitm" ON "instruction";
DROP FUNCTION "instruction_insert_instruction_target_map" ( );
DROP FUNCTION "update_after_test_case_instruction_types_map" ( );
DROP FUNCTION "instruction_insert_update_tcdam" ( );
DROP FUNCTION "instruction_update_instruction_target_map" ( );
DROP FUNCTION "instruction_update_update_tcdam" ( );
DROP FUNCTION "update_before_test_case_instruction_types_map" ( );
--------------------------------------------------------------------------------------------------------