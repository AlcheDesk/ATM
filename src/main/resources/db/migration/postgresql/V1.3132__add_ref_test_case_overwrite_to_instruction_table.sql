ALTER TABLE instruction ADD COLUMN ref_test_case_overwrite_id bigint;

ALTER TABLE public.test_case_overwrite
    ADD CONSTRAINT test_case_overwrite_ix_test_case_test_case_overwrite UNIQUE (id, test_case_id);

ALTER TABLE instruction
    ADD CONSTRAINT instruction_fk_ref_test_case_ref_test_case_overwrite FOREIGN KEY (ref_test_case_overwrite_id, ref_test_case_id)
    REFERENCES test_case_overwrite (id, test_case_id);