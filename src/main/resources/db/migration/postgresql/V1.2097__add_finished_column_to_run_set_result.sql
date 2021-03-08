ALTER TABLE "run_set_result" ADD COLUMN is_finished BOOLEAN;
UPDATE "run_set_result" SET is_finished = FALSE WHERE is_finished IS NULL;
UPDATE "run_set_result" SET is_finished = TRUE WHERE end_at IS NOT NULL;
ALTER TABLE "run_set_result" ALTER COLUMN is_finished SET NOT NULL;
ALTER TABLE "run_set_result" ALTER COLUMN is_finished SET DEFAULT false;
