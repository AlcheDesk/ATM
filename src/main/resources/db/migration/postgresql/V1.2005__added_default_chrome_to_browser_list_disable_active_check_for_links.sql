INSERT INTO "driver" (id,name,comment,active,default_flag,driver_type_id) VALUES (2,'Chrome','Default Firefox settings',true,false,1) ON CONFLICT DO NOTHING;

ALTER TABLE "section_element_link" DROP CONSTRAINT IF EXISTS section_element_link_ck_element_active;
ALTER TABLE "section_element_link" DROP CONSTRAINT IF EXISTS section_element_link_ck_section_active;
ALTER TABLE "project_application_link" DROP CONSTRAINT IF EXISTS project_application_link_ck_project_active;
ALTER TABLE "project_application_link" DROP CONSTRAINT IF EXISTS project_application_link_ck_application_active;
ALTER TABLE "project_test_case_link" DROP CONSTRAINT IF EXISTS project_test_case_link_ck_project_active;
ALTER TABLE "project_test_case_link" DROP CONSTRAINT IF EXISTS project_test_case_link_ck_test_case_active;
ALTER TABLE "run_set_test_case_link" DROP CONSTRAINT IF EXISTS run_set_test_case_link_ck_run_set_active;
ALTER TABLE "run_set_test_case_link" DROP CONSTRAINT IF EXISTS run_set_test_case_link_ck_test_case_active;
ALTER TABLE "test_case_folder_test_case_link" DROP CONSTRAINT IF EXISTS test_case_folder_test_case_link_ck_test_case_folder_active;
ALTER TABLE "test_case_folder_test_case_link" DROP CONSTRAINT IF EXISTS test_case_folder_test_case_link_ck_test_case_active;