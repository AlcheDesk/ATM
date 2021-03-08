-- add new column
ALTER TABLE "element" ADD COLUMN project_id bigint;
ALTER TABLE "element" ADD COLUMN section_id bigint;
ALTER TABLE "element" ADD CONSTRAINT element_fk_project FOREIGN KEY (project_id) REFERENCES "project" ("id");
ALTER TABLE "element" ADD CONSTRAINT element_fk_section FOREIGN KEY (section_id) REFERENCES "section" ("id");

-- insert the data
UPDATE "element" target SET project_id = (SELECT project_id FROM "project_element_link" WHERE element_id = target.id LIMIT 1);
UPDATE "element" target SET section_id = (SELECT section_id FROM "section_element_link" WHERE element_id = target.id LIMIT 1);

-- fix the element without project id or section id but link to instruction
UPDATE "element" target SET 
project_id = (SELECT project_id FROM "instruction" WHERE element_id = target.id LIMIT 1),
section_id = (SELECT section_id FROM "instruction" WHERE element_id = target.id LIMIT 1) 
WHERE project_id IS NULL AND section_id IS NULL;

-- if the element has both project id and section id, will use section id only
UPDATE "element" SET project_id = NULL WHERE project_id IS NOT NULL AND section_id IS NOT NULL;

-- delete instruction where use element has 
DELETE FROM instruction WHERE element_id IN (SELECT id FROM element WHERE project_id IS NULL AND section_id IS NULL );

-- clean up element
DELETE FROM "element" WHERE project_id IS NULL AND section_id IS NULL AND id > 999;

-- add constraint
ALTER TABLE "element" ADD CONSTRAINT element_ck_project_section CHECK ((project_id IS NULL AND section_id IS NOT NULL) OR (project_id IS NOT NULL AND section_id IS NULL) OR id < 1000);

-- if the element id less than 999, will set project id and section id null
UPDATE "element" SET project_id = NULL, section_id = NULL WHERE id < 999;