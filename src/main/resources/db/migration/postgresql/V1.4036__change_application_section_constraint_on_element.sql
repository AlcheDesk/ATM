-- remove the constraint
ALTER TABLE "element" DROP CONSTRAINT "element_ck_project_section";

-- add application id
ALTER TABLE "element" ADD COLUMN application_id bigint;
ALTER TABLE "element" ADD CONSTRAINT element_fk_application FOREIGN KEY (application_id) REFERENCES "application" ("id");
DROP INDEX "element_unique_index_name_project_id_is_deleted";

-- update the element table
UPDATE "element" ele SET project_id = com.project_id, application_id = com.application_id FROM 
(select app.id as application_id, app.project_id, sec.id as section_id from application app inner join "section" sec on app.id = sec.application_id ) com 
WHERE com.section_id = ele.section_id AND ele.section_id IS NOT NULL;

-- recreate the constraint
ALTER TABLE "element" ADD CONSTRAINT element_ck_application_section CHECK 
(
(project_id IS NOT NULL AND application_id IS NULL AND section_id IS NULL) 
OR 
(project_id IS NOT NULL AND application_id IS NOT NULL AND section_id IS NOT NULL) 
OR id < 1000
);