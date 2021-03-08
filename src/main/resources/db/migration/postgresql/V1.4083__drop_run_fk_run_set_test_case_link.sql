ALTER TABLE run
    DROP CONSTRAINT IF EXISTS run_fk_run_set_test_case_link;

ALTER TABLE user_content
    DROP CONSTRAINT IF EXISTS user_content_fk_tenant_id;