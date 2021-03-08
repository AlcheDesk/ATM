UPDATE element_type SET is_active= true WHERE name = 'REST_API';

INSERT INTO instruction_type_element_type_link (id,instruction_type_id,element_type_id) 
VALUES 
(
11,
(SELECT id FROM instruction_type WHERE name = 'WebBrowser'), 
(SELECT id FROM element_type WHERE name = 'WebBrowser')
);

INSERT INTO instruction_type_element_type_link (id,instruction_type_id,element_type_id) 
VALUES 
(
12,
(SELECT id FROM instruction_type WHERE name = 'SQL'), 
(SELECT id FROM element_type WHERE name = 'SQL')
);

INSERT INTO instruction_type_element_type_link (id,instruction_type_id,element_type_id) 
VALUES 
(
13,
(SELECT id FROM instruction_type WHERE name = 'JavaScript'), 
(SELECT id FROM element_type WHERE name = 'JavaScript')
);