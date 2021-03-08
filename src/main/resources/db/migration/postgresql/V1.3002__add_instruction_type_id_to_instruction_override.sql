-- add the column
ALTER TABLE "instruction_override" ADD COLUMN instruction_type_id bigint;
ALTER TABLE "instruction_override" ALTER COLUMN instruction_type_id SET NOT NULL;
ALTER TABLE "instruction_override" ADD CONSTRAINT instruction_override_fk_isntruction_type FOREIGN KEY (instruction_type_id) REFERENCES "instruction_type" ("id");

-- update the fkey
ALTER TABLE "instruction_override" DROP CONSTRAINT "instruction_override_fk_test_case_instruction_element";
ALTER TABLE "instruction" DROP CONSTRAINT "instruction_ix_test_case_element_self";
ALTER TABLE "instruction" ADD CONSTRAINT instruction_ix_test_case_element_self_type UNIQUE ("id", "element_id", "instruction_type_id", "test_case_id");
ALTER TABLE "instruction_override" ADD CONSTRAINT instruction_override_fk_test_case_instruction_type_element FOREIGN KEY
("test_case_id", "instruction_id", "element_id", "instruction_type_id") REFERENCES "instruction"
("test_case_id", "id", "element_id", "instruction_type_id");