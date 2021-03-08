ALTER TABLE "driver_pack" ADD COLUMN is_deleted BOOLEAN;
ALTER TABLE "driver_pack" ALTER COLUMN is_deleted SET NOT NULL;
ALTER TABLE "driver_pack" ALTER COLUMN is_deleted SET DEFAULT false;
ALTER TABLE "driver_pack" ADD COLUMN log text;
ALTER TABLE "driver_pack" DROP CONSTRAINT "driver_pack_ix_name";
ALTER TABLE "driver_pack" ADD CONSTRAINT driver_pack_ix_name_deleted UNIQUE ("name", is_deleted);

ALTER TABLE "test_case_folder" ALTER COLUMN updated_at SET DEFAULT now();
ALTER TABLE "test_case_folder" ALTER COLUMN created_at SET DEFAULT now();