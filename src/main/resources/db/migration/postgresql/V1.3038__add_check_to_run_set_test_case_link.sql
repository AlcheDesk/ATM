ALTER TABLE run_set_test_case_link ALTER COLUMN test_case_id DROP NOT NULL;

ALTER TABLE run_set_test_case_link ADD CONSTRAINT run_set_test_case_link_check_test_case_id_not_null_or_ref_run_set_id_not_null CHECK (test_case_id is not null or ref_run_set_id is not null);