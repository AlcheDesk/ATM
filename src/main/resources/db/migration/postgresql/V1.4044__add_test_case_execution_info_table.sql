ALTER TABLE "test_case" ADD CONSTRAINT test_case_ix_id_name UNIQUE ("id", "name");

CREATE TABLE test_case_execution_info
    (
        test_case_id bigint NOT NULL,
        test_case_name text NOT NULL,
        test_case_created_at TIMESTAMP WITH TIME zone NOT NULL,
        total_run_count bigint DEFAULT 0 NOT NULL,
        latest_run_status_id bigint,
        latest_run_update_at TIMESTAMP WITH TIME zone,
        latest_run_instruction_total_count bigint,
        latest_run_instruction_pass_count bigint,
        latest_run_source_ip bigint,
        PRIMARY KEY (test_case_id),
        CONSTRAINT test_case_execution_info_ix_test_case_id UNIQUE (test_case_id),
        CONSTRAINT test_case_execution_info_fk_test_case_id_name FOREIGN KEY (test_case_id, test_case_name) REFERENCES "test_case" ("id", "name"),
        CONSTRAINT test_case_execution_info_ck_total CHECK (latest_run_instruction_pass_count <= latest_run_instruction_total_count)
    )