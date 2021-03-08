--functions
CREATE OR REPLACE FUNCTION "finished_update_status" ()  RETURNS trigger
  VOLATILE
AS $$
DECLARE
	wip_status_id bigint;
    pass_status_id bigint;
BEGIN
	select id into wip_status_id from status where name = 'WIP';
    select id into pass_status_id from status where name = 'PASS';

    IF OLD.status_id = wip_status_id and OLD.is_finished = false and NEW.is_finished = true THEN
    	NEW.status_id = pass_status_id;
    	NEW.end_at =now();
    ELSIF OLD.is_finished = false and NEW.is_finished = true THEN
        NEW.end_at =now();  
    END IF;
    RETURN NEW;   
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "verify_test_case_name" (tc_id bigint, tc_name text)  RETURNS boolean
  VOLATILE
AS $$
DECLARE
    tc_project_names text[];
    tc_tcf_names text[];
    tc_rs_names text[];
BEGIN
	select array_agg(tc.name) into tc_project_names from 
	test_case tc where tc.is_deleted is false and tc.id <> tc_id and tc.id in 
    (
        select link2.test_case_id from project_test_case_link link2 where link2.project_id in ( 
            select link.project_id as pj_id from project_test_case_link link where link.test_case_id = tc_id
        )
    );
    
    select array_agg(tc.name) into tc_tcf_names from 
	test_case tc where tc.is_deleted is false and tc.id <> tc_id and tc.id in 
    (
        select link2.test_case_id from test_case_folder_test_case_link link2 where link2.test_case_folder_id in ( 
            select link.test_case_folder_id as tcf_id from test_case_folder_test_case_link link where link.test_case_id = tc_id
        )
    );
    
    select array_agg(tc.name) into tc_rs_names from 
	test_case tc where tc.is_deleted is false and tc.id <> tc_id and tc.id in 
    (
        select link2.test_case_id from run_set_test_case_link link2 where link2.run_set_id in ( 
            select link.run_set_id as rs_id from run_set_test_case_link link where link.test_case_id = tc_id
        )
    );
   IF tc_name = ANY(tc_project_names) THEN
      RETURN FALSE;
   ELSIF tc_name = ANY(tc_tcf_names) THEN
      RETURN FALSE;
   ELSIF tc_name = ANY(tc_rs_names) THEN
      RETURN FALSE;
   ELSE
      RETURN TRUE;
   END IF;
END
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "verify_element_name" (ele_id bigint, ele_name text)  RETURNS boolean
  VOLATILE
AS $$
DECLARE
    ele_names text[];
BEGIN
	select array_agg(ele.name) into ele_names from 
	element ele where ele.is_deleted is false and ele.id <> ele_id and ele.id in 
    (
        select link2.element_id from section_element_link link2 where link2.section_id in ( 
            select link.section_id as sec_id from section_element_link link where link.element_id = ele_id
        )
    );
   IF ele_name = ANY(ele_names) THEN
      RETURN FALSE;
   ELSE
      RETURN TRUE;
   END IF;
END
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "verify_section_name" (sec_id bigint, sec_name text)  RETURNS boolean
  VOLATILE
AS $$
DECLARE
    sec_names text[];
BEGIN
	select array_agg(sec.name) into sec_names from 
	section sec where sec.is_deleted is false and sec.id != sec_id and sec.id in 
	(select sec2.id from section sec2 where sec2.application_id = (select sec1.application_id from section sec1 where sec1.id = sec_id));
   IF sec_name = ANY(sec_names) THEN
      RETURN FALSE;
   ELSE
      RETURN TRUE;
   END IF;
END
$$ LANGUAGE plpgsql;
--
-- constraints
ALTER TABLE "element" DROP CONSTRAINT IF EXISTS "element_ck_name";
ALTER TABLE "instruction" DROP CONSTRAINT IF EXISTS "instruction_ck_ref_test_case_id";
ALTER TABLE "section" DROP CONSTRAINT IF EXISTS "section_ck_name";
ALTER TABLE "test_case" DROP CONSTRAINT IF EXISTS "test_case_ck_name";
-- triggers
DROP TRIGGER "section_udpate_trigger_instruction_target_update" ON "section";
DROP TRIGGER "run_update_finished" ON "run";
DROP TRIGGER "prod_step_log_trigger_prod_instruction_result_status_update" ON "prod_step_log";
DROP TRIGGER "prod_instruction_result_finished_status_update" ON "prod_instruction_result";
DROP TRIGGER "prod_instruction_result_insert_trigger_run_status" ON "prod_instruction_result";
DROP TRIGGER "prod_instruction_result_update_trigger_run_status" ON "prod_instruction_result";
DROP TRIGGER "dev_step_log_trigger_dev_instruction_result_status_update" ON "dev_step_log";
DROP TRIGGER "dev_instruction_result_finished_status_update" ON "dev_instruction_result";
DROP TRIGGER "dev_instruction_result_insert_trigger_run_status" ON "dev_instruction_result";
DROP TRIGGER "dev_instruction_result_update_trigger_run_status" ON "dev_instruction_result";
--
ALTER TABLE "application" RENAME COLUMN "is_active" TO is_deleted;
UPDATE "application" SET is_deleted = NOT is_deleted;
--
ALTER TABLE "driver" ADD COLUMN is_predefined BOOLEAN;
UPDATE "driver" SET is_predefined = false WHERE is_predefined IS NULL;
UPDATE "driver" SET is_predefined = true WHERE id < 10;
ALTER TABLE "driver" ALTER COLUMN is_predefined SET NOT NULL;
ALTER TABLE "driver" ALTER COLUMN is_predefined SET DEFAULT false;
--
ALTER TABLE "driver_property" ADD COLUMN is_active BOOLEAN;
UPDATE "driver_property" SET is_active = true WHERE is_active IS NULL;
ALTER TABLE "driver_property" ALTER COLUMN is_active SET NOT NULL;
ALTER TABLE "driver_property" ALTER COLUMN is_active SET DEFAULT false;
ALTER TABLE "driver_property" ADD COLUMN is_predefined BOOLEAN;
UPDATE "driver_property" SET is_predefined = true WHERE is_predefined IS NULL;
ALTER TABLE "driver_property" ALTER COLUMN is_predefined SET NOT NULL;
ALTER TABLE "driver_property" ALTER COLUMN is_predefined SET DEFAULT false;
--
ALTER TABLE "driver_type" ADD COLUMN is_active BOOLEAN;
UPDATE "driver_type" SET is_active = true WHERE is_active IS NULL;
ALTER TABLE "driver_type" ALTER COLUMN is_active SET NOT NULL;
ALTER TABLE "driver_type" ALTER COLUMN is_active SET DEFAULT false;
ALTER TABLE "driver_type" ADD COLUMN is_predefined BOOLEAN;
UPDATE "driver_type" SET is_predefined = true WHERE is_predefined IS NULL;
ALTER TABLE "driver_type" ALTER COLUMN is_predefined SET NOT NULL;
ALTER TABLE "driver_type" ALTER COLUMN is_predefined SET DEFAULT false;
--
ALTER TABLE "driver_vendor" ADD COLUMN is_predefined BOOLEAN;
UPDATE "driver_vendor" SET is_predefined = true WHERE is_predefined IS NULL;
ALTER TABLE "driver_vendor" ALTER COLUMN is_predefined SET NOT NULL;
ALTER TABLE "driver_vendor" ALTER COLUMN is_predefined SET DEFAULT false;
--
ALTER TABLE "element" RENAME COLUMN "is_active" TO is_deleted;
UPDATE "element" SET is_deleted = NOT is_deleted;
--
ALTER TABLE "element_action" ADD COLUMN is_predefined BOOLEAN;
UPDATE "element_action" SET is_predefined = true WHERE is_predefined IS NULL;
ALTER TABLE "element_action" ALTER COLUMN is_predefined SET NOT NULL;
ALTER TABLE "element_action" ALTER COLUMN is_predefined SET DEFAULT false;
--
ALTER TABLE "element_locator_type" ADD COLUMN is_predefined BOOLEAN;
UPDATE "element_locator_type" SET is_predefined = true WHERE is_predefined IS NULL;
ALTER TABLE "element_locator_type" ALTER COLUMN is_predefined SET NOT NULL;
ALTER TABLE "element_locator_type" ALTER COLUMN is_predefined SET DEFAULT false;
--
ALTER TABLE "error_code" ADD COLUMN is_active BOOLEAN;
UPDATE "error_code" SET is_active = true WHERE is_active IS NULL;
ALTER TABLE "error_code" ALTER COLUMN is_active SET NOT NULL;
ALTER TABLE "error_code" ALTER COLUMN is_active SET DEFAULT false;
ALTER TABLE "error_code" ADD COLUMN is_predefined BOOLEAN;
UPDATE "error_code" SET is_predefined = true WHERE is_predefined IS NULL;
ALTER TABLE "error_code" ALTER COLUMN is_predefined SET NOT NULL;
ALTER TABLE "error_code" ALTER COLUMN is_predefined SET DEFAULT false;
--
ALTER TABLE "file_type" ADD COLUMN is_predefined BOOLEAN;
UPDATE "file_type" SET is_predefined = true WHERE is_predefined IS NULL;
ALTER TABLE "file_type" ALTER COLUMN is_predefined SET NOT NULL;
ALTER TABLE "file_type" ALTER COLUMN is_predefined SET DEFAULT false;
--
ALTER TABLE "instruction" RENAME COLUMN "is_active" TO is_deleted;
UPDATE "instruction" SET is_deleted = NOT is_deleted;
--
ALTER TABLE "instruction_type" ADD COLUMN is_predefined BOOLEAN;
UPDATE "instruction_type" SET is_predefined = true WHERE is_predefined IS NULL;
ALTER TABLE "instruction_type" ALTER COLUMN is_predefined SET NOT NULL;
ALTER TABLE "instruction_type" ALTER COLUMN is_predefined SET DEFAULT false;
--
ALTER TABLE "project" RENAME COLUMN "is_active" TO is_deleted;
UPDATE "project" SET is_deleted = NOT is_deleted;
--
ALTER TABLE "run_set" RENAME COLUMN "is_active" TO is_deleted;
UPDATE "run_set" SET is_deleted = NOT is_deleted;
--
ALTER TABLE "run_type" ADD COLUMN is_predefined BOOLEAN;
UPDATE "run_type" SET is_predefined = true WHERE is_predefined IS NULL;
ALTER TABLE "run_type" ALTER COLUMN is_predefined SET NOT NULL;
ALTER TABLE "run_type" ALTER COLUMN is_predefined SET DEFAULT false;
--
ALTER TABLE "section" RENAME COLUMN "is_active" TO is_deleted;
UPDATE "section" SET is_deleted = NOT is_deleted;
--
ALTER TABLE "section_line" DROP COLUMN "is_active";
--
ALTER TABLE "status" ADD COLUMN is_predefined BOOLEAN;
UPDATE "status" SET is_predefined = true WHERE is_predefined IS NULL;
ALTER TABLE "status" ALTER COLUMN is_predefined SET NOT NULL;
ALTER TABLE "status" ALTER COLUMN is_predefined SET DEFAULT false;
--
ALTER TABLE "step_log_type" ADD COLUMN is_predefined BOOLEAN;
UPDATE "step_log_type" SET is_predefined = true WHERE is_predefined IS NULL;
ALTER TABLE "step_log_type" ALTER COLUMN is_predefined SET NOT NULL;
ALTER TABLE "step_log_type" ALTER COLUMN is_predefined SET DEFAULT false;
--
ALTER TABLE "system_requirement" ADD COLUMN is_predefined BOOLEAN;
UPDATE "system_requirement" SET is_predefined = true WHERE is_predefined IS NULL;
ALTER TABLE "system_requirement" ALTER COLUMN is_predefined SET NOT NULL;
ALTER TABLE "system_requirement" ALTER COLUMN is_predefined SET DEFAULT false;
--
ALTER TABLE "test_case" RENAME COLUMN "is_active" TO is_deleted;
UPDATE "test_case" SET is_deleted = NOT is_deleted;
--
ALTER TABLE "test_case_folder" RENAME COLUMN "is_active" TO is_deleted;
UPDATE "test_case_folder" SET is_deleted = NOT is_deleted;
--
ALTER TABLE "test_case_folder_type" ADD COLUMN is_predefined BOOLEAN;
UPDATE "test_case_folder_type" SET is_predefined = true WHERE is_predefined IS NULL;
ALTER TABLE "test_case_folder_type" ALTER COLUMN is_predefined SET NOT NULL;
ALTER TABLE "test_case_folder_type" ALTER COLUMN is_predefined SET DEFAULT false;
--
ALTER TABLE "test_case_option" ADD COLUMN is_active BOOLEAN;
UPDATE "test_case_option" SET is_active = true WHERE is_active IS NULL;
ALTER TABLE "test_case_option" ALTER COLUMN is_active SET NOT NULL;
ALTER TABLE "test_case_option" ALTER COLUMN is_active SET DEFAULT false;
ALTER TABLE "test_case_option" ADD COLUMN is_predefined BOOLEAN;
UPDATE "test_case_option" SET is_predefined = true WHERE is_predefined IS NULL;
ALTER TABLE "test_case_option" ALTER COLUMN is_predefined SET NOT NULL;
ALTER TABLE "test_case_option" ALTER COLUMN is_predefined SET DEFAULT false;
--
ALTER TABLE "test_case_option_entry" DROP COLUMN "is_value_required";
--



