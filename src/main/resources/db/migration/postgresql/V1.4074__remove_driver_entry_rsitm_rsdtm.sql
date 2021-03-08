/*
CREATE TABLE driver_entry 
	(id BIGSERIAL NOT NULL, 
	name TEXT NOT NULL, 
	comment TEXT, 
	parameter JSON DEFAULT '{}'::json NOT NULL, 
	driver_type_id BIGINT NOT NULL, 
	run_id BIGINT, 
	vendor_name TEXT NOT NULL, 
	version TEXT, 
	property JSON DEFAULT '{}'::json NOT NULL, 
	log TEXT, 
	tenant_id BIGINT DEFAULT 1 NOT NULL, 
	PRIMARY KEY (id), 
	CONSTRAINT driver_entry_fk_run FOREIGN KEY (run_id) REFERENCES "run" ("id"), 
	CONSTRAINT driver_entry_fk_type FOREIGN KEY (driver_type_id) REFERENCES "driver_type" ("id"), 
	CONSTRAINT driver_entry_fk_vendor_type_version FOREIGN KEY (vendor_name, driver_type_id, version) REFERENCES "driver_vendor" ("name", "driver_type_id", "version"), 
	UNIQUE (name, vendor_name, version, run_id)
);
*/

DROP TABLE "driver_entry";
-----------------------------------------------------------------------------------------------
DROP TRIGGER "rsitm_after_update_run_set_driver_type_map" ON "run_set_instruction_type_map";
DROP TRIGGER "rsitm_after_insert_run_set_driver_type_map" ON "run_set_instruction_type_map";
DROP TRIGGER "rsitm_before_delete_run_set_driver_type_map" ON "run_set_instruction_type_map";
DROP FUNCTION "rsitm_update_run_set_driver_type_map" ( );
DROP FUNCTION "rsitm_insert_run_set_driver_type_map" ( );
DROP FUNCTION "rsitm_delete_run_set_driver_type_map" ( );
-----------------------------------------------------------------------------------------------
DROP TRIGGER "tcitm_after_update_run_set_instruction_type_map" ON "test_case_instruction_type_map";
DROP TRIGGER "run_set_after_insert_run_set_instruction_type_map" ON "run_set";
DROP TRIGGER "rstcl_after_insert_run_set_instruction_type_map" ON "run_set_test_case_link";
DROP TRIGGER "rstcl_after_delete_run_set_instruction_type_map" ON "run_set_test_case_link";
DROP FUNCTION "tcitm_update_update_run_set_instruction_type_map" ( );
DROP FUNCTION "run_set_insert_insert_run_set_instruction_type_map" ( );
DROP FUNCTION "rstcl_insert_update_run_set_instruction_type_map" ( );
DROP FUNCTION "rstcl_delete_update_run_set_instruction_type_map" ( );