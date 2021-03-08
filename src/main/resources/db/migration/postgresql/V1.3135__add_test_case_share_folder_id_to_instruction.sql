ALTER TABLE instruction ADD COLUMN test_case_share_folder_id bigint;

UPDATE instruction
	SET test_case_share_folder_id= (SELECT test_case_share_folder_id FROM test_case_share_folder_test_case_link link WHERE link.test_case_id = ref_test_case_id limit 1)
	WHERE instruction_type_id = 6;

ALTER TABLE instruction
    ADD CONSTRAINT instruction_ck_instruction_type_id_test_case_share_folder_id CHECK (instruction_type_id = 6 AND test_case_share_folder_id IS NOT NULL OR instruction_type_id <> 6 AND test_case_share_folder_id IS NULL);