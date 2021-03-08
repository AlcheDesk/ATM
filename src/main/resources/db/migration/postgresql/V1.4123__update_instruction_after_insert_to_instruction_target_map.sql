------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "instruction_target_map_insert_update_instruction" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	update instruction set target = 
	concat_ws ('.', NEW.application_name, NEW.section_name, NEW.element_name, NEW.ref_test_case_name, NEW.test_case_share_folder_name),
	ref_test_case_overwrite_name = NEW.ref_test_case_overwrite_name
	where id = NEW.instruction_id;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "instruction_target_map_after_insert_update_instruction"
  AFTER INSERT ON instruction_target_map
  FOR EACH ROW
EXECUTE PROCEDURE instruction_target_map_insert_update_instruction();
------------------------------------------------------------------------------
UPDATE "instruction"
SET target = i.name, ref_test_case_overwrite_name = i.tcon
FROM (SELECT instruction_id, concat_ws ('.', application_name, section_name, element_name, ref_test_case_name, test_case_share_folder_name) as name,
ref_test_case_overwrite_name as tcon
    FROM instruction_target_map) i
WHERE 
    i.instruction_id = id;
------------------------------------------------------------------------------