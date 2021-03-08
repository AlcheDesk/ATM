ALTER TABLE run_set_test_case_link ADD COLUMN ref_run_set_id bigint;

ALTER TABLE run_set_test_case_link ADD CONSTRAINT run_set_test_case_link_fk_ref_run_set FOREIGN KEY (ref_run_set_id) REFERENCES run_set (id);