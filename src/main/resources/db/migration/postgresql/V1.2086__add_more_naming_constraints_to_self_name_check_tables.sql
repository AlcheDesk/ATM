-- driver
ALTER TABLE "driver" DROP CONSTRAINT "driver_ix_name";
ALTER TABLE "driver" DROP CONSTRAINT "driver_ix_name_vendor_version";
CREATE UNIQUE INDEX driver_unique_index_name_is_deleted ON driver (name, vendor_name, version) WHERE is_deleted IS FALSE;
-- driver_pack
ALTER TABLE "driver_pack" DROP CONSTRAINT "driver_pack_ix_name_deleted";
CREATE UNIQUE INDEX driver_pack_unique_index_name_is_deleted ON driver_pack (name) WHERE is_deleted IS FALSE;
-- project
ALTER TABLE "project" DROP CONSTRAINT "project_ix_name_version";
CREATE UNIQUE INDEX project_unique_index_name_version_is_deleted ON project (name, version) WHERE is_deleted IS FALSE;
-- run_set
CREATE UNIQUE INDEX run_set_unique_index_name_is_deleted ON run_set (name) WHERE is_deleted IS FALSE;
-- test_case_folder
CREATE UNIQUE INDEX test_case_folder_unique_index_name_is_deleted ON test_case_folder (name) WHERE is_deleted IS FALSE;