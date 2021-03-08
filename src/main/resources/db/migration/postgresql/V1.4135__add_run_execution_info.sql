CREATE TABLE run_execution_info
    (
        run_id BIGINT NOT NULL,
        run_name TEXT NOT NULL,
        run_type_id BIGINT NOT NULL,
        run_status_id BIGINT DEFAULT 7,
        run_created_at TIMESTAMP(6) WITH TIME ZONE NOT NULL,
        run_updated_at TIMESTAMP(6) WITH TIME ZONE,
        test_case_id BIGINT NOT NULL,
        test_case_sha2 text,
        run_set_result_id BIGINT,
        total_instruction_result_count INTEGER DEFAULT 0 NOT NULL,
        executable_instruction_number INTEGER DEFAULT 0 NOT NULL,
        instruction_executed_count BIGINT,
        instruction_pass_count BIGINT,
        source_ip TEXT,
        drivers_sha2 text,
        test_case_overwrite_sha2 text,
        tenant_id BIGINT DEFAULT 1000 NOT NULL,
        CONSTRAINT run_execution_info_ix_run_id PRIMARY KEY (run_id),
        CONSTRAINT run_execution_info_fk_run_id FOREIGN KEY (run_id) REFERENCES
        "run" ("id"),
        CONSTRAINT run_execution_info_fk_test_case_id FOREIGN KEY (run_id) REFERENCES
        "test_case" ("id"),
        CONSTRAINT run_execution_info_fk_run_set_result_id FOREIGN KEY (run_set_result_id) REFERENCES
        "run_set_result" ("id"),
        CONSTRAINT run_execution_info_ck_total CHECK (instruction_pass_count <=
        instruction_executed_count)
    );
