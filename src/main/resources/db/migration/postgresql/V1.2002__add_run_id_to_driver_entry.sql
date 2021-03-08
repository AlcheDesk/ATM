--add priority to run
ALTER TABLE "run" ADD COLUMN priority INTEGER;
UPDATE "run" SET priority = 10 WHERE priority IS NULL;
ALTER TABLE "run" ALTER COLUMN priority SET NOT NULL;
ALTER TABLE "run" ALTER COLUMN priority SET DEFAULT 10;

-- modify driver entry
ALTER TABLE "driver_entry" DROP COLUMN "test_case_id";
ALTER TABLE "driver_entry" ADD COLUMN run_id bigint;
DELETE FROM "driver_entry" WHERE run_id IS NULL;
ALTER TABLE "driver_entry" ALTER COLUMN run_id SET NOT NULL;
ALTER TABLE "driver_entry" ADD CONSTRAINT driver_entry_fk_run FOREIGN KEY (run_id) REFERENCES "run" ("id");