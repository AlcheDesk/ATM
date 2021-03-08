ALTER TABLE "instruction" DROP CONSTRAINT "instruction_ck_element_element_action";
ALTER TABLE "instruction" DROP CONSTRAINT "instruction_ck_element_id_and_instruction_type_id";
ALTER TABLE "instruction" DROP CONSTRAINT "instruction_ck_element_id_is_driver";
ALTER TABLE "instruction_overwrite" DROP CONSTRAINT "instruction_override_fk_test_case_instruction_type_element";
ALTER TABLE "instruction_overwrite" DROP CONSTRAINT "instruction_overwrite_ck_element_id_and_instruction_type_id";

ALTER TABLE "instruction_type" ADD CONSTRAINT instruction_type_ix_id_is_driver UNIQUE ("id", "is_driver");

UPDATE instruction SET element_id = NULL, element_type_id = NULL WHERE element_id IN (1, 2, 3);
UPDATE instruction_overwrite SET element_id = NULL WHERE element_id IN (1, 2, 3);

ALTER TABLE "instruction_overwrite" ADD CONSTRAINT instruction_overwrite_fk_test_case_instruction_type_element FOREIGN KEY
("test_case_id", "instruction_id", "element_id", "instruction_type_id") REFERENCES "instruction"
("test_case_id", "id", "element_id", "instruction_type_id");

ALTER TABLE "instruction" ADD CONSTRAINT instruction_fk_instruction_type_is_driver
FOREIGN KEY ("instruction_type_id", "is_driver")
REFERENCES "instruction_type" ("id", "is_driver");

-- update current check instruction type to have execute action
UPDATE instruction SET instruction_action_id = (SELECT id FROM instruction_action WHERE name = 'Execute')
WHERE instruction_type_id IN (SELECT id FROM instruction_type WHERE name = 'StringDataProcessor' OR name = 'MathExpressionProcessor');


DELETE FROM instruction WHERE id IN
(
	SELECT ins.id FROM instruction ins LEFT JOIN instruction_type_instruction_action_link link
	ON  ins.instruction_type_id = link.instruction_type_id AND ins.instruction_action_id = link.instruction_action_id
	WHERE link.instruction_type_id IS NULL AND link.instruction_action_id IS NULL
	AND ins.instruction_type_id NOT IN (SELECT id FROM instruction_type WHERE name IN ('Manual','Reference', 'Comment'))
);

ALTER TABLE "instruction" ADD CONSTRAINT instruction_fk_instruction_type_instruction_action
FOREIGN KEY ("instruction_type_id", "instruction_action_id")
REFERENCES "instruction_type_instruction_action_link" ("instruction_type_id", "instruction_action_id");