ALTER TABLE "section" ADD COLUMN project_id bigint;
UPDATE "section" target SET project_id = (SELECT project_id FROM application WHERE id = target.application_id) WHERE target.project_id IS NULL;
ALTER TABLE "section" ALTER COLUMN project_id SET NOT NULL;
ALTER TABLE "section" ADD CONSTRAINT section_fk_project_id FOREIGN KEY (project_id) REFERENCES "project" ("id");