CREATE OR REPLACE FUNCTION "tag_delete_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	DELETE FROM test_case_tag_link
	WHERE tag_id = OLD.id;
	RETURN NULL;
END;
$body$ LANGUAGE plpgsql;
DROP TRIGGER "tag_before_delete_change_others" ON "tag";
CREATE TRIGGER "tag_after_delete_change_others"
  AFTER DELETE ON tag
  FOR EACH ROW
EXECUTE PROCEDURE tag_delete_change_others();
----------------------------------------------------------------------------------------------------------------------------------
ALTER FUNCTION "rsal_delete_update_run_set_alias_name_map" ( ) RENAME TO run_set_alias_link_delete_change_others;
ALTER TRIGGER "rsal_delete_update_run_set_alias_name_map" ON "run_set_alias_link" RENAME TO run_set_alias_link_after_delete_change_others;
----------------------------------------------------------------------------------------------------------------------------------
ALTER FUNCTION "rsal_update_run_set_alias_name_map" ( ) RENAME TO run_set_alias_link_change_others;
ALTER TRIGGER "run_set_alias_link_after_insert_or_update_rsanm" ON "run_set_alias_link" RENAME TO run_set_alias_link_change_others;
----------------------------------------------------------------------------------------------------------------------------------
ALTER TABLE "system_requirement_pack_system_requirement_link" DROP CONSTRAINT "srpsrl_fk_system_requirement";
ALTER TABLE "system_requirement_pack_system_requirement_link" DROP CONSTRAINT "srpsrl_fk_system_requirement_ant_type";
ALTER TABLE "system_requirement_pack_system_requirement_link" ADD CONSTRAINT 
srpsrl_fk_system_requirement FOREIGN KEY (system_requirement_id) 
REFERENCES "system_requirement" ("id") DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE "system_requirement_pack_system_requirement_link" ADD CONSTRAINT 
srpsrl_fk_system_requirement_and_type FOREIGN KEY ("system_requirement_id", "system_requirement_type_id") 
REFERENCES "system_requirement" ("id", "system_requirement_type_id") DEFERRABLE INITIALLY DEFERRED;
DROP TRIGGER "system_requirement_before_delete_change_others" ON "system_requirement";
CREATE TRIGGER "system_requirement_after_delete_change_others"
  AFTER DELETE ON system_requirement
  FOR EACH ROW
EXECUTE PROCEDURE system_requirement_delete_change_others();
CREATE OR REPLACE FUNCTION "system_requirement_delete_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	DELETE FROM system_requirement_pack_system_requirement_link
	WHERE system_requirement_id = OLD.id;
	RETURN NULL;
END;
$body$ LANGUAGE plpgsql;