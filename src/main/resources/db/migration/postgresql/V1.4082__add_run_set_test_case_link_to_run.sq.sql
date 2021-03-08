ALTER TABLE run
    ADD COLUMN run_set_test_case_link_id bigint;

ALTER TABLE run
    ADD CONSTRAINT run_fk_run_set_test_case_link FOREIGN KEY (run_set_test_case_link_id)
    REFERENCES run_set_test_case_link (id);