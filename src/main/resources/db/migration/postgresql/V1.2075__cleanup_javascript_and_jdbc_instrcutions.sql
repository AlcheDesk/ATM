-- element id must come with element action
DELETE FROM instruction WHERE NOT ((element_id IS NULL AND element_action_id IS NULL) OR (element_id IS NOT NULL AND element_action_id IS NOT NULL));

ALTER TABLE "instruction" ADD CONSTRAINT instruction_ck_element_element_action CHECK ((element_id IS NULL AND element_action_id IS NULL) OR (element_id IS NOT NULL AND element_action_id IS NOT NULL));
