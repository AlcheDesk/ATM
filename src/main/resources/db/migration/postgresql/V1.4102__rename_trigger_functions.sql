ALTER FUNCTION "tcitm_delete_delete_test_case_driver_type_map" ( ) RENAME TO tcitm_delete_delete_tcdtm;
ALTER FUNCTION "tcitm_insert_insert_test_case_driver_type_map" ( ) RENAME TO tcitm_insert_insert_tcdtm;
ALTER FUNCTION "tcitm_update_update_test_case_driver_type_map" ( ) RENAME TO tcitm_update_update_tcdtm;

ALTER FUNCTION "update_copy_from_id_column" ( ) RENAME TO keep_copy_reference;
ALTER FUNCTION "update_created_at_updated_at_column" ( ) RENAME TO keep_timestampes;
ALTER FUNCTION "update_log_column" ( ) RENAME TO keep_log;
ALTER FUNCTION "update_updated_at_created_at_uuid_column" ( ) RENAME TO keep_timestampes_and_uuid;
ALTER FUNCTION "update_uuid_column" ( ) RENAME TO keep_uuid;
ALTER FUNCTION "update_created_at_column" ( ) RENAME TO keep_created_at_timestamp;

ALTER FUNCTION "dpdtl_delete_update_driver_pack_driver_type_map" ( ) RENAME TO dpdtl_delete_update_dpdtm;
ALTER FUNCTION "dpdtl_insert_update_driver_pack_driver_type_map" ( ) RENAME TO dpdtl_insert_update_dpdtm;
ALTER FUNCTION "dpdtl_update_update_driver_pack_driver_type_map" ( ) RENAME TO dpdtl_update_update_dpdtm;