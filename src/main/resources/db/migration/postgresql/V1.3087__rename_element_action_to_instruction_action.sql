ALTER TABLE "element_action" RENAME TO instruction_action;
ALTER SEQUENCE element_action_id_seq RENAME TO instruction_action_id_seq;


ALTER TABLE "element_type_element_action_link" RENAME COLUMN "element_action_id" TO instruction_action_id;
ALTER TABLE "element_type_element_action_link" DROP CONSTRAINT "element_type_element_action_link_element_action";
ALTER TABLE "element_type_element_action_link" ADD CONSTRAINT "element_type_element_action_link_instruction_action" FOREIGN KEY (instruction_action_id) REFERENCES instruction_action(id);
ALTER TABLE "element_type_element_action_link" RENAME TO element_type_instruction_action_link;
ALTER SEQUENCE element_type_element_action_link_id_seq RENAME TO element_type_instruction_action_link_id_seq;

ALTER TABLE "instruction" RENAME COLUMN "element_action_id" TO instruction_action_id;
ALTER TABLE "instruction" DROP CONSTRAINT "instruction_fk_element_action";
ALTER TABLE "instruction" ADD CONSTRAINT "instruction_fk_instruction_action" FOREIGN KEY (instruction_action_id) REFERENCES instruction_action(id);
