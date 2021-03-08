ALTER TABLE "task_type" RENAME TO test_case_task_type;
INSERT INTO instruction_type (name) VALUES ('TEST_CASE_REF') ON CONFLICT DO NOTHING;