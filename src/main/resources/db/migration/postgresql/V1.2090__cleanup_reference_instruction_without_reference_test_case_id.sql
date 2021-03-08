-- clean up 
DELETE FROM instruction WHERE instruction_type_id = (SELECT id FROM instruction_type WHERE name = 'Reference') AND ref_test_case_id IS NULL; 
-- add check constraint
ALTER TABLE "instruction" ADD CONSTRAINT instruction_ck_type_id_reference_test_case_id CHECK ((instruction_type_id = 6 AND ref_test_case_id IS NOT NULL) OR (instruction_type_id <> 6 AND ref_test_case_id IS NULL));