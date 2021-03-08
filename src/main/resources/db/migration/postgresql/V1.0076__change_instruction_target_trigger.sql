DROP TRIGGER IF EXISTS "update_target_trigger" ON "instruction";

CREATE TRIGGER "update_target_trigger"
AFTER UPDATE ON "instruction"
FOR EACH ROW
WHEN ((OLD.application_id, OLD.section_id, OLD.element_id)
       IS DISTINCT FROM
      (NEW.application_id, NEW.section_id, NEW.element_id))
EXECUTE PROCEDURE generate_instrcution_target();