ALTER SEQUENCE run_set_ems_job_link_id_seq RENAME TO run_set_job_link_id_seq;
ALTER SEQUENCE test_case_ems_task_link_id_seq RENAME TO test_case_task_link_id_seq;
ALTER SEQUENCE instruction_id_seq RENAME TO prod_instruction_id_seq;
ALTER SEQUENCE file_id_seq RENAME TO prod_file_id_seq;
ALTER SEQUENCE step_log_id_seq RENAME TO prod_step_log_id_seq;

--
ALTER TABLE "application" DROP COLUMN "owner_id";
ALTER TABLE "application" RENAME COLUMN "active" TO is_active;
--
ALTER TABLE "dev_instruction_result" RENAME COLUMN "finished" TO is_finished;
UPDATE "dev_instruction_result" SET data = '{}'::json WHERE data IS NULL;
ALTER TABLE "dev_instruction_result" ALTER COLUMN data SET NOT NULL;
ALTER TABLE "dev_instruction_result" ALTER COLUMN data SET DEFAULT '{}'::json;
--
ALTER TABLE "driver" RENAME COLUMN "active" TO is_active;
ALTER TABLE "driver" RENAME COLUMN "vendor" TO vendor_name;
ALTER TABLE "driver" RENAME COLUMN "properties" TO property;
--
ALTER TABLE "driver_entry" RENAME COLUMN "properties" TO property;
ALTER TABLE "driver_entry" RENAME COLUMN "vendor" TO vendor_name;
--
ALTER TABLE "driver_property" RENAME COLUMN "force_predefined_value" TO is_predefined_value_required;
--
ALTER TABLE "driver_property_predefined_value" RENAME COLUMN "is_predered" TO is_prefered;
--
ALTER TABLE "driver_vendor" RENAME COLUMN "active" TO is_active;
--
ALTER TABLE "element" DROP COLUMN "owner_id";
ALTER TABLE "element" RENAME COLUMN "active" TO is_active;
--
ALTER TABLE "element_action" RENAME COLUMN "active" TO is_active;
--
ALTER TABLE "element_type" RENAME COLUMN "active" TO is_active;
--
ALTER TABLE "group" RENAME COLUMN "preloaded" TO is_predefined;
--
ALTER TABLE "instruction" DROP COLUMN "protocol";
ALTER TABLE "instruction" DROP COLUMN "host";
ALTER TABLE "instruction" DROP COLUMN "port";
ALTER TABLE "instruction" DROP COLUMN "base_url";
ALTER TABLE "instruction" DROP COLUMN "action";
ALTER TABLE "instruction" DROP COLUMN "request_body";
ALTER TABLE "instruction" DROP COLUMN "http_response_code";
ALTER TABLE "instruction" DROP COLUMN "json_schema";
ALTER TABLE "instruction" DROP COLUMN "xml_schema";
ALTER TABLE "instruction" DROP COLUMN "request_headers";
ALTER TABLE "instruction" DROP COLUMN "query_parameters";
ALTER TABLE "instruction" ADD COLUMN parameter json;
UPDATE "instruction" SET parameter = '{}'::json WHERE parameter IS NULL;
ALTER TABLE "instruction" ALTER COLUMN parameter SET NOT NULL;
ALTER TABLE "instruction" ALTER COLUMN parameter SET DEFAULT '{}'::json;
ALTER TABLE "instruction" RENAME COLUMN "active" TO is_active;
ALTER TABLE "instruction" RENAME COLUMN "with_driver_element" TO is_driver;
--
ALTER TABLE "instruction_option" ALTER COLUMN preloaded SET NOT NULL;
ALTER TABLE "instruction_option" RENAME COLUMN "preloaded" TO is_predefined;
ALTER TABLE "instruction_option" RENAME COLUMN "with_value" TO is_value_required;
ALTER TABLE "instruction_option" RENAME COLUMN "active" TO is_active;
--
ALTER TABLE "instruction_option_entry" RENAME COLUMN "with_value" TO is_value_required;
--
ALTER TABLE "prod_instruction_result" RENAME COLUMN "finished" TO is_finished;
UPDATE "prod_instruction_result" SET data = '{}'::json WHERE data IS NULL;
ALTER TABLE "prod_instruction_result" ALTER COLUMN data SET NOT NULL;
ALTER TABLE "prod_instruction_result" ALTER COLUMN data SET DEFAULT '{}'::json;
--
ALTER TABLE "project" DROP COLUMN "owner_id";
--
ALTER TABLE "property" RENAME COLUMN "preloaded" TO is_predefined;
--
ALTER TABLE "run" RENAME COLUMN "finished" TO is_finished;
UPDATE "run" SET test_case = '{}'::json WHERE test_case IS NULL;
ALTER TABLE "run" ALTER COLUMN test_case SET NOT NULL;
ALTER TABLE "run" ALTER COLUMN test_case SET DEFAULT '{}'::json;
--
ALTER TABLE "run_set" DROP COLUMN "owner_id";
ALTER TABLE "run_set" RENAME COLUMN "active" TO is_active;
--
ALTER TABLE "run_set_type" RENAME COLUMN "preloaded" TO is_predefined;
--
ALTER TABLE "section" DROP COLUMN "owner_id";
ALTER TABLE "section" RENAME COLUMN "active" TO is_active;
--
ALTER TABLE "section_line" RENAME COLUMN "active" TO is_active;
--
ALTER TABLE "system_requirement" RENAME COLUMN "reserved" TO is_reserved;
ALTER TABLE "system_requirement" RENAME COLUMN "with_value" TO is_value_required;
--
ALTER TABLE "test_case" DROP COLUMN "result_status";
ALTER TABLE "test_case" DROP COLUMN "status_id";
ALTER TABLE "test_case" DROP COLUMN "owner_id";
ALTER TABLE "test_case" ALTER COLUMN flag SET NOT NULL;
ALTER TABLE "test_case" RENAME COLUMN "flag" TO is_flagged;
ALTER TABLE "test_case" RENAME COLUMN "active" TO is_active;
--
ALTER TABLE "test_case_folder" DROP COLUMN "owner_id";
ALTER TABLE "test_case_folder" RENAME COLUMN "active" TO is_active;
DELETE FROM "test_case_folder" WHERE test_case_folder_type_id IS NULL;
ALTER TABLE "test_case_folder" ALTER COLUMN test_case_folder_type_id SET NOT NULL;
--
ALTER TABLE "test_case_option" RENAME COLUMN "with_value" TO is_value_required;
--
ALTER TABLE "test_case_option_entry" RENAME COLUMN "with_value" TO is_value_required;
--
ALTER TABLE "test_case_task_type" RENAME COLUMN "preloaded" TO is_predefined;

