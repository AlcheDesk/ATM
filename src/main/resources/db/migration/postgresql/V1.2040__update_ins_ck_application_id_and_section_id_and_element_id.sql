ALTER TABLE "instruction" DROP CONSTRAINT IF EXISTS "ins_ck_application_id_and_section_id_and_element_id";

ALTER TABLE "instruction"
    ADD CONSTRAINT ins_ck_application_id_and_section_id_and_element_id CHECK 
    (application_id IS NOT NULL AND section_id IS NOT NULL 
    OR application_id IS NULL AND section_id IS NULL AND element_id IS NULL 
    OR application_id IS NULL AND section_id IS NULL AND element_id IS NOT NULL);