
ALTER SEQUENCE "step_option_id_seq" RENAME TO instrcution_option_id_seq;
ALTER TABLE "step_option" RENAME TO instruction_option;

ALTER TABLE "step_option_entry" DROP CONSTRAINT IF EXISTS "step_option_entry_fk_name";
--ALTER TABLE "step_option_entry" RENAME CONSTRAINT "step_option_entry_fk_name" TO instrcution_option_entry_fk_name;
ALTER TABLE "step_option_entry" RENAME CONSTRAINT "step_option_entry_fk_instruction" TO instruction_option_entry_fk_instruction;
ALTER SEQUENCE "step_option_entry_id_seq" RENAME TO instruction_option_entry_id_seq;
ALTER TABLE "step_option_entry" RENAME TO instruction_option_entry;
ALTER TABLE "instruction_option_entry" ADD CONSTRAINT instruction_option_entry_fk_name FOREIGN KEY (name) REFERENCES "instruction_option" ("name");

ALTER TABLE "element_type_step_option_link" RENAME COLUMN "step_option_id" TO instruction_option_id;
ALTER TABLE "element_type_step_option_link" RENAME CONSTRAINT "element_type_step_option_link_fk_element_type" TO element_type_isntrcution_option_link_fk_element_type;
--ALTER TABLE "element_type_step_option_link" RENAME CONSTRAINT "element_type_step_option_link_fk_step_option" TO element_type_instruction_option_link_fk_instruction_option;
ALTER TABLE "element_type_step_option_link" DROP CONSTRAINT IF EXISTS "element_type_step_option_link_fk_step_option";
ALTER SEQUENCE "element_type_step_option_link_id_seq" RENAME TO element_type_instruction_option_link_id_seq;
ALTER TABLE "element_type_step_option_link" RENAME TO element_type_instruction_option_link;
ALTER TABLE "element_type_instruction_option_link" ADD CONSTRAINT element_type_instruction_option_fk_instruction_option FOREIGN KEY (instruction_option_id) REFERENCES "instruction_option" ("id");
