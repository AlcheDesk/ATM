ALTER TRIGGER "update_application_created_at_updated_at" ON "application" RENAME TO application_update_created_at_updated_at;
ALTER TRIGGER "update_engine_created_at_updated_at" ON "driver" RENAME TO driver_update_created_at_updated_at;
ALTER TRIGGER "update_module_property_created_at_updated_at" ON "driver_property" RENAME TO driver_property_update_created_at_updated_at;
ALTER TRIGGER "update_element_created_at_updated_at" ON "element" RENAME TO element_updated_created_at_updated_at;
ALTER TRIGGER "insert_target_trigger" ON "instruction" RENAME TO instruction_insert_target;
ALTER TRIGGER "update_instruction_created_at_updated_at" ON "instruction" RENAME TO instruction_update_created_at_updated_at;
ALTER TRIGGER "update_target_trigger" ON "instruction" RENAME TO instruction_update_target;
ALTER TRIGGER "update_step_option_created_at_updated_at" ON "instruction_option" RENAME TO instruction_option_update_created_at_updated_at;
ALTER TRIGGER "update_project_created_at_updated_at" ON "project" RENAME TO project_update_created_at_updated_at;
ALTER TRIGGER "finished_status_update" ON "run" RENAME TO run_update_finished;
DROP TRIGGER "insert_run_created_at_updated_at" ON "run";
ALTER TRIGGER "update_run_created_at_updated_at" ON "run" RENAME TO run_update_created_at_updated_at;
ALTER TRIGGER "update_run_set_created_at_updated_at" ON "run_set" RENAME TO run_set_update_created_at_updated_at;
ALTER TRIGGER "update_section_created_at_updated_at" ON "section" RENAME TO section_update_created_at_updated_at;
ALTER TRIGGER "update_section_line_created_at_updated_at" ON "section_line" RENAME TO section_line_update_created_at_updated_at;
ALTER TRIGGER "update_storage_created_at_updated_at" ON "storage" RENAME TO storage_update_created_at_updated_at;
ALTER TRIGGER "update_test_case_created_at_updated_at" ON "test_case" RENAME TO test_case_update_created_at_updated_at;
ALTER TRIGGER "update_test_case_folder_created_at_updated_at" ON "test_case_folder" RENAME TO test_case_folder_update_created_at_updated_at;
ALTER TRIGGER "update_test_case_option_created_at_updated_at" ON "test_case_option" RENAME TO test_case_option_update_created_at_updated_at;

--update run
ALTER TABLE "run" disable TRIGGER "run_update_created_at_updated_at";
ALTER TABLE "run" ADD COLUMN group_id bigint;
UPDATE "run" SET group_id = 1 WHERE group_id IS NULL;
ALTER TABLE "run" ALTER COLUMN group_id SET NOT NULL;
ALTER TABLE "run" ALTER COLUMN group_id SET DEFAULT 1;
ALTER TABLE "run" ADD CONSTRAINT run_fk_group FOREIGN KEY (group_id) REFERENCES "group" ("id");
ALTER TABLE "run" RENAME CONSTRAINT "run_set_type_fk_type" TO run_fk_type;
ALTER TABLE "run" enable TRIGGER "run_update_created_at_updated_at";

--other
ALTER TABLE "storage" ADD CONSTRAINT storage_uk_name UNIQUE ("name");