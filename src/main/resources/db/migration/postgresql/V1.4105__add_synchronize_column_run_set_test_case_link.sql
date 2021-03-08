ALTER TABLE "run_set_test_case_link" ADD COLUMN synchronize BOOLEAN;
UPDATE "run_set_test_case_link" SET synchronize = FALSE WHERE synchronize IS NULL;
ALTER TABLE "run_set_test_case_link" ALTER COLUMN synchronize SET NOT NULL;
ALTER TABLE "run_set_test_case_link" ALTER COLUMN synchronize SET DEFAULT false;