-- delete instrcution with duplciate naming element in same project
DELETE FROM instruction WHERE element_id IN 
(
	SELECT id FROM 
	(
		SELECT a.id, a.name FROM element a JOIN element b ON a.id > b.id AND a.name = b.name AND a.project_id = b.project_id GROUP BY a.name, a.id
	) AS list
);

-- delete instrcution with duplciate naming element in same section
DELETE FROM instruction WHERE element_id IN 
(
	SELECT id FROM 
	(
		SELECT a.id, a.name FROM element a JOIN element b ON a.id > b.id AND a.name = b.name AND a.section_id = b.section_id GROUP BY a.name, a.id
	) AS list
);
 
-- delete the elements with same in same project
DELETE FROM "element" WHERE id IN 
(
	SELECT id FROM 
	(
		SELECT a.id, a.name FROM element a JOIN element b ON a.id > b.id AND a.name = b.name AND a.project_id = b.project_id GROUP BY a.name, a.id
	) AS list
);

-- delete the elements with same in same section
DELETE FROM "element" WHERE id IN 
(
	SELECT id FROM 
	(
		SELECT a.id, a.name FROM element a JOIN element b ON a.id > b.id AND a.name = b.name AND a.section_id = b.section_id GROUP BY a.name, a.id
	) AS list
);

-- add constraints
ALTER TABLE "element" ADD CONSTRAINT element_ix_name_project UNIQUE ("name", "project_id");
ALTER TABLE "element" ADD CONSTRAINT element_ix_name_section UNIQUE ("name", "section_id");