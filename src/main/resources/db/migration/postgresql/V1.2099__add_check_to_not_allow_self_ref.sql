-- delete those instructions
DELETE FROM instruction WHERE test_case_id = ref_test_case_id;

-- add check constraint
ALTER TABLE "instruction" ADD CONSTRAINT instruction_test_case_not_equal_ref_test_case CHECK (test_case_id <> ref_test_case_id);