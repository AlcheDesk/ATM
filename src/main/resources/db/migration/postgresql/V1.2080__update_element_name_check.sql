-- drop current constraints
ALTER TABLE "element" DROP CONSTRAINT "element_ix_name_project";
ALTER TABLE "element" DROP CONSTRAINT "element_ix_name_section";

-- add new constraints
CREATE UNIQUE INDEX element_unique_index_name_project_id_is_deleted ON element (name, project_id) WHERE is_deleted IS FALSE;
CREATE UNIQUE INDEX element_unique_index_name_section_id_is_deleted ON element (name, section_id) WHERE is_deleted IS FALSE;

-- drop unused index
DROP INDEX "driver_ix_default_driver_type";
DROP INDEX "fki_execution_fkey_status";
DROP INDEX "fki_result_fkey_status";
DROP INDEX "fki_run_set_fkey";
DROP INDEX "fki_test_case_group_id_fkey";
DROP INDEX IF EXISTS "schema_version_s_idx";