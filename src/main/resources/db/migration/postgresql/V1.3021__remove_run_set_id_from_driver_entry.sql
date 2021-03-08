ALTER TABLE "driver_entry" DROP CONSTRAINT "driver_entry_fk_run_set";
ALTER TABLE "driver_entry" RENAME CONSTRAINT "driver_entry_ix_name_vendor_version_run_set" TO driver_entry_ix_name_vendor_version;
ALTER TABLE "driver_entry" DROP COLUMN "run_set_id";