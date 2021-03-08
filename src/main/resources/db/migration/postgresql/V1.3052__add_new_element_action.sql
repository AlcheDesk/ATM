INSERT INTO element_action(id, name, is_active, is_predefined) VALUES (40, 'DragAndDrop', true, true);

INSERT INTO element_type_element_action_link(id, element_type_id, element_action_id) VALUES (324, (select id from element_type where name='WebButton'), 40);