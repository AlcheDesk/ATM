-- add new column
ALTER TABLE "test_case" ADD COLUMN project_id bigint;
ALTER TABLE "test_case" ADD CONSTRAINT test_case_fk_project FOREIGN KEY (project_id) REFERENCES "project" ("id");

-- insert the data
UPDATE "test_case" target SET project_id = (SELECT project_id FROM "project_test_case_link" WHERE test_case_id = target.id ORDER BY id LIMIT 1);

-- clean up element
UPDATE "test_case" SET project_id = (SELECT MAX(id) FROM project WHERE is_deleted IS FALSE) WHERE project_id IS NULL;

-- update unused duplicate test cases to deleted
UPDATE test_case SET is_deleted = TRUE WHERE id IN (SELECT a.id FROM test_case a JOIN test_case b ON a.id > b.id AND a.name = b.name AND a.project_id = b.project_id GROUP BY a.name, a.id);

-- add constraint
CREATE UNIQUE INDEX test_case_unique_index_name_project_id_is_deleted ON test_case (name, project_id) WHERE is_deleted IS FALSE;