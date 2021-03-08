ALTER TABLE application ALTER COLUMN is_deleted SET DEFAULT false;
ALTER TABLE element ALTER COLUMN is_deleted SET DEFAULT false;
ALTER TABLE instruction ALTER COLUMN is_deleted SET DEFAULT false;
ALTER TABLE project ALTER COLUMN is_deleted SET DEFAULT false;
ALTER TABLE run_set ALTER COLUMN is_deleted SET DEFAULT false;
ALTER TABLE section ALTER COLUMN is_deleted SET DEFAULT false;
ALTER TABLE test_case ALTER COLUMN is_deleted SET DEFAULT false;
ALTER TABLE test_case_folder ALTER COLUMN is_deleted SET DEFAULT false;