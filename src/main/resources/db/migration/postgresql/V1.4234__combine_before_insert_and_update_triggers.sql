DROP TRIGGER "alias_before_insert_timestamp" ON "alias";
DROP TRIGGER "alias_before_update_timestamp" ON "alias";

CREATE OR REPLACE FUNCTION "alias_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "alias_generate_columns"
  BEFORE INSERT OR UPDATE ON alias
  FOR EACH ROW
EXECUTE PROCEDURE alias_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "api_schema_before_update_copy_reference" ON "api_schema";

CREATE OR REPLACE FUNCTION "api_schema_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.copy_from_id = OLD.copy_from_id;
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "api_schema_generate_columns"
  BEFORE INSERT OR UPDATE ON api_schema
  FOR EACH ROW
EXECUTE PROCEDURE api_schema_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "application_before_insert_timestamp" ON "application";
DROP TRIGGER "application_before_update_copy_reference" ON "application";
DROP TRIGGER "application_before_update_log" ON "application";
DROP TRIGGER "application_before_update_timestamp" ON "application";

CREATE OR REPLACE FUNCTION "application_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
      ----------------
      IF NEW.log IS NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created.';
      ELSIF NEW.log IS NOT NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created with initial log:' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
      NEW.copy_from_id = OLD.copy_from_id;
      ----------------
      IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL THEN
        NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL THEN
        NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "application_generate_columns"
  BEFORE INSERT OR UPDATE ON application
  FOR EACH ROW
EXECUTE PROCEDURE application_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "content_archive_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.content_sha2 = md5(NEW.content_json::text)::uuid;
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      ----------------
      IF OLD.content_json IS DISTINCT FROM NEW.content_json THEN
        NEW.content_sha2 = md5(NEW.content_json::text)::uuid;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "dev_execution_log_before_insert_timestamp" ON "dev_execution_log";
DROP TRIGGER "dev_execution_log_before_update_timestamp" ON "dev_execution_log";

CREATE OR REPLACE FUNCTION "execution_log_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "dev_execution_log_generate_columns"
  BEFORE INSERT OR UPDATE ON dev_execution_log
  FOR EACH ROW
EXECUTE PROCEDURE execution_log_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "prod_execution_log_before_insert_timestamp" ON "prod_execution_log";
DROP TRIGGER "prod_execution_log_before_update_timestamp" ON "prod_execution_log";

CREATE TRIGGER "prod_execution_log_generate_columns"
  BEFORE INSERT OR UPDATE ON prod_execution_log
  FOR EACH ROW
EXECUTE PROCEDURE execution_log_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "dev_file_before_insert_timestamp" ON "dev_file";
DROP TRIGGER "dev_file_before_update_timestamp" ON "dev_file";

CREATE OR REPLACE FUNCTION "file_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "dev_file_generate_columns"
  BEFORE INSERT OR UPDATE ON dev_file
  FOR EACH ROW
EXECUTE PROCEDURE file_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "prod_file_before_insert_timestamp" ON "prod_file";
DROP TRIGGER "prod_file_before_update_timestamp" ON "prod_file";

CREATE TRIGGER "prod_file_generate_columns"
  BEFORE INSERT OR UPDATE ON prod_file
  FOR EACH ROW
EXECUTE PROCEDURE file_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "dev_instruction_result_before_insert_timestamp" ON "dev_instruction_result";
DROP TRIGGER "dev_instruction_result_before_update_finished" ON "dev_instruction_result";
DROP TRIGGER "dev_instruction_result_before_update_instruction_option_log" ON "dev_instruction_result";
DROP TRIGGER "dev_instruction_result_before_update_log" ON "dev_instruction_result";
DROP TRIGGER "dev_instruction_result_before_update_timestamp" ON "dev_instruction_result";

CREATE OR REPLACE FUNCTION "instruction_result_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
      ----------------
      IF NEW.log IS NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created.';
      ELSIF NEW.log IS NOT NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created with initial log:' || NEW.log;
      END IF;
      ----------------
      IF NEW.instruction IS NOT NULL THEN
        NEW.target = NEW.instruction->>'target';
      ELSIF NEW.instruction IS NULL THEN
        NEW.target = NULL;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
      NEW.copy_from_id = OLD.copy_from_id;
      ----------------
      IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL THEN
        NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL THEN
        NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      END IF;
      ----------------
      IF NEW.instruction IS NOT NULL AND NEW.instruction <> OLD.instruction THEN
        NEW.target = NEW.instruction->>'target';
      ELSIF NEW.instruction IS NULL THEN
        NEW.target = NULL;
      END IF;
      ----------------
      IF OLD.instruction_option_log IS NOT NULL AND NEW.instruction_option_log IS NOT NULL THEN
        NEW.instruction_option_log = OLD.instruction_option_log || chr(10) || NEW.instruction_option_log;        
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "prod_instruction_result_before_insert_timestamp" ON "prod_instruction_result";
DROP TRIGGER "prod_instruction_result_before_update_finished" ON "prod_instruction_result";
DROP TRIGGER "prod_instruction_result_before_update_instruction_option_log" ON "prod_instruction_result";
DROP TRIGGER "prod_instruction_result_before_update_log" ON "prod_instruction_result";
DROP TRIGGER "prod_instruction_result_before_update_timestamp" ON "prod_instruction_result";
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "dev_step_log_before_insert_timestamp" ON "dev_step_log";
DROP TRIGGER "dev_step_log_before_update_timestamp" ON "dev_step_log";

CREATE OR REPLACE FUNCTION "step_log_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "dev_step_log_generate_columns"
  BEFORE INSERT OR UPDATE ON dev_step_log
  FOR EACH ROW
EXECUTE PROCEDURE step_log_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "prod_step_log_before_insert_timestamp" ON "prod_step_log";
DROP TRIGGER "prod_step_log_before_update_timestamp" ON "prod_step_log";

CREATE TRIGGER "prod_step_log_generate_columns"
  BEFORE INSERT OR UPDATE ON prod_step_log
  FOR EACH ROW
EXECUTE PROCEDURE step_log_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "driver_before_insert_timestamp" ON "driver";
DROP TRIGGER "driver_before_update_copy_reference" ON "driver";
DROP TRIGGER "driver_before_update_log" ON "driver";
DROP TRIGGER "driver_before_update_timestamp" ON "driver";
DROP TRIGGER "driver_before_update_driver_type" ON "driver";

CREATE OR REPLACE FUNCTION "driver_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
      ----------------
      IF NEW.log IS NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created.';
      ELSIF NEW.log IS NOT NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created with initial log:' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
      NEW.copy_from_id = OLD.copy_from_id;
      NEW.driver_type_id = OLD.driver_type_id;
      ----------------
      IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL THEN
        NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL THEN
        NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "driver_generate_columns"
  BEFORE INSERT OR UPDATE ON driver
  FOR EACH ROW
EXECUTE PROCEDURE driver_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "driver_pack_before_insert_timestamp" ON "driver_pack";
DROP TRIGGER "driver_pack_before_update_copy_reference" ON "driver_pack";
DROP TRIGGER "driver_pack_before_update_log" ON "driver_pack";
DROP TRIGGER "driver_pack_before_update_timestamp" ON "driver_pack";

CREATE OR REPLACE FUNCTION "driver_pack_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
      ----------------
      IF NEW.log IS NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created.';
      ELSIF NEW.log IS NOT NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created with initial log:' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
      NEW.copy_from_id = OLD.copy_from_id;
      ----------------
      IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL THEN
        NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL THEN
        NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "driver_pack_generate_columns"
  BEFORE INSERT OR UPDATE ON driver_pack
  FOR EACH ROW
EXECUTE PROCEDURE driver_pack_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "element_before_insert_timestamp" ON "element";
DROP TRIGGER "element_before_update_copy_reference" ON "element";
DROP TRIGGER "element_before_update_log" ON "element";
DROP TRIGGER "element_before_update_timestamp" ON "element";

CREATE OR REPLACE FUNCTION "element_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
      ----------------
      IF NEW.log IS NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created.';
      ELSIF NEW.log IS NOT NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created with initial log:' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
      NEW.copy_from_id = OLD.copy_from_id;
      ----------------
      IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL THEN
        NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL THEN
        NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "element_generate_columns"
  BEFORE INSERT OR UPDATE ON element
  FOR EACH ROW
EXECUTE PROCEDURE element_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "email_notification_target_before_insert_timestamp" ON "email_notification_target";
DROP TRIGGER "email_notification_target_before_update_timestamp" ON "email_notification_target";

CREATE OR REPLACE FUNCTION "email_notification_target_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "email_notification_target_generate_columns"
  BEFORE INSERT OR UPDATE ON email_notification_target
  FOR EACH ROW
EXECUTE PROCEDURE email_notification_target_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "instruction_before_insert_timestamp" ON "instruction";
DROP TRIGGER "instruction_before_update_copy_reference" ON "instruction";
DROP TRIGGER "instruction_before_update_log" ON "instruction";
DROP TRIGGER "instruction_before_update_timestamp" ON "instruction";
DROP TRIGGER "instruction_before_update_instruction_type" ON "instruction";

CREATE OR REPLACE FUNCTION "instruction_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
      NEW.target = null;
      NEW.ref_test_case_overwrite_name = null;  
      ----------------
      IF NEW.log IS NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created.';
      ELSIF NEW.log IS NOT NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created with initial log:' || NEW.log;
      END IF;
      ----------------
      IF NEW.instruction_type_id IS NOT NULL THEN
        NEW.driver_type_id = (SELECT driver_type_id FROM driver_type_instruction_type_link WHERE instruction_type_id = NEW.instruction_type_id);
      ELSIF NEW.instruction_type_id IS NULL THEN
        NEW.driver_type_id = NULL; 
      END IF;
      ----------------
      IF NEW.element_id IS NOT NULL THEN
        NEW.element_type_id = (SELECT element_type_id FROM element WHERE id = NEW.element_id);
      ELSIF NEW.element_id IS NULL THEN
        NEW.element_type_id = NULL;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
      NEW.copy_from_id = OLD.copy_from_id;
      NEW.instruction_type_id = OLD.instruction_type_id;
      ----------------
      IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL THEN
        NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL THEN
        NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      END IF;
      ----------------
      IF NEW.ref_test_case_overwrite_id IS DISTINCT FROM OLD.ref_test_case_overwrite_id AND NEW.ref_test_case_overwrite_id IS NULL THEN
        NEW.ref_test_case_overwrite_name = NULL;
      ELSIF NEW.ref_test_case_overwrite_id IS DISTINCT FROM OLD.ref_test_case_overwrite_id THEN
        NEW.ref_test_case_overwrite_name = (SELECT name FROM test_case_overwrite WHERE id = NEW.ref_test_case_overwrite_id);
      END IF;
      ----------------
      IF NEW.element_id IS NOT NULL AND NEW.element_id IS DISTINCT FROM OLD.element_id THEN
        NEW.element_type_id = (SELECT element_type_id FROM element WHERE id = NEW.element_id);
      ELSIF NEW.element_id IS NULL THEN
        NEW.element_type_id = NULL;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
ALTER TRIGGER "instruction_action_instruction_option_link_after_delete_change_" ON "instruction_action_instruction_option_link" RENAME TO iaiol_after_delete_change_others;
DROP TRIGGER "instruction_action_instruction_option_link_change_others" ON "instruction_action_instruction_option_link";

CREATE OR REPLACE FUNCTION "instruction_action_instruction_option_link_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    ------------------------------------
    UPDATE instruction_option_map SET instruction_action_ids = 
    (SELECT case when array_agg(distinct(link.instruction_action_id)) = '{null}'::bigint[] then '{}'::bigint[] else array_agg(distinct(link.instruction_action_id)) end
    from instruction_action_instruction_option_link link where link.instruction_option_id = NEW.instruction_option_id
    group by link.instruction_option_id)
    WHERE instruction_option_id = NEW.instruction_option_id;
    ------------------------------------  
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

ALTER FUNCTION "instruction_action_instruction_option_link_change_others" ( ) RENAME TO iaiol_change_others;

CREATE TRIGGER "iaiol_after_update_change_others"
  AFTER UPDATE ON instruction_action_instruction_option_link
  FOR EACH ROW
EXECUTE PROCEDURE iaiol_change_others();

CREATE TRIGGER "iaiol_after_insert_change_others"
  AFTER INSERT ON instruction_action_instruction_option_link
  FOR EACH ROW
EXECUTE PROCEDURE iaiol_change_others();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "instruction_bundle_before_insert_timestamp" ON "instruction_bundle";
DROP TRIGGER "instruction_bundle_before_update_timestamp" ON "instruction_bundle";

CREATE OR REPLACE FUNCTION "instruction_bundle_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "instruction_bundle_generate_columns"
  BEFORE INSERT OR UPDATE ON instruction_bundle
  FOR EACH ROW
EXECUTE PROCEDURE instruction_bundle_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "instruction_bundle_entry_before_insert_timestamp" ON "instruction_bundle_entry";
DROP TRIGGER "instruction_bundle_entry_before_update_timestamp" ON "instruction_bundle_entry";

CREATE OR REPLACE FUNCTION "instruction_bundle_entry_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "instruction_bundle_entry_generate_columns"
  BEFORE INSERT OR UPDATE ON instruction_bundle_entry
  FOR EACH ROW
EXECUTE PROCEDURE instruction_bundle_entry_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "instruction_option_before_insert_timestamp" ON "instruction_option";
DROP TRIGGER "instruction_option_before_update_timestamp" ON "instruction_option";

CREATE OR REPLACE FUNCTION "instruction_option_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "instruction_option_generate_columns"
  BEFORE INSERT OR UPDATE ON instruction_option
  FOR EACH ROW
EXECUTE PROCEDURE instruction_option_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "instruction_overwrite_before_update_log" ON "instruction_overwrite";

CREATE OR REPLACE FUNCTION "instruction_overwrite_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      ----------------
      IF NEW.log IS NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created.';
      ELSIF NEW.log IS NOT NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created with initial log:' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      ----------------
      IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL THEN
        NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL THEN
        NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "instruction_overwrite_generate_columns"
  BEFORE INSERT OR UPDATE ON instruction_overwrite
  FOR EACH ROW
EXECUTE PROCEDURE instruction_overwrite_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "module_before_insert_timestamp" ON "module";
DROP TRIGGER "module_before_update_log" ON "module";
DROP TRIGGER "module_before_update_timestamp" ON "module";

CREATE OR REPLACE FUNCTION "module_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
      ----------------
      IF NEW.log IS NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created.';
      ELSIF NEW.log IS NOT NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created with initial log:' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
      ----------------
      IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL THEN
        NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL THEN
        NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "module_generate_columns"
  BEFORE INSERT OR UPDATE ON module
  FOR EACH ROW
EXECUTE PROCEDURE module_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "notification_before_insert_timestamp" ON "notification";
DROP TRIGGER "notification_before_update_log" ON "notification";
DROP TRIGGER "notification_before_update_timestamp" ON "notification";

CREATE OR REPLACE FUNCTION "notification_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
      ----------------
      IF NEW.log IS NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created.';
      ELSIF NEW.log IS NOT NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created with initial log:' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
      ----------------
      IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL THEN
        NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL THEN
        NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "notification_generate_columns"
  BEFORE INSERT OR UPDATE ON notification
  FOR EACH ROW
EXECUTE PROCEDURE notification_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "project_before_insert_timestamp" ON "project";
DROP TRIGGER "project_before_update_copy_reference" ON "project";
DROP TRIGGER "project_before_update_log" ON "project";
DROP TRIGGER "project_before_update_timestamp" ON "project";

CREATE OR REPLACE FUNCTION "project_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
      ----------------
      IF NEW.log IS NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created.';
      ELSIF NEW.log IS NOT NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created with initial log:' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
      NEW.copy_from_id = OLD.copy_from_id;
      NEW.uuid = OLD.uuid;
      ----------------
      IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL THEN
        NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL THEN
        NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "project_generate_columns"
  BEFORE INSERT OR UPDATE ON project
  FOR EACH ROW
EXECUTE PROCEDURE project_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "run_before_insert_timestamp" ON "run";
DROP TRIGGER "run_before_update_log" ON "run";
DROP TRIGGER "run_before_update_timestamp_with_uuid" ON "run";
DROP TRIGGER "run_before_update_finished" ON "run";

CREATE OR REPLACE FUNCTION "run_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
      NEW.end_at = NULL;
      NEW.is_finished = FALSE;
      NEW.start_at = NULL;
      NEW.test_case_id = (NEW.test_case->>'id')::bigint;
      NEW.project_id = (NEW.test_case->>'projectId')::bigint;
      ----------------
      IF NEW.log IS NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created.';
      ELSIF NEW.log IS NOT NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created with initial log:' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
      NEW.copy_from_id = OLD.copy_from_id;
      NEW.test_case_id = OLD.test_case_id;
      NEW.project_id = OLD.project_id;
      ----------------
      IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL THEN
        NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL THEN
        NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      END IF;
      ----------------
      IF NEW.is_finished IS TRUE AND OLD.is_finished IS FALSE AND NEW.start_at IS NOT NULL THEN
        NEW.end_at = now();
      ELSIF OLD.is_finished IS TRUE THEN
        NEW.end_at = OLD.end_at;
        NEW.is_finished = OLD.is_finished;
        NEW.start_at = OLD.start_at;
      END IF;
      ----------------
      IF OLD.status_id = 1 AND NEW.status_id <> 1 AND OLD.start_at IS NULL THEN
        NEW.start_at = now();
      ELSIF OLD.start_at IS NOT NULL THEN
        NEW.start_at = OLD.start_at;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "run_set_before_insert_timestamp" ON "run_set";
DROP TRIGGER "run_set_before_update_copy_reference" ON "run_set";
DROP TRIGGER "run_set_before_update_log" ON "run_set";
DROP TRIGGER "run_set_before_update_timestamp_with_uuid" ON "run_set";

CREATE OR REPLACE FUNCTION "run_set_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
      NEW.aliases = ARRAY[]::text[];
      ----------------
      IF NEW.log IS NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created.';
      ELSIF NEW.log IS NOT NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created with initial log:' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
      NEW.copy_from_id = OLD.copy_from_id;
      NEW.uuid = OLD.uuid;
      ----------------
      IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL THEN
        NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL THEN
        NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "run_set_alias_link_change_others" ON "run_set_alias_link";

CREATE TRIGGER "run_set_alias_link_after_update_change_others"
  AFTER UPDATE ON run_set_alias_link
  FOR EACH ROW
EXECUTE PROCEDURE run_set_alias_link_change_others();

CREATE TRIGGER "run_set_alias_link_after_insert_change_others"
  AFTER INSERT ON run_set_alias_link
  FOR EACH ROW
EXECUTE PROCEDURE run_set_alias_link_change_others();

CREATE OR REPLACE FUNCTION "run_set_alias_link_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    UPDATE run_set_alias_name_map
    SET 
      alias_names = types.names
    FROM
      (SELECT 
         CASE
           WHEN array_agg(distinct(name)) = '{null}'::text[] THEN '{}'::text[]
           WHEN array_agg(distinct(name)) IS NULL THEN '{}'::text[]
           ELSE array_agg(distinct(name))
         END
      AS names FROM alias al
      left join run_set_alias_link link
      on al.id = link.alias_id
      WHERE al.is_deleted IS FALSE AND link.run_set_id = NEW.run_set_id) types
    WHERE 
      run_set_id = NEW.run_set_id;
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
ALTER TRIGGER "run_set_alias_name_map_after_insert_update_run_set" ON "run_set_alias_name_map" RENAME TO run_set_alias_name_map_after_insert_change_others;
ALTER TRIGGER "run_set_alias_name_map_after_update_update_run_set" ON "run_set_alias_name_map" RENAME TO run_set_alias_name_map_after_update_change_others;
ALTER FUNCTION "run_set_alias_name_map_insert_update_run_set" ( ) RENAME TO run_set_alias_name_map_insert_change_others;
ALTER FUNCTION "run_set_alias_name_map_update_run_set" ( ) RENAME TO run_set_alias_name_map_update_change_others;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "run_set_job_link_before_insert_timestamp" ON "run_set_job_link";
DROP TRIGGER "run_set_job_link_before_update_timestamp" ON "run_set_job_link";

CREATE OR REPLACE FUNCTION "run_set_job_link_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "run_set_job_link_generate_columns"
  BEFORE INSERT OR UPDATE ON run_set_job_link
  FOR EACH ROW
EXECUTE PROCEDURE run_set_job_link_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "run_set_result_before_insert_timestamp" ON "run_set_result";
DROP TRIGGER "run_set_result_before_update_log" ON "run_set_result";
DROP TRIGGER "run_set_result_before_update_timestamp_with_uuid" ON "run_set_result";
DROP TRIGGER "run_set_result_before_update_finished" ON "run_set_result";

CREATE OR REPLACE FUNCTION "run_set_result_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
      NEW.end_at = NULL;
      NEW.is_finished = FALSE;
      NEW.start_at = NULL;
      ----------------
      IF NEW.log IS NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created.';
      ELSIF NEW.log IS NOT NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created with initial log:' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
      NEW.copy_from_id = OLD.copy_from_id;
      NEW.uuid = OLD.uuid;
      ----------------
      IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL THEN
        NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL THEN
        NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      END IF;
      ----------------
      IF NEW.is_finished IS TRUE AND OLD.is_finished IS FALSE AND NEW.start_at IS NOT NULL THEN
        NEW.end_at = now();
      ELSIF OLD.is_finished IS TRUE THEN
        NEW.end_at = OLD.end_at;
        NEW.is_finished = OLD.is_finished;
        NEW.start_at = OLD.start_at;
      END IF;
      ----------------
      IF OLD.status_id = 1 AND NEW.status_id <> 1 AND OLD.start_at IS NULL THEN
        NEW.start_at = now();
      ELSIF OLD.start_at IS NOT NULL THEN
        NEW.start_at = OLD.start_at;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "run_set_result_job_link_before_insert_timestamp" ON "run_set_result_job_link";
DROP TRIGGER "run_set_result_job_link_before_update_timestamp" ON "run_set_result_job_link";

CREATE OR REPLACE FUNCTION "run_set_result_job_link_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "run_set_result_job_link_generate_columns"
  BEFORE INSERT OR UPDATE ON run_set_result_job_link
  FOR EACH ROW
EXECUTE PROCEDURE run_set_result_job_link_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "run_task_link_before_insert_timestamp" ON "run_task_link";
DROP TRIGGER "run_task_link_before_update_timestamp" ON "run_task_link";

CREATE OR REPLACE FUNCTION "run_task_link_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "run_task_link_generate_columns"
  BEFORE INSERT OR UPDATE ON run_task_link
  FOR EACH ROW
EXECUTE PROCEDURE run_task_link_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "section_before_insert_timestamp" ON "section";
DROP TRIGGER "section_before_update_copy_reference" ON "section";
DROP TRIGGER "section_before_update_log" ON "section";
DROP TRIGGER "section_before_update_timestamp" ON "section";

CREATE OR REPLACE FUNCTION "section_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
      ----------------
      IF NEW.log IS NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created.';
      ELSIF NEW.log IS NOT NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created with initial log:' || NEW.log;
      END IF;
      ----------------
      IF NEW.application_id IS NOT NULL THEN
        NEW.project_id = (SELECT project_id FROM application WHERE id = NEW.application_id);
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
      NEW.copy_from_id = OLD.copy_from_id;
      ----------------
      IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL THEN
        NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL THEN
        NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      END IF;
      ----------------
      IF NEW.project_id IS NOT NULL AND NEW.application_id IS DISTINCT FROM OLD.application_id THEN
        NEW.project_id = (SELECT project_id FROM application WHERE id = NEW.application_id);
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "system_requirement_pack_before_update_copy_reference" ON "system_requirement_pack";
DROP TRIGGER "system_requirement_pack_before_update_log" ON "system_requirement_pack";
DROP TRIGGER "system_requirement_pack_before_update_timestamp" ON "system_requirement_pack";

CREATE OR REPLACE FUNCTION "system_requirement_pack_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
      ----------------
      IF NEW.log IS NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created.';
      ELSIF NEW.log IS NOT NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created with initial log:' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
      NEW.copy_from_id = OLD.copy_from_id;
      ----------------
      IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL THEN
        NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL THEN
        NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "system_requirement_pack_generate_columns"
  BEFORE INSERT OR UPDATE ON system_requirement_pack
  FOR EACH ROW
EXECUTE PROCEDURE system_requirement_pack_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "system_requirement_pack_system_requirement_link_generate_column" ON "system_requirement_pack_system_requirement_link";
CREATE TRIGGER "srpsrl_generate_columns"
  BEFORE INSERT OR UPDATE ON system_requirement_pack_system_requirement_link
  FOR EACH ROW
EXECUTE PROCEDURE system_requirement_pack_system_requirement_link_generate_column();
ALTER FUNCTION "system_requirement_pack_system_requirement_link_generate_column" ( ) RENAME TO srpsrl_generate_columns;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "tag_before_insert_timestamp" ON "tag";
DROP TRIGGER "tag_before_update_timestamp" ON "tag";

CREATE OR REPLACE FUNCTION "tag_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "tag_generate_columns"
  BEFORE INSERT OR UPDATE ON tag
  FOR EACH ROW
EXECUTE PROCEDURE tag_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "template_before_insert_timestamp" ON "template";
DROP TRIGGER "template_before_update_log" ON "template";
DROP TRIGGER "template_before_update_timestamp" ON "template";

CREATE OR REPLACE FUNCTION "template_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
      ----------------
      IF NEW.log IS NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created.';
      ELSIF NEW.log IS NOT NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created with initial log:' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
      ----------------
      IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL THEN
        NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL THEN
        NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "template_generate_columns"
  BEFORE INSERT OR UPDATE ON template
  FOR EACH ROW
EXECUTE PROCEDURE template_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "test_case_before_insert_timestamp" ON "test_case";
DROP TRIGGER "test_case_before_update_copy_reference" ON "test_case";
DROP TRIGGER "test_case_before_update_log" ON "test_case";
DROP TRIGGER "test_case_before_update_timestamp_with_uuid" ON "test_case";
DROP TRIGGER "test_case_before_update_delete_or_insert_tcdam" ON "test_case";
DROP FUNCTION "test_case_update_delete_or_insert_tcdam" ( );

CREATE OR REPLACE FUNCTION "test_case_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
      NEW.is_deleted = FALSE;
      ----------------
      IF NEW.log IS NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created.';
      ELSIF NEW.log IS NOT NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created with initial log:' || NEW.log;
      END IF;
      ----------------
      IF NEW.project_id IS NOT NULL THEN
        NEW.project_name = (SELECT name FROM project WHERE id = NEW.project_id);
      ELSE
        NEW.project_name = NULL;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.id = OLD.id;
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
      NEW.copy_from_id = OLD.copy_from_id;
      NEW.uuid = OLD.uuid;
      ----------------
      IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL THEN
        NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL THEN
        NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      END IF;
      ----------------
      IF NEW.project_id IS NOT NULL AND NEW.project_id IS DISTINCT FROM OLD.project_id THEN
        NEW.project_name = (SELECT name FROM project WHERE id = NEW.project_id);
      ELSIF NEW.project_id IS NULL THEN
        NEW.project_name = NULL;
      END IF;
      ----------------
      IF NEW.is_deleted IS TRUE AND OLD.is_deleted IS FALSE THEN
        DELETE FROM test_case_driver_alias_map
        WHERE test_case_id = NEW.id;
      ELSIF NEW.is_deleted IS FALSE AND OLD.is_deleted IS TRUE THEN
        INSERT INTO test_case_driver_alias_map (test_case_id, driver_aliases) 
        VALUES (NEW.id, '{}'::text[] );
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
ALTER TRIGGER "test_case_execution_info_update_change_others" ON "test_case_execution_info" RENAME TO test_case_execution_info_after_update_change_others;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "test_case_option_before_insert_timestamp" ON "test_case_option";
DROP TRIGGER "test_case_option_before_update_timestamp" ON "test_case_option";

CREATE OR REPLACE FUNCTION "test_case_option_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "test_case_option_generate_columns"
  BEFORE INSERT OR UPDATE ON test_case_option
  FOR EACH ROW
EXECUTE PROCEDURE test_case_option_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "test_case_overwrite_before_insert_timestamp" ON "test_case_overwrite";
DROP TRIGGER "test_case_overwrite_before_update_log" ON "test_case_overwrite";
DROP TRIGGER "test_case_overwrite_before_update_timestamp" ON "test_case_overwrite";

CREATE OR REPLACE FUNCTION "test_case_overwrite_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
      ----------------
      IF NEW.log IS NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created.';
      ELSIF NEW.log IS NOT NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created with initial log:' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
      ----------------
      IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL THEN
        NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL THEN
        NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "test_case_overwrite_generate_columns"
  BEFORE INSERT OR UPDATE ON test_case_overwrite
  FOR EACH ROW
EXECUTE PROCEDURE test_case_overwrite_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "test_case_share_folder_before_insert_timestamp" ON "test_case_share_folder";
DROP TRIGGER "test_case_share_folder_before_update_copy_reference" ON "test_case_share_folder";
DROP TRIGGER "test_case_share_folder_before_update_log" ON "test_case_share_folder";
DROP TRIGGER "test_case_share_folder_before_update_timestamp" ON "test_case_share_folder";

CREATE OR REPLACE FUNCTION "test_case_share_folder_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
      ----------------
      IF NEW.log IS NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created.';
      ELSIF NEW.log IS NOT NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' Created with initial log:' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
      NEW.copy_from_id = OLD.copy_from_id;
      ----------------
      IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL THEN
        NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL THEN
        NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "test_case_share_folder_generate_columns"
  BEFORE INSERT OR UPDATE ON test_case_share_folder
  FOR EACH ROW
EXECUTE PROCEDURE test_case_share_folder_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "test_case_task_link_before_insert_timestamp" ON "test_case_task_link";
DROP TRIGGER "test_case_task_link_before_update_timestamp" ON "test_case_task_link";

CREATE OR REPLACE FUNCTION "test_case_task_link_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "test_case_task_link_generate_columns"
  BEFORE INSERT OR UPDATE ON test_case_task_link
  FOR EACH ROW
EXECUTE PROCEDURE test_case_task_link_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "user_activity_log_before_insert_timestamp" ON "user_activity_log";
DROP TRIGGER "user_activity_log_before_update_timestamp" ON "user_activity_log";

CREATE OR REPLACE FUNCTION "user_activity_log_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "user_activity_log_generate_columns"
  BEFORE INSERT OR UPDATE ON user_activity_log
  FOR EACH ROW
EXECUTE PROCEDURE user_activity_log_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER "user_content_before_insert_timestamp" ON "user_content";
DROP TRIGGER "user_content_before_update_timestamp" ON "user_content";
DROP TRIGGER "user_content_generate_columns" ON "user_content";

CREATE OR REPLACE FUNCTION "user_content_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
      ----------------
      IF NEW.modified_name IS NULL AND NEW.original_name IS NOT NULL THEN
        NEW.modified_name = NEW.original_name;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "user_content_generate_columns"
  BEFORE INSERT OR UPDATE ON user_content
  FOR EACH ROW
EXECUTE PROCEDURE user_content_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------