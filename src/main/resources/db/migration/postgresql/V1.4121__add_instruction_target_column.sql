ALTER TABLE "instruction" ADD COLUMN target text;
------------------------------------------------------------------------------
UPDATE "instruction"
SET target = i.name
FROM (SELECT instruction_id, concat_ws ('.', application_name, section_name, element_name, ref_test_case_name, test_case_share_folder_name) as name
    FROM instruction_target_map) i
WHERE 
    i.instruction_id = id;
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "instruction_target_map_update_instruction" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	update instruction set target = 
	concat_ws ('.', NEW.application_name, NEW.section_name, NEW.element_name, NEW.ref_test_case_name, NEW.test_case_share_folder_name) 
	where id = NEW.instruction_id;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "instruction_target_map_after_update_update_instruction"
  AFTER UPDATE ON instruction_target_map
  FOR EACH ROW
EXECUTE PROCEDURE instruction_target_map_update_instruction();
------------------------------------------------------------------------------