DELETE FROM section_element_link WHERE element_id in (SELECT id FROM element WHERE active IS FALSE);
DELETE FROM section_element_link WHERE section_id in (SELECT id FROM section WHERE active IS FALSE);
DELETE FROM project_test_case_link WHERE test_case_id in (SELECT id FROM test_case WHERE active IS FALSE);
DELETE FROM project_test_case_link WHERE project_id in (SELECT id FROM project WHERE active IS FALSE);
DELETE FROM project_application_link WHERE application_id in (SELECT id FROM application WHERE active IS FALSE);
DELETE FROM project_application_link WHERE project_id in (SELECT id FROM project WHERE active IS FALSE);
DELETE FROM test_case_folder_test_case_link WHERE test_case_id in (SELECT id FROM test_case WHERE active IS FALSE);
DELETE FROM test_case_folder_test_case_link WHERE test_case_folder_id in (SELECT id FROM test_case_folder WHERE active IS FALSE);
DELETE FROM run_set_test_case_link WHERE test_case_id in (SELECT id FROM test_case WHERE active IS FALSE);
DELETE FROM run_set_test_case_link WHERE run_set_id in (SELECT id FROM run_set WHERE active IS FALSE);

--verify active functions
CREATE OR REPLACE FUNCTION "verify_application_active" (app_id bigint) RETURNS boolean
  VOLATILE
AS $dbvis$
BEGIN
   IF (SELECT active FROM application WHERE id = app_id LIMIT 1) THEN
      RETURN TRUE;
   ELSE
      RETURN FALSE;
   END IF;
END
$dbvis$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "verify_element_active" (ele_id bigint) RETURNS boolean
  VOLATILE
AS $$
BEGIN
   IF (SELECT active FROM element WHERE id = ele_id LIMIT 1) THEN
      RETURN TRUE;
   ELSE
      RETURN FALSE;
   END IF;
END
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "verify_section_active" (sec_id bigint) RETURNS boolean
  VOLATILE
AS $$
BEGIN
   IF (SELECT active FROM section WHERE id = sec_id LIMIT 1) THEN
      RETURN TRUE;
   ELSE
      RETURN FALSE;
   END IF;
END
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "verify_project_active" (prj_id bigint) RETURNS boolean
  VOLATILE
AS $$
BEGIN
   IF (SELECT active FROM project WHERE id = prj_id LIMIT 1) THEN
      RETURN TRUE;
   ELSE
      RETURN FALSE;
   END IF;
END
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "verify_test_case_active" (tc_id bigint) RETURNS boolean
  VOLATILE
AS $$
BEGIN
   IF (SELECT active FROM test_case WHERE id = tc_id LIMIT 1) THEN
      RETURN TRUE;
   ELSE
      RETURN FALSE;
   END IF;
END
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "verify_run_set_active" (rs_id bigint) RETURNS boolean
  VOLATILE
AS $$
BEGIN
   IF (SELECT active FROM run_set WHERE id = rs_id LIMIT 1) THEN
      RETURN TRUE;
   ELSE
      RETURN FALSE;
   END IF;
END
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "verify_test_case_folder_active" (tcf_id bigint) RETURNS boolean
  VOLATILE
AS $$
BEGIN
   IF (SELECT active FROM test_case_folder WHERE id =tcf_id LIMIT 1) THEN
      RETURN TRUE;
   ELSE
      RETURN FALSE;
   END IF;
END
$$ LANGUAGE plpgsql;

-- add constraints
ALTER TABLE "section_element_link" ADD CONSTRAINT section_element_link_ck_element_active CHECK (verify_element_active(element_id));
ALTER TABLE "section_element_link" ADD CONSTRAINT section_element_link_ck_section_active CHECK (verify_section_active(section_id));
ALTER TABLE "project_application_link" ADD CONSTRAINT project_application_link_ck_project_active CHECK (verify_project_active(project_id));
ALTER TABLE "project_application_link" ADD CONSTRAINT project_application_link_ck_application_active CHECK (verify_application_active(application_id));
ALTER TABLE "project_test_case_link" ADD CONSTRAINT project_test_case_link_ck_project_active CHECK (verify_project_active(project_id));
ALTER TABLE "project_test_case_link" ADD CONSTRAINT project_test_case_link_ck_test_case_active CHECK (verify_test_case_active(test_case_id));
ALTER TABLE "run_set_test_case_link" ADD CONSTRAINT run_set_test_case_link_ck_run_set_active CHECK (verify_run_set_active(run_set_id));
ALTER TABLE "run_set_test_case_link" ADD CONSTRAINT run_set_test_case_link_ck_test_case_active CHECK (verify_test_case_active(test_case_id));
ALTER TABLE "test_case_folder_test_case_link" ADD CONSTRAINT test_case_folder_test_case_link_ck_test_case_folder_active CHECK (verify_test_case_folder_active(test_case_folder_id));
ALTER TABLE "test_case_folder_test_case_link" ADD CONSTRAINT test_case_folder_test_case_link_ck_test_case_active CHECK (verify_test_case_active(test_case_id));

