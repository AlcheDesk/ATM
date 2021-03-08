CREATE OR REPLACE FUNCTION "run_set_insert_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	------------------------------------
	-- run_set_alias_name_map
	insert into run_set_alias_name_map (run_set_id, alias_names) 
	values (NEW.id, 
	        '{}'::text[]
	       );
	------------------------------------  
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------
CREATE TRIGGER "run_set_after_insert_change_others"
  AFTER INSERT ON run_set
  FOR EACH ROW
EXECUTE PROCEDURE run_set_insert_change_others();
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_set_update_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	------------------------------------
	IF NEW.is_deleted IS TRUE THEN
		DELETE FROM run_set_alias_link WHERE run_set_id = NEW.id;
		DELETE FROM run_set_notification_link WHERE run_set_id = NEW.id;
		DELETE FROM run_set_test_case_link WHERE run_set_id = NEW.id;
	END IF;
	------------------------------------  
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------
CREATE TRIGGER "run_set_after_update_change_others"
  AFTER UPDATE ON run_set
  FOR EACH ROW
EXECUTE PROCEDURE run_set_update_change_others();
--------------------------------------------------------------------------------------------------------
DROP TRIGGER "run_set_after_insert_run_set_alias_name_map" ON "run_set";
DROP FUNCTION "run_set_insert_run_set_alias_name_map" ( );
DROP TRIGGER "run_set_after_update_delete_rsal" ON "run_set";
DROP FUNCTION "run_set_delete_delete_rsal" ( );
DROP TRIGGER "run_set_after_update_delete_rsnl" ON "run_set";
DROP FUNCTION "run_set_delete_delete_rsnl" ( );
DROP TRIGGER "run_set_after_update_delete_rstcl" ON "run_set";
DROP FUNCTION "run_set_delete_delete_rstcl" ( );
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "insert_run_set_fields" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF TG_OP = 'UPDATE' THEN 
        NEW.aliases = ARRAY[]::text[];
    END IF;
    RETURN NEW;   
END;
$body$ LANGUAGE plpgsql;
ALTER FUNCTION "insert_run_set_fields" ( ) RENAME TO run_set_generate_columns;
DROP TRIGGER "run_set_before_insert_fields" ON "run_set";
CREATE TRIGGER "run_set_generate_columns"
  BEFORE INSERT OR UPDATE ON run_set
  FOR EACH ROW
EXECUTE PROCEDURE run_set_generate_columns();