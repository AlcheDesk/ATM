ALTER TRIGGER "dpdtl_after_update_driver_pack_driver_type_map" ON "driver_pack_driver_link" RENAME TO driver_pack_driver_link_after_update_dpdtm;
ALTER TRIGGER "dpdtl_delete_update_driver_pack_driver_type_map" ON "driver_pack_driver_link" RENAME TO driver_pack_driver_link_before_delete_dpdtm;
ALTER TRIGGER "dpdtl_insert_update_driver_pack_driver_type_map" ON "driver_pack_driver_link" RENAME TO driver_pack_driver_link_after_insert_dpdtm;

ALTER TRIGGER "tcitm_after_update_run_set_alias_name_map" ON "run_set_alias_link" RENAME TO run_set_alias_link_after_insert_or_update_rsanm;

ALTER TRIGGER "api_schema_update_copy_from_id" ON "api_schema" RENAME TO api_schema_before_update_copy_reference;
ALTER TRIGGER "application_update_copy_from_id" ON "application" RENAME TO application_before_update_copy_reference;
ALTER TRIGGER "driver_update_copy_from_id" ON "driver" RENAME TO driver_before_update_copy_reference;
ALTER TRIGGER "driver_pack_update_copy_from_id" ON "driver_pack" RENAME TO driver_pack_before_update_copy_reference;
ALTER TRIGGER "element_update_copy_from_id" ON "element" RENAME TO element_before_update_copy_reference;
ALTER TRIGGER "instruction_update_copy_from_id" ON "instruction" RENAME TO instruction_before_update_copy_reference;
ALTER TRIGGER "project_update_copy_from_id" ON "project" RENAME TO project_before_update_copy_reference;
ALTER TRIGGER "run_set_update_copy_from_id" ON "run_set" RENAME TO run_set_before_update_copy_reference;
ALTER TRIGGER "section_update_copy_from_id" ON "section" RENAME TO section_before_update_copy_reference;
ALTER TRIGGER "system_requirement_pack_update_copy_from_id" ON "system_requirement_pack" RENAME TO system_requirement_pack_before_update_copy_reference;
ALTER TRIGGER "test_case_update_copy_from_id" ON "test_case" RENAME TO test_case_before_update_copy_reference;
ALTER TRIGGER "test_case_folder_update_copy_from_id" ON "test_case_share_folder" RENAME TO test_case_share_folder_before_update_copy_reference;