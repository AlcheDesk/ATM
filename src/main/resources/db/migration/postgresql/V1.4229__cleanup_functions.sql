DROP FUNCTION "status_na_insert" ( );
DROP FUNCTION "run_status_trigger" ( );
ALTER TRIGGER "element_update_update_itm" ON "element" RENAME TO element_after_update_change_others;
ALTER FUNCTION "element_update_instruction_target_map" ( ) RENAME TO element_update_change_others;
DROP TRIGGER "element_before_update" ON "element";
DROP FUNCTION "before_element_update" ( );
CREATE OR REPLACE FUNCTION "element_update_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.name IS DISTINCT FROM OLD.name THEN
		update instruction_target_map set element_name = NEW.name where element_id = NEW.id;
	END IF;
	IF NEW.element_type_id IS DISTINCT FROM OLD.element_type_id THEN
		UPDATE instruction SET element_type_id = NEW.element_type_id WHERE element_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

ALTER TRIGGER "user_activity_log_insert_created_at" ON "user_activity_log" RENAME TO user_activity_log_before_insert_timestamp;
ALTER TRIGGER "user_activity_log_update_created_at" ON "user_activity_log" RENAME TO user_activity_log_before_update_timestamp;