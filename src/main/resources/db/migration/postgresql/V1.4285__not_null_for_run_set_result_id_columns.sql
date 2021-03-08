UPDATE "run_set_result" SET run_ids = ARRAY[]::bigint[] WHERE run_ids IS NULL;
UPDATE "run_set_result" SET passed_run_ids = ARRAY[]::bigint[] WHERE passed_run_ids IS NULL;
UPDATE "run_set_result" SET failed_run_ids = ARRAY[]::bigint[] WHERE failed_run_ids IS NULL;
ALTER TABLE "run_set_result" ALTER COLUMN run_ids SET NOT NULL;
ALTER TABLE "run_set_result" ALTER COLUMN passed_run_ids SET NOT NULL;
ALTER TABLE "run_set_result" ALTER COLUMN failed_run_ids SET NOT NULL;