-- clean up element table
UPDATE element SET element_locator_type_id =  (SELECT id FROM element_locator_type WHERE name = 'URL')
WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'REST_API') AND element_locator_type_id <> (SELECT id FROM element_locator_type WHERE name = 'URL');
UPDATE element SET element_locator_type_id =  (SELECT id FROM element_locator_type WHERE name = 'Script')
WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'JavaScript') AND element_locator_type_id <> (SELECT id FROM element_locator_type WHERE name = 'Script');

-- add the entry to element type element locator type table
INSERT INTO element_type_element_locator_type_link (id, element_type_id, element_locator_type_id) 
VALUES (43, (SELECT id FROM element_type WHERE name = 'REST_API'),(SELECT id FROM element_locator_type WHERE name = 'URL'));
INSERT INTO element_type_element_locator_type_link (id, element_type_id, element_locator_type_id) 
VALUES (44, (SELECT id FROM element_type WHERE name = 'JavaScript'),(SELECT id FROM element_locator_type WHERE name = 'Script'));


-- clean up element table
DELETE FROM element WHERE id IN
(
	SELECT ele.ID FROM element ele 
	LEFT JOIN element_type_element_locator_type_link link ON ele.element_type_id = link.element_type_id AND ele.element_locator_type_id = link.element_locator_type_id 
	WHERE link.ID IS NULL AND ele.ID > 999
);

-- add constraints to the element table
ALTER TABLE "element" ADD CONSTRAINT element_fk_type_locator_type FOREIGN KEY ("element_type_id", "element_locator_type_id") 
REFERENCES "element_type_element_locator_type_link" ("element_type_id", "element_locator_type_id");

