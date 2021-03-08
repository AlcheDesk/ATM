-- web browser instructions
UPDATE instruction SET instruction_type_id = (SELECT id FROM instruction_type WHERE name = 'WebBrowser') WHERE is_driver = true AND element_id = (SELECT id FROM element WHERE is_driver IS TRUE AND element_type_id IN (SELECT id FROM element_type WHERE name = 'WebBrowser') AND id < 1000 ORDER BY id ASC LIMIT 1);

-- SQL instructions
UPDATE instruction SET instruction_type_id = (SELECT id FROM instruction_type WHERE name = 'SQL') WHERE is_driver = true AND element_id = (SELECT id FROM element WHERE is_driver IS TRUE AND element_type_id IN (SELECT id FROM element_type WHERE name = 'SQL') AND id < 1000 ORDER BY id ASC LIMIT 1);

-- JavaScript instructions
UPDATE instruction SET instruction_type_id = (SELECT id FROM instruction_type WHERE name = 'JavaScript') WHERE is_driver = true AND element_id = (SELECT id FROM element WHERE is_driver IS TRUE AND element_type_id IN (SELECT id FROM element_type WHERE name = 'JavaScript') AND id < 1000 ORDER BY id ASC LIMIT 1);