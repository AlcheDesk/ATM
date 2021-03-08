ALTER TABLE test_case_instruction_type_map DROP COLUMN instruction_types;

ALTER TABLE test_case_instruction_type_map ADD COLUMN instruction_types bigint[];