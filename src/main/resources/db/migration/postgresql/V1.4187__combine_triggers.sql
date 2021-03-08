ALTER FUNCTION "tcitm_insert_insert_tcdtm" ( ) RENAME TO test_case_instruction_type_map_insert_change_others;
ALTER TRIGGER "test_case_instruction_type_map_after_insert_tcdtm" ON "test_case_instruction_type_map" RENAME TO test_case_instruction_type_map_after_insert_change_others;

ALTER FUNCTION "tcitm_update_update_tcdtm" ( ) RENAME TO test_case_instruction_type_map_update_change_others;
ALTER TRIGGER "test_case_instruction_type_map_after_update_tcdtm" ON "test_case_instruction_type_map" RENAME TO test_case_instruction_type_map_after_update_change_others;

ALTER FUNCTION "tcitm_delete_delete_tcdtm" ( ) RENAME TO test_case_instruction_type_map_delete_change_others;
ALTER TRIGGER "test_case_instruction_type_map_before_delete_tcdtm" ON "test_case_instruction_type_map" RENAME TO test_case_instruction_type_map_before_delete_change_others;

ALTER FUNCTION "tag_delete_delete_tctl" ( ) RENAME TO tag_delete_change_others;
ALTER TRIGGER "tag_before_delete_delete_tctl" ON "tag" RENAME TO tag_before_delete_change_others;

ALTER FUNCTION "system_requirement_delete_delete_srpsrl" ( ) RENAME TO system_requirement_delete_change_others;
ALTER TRIGGER "system_requirement_before_delete_delete_srpsrl" ON "system_requirement" RENAME TO system_requirement_before_delete_change_others;

ALTER FUNCTION "system_requirement_pack_delete_delete_srpsrl" ( ) RENAME TO system_requirement_pack_delete_change_others;
ALTER TRIGGER "system_requirement_pack_after_update_delete_srpsrl" ON "system_requirement_pack" RENAME TO system_requirement_pack_after_update_change_others;

ALTER FUNCTION "insert_system_requirement_type_id" ( ) RENAME TO system_requirement_pack_system_requirement_link_generate_columns;
ALTER TRIGGER "srpsrl_before_update_system_requirement_type" ON "system_requirement_pack_system_requirement_link" RENAME TO system_requirement_pack_system_requirement_link_generate_columns;

CREATE OR REPLACE FUNCTION "modified_name_default" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.modified_name IS NULL AND NEW.original_name IS NOT NULL THEN
        NEW.modified_name = NEW.original_name;
    END IF;
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

ALTER FUNCTION "modified_name_default" ( ) RENAME TO user_content_generate_columns;
DROP TRIGGER "modified_name_default" ON "user_content";
CREATE TRIGGER "user_content_generate_columns"
  BEFORE INSERT ON user_content
  FOR EACH ROW
EXECUTE PROCEDURE user_content_generate_columns();