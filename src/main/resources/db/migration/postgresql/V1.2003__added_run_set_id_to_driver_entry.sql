ALTER TABLE "driver_entry" ADD COLUMN run_set_id bigint;
ALTER TABLE "driver_entry" ALTER COLUMN "run_id" DROP NOT NULL;
ALTER TABLE "driver_entry" ADD CONSTRAINT driver_entry_fk_run_set FOREIGN KEY (run_set_id) REFERENCES "run_set" ("id");