--##########project
-- drop constraints
ALTER TABLE "project_test_case_link" DROP CONSTRAINT "project_to_test_case_link_fk_project";
ALTER TABLE "project_application_link" DROP CONSTRAINT "project_to_application_link_fk_project";
ALTER TABLE "instruction" DROP CONSTRAINT "instruction_fk_project";
ALTER TABLE "project" DROP CONSTRAINT "project_pkey";

-- increase id
UPDATE project SET id = id + 999;
UPDATE project_test_case_link SET project_id = project_id + 999;
UPDATE project_application_link SET project_id = project_id + 999;
UPDATE instruction SET project_id = project_id + 999;

-- increase sequence
SELECT setval(pg_get_serial_sequence('project', 'id'), max(id)) FROM project;

-- recreate constraints
ALTER TABLE project ADD PRIMARY KEY (id);
ALTER TABLE ONLY project_test_case_link ADD CONSTRAINT project_test_case_link_fk_project FOREIGN KEY (project_id) REFERENCES project(id);
ALTER TABLE ONLY project_application_link ADD CONSTRAINT project_to_application_link_fk_project FOREIGN KEY (project_id) REFERENCES project(id);
ALTER TABLE ONLY instruction ADD CONSTRAINT instruction_fk_project FOREIGN KEY (project_id) REFERENCES project(id);


--##########application
-- drop constraints
ALTER TABLE "application" DROP CONSTRAINT IF EXISTS "application_ck_name";
ALTER TABLE "instruction" DROP CONSTRAINT "instruction_fk_application";
ALTER TABLE "project_application_link" DROP CONSTRAINT "project_to_application_link_fk_application";
ALTER TABLE "section" DROP CONSTRAINT "section_fk_application";
ALTER TABLE "application" DROP CONSTRAINT "application_pkey";

-- increase id
UPDATE application SET id = id + 999;
UPDATE instruction SET application_id = application_id + 999;
UPDATE project_application_link SET application_id = application_id + 999;
UPDATE section SET application_id = application_id + 999;

-- increase sequence
SELECT setval(pg_get_serial_sequence('application', 'id'), max(id)) FROM application;

-- recreate constraints
ALTER TABLE application ADD PRIMARY KEY (id);
ALTER TABLE ONLY instruction ADD CONSTRAINT instruction_fk_application FOREIGN KEY (application_id) REFERENCES application(id);
ALTER TABLE ONLY project_application_link ADD CONSTRAINT project_application_link_fk_application FOREIGN KEY (application_id) REFERENCES application(id);
ALTER TABLE ONLY section ADD CONSTRAINT section_fk_application FOREIGN KEY (application_id) REFERENCES application(id);

--##########section
-- drop constraints
ALTER TABLE "instruction" DROP CONSTRAINT "instruction_fk_section";
ALTER TABLE "section_element_link" DROP CONSTRAINT "section_element_link_fk_section";
ALTER TABLE "section_line" DROP CONSTRAINT "section_line_fk_section";
ALTER TABLE "section" DROP CONSTRAINT "section_pkey";

-- increase id
UPDATE section SET id = id + 999;
UPDATE instruction SET section_id = section_id + 999;
UPDATE section_element_link SET section_id = section_id + 999;
UPDATE section_line SET section_id = section_id + 999;

-- increase sequence
SELECT setval(pg_get_serial_sequence('section', 'id'), max(id)) FROM section;

-- recreate constraints
ALTER TABLE section ADD PRIMARY KEY (id);
ALTER TABLE ONLY instruction ADD CONSTRAINT instruction_fk_section FOREIGN KEY (section_id) REFERENCES section(id);
ALTER TABLE ONLY section_element_link ADD CONSTRAINT section_element_link_fk_section FOREIGN KEY (section_id) REFERENCES section(id);
ALTER TABLE ONLY section_line ADD CONSTRAINT section_line_fk_section FOREIGN KEY (section_id) REFERENCES section(id);

--##########element
-- drop constraints
ALTER TABLE "instruction" DROP CONSTRAINT "instruction_fk_element";
ALTER TABLE "section_element_link" DROP CONSTRAINT "section_element_link_fk_element";
ALTER TABLE "element" DROP CONSTRAINT "element_pkey";

-- increase id
UPDATE element SET id = id + 999;
UPDATE instruction SET element_id = element_id + 999;
UPDATE section_element_link SET element_id = element_id + 999;

-- increase sequence
SELECT setval(pg_get_serial_sequence('element', 'id'), max(id)) FROM element;

-- recreate constraints
ALTER TABLE element ADD PRIMARY KEY (id);
ALTER TABLE ONLY instruction ADD CONSTRAINT instruction_fk_element FOREIGN KEY (element_id) REFERENCES element(id);
ALTER TABLE ONLY section_element_link ADD CONSTRAINT section_element_link_fk_element FOREIGN KEY (element_id) REFERENCES element(id);

--##########test_case_folder
-- drop constraints
ALTER TABLE "test_case_folder_test_case_link" DROP CONSTRAINT "test_case_folder_test_case_link_fk_test_case_folder";
ALTER TABLE "test_case_folder" DROP CONSTRAINT "test_case_folder_pkey";

-- increase id
UPDATE test_case_folder SET id = id + 999;
UPDATE test_case_folder_test_case_link SET test_case_folder_id = test_case_folder_id + 999;

-- increase sequence
SELECT setval(pg_get_serial_sequence('test_case_folder', 'id'), max(id)) FROM test_case_folder;

-- recreate constraints
ALTER TABLE test_case_folder ADD PRIMARY KEY (id);
ALTER TABLE ONLY test_case_folder_test_case_link ADD CONSTRAINT test_case_folder_test_case_link_fk_test_case_folder FOREIGN KEY (test_case_folder_id) REFERENCES test_case_folder(id);

--##########run_set
-- drop constraints
ALTER TABLE "run_set_test_case_link" DROP CONSTRAINT "run_set_fk_run_set";
ALTER TABLE "run_set_job_link" DROP CONSTRAINT "run_set_job_link_fk_run_set";
ALTER TABLE "driver_entry" DROP CONSTRAINT "driver_entry_fk_run_set";
ALTER TABLE "run_set" DROP CONSTRAINT "run_set_pkey";

-- increase id
UPDATE run_set SET id = id + 999;
UPDATE run_set_test_case_link SET run_set_id = run_set_id + 999;
UPDATE run_set_job_link SET run_set_id = run_set_id + 999;
UPDATE driver_entry SET run_set_id = run_set_id + 999;

-- increase sequence
SELECT setval(pg_get_serial_sequence('run_set', 'id'), max(id)) FROM run_set;

-- recreate constraints
ALTER TABLE run_set ADD PRIMARY KEY (id);
ALTER TABLE ONLY run_set_test_case_link ADD CONSTRAINT run_set_test_case_link_fk_run_set FOREIGN KEY (run_set_id) REFERENCES run_set(id);
ALTER TABLE ONLY run_set_job_link ADD CONSTRAINT run_set_job_link_fk_run_set FOREIGN KEY (run_set_id) REFERENCES run_set(id);
ALTER TABLE ONLY driver_entry ADD CONSTRAINT driver_entry_fk_run_set FOREIGN KEY (run_set_id) REFERENCES run_set(id);

ALTER TABLE "run_set_test_case_link" RENAME CONSTRAINT "test_case_fk_test_case" TO run_set_test_case_fk_test_case;