-- add new column
ALTER TABLE "application" ADD COLUMN project_id bigint;
ALTER TABLE "application" ADD CONSTRAINT application_fk_project FOREIGN KEY (project_id) REFERENCES "project" ("id");

-- insert the data
UPDATE "application" target SET project_id = (SELECT project_id FROM "project_application_link" WHERE application_id = target.id ORDER BY id LIMIT 1);

-- clean up element
UPDATE "application" SET project_id = (SELECT MAX(id) FROM project WHERE is_deleted IS FALSE) WHERE project_id IS NULL;

-- update unused duplicate test cases to deleted
UPDATE application SET is_deleted = TRUE WHERE id IN (SELECT a.id FROM application a JOIN application b ON a.id > b.id AND a.name = b.name AND a.project_id = b.project_id GROUP BY a.name, a.id);

-- add constraint
CREATE UNIQUE INDEX application_unique_index_name_project_id_is_deleted ON application (name, project_id) WHERE is_deleted IS FALSE;