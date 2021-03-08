ALTER TABLE "run" disable TRIGGER "run_generate_columns";
UPDATE "run" SET end_at = start_at WHERE end_at < start_at AND end_at IS NOT NULL AND start_at IS NOT NULL;
COMMIT;
ALTER TABLE "run" ADD CONSTRAINT run_ck_end_after_start 
CHECK (NOT (end_at IS NOT NULL AND start_at IS NOT NULL AND end_at < start_at));
ALTER TABLE "run" ADD CONSTRAINT run_ix_id_start_end UNIQUE ("id", "start_at", "end_at");
ALTER TABLE "run" enable TRIGGER "run_generate_columns";