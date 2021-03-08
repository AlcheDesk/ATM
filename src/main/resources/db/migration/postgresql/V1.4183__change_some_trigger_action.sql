DROP TRIGGER "driver_before_delete_change_others" ON "driver";
CREATE TRIGGER "driver_after_delete_change_others"
  AFTER DELETE ON driver
  FOR EACH ROW
EXECUTE PROCEDURE driver_delete_change_others();
CREATE OR REPLACE FUNCTION "driver_delete_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN			  
	delete from driver_pack_driver_link where driver_id = OLD.id;
	RETURN NULL;
END;
$body$ LANGUAGE plpgsql;
ALTER TABLE "driver_pack_driver_link" DROP CONSTRAINT "driver_pack_driver_link_fk_driver";
ALTER TABLE "driver_pack_driver_link" DROP CONSTRAINT "driver_pack_driver_link_fk_driver_pack";
ALTER TABLE ONLY driver_pack_driver_link ADD CONSTRAINT driver_pack_driver_link_fk_driver_pack FOREIGN KEY (driver_pack_id) REFERENCES driver_pack(id) DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE ONLY driver_pack_driver_link ADD CONSTRAINT driver_pack_driver_link_fk_driver FOREIGN KEY (driver_id) REFERENCES driver(id) DEFERRABLE INITIALLY DEFERRED;
ALTER TRIGGER "instruction_target_map_after_insert_update_instruction" ON "instruction_target_map" RENAME TO instruction_target_map_after_insert_change_others;
ALTER TRIGGER "instruction_target_map_after_update_update_instruction" ON "instruction_target_map" RENAME TO instruction_target_map_after_update_change_others;
ALTER FUNCTION "instruction_target_map_insert_update_instruction" ( ) RENAME TO instruction_target_map_insert_change_others;
ALTER FUNCTION "instruction_target_map_update_instruction" ( ) RENAME TO instruction_target_map_update_change_others;
ALTER TRIGGER "module_after_update_delete_tcml" ON "module" RENAME TO module_after_update_change_others;
ALTER FUNCTION "module_delete_delete_tcml" ( ) RENAME TO module_update_change_others;
ALTER TRIGGER "notification_after_update_delete_nentl" ON "notification" RENAME TO notification_after_update_change_others;
ALTER FUNCTION "notification_delete_delete_nentl" ( ) RENAME TO notification_update_change_others;
CREATE OR REPLACE FUNCTION "notification_update_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.is_deleted IS TRUE THEN
		DELETE FROM notification_email_notification_target_link WHERE notification_id = NEW.id;
		DELETE FROM run_set_notification_link WHERE notification_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
DROP TRIGGER "notification_after_update_delete_rsnl" ON "notification";
DROP FUNCTION "notification_delete_delete_rsnl" ( );
ALTER TRIGGER "run_execution_info_after_insert_update_tcei" ON "run_execution_info" RENAME TO run_execution_info_after_insert_change_others;
ALTER FUNCTION "run_execution_info_insert_update_test_case_execution_info" ( ) RENAME TO run_execution_info_insert_change_others;
ALTER TRIGGER "run_execution_info_after_update_update_test_case_execution_info" ON "run_execution_info" RENAME TO run_execution_info_after_update_change_others;
ALTER FUNCTION "run_execution_info_update_test_case_execution_info" ( ) RENAME TO run_execution_info_update_change_others;