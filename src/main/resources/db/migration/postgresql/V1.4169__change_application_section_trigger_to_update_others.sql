DROP TRIGGER "application_update_update_itm" ON "application";
ALTER FUNCTION "application_update_instruction_target_map" ( ) RENAME TO application_update_others;
CREATE TRIGGER "application_after_update_update_others"
  AFTER UPDATE ON application
  FOR EACH ROW
EXECUTE PROCEDURE application_update_others();
--------------------------------------------------------------------------------------------------------
DROP TRIGGER "section_update_update_itm" ON "section";
ALTER FUNCTION "section_update_instruction_target_map" ( ) RENAME TO section_update_others;
CREATE TRIGGER "section_after_update_update_others"
  AFTER UPDATE ON section
  FOR EACH ROW
EXECUTE PROCEDURE section_update_others();
--------------------------------------------------------------------------------------------------------