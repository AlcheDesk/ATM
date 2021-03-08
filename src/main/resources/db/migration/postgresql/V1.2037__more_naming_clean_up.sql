CREATE OR REPLACE FUNCTION "verify_application_active" (app_id bigint)  RETURNS boolean
  VOLATILE
AS $$
BEGIN
   IF (SELECT is_active FROM application WHERE id = app_id LIMIT 1) THEN
      RETURN TRUE;
   ELSE
      RETURN FALSE;
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
	element ele where ele.is_active is true and ele.id <> ele_id and ele.id in 
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

CREATE OR REPLACE FUNCTION "verify_application_name" (app_id bigint, app_name text)  RETURNS boolean
  VOLATILE
AS $$
BEGIN
   IF (select count(*) from application where name = app_name and id != app_id and is_active is true) > 0 THEN
      RETURN FALSE;
   ELSE
      RETURN TRUE;
   END IF;
END
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "verify_element_active" (ele_id bigint)  RETURNS boolean
  VOLATILE
AS $$
BEGIN
   IF (SELECT is_active FROM element WHERE id = ele_id LIMIT 1) THEN
      RETURN TRUE;
   ELSE
      RETURN FALSE;
   END IF;
END
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "verify_project_active" (prj_id bigint)  RETURNS boolean
  VOLATILE
AS $$
BEGIN
   IF (SELECT is_active FROM project WHERE id = prj_id LIMIT 1) THEN
      RETURN TRUE;
   ELSE
      RETURN FALSE;
   END IF;
END
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "verify_run_set_active" (rs_id bigint)  RETURNS boolean
  VOLATILE
AS $$
BEGIN
   IF (SELECT is_active FROM run_set WHERE id = rs_id LIMIT 1) THEN
      RETURN TRUE;
   ELSE
      RETURN FALSE;
   END IF;
END
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "verify_section_active" (sec_id bigint)  RETURNS boolean
  VOLATILE
AS $$
BEGIN
   IF (SELECT is_active FROM section WHERE id = sec_id LIMIT 1) THEN
      RETURN TRUE;
   ELSE
      RETURN FALSE;
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
	section sec where sec.is_active is true and sec.id != sec_id and sec.id in 
	(select sec2.id from section sec2 where sec2.application_id = (select sec1.application_id from section sec1 where sec1.id = sec_id));
   IF sec_name = ANY(sec_names) THEN
      RETURN FALSE;
   ELSE
      RETURN TRUE;
   END IF;
END
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "verify_test_case_active" (tc_id bigint)  RETURNS boolean
  VOLATILE
AS $$
BEGIN
   IF (SELECT is_active FROM test_case WHERE id = tc_id LIMIT 1) THEN
      RETURN TRUE;
   ELSE
      RETURN FALSE;
   END IF;
END
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "verify_test_case_folder_active" (tcf_id bigint)  RETURNS boolean
  VOLATILE
AS $$
BEGIN
   IF (SELECT is_active FROM test_case_folder WHERE id =tcf_id LIMIT 1) THEN
      RETURN TRUE;
   ELSE
      RETURN FALSE;
   END IF;
END
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
	test_case tc where tc.is_active is true and tc.id <> tc_id and tc.id in 
    (
        select link2.test_case_id from project_test_case_link link2 where link2.project_id in ( 
            select link.project_id as pj_id from project_test_case_link link where link.test_case_id = tc_id
        )
    );
    
    select array_agg(tc.name) into tc_tcf_names from 
	test_case tc where tc.is_active is true and tc.id <> tc_id and tc.id in 
    (
        select link2.test_case_id from test_case_folder_test_case_link link2 where link2.test_case_folder_id in ( 
            select link.test_case_folder_id as tcf_id from test_case_folder_test_case_link link where link.test_case_id = tc_id
        )
    );
    
    select array_agg(tc.name) into tc_rs_names from 
	test_case tc where tc.is_active is true and tc.id <> tc_id and tc.id in 
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

--
ALTER TABLE "dev_instruction_result" DROP COLUMN "input";
ALTER TABLE "dev_instruction_result" ALTER COLUMN created_at SET NOT NULL;
ALTER TABLE "dev_instruction_result" ALTER COLUMN updated_at SET NOT NULL;
--
ALTER TABLE "driver_property" ALTER COLUMN default_action SET NOT NULL;
ALTER TABLE "driver_property" ALTER COLUMN description SET NOT NULL;
--
ALTER TABLE "driver_vendor" ALTER COLUMN is_active SET DEFAULT true;
--
UPDATE "element" SET color_id = 1 WHERE color_id IS NULL;
ALTER TABLE "element" ALTER COLUMN color_id SET DEFAULT 1;
ALTER TABLE "element" ALTER COLUMN color_id SET NOT NULL;
--
ALTER TABLE "element_locator_type" ADD COLUMN is_active BOOLEAN;
UPDATE "element_locator_type" SET is_active = true WHERE is_active IS NULL;
ALTER TABLE "element_locator_type" ALTER COLUMN is_active SET NOT NULL;
ALTER TABLE "element_locator_type" ALTER COLUMN is_active SET DEFAULT true;
--
ALTER TABLE "error_code" ALTER COLUMN code SET NOT NULL;
--
ALTER TABLE "file_type" ADD COLUMN is_active BOOLEAN;
UPDATE "file_type" SET is_active = true WHERE is_active IS NULL;
ALTER TABLE "file_type" ALTER COLUMN is_active SET NOT NULL;
ALTER TABLE "file_type" ALTER COLUMN is_active SET DEFAULT true;
--
ALTER TABLE "group" ADD COLUMN is_active BOOLEAN;
UPDATE "group" SET is_active = true WHERE is_active IS NULL;
ALTER TABLE "group" ALTER COLUMN is_active SET NOT NULL;
ALTER TABLE "group" ALTER COLUMN is_active SET DEFAULT true;
--
UPDATE "instruction" SET data = '{}'::json WHERE data IS NULL;
ALTER TABLE "instruction" ALTER COLUMN data SET DEFAULT '{}'::json;
ALTER TABLE "instruction" ALTER COLUMN data SET NOT NULL;
UPDATE "instruction" SET color_id = 1 WHERE color_id IS NULL;
ALTER TABLE "instruction" ALTER COLUMN color_id SET DEFAULT 1;
ALTER TABLE "instruction" ALTER COLUMN color_id SET NOT NULL;
--
ALTER TABLE "instruction_option" DROP COLUMN "value";
--
ALTER TABLE "instruction_type" ALTER COLUMN name SET NOT NULL;
ALTER TABLE "instruction_type" ADD COLUMN is_active BOOLEAN;
UPDATE "instruction_type" SET is_active = true WHERE is_active IS NULL;
ALTER TABLE "instruction_type" ALTER COLUMN is_active SET NOT NULL;
ALTER TABLE "instruction_type" ALTER COLUMN is_active SET DEFAULT true;
--
ALTER TABLE "prod_instruction_result" DROP COLUMN "input";
ALTER TABLE "prod_instruction_result" ALTER COLUMN created_at SET NOT NULL;
ALTER TABLE "prod_instruction_result" ALTER COLUMN updated_at SET NOT NULL;
--
ALTER TABLE "project" RENAME COLUMN "active" TO is_active;
ALTER TABLE "project" ALTER COLUMN name SET NOT NULL;
ALTER TABLE "project" ALTER COLUMN project_type_id SET NOT NULL;
--
ALTER TABLE "project_type" ADD COLUMN is_active BOOLEAN;
UPDATE "project_type" SET is_active = true WHERE is_active IS NULL;
ALTER TABLE "project_type" ALTER COLUMN is_active SET NOT NULL;
ALTER TABLE "project_type" ALTER COLUMN is_active SET DEFAULT true;
--
ALTER TABLE "run" ALTER COLUMN test_case_id SET NOT NULL;
--
UPDATE "run_set" SET group_id = 1 WHERE group_id IS NULL;
ALTER TABLE "run_set" ALTER COLUMN group_id SET NOT NULL;
ALTER TABLE "run_set" ALTER COLUMN group_id SET DEFAULT 1;
--
ALTER TABLE "run_set_type" ADD COLUMN is_active BOOLEAN;
UPDATE "run_set_type" SET is_active = true WHERE is_active IS NULL;
ALTER TABLE "run_set_type" ALTER COLUMN is_active SET NOT NULL;
ALTER TABLE "run_set_type" ALTER COLUMN is_active SET DEFAULT true;
--
ALTER TABLE "run_type" ADD COLUMN is_active BOOLEAN;
UPDATE "run_type" SET is_active = true WHERE is_active IS NULL;
ALTER TABLE "run_type" ALTER COLUMN is_active SET NOT NULL;
ALTER TABLE "run_type" ALTER COLUMN is_active SET DEFAULT true;
--
ALTER TABLE "section_line" ALTER COLUMN section_id SET NOT NULL;
--
ALTER TABLE "status" ADD COLUMN is_active BOOLEAN;
UPDATE "status" SET is_active = true WHERE is_active IS NULL;
ALTER TABLE "status" ALTER COLUMN is_active SET NOT NULL;
ALTER TABLE "status" ALTER COLUMN is_active SET DEFAULT true;
--
ALTER TABLE "step_log_type" ADD COLUMN is_active BOOLEAN;
UPDATE "step_log_type" SET is_active = true WHERE is_active IS NULL;
ALTER TABLE "step_log_type" ALTER COLUMN is_active SET NOT NULL;
ALTER TABLE "step_log_type" ALTER COLUMN is_active SET DEFAULT true;
--
ALTER TABLE "system_requirement_entry" ALTER COLUMN value SET NOT NULL;
--
ALTER TABLE "test_case" ALTER COLUMN created_at SET NOT NULL;
ALTER TABLE "test_case" ALTER COLUMN updated_at SET NOT NULL;
ALTER TABLE "test_case" ALTER COLUMN group_id SET NOT NULL;
--
ALTER TABLE "test_case_folder_type" ADD COLUMN is_active BOOLEAN;
UPDATE "test_case_folder_type" SET is_active = true WHERE is_active IS NULL;
ALTER TABLE "test_case_folder_type" ALTER COLUMN is_active SET NOT NULL;
ALTER TABLE "test_case_folder_type" ALTER COLUMN is_active SET DEFAULT true;
--
ALTER TABLE "test_case_option" DROP COLUMN "value";
--
ALTER TABLE "test_case_task_type" ADD COLUMN is_active BOOLEAN;
UPDATE "test_case_task_type" SET is_active = true WHERE is_active IS NULL;
ALTER TABLE "test_case_task_type" ALTER COLUMN is_active SET NOT NULL;
ALTER TABLE "test_case_task_type" ALTER COLUMN is_active SET DEFAULT true;

