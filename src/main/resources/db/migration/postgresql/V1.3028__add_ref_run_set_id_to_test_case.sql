ALTER TABLE "test_case" ADD COLUMN ref_run_set_id bigint;

INSERT INTO test_case_task_type(id, name, is_predefined, is_active) VALUES (9, 'Reference Test', true, true);