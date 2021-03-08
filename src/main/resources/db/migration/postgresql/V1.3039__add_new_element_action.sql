INSERT INTO element_action(id, name, is_active, is_predefined) VALUES (38, 'JsExcuteForElement', true, true);

INSERT INTO element_type_element_action_link(id, element_type_id, element_action_id) VALUES (308, (select id from element_type where name='WebButton'), 38);
INSERT INTO element_type_element_action_link(id, element_type_id, element_action_id) VALUES (309, (select id from element_type where name='WebLink'), 38);
INSERT INTO element_type_element_action_link(id, element_type_id, element_action_id) VALUES (310, (select id from element_type where name='WebDropdown'), 38);
INSERT INTO element_type_element_action_link(id, element_type_id, element_action_id) VALUES (311, (select id from element_type where name='WebRadio'), 38);
INSERT INTO element_type_element_action_link(id, element_type_id, element_action_id) VALUES (312, (select id from element_type where name='WebCheckbox'), 38);
INSERT INTO element_type_element_action_link(id, element_type_id, element_action_id) VALUES (313, (select id from element_type where name='WebFile'), 38);
INSERT INTO element_type_element_action_link(id, element_type_id, element_action_id) VALUES (314, (select id from element_type where name='WebFrame'), 38);
INSERT INTO element_type_element_action_link(id, element_type_id, element_action_id) VALUES (315, (select id from element_type where name='WebTextbox'), 38);