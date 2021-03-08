UPDATE instruction SET action  = 'Enter' WHERE action  = 'Modify';

DELETE FROM element_type_element_action_link WHERE element_action_id = (SELECT id FROM element_action WHERE name = 'Modify');

DELETE FROM element_action WHERE name = 'Modify';