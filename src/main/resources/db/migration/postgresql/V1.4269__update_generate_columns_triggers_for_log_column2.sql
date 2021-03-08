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
      -- get the clean new log first
      IF NEW.log IS DISTINCT FROM OLD.log THEN
        NEW.log = replace(NEW.log, OLD.log, '');
        IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log <> '' THEN
          NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
        ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log <> '' THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
        END IF;
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
      NEW.copy_from_id = OLD.copy_from_id;
      ----------------
      -- get the clean new log first
      IF NEW.log IS DISTINCT FROM OLD.log THEN
        NEW.log = replace(NEW.log, OLD.log, '');
        IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log <> '' THEN
          NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
        ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log <> '' THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
        END IF;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
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
      -- get the clean new log first
      IF NEW.log IS DISTINCT FROM OLD.log THEN
        NEW.log = replace(NEW.log, OLD.log, '');
        IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log <> '' THEN
          NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
        ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log <> '' THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
        END IF;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
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
      -- get the clean new log first
      IF NEW.log IS DISTINCT FROM OLD.log THEN
        NEW.log = replace(NEW.log, OLD.log, '');
        IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log <> '' THEN
          NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
        ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log <> '' THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
        END IF;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
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
      -- get the clean new log first
      IF NEW.log IS DISTINCT FROM OLD.log THEN
        NEW.log = replace(NEW.log, OLD.log, '');
        IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log <> '' THEN
          NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
        ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log <> '' THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
        END IF;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
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
      -- get the clean new log first
      IF NEW.log IS DISTINCT FROM OLD.log THEN
        NEW.log = replace(NEW.log, OLD.log, '');
        IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log <> '' THEN
          NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
        ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log <> '' THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
        END IF;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
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
      -- get the clean new log first
      IF NEW.log IS DISTINCT FROM OLD.log THEN
        NEW.log = replace(NEW.log, OLD.log, '');
        IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log <> '' THEN
          NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
        ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log <> '' THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
        END IF;
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
      -- get the clean new log first
      IF NEW.log IS DISTINCT FROM OLD.log THEN
        NEW.log = replace(NEW.log, OLD.log, '');
        IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log <> '' THEN
          NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
        ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log <> '' THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
        END IF;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
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
      -- get the clean new log first
      IF NEW.log IS DISTINCT FROM OLD.log THEN
        NEW.log = replace(NEW.log, OLD.log, '');
        IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log <> '' THEN
          NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
        ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log <> '' THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
        END IF;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
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
      -- get the clean new log first
      IF NEW.log IS DISTINCT FROM OLD.log THEN
        NEW.log = replace(NEW.log, OLD.log, '');
        IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log <> '' THEN
          NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
        ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log <> '' THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
        END IF;
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
      NEW.copy_from_id = OLD.copy_from_id;
      ----------------
      -- get the clean new log first
      IF NEW.log IS DISTINCT FROM OLD.log THEN
        NEW.log = replace(NEW.log, OLD.log, '');
        IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log <> '' THEN
          NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
        ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log <> '' THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
        END IF;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
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
      -- get the clean new log first
      IF NEW.log IS DISTINCT FROM OLD.log THEN
        NEW.log = replace(NEW.log, OLD.log, '');
        IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log <> '' THEN
          NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
        ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log <> '' THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
        END IF;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;