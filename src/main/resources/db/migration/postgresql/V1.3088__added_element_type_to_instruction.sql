ALTER TABLE "instruction" ADD COLUMN element_type_id bigint;
UPDATE "instruction" ins SET element_type_id = (SELECT element_type_id from element WHERE id = ins.element_id) WHERE element_id IS NOT NULL;
ALTER TABLE "instruction" ADD CONSTRAINT instruction_fk_element_type FOREIGN KEY (element_type_id) REFERENCES "element_type" ("id");
ALTER TABLE "element" ADD CONSTRAINT element_ix_id_type UNIQUE ("id", "element_type_id");
ALTER TABLE "instruction" ADD CONSTRAINT instruction_fk_element_element_type FOREIGN KEY ("element_id", element_type_id) REFERENCES "element" ("id", "element_type_id");
ALTER TABLE "instruction" ADD CONSTRAINT instruction_ck_element_element_type CHECK 
(
	(element_id IS NULL AND element_type_id IS NULL)
	OR  
	(element_id IS NOT NULL AND element_type_id IS NOT NULL)
);