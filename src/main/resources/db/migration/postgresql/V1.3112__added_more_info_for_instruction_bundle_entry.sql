ALTER TABLE "instruction_bundle_entry" ADD COLUMN instruction_type_id bigint;
ALTER TABLE "instruction_bundle_entry" ADD COLUMN element_type_id bigint;
ALTER TABLE "instruction_bundle_entry" ADD COLUMN instruction_action_id bigint;
ALTER TABLE "instruction_bundle_entry" ALTER COLUMN instruction_bundle_id SET NOT NULL;
ALTER TABLE "instruction_bundle_entry" ADD CONSTRAINT
    instruction_bundle_entry_fk_instruction_element FOREIGN KEY (element_type_id,
    instruction_type_id) REFERENCES "instruction_type_element_type_link"
    ("element_type_id", "instruction_type_id");
ALTER TABLE "instruction_bundle_entry" ADD CONSTRAINT
    instruction_bundle_entry_fk_instruction_type_action FOREIGN KEY (instruction_action_id,
    instruction_type_id) REFERENCES "instruction_type_instruction_action_link"
    ("instruction_action_id", "instruction_type_id");
ALTER TABLE "instruction_bundle_entry" ADD CONSTRAINT
    instruction_bundle_entry_fk_ele_type_ins_action FOREIGN KEY (element_type_id,
    instruction_action_id) REFERENCES "element_type_instruction_action_link"
    ("element_type_id", "instruction_action_id");