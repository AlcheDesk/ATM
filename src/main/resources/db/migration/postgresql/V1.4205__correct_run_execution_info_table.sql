ALTER TABLE "run" DROP CONSTRAINT "run_ck_start_at_end_at";
ALTER TABLE "run_execution_info" disable TRIGGER "run_execution_info_section_generate_columns";
UPDATE "run_execution_info" 
SET run_start_at = i.start_at, 
    run_end_at = i.end_at 
FROM (
    SELECT id, start_at, end_at 
    FROM "run") i
WHERE 
    i.id = run_execution_info.run_id;
ALTER TABLE "run_execution_info" ADD CONSTRAINT run_execution_info_ck_run_end_after_run_start 
CHECK (NOT (run_end_at IS NOT NULL AND run_start_at IS NOT NULL AND run_end_at < run_start_at));

UPDATE "run_execution_info" SET duration = EXTRACT(EPOCH FROM (run_end_at - run_start_at)) WHERE duration IS NULL AND run_start_at IS NOT NULL AND run_end_at IS NOT NULL;

ALTER TABLE "run_execution_info" ADD CONSTRAINT run_execution_info_fk_run_start_end 
FOREIGN KEY ("run_id", "run_start_at", "run_end_at") REFERENCES "run" ("id", "start_at", "end_at") DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE "run_execution_info" ALTER COLUMN duration SET NOT NULL;
ALTER TABLE "run_execution_info" enable TRIGGER "run_execution_info_section_generate_columns";
ALTER TRIGGER "run_execution_info_section_generate_columns" ON "run_execution_info" RENAME TO run_execution_info_generate_columns;
ALTER FUNCTION "run_execution_info_section_generate_columns" ( ) RENAME TO run_execution_info_generate_columns;