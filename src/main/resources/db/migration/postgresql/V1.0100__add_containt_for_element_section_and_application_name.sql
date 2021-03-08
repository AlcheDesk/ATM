-- clean up section
DELETE FROM section_element_link WHERE section_id IN (SELECT id FROM section WHERE application_id IS NULL);
DELETE FROM section WHERE application_id IS NULL;
ALTER TABLE "section" ALTER COLUMN application_id SET NOT NULL;

DELETE FROM section_element_link WHERE section_id IN (SELECT id FROM section WHERE name IS NULL);
DELETE FROM section WHERE name IS NULL;
ALTER TABLE "section" ALTER COLUMN name SET NOT NULL;

-- clean up element
DELETE FROM section_element_link WHERE element_id IN (SELECT id FROM element WHERE locator_value IS NULL OR element_type_id IS NULL OR element_locator_type_id IS NULL);
DELETE FROM element WHERE locator_value IS NULL OR element_type_id IS NULL OR element_locator_type_id IS NULL;
ALTER TABLE "element" ALTER COLUMN locator_value SET NOT NULL;
ALTER TABLE "element" ALTER COLUMN element_type_id SET NOT NULL;
ALTER TABLE "element" ALTER COLUMN element_locator_type_id SET NOT NULL;

-- create element name check function
CREATE OR REPLACE FUNCTION "verify_element_name_with_section" (sec_id bigint, ele_id bigint)  RETURNS boolean
  VOLATILE
AS $$
DECLARE
    ele_name text;
    ele_names text[];
BEGIN
   select ele.name into ele_name FROM element ele WHERE ele.id = ele_id;

   select array_agg(ele.name) into ele_names FROM element ele WHERE ele.active IS true AND ele.id != ele_id AND ele.id IN (select link.element_id FROM section_element_link link WHERE link.section_id = sec_id);

   IF ele_name = ANY(ele_names) THEN
      raise notice 'check name: %', ele_name;
      raise notice 'list names: %', array_to_string(ele_names,',');
      RETURN FALSE;
   ELSE
      RETURN TRUE;
   END IF;
END
$$ LANGUAGE plpgsql;

DELETE FROM instruction WHERE element_id IN (SELECT element_id FROM section_element_link WHERE (verify_element_name_with_section(section_id,element_id)) IS false);
ALTER TABLE "section_element_link" DROP CONSTRAINT "section_element_link_fk_element";
ALTER TABLE "section_element_link" ADD CONSTRAINT section_element_link_fk_element FOREIGN KEY ("element_id") REFERENCES "element" ("id") ON DELETE CASCADE;
DELETE FROM section_element_link WHERE element_id IN (SELECT element_id FROM section_element_link WHERE (verify_element_name_with_section(section_id,element_id)) IS false);
ALTER TABLE "section_element_link" DROP CONSTRAINT "section_element_link_fk_element";
ALTER TABLE "section_element_link" ADD CONSTRAINT section_element_link_fk_element FOREIGN KEY ("element_id") REFERENCES "element" ("id");
ALTER TABLE "section_element_link" ADD CONSTRAINT section_element_link_ck_element_name CHECK (verify_element_name_with_section(section_id,element_id));

-- create section name check function 
ALTER TABLE "section" ADD CONSTRAINT section_ix_name_application_id_active UNIQUE ("name","active", "application_id");

-- create application name check function 
CREATE OR REPLACE FUNCTION "verify_application_name_with_project" (proj_id bigint, app_id bigint)  RETURNS boolean
  VOLATILE
AS $$
DECLARE
    app_name text;
    app_names text[];
BEGIN
   select app.name into app_name FROM application app WHERE app.id = app_id;

   select array_agg(app.name) into app_names FROM application app WHERE app.active IS true AND app.id != app_id AND app.id IN (select link.application_id FROM project_application_link link WHERE link.project_id = proj_id);

   IF app_name = ANY(app_names) THEN
      raise notice 'check name: %', app_name;
      raise notice 'list names: %', array_to_string(app_names,',');
      RETURN FALSE;
   ELSE
      RETURN TRUE;
   END IF;
END
$$ LANGUAGE plpgsql;
ALTER TABLE "project_application_link" ADD CONSTRAINT project_application_link_ck_application_name CHECK (verify_application_name_with_project(project_id, application_id));

-- ========================create test case name check function 
CREATE OR REPLACE FUNCTION "verify_test_case_name_with_project" (proj_id bigint, tc_id bigint)  RETURNS boolean
  VOLATILE
AS $$
DECLARE
    tc_name text;
    tc_names text[];
BEGIN
   select tc.name into tc_name FROM test_case tc WHERE tc.id = tc_id;

   select array_agg(tc.name) into tc_names FROM test_case tc WHERE tc.active IS true AND tc.id != tc_id AND tc.id IN (select link.test_case_id FROM project_test_case_link link WHERE link.project_id = proj_id);

   IF tc_name = ANY(tc_names) THEN
      raise notice 'check name: %', tc_name;
      raise notice 'list names: %', array_to_string(tc_names,',');
      RETURN FALSE;
   ELSE
      RETURN TRUE;
   END IF;
END
$$ LANGUAGE plpgsql;
ALTER TABLE "project_test_case_link" ADD CONSTRAINT project_test_case_link_ck_test_case_name CHECK (verify_test_case_name_with_project(project_id, test_case_id));
--========================================================
CREATE OR REPLACE FUNCTION "verify_test_case_name_with_test_case_folder" (tcf_id bigint, tc_id bigint)  RETURNS boolean
  VOLATILE
AS $$
DECLARE
    tc_name text;
    tc_names text[];
BEGIN
   select tc.name into tc_name FROM test_case tc WHERE tc.id = tc_id;

   select array_agg(tc.name) into tc_names FROM test_case tc WHERE tc.active IS true AND tc.id != tc_id AND tc.id IN (select link.test_case_id FROM test_case_folder_test_case_link link WHERE link.test_case_folder_id = tcf_id);

   IF tc_name = ANY(tc_names) THEN
      raise notice 'check name: %', ele_name;
      raise notice 'list names: %', array_to_string(ele_names,',');
      RETURN FALSE;
   ELSE
      RETURN TRUE;
   END IF;
END
$$ LANGUAGE plpgsql;
ALTER TABLE "test_case_folder_test_case_link" ADD CONSTRAINT test_case_folder_test_case_link_ck_test_case_name CHECK (verify_test_case_name_with_test_case_folder(test_case_folder_id, test_case_id));
--=================================================
CREATE OR REPLACE FUNCTION "verify_test_case_name_with_run_set" (rs_id bigint, tc_id bigint)  RETURNS boolean
  VOLATILE
AS $$
DECLARE
    tc_name text;
    tc_names text[];
BEGIN
   select tc.name into tc_name FROM test_case tc WHERE tc.id = tc_id;

   select array_agg(tc.name) into tc_names FROM test_case tc WHERE tc.active IS true AND tc.id != tc_id AND tc.id IN (select link.test_case_id FROM run_set_test_case_link link WHERE link.run_set_id = rs_id);

   IF tc_name = ANY(tc_names) THEN
      raise notice 'check name: %', ele_name;
      raise notice 'list names: %', array_to_string(ele_names,',');
      RETURN FALSE;
   ELSE
      RETURN TRUE;
   END IF;
END
$$ LANGUAGE plpgsql;
ALTER TABLE "run_set_test_case_link" ADD CONSTRAINT run_set_test_case_link_ck_test_case_name CHECK (verify_test_case_name_with_run_set(run_set_id, test_case_id));

