-- web browser instructions
UPDATE instruction SET is_driver = true, element_id = (SELECT id FROM element WHERE is_driver IS TRUE AND element_type_id IN (SELECT id FROM element_type WHERE name = 'WebBrowser') AND id < 1000 ORDER BY id ASC LIMIT 1) WHERE element_id IN
(SELECT id FROM element WHERE 
	element_type_id IN 
	(SELECT id FROM element_type WHERE name = 'WebBrowser') 
	AND element_action_id IN 
	(SELECT id FROM element_action WHERE name = 'Wait' OR name = 'Navigate' OR name = 'Back' OR name = 'Close' OR name = 'Forward' OR name = 'Refresh')
);

-- SQL instructions
UPDATE instruction SET is_driver = true, element_id = (SELECT id FROM element WHERE is_driver IS TRUE AND element_type_id IN (SELECT id FROM element_type WHERE name = 'SQL') AND id < 1000 ORDER BY id ASC LIMIT 1) WHERE element_id IN
(SELECT id FROM element WHERE 
	element_type_id IN 
	(SELECT id FROM element_type WHERE name = 'SQL') 
	AND element_action_id IN 
	(SELECT id FROM element_action WHERE name = 'Execute')
);

-- JavaScript instructions
UPDATE instruction SET is_driver = true, element_id = (SELECT id FROM element WHERE is_driver IS TRUE AND element_type_id IN (SELECT id FROM element_type WHERE name = 'JavaScript') AND id < 1000 ORDER BY id ASC LIMIT 1) WHERE element_id IN
(SELECT id FROM element WHERE 
	element_type_id IN 
	(SELECT id FROM element_type WHERE name = 'JavaScript') 
	AND element_action_id IN 
	(SELECT id FROM element_action WHERE name = 'Execute')
);