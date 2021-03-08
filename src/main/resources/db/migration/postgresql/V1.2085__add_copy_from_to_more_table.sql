-- application
ALTER TABLE "application" ADD COLUMN copy_from_id bigint;
ALTER TABLE "application" ADD CONSTRAINT application_fk_copy_from FOREIGN KEY (copy_from_id) REFERENCES "application" ("id");
-- api_schema
ALTER TABLE "api_schema" ADD COLUMN copy_from_id bigint;
ALTER TABLE "api_schema" ADD CONSTRAINT api_schema_fk_copy_from FOREIGN KEY (copy_from_id) REFERENCES "api_schema" ("id");
-- driver
ALTER TABLE "driver" ADD COLUMN copy_from_id bigint;
ALTER TABLE "driver" ADD CONSTRAINT driver_fk_copy_from FOREIGN KEY (copy_from_id) REFERENCES "driver" ("id");
-- element
ALTER TABLE "element" ADD COLUMN copy_from_id bigint;
ALTER TABLE "element" ADD CONSTRAINT element_fk_copy_from FOREIGN KEY (copy_from_id) REFERENCES "element" ("id");
-- run_set
ALTER TABLE "run_set" ADD COLUMN copy_from_id bigint;
ALTER TABLE "run_set" ADD CONSTRAINT run_set_fk_copy_from FOREIGN KEY (copy_from_id) REFERENCES "run_set" ("id");
-- section
ALTER TABLE "section" ADD COLUMN copy_from_id bigint;
ALTER TABLE "section" ADD CONSTRAINT section_fk_copy_from FOREIGN KEY (copy_from_id) REFERENCES "section" ("id");
-- test_case_folder
ALTER TABLE "test_case_folder" ADD COLUMN copy_from_id bigint;
ALTER TABLE "test_case_folder" ADD CONSTRAINT test_case_folder_fk_copy_from FOREIGN KEY (copy_from_id) REFERENCES "test_case_folder" ("id");
