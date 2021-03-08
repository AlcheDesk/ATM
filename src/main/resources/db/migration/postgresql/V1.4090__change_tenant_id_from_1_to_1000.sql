CREATE OR REPLACE FUNCTION "tcitm_update_update_test_case_driver_type_map" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN			  
	UPDATE test_case_driver_type_map
	SET 
	  driver_types = types.d_types
	FROM
	  (select 
	  	CASE
			WHEN array_agg(DISTINCT(link.driver_type_id)) IS NULL THEN '{}'::bigint[]
			ELSE array_agg(DISTINCT(link.driver_type_id))
	    END
	  as d_types from driver_type_instruction_type_link link 
	   where link.instruction_type_id = ANY (NEW.instruction_types)
	  ) types
	WHERE 
	  test_case_id = NEW.test_case_id;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "update_after_test_case_instruction_types_map" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN			  
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
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "public"."update_before_test_case_instruction_types_map" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.test_case_id IS NOT NULL AND NEW.test_case_id <> OLD.test_case_id THEN				  
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
		  WHERE ins.is_deleted IS FALSE AND ins.id != OLD.id AND ins.test_case_id = OLD.test_case_id) types
		WHERE 
		  test_case_id = OLD.test_case_id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
------------------------------------------------------------------------------------------------------
ALTER TABLE "prod_instruction_result" disable TRIGGER "prod_instruction_result_after_update_test_case_execution_info";
ALTER TABLE "dev_instruction_result" disable TRIGGER "dev_instruction_result_after_update_test_case_execution_info";
ALTER TABLE "run" disable TRIGGER "run_update_log";
ALTER TABLE "run" disable TRIGGER "run_update_created_at_updated_at_uuid";
UPDATE "alias" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "api_schema" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "application" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "dev_execution_log" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "dev_file" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "dev_instruction_result" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "dev_step_log" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "driver_pack" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "element" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "email_notification_target" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "instruction" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "instruction_bundle" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "instruction_bundle_entry" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "instruction_option_entry" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "instruction_overwrite" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "module" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "notification" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "parameter_script" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "prod_execution_log" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "prod_file" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "prod_instruction_result" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "prod_step_log" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "project" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "property" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "resource" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "run" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "run_set" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "run_set_result" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "section" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "system_requirement_pack" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "tag" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "template" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "test_case" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "test_case_execution_info" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "test_case_option_entry" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "test_case_overwrite" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "test_case_share_folder" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "user_activity_log" SET tenant_id = 1000 WHERE tenant_id = 1;
UPDATE "user_content" SET tenant_id = 1000 WHERE tenant_id = 1;
ALTER TABLE "prod_instruction_result" enable TRIGGER "prod_instruction_result_after_update_test_case_execution_info";
ALTER TABLE "dev_instruction_result" enable TRIGGER "dev_instruction_result_after_update_test_case_execution_info";
ALTER TABLE "run" enable TRIGGER "run_update_log";
ALTER TABLE "run" enable TRIGGER "run_update_created_at_updated_at_uuid";