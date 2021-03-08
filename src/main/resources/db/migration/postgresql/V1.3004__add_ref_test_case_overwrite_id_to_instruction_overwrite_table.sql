ALTER TABLE "instruction_overwrite" ADD COLUMN ref_test_case_overwrite_id bigint;
ALTER TABLE "instruction_overwrite" ADD CONSTRAINT instruction_overwrite_fk_ref_test_case_overwrite_id FOREIGN KEY (ref_test_case_overwrite_id) REFERENCES "test_case_overwrite" ("id");
ALTER TABLE "instruction_overwrite" ADD CONSTRAINT instruction_overwrite_check_type_ref_case_overwrite CHECK ((instruction_type_id = 6 AND ref_test_case_overwrite_id IS NOT NULL) OR (instruction_type_id <> 6 AND ref_test_case_overwrite_id IS NULL));