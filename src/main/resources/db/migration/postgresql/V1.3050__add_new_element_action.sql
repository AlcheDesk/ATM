INSERT INTO element_action(id, name, is_active, is_predefined) VALUES (39, 'MoveToElement', true, true);

INSERT INTO element_type_element_action_link(id, element_type_id, element_action_id) VALUES (316, (select id from element_type where name='WebButton'), 39);
INSERT INTO element_type_element_action_link(id, element_type_id, element_action_id) VALUES (317, (select id from element_type where name='WebTextbox'), 39);
INSERT INTO element_type_element_action_link(id, element_type_id, element_action_id) VALUES (318, (select id from element_type where name='WebRadio'), 39);
INSERT INTO element_type_element_action_link(id, element_type_id, element_action_id) VALUES (319, (select id from element_type where name='WebLink'), 39);
INSERT INTO element_type_element_action_link(id, element_type_id, element_action_id) VALUES (320, (select id from element_type where name='WebCheckbox'), 39);
INSERT INTO element_type_element_action_link(id, element_type_id, element_action_id) VALUES (321, (select id from element_type where name='WebDropdown'), 39);
INSERT INTO element_type_element_action_link(id, element_type_id, element_action_id) VALUES (322, (select id from element_type where name='WebFile'), 39);
INSERT INTO element_type_element_action_link(id, element_type_id, element_action_id) VALUES (323, (select id from element_type where name='WebFrame'), 39);