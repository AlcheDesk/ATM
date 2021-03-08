INSERT INTO element_type (id, name, is_active, is_driver) VALUES (18, 'Redis', true, true); 
INSERT INTO driver_type (id, name, is_active, is_multiselectable) VALUES (7, 'Redis', true, false); 
INSERT INTO element (id,name,comment,locator_value,element_type_id,element_locator_type_id,is_driver,tenant_id) VALUES
(4,
'Redia Driver',
'This is Redis Driver, created by system',
NULL,
(SELECT id FROM element_type WHERE name = 'Redis'),
NULL,
true,
1
);

INSERT INTO instruction_type(id, name, is_active, is_predefined, is_driver, driver_type_id, is_element_required, virtual_element_id, overridable_fields)
VALUES (14, 'Redis', true, true, true, 7, true, 4, '$.action;$.data.key;$.data.value');

INSERT INTO instruction_type_element_type_link(id, instruction_type_id, element_type_id)
VALUES 
(
15,
(SELECT id FROM instruction_type WHERE name = 'Redis'), 
(SELECT id FROM element_type WHERE name = 'Redis')
);


INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (44, 'append',true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (45, 'del',true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (46, 'exists',true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (47, 'get',true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (48, 'hget',true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (49, 'hgetAll',true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (50, 'hlen',true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (51, 'set',true, true);


INSERT INTO element_type_instruction_action_link(element_type_id, instruction_action_id)
VALUES 
((SELECT id FROM element_type WHERE name = 'Redis'), (SELECT id FROM instruction_action WHERE name = 'append')
);
INSERT INTO element_type_instruction_action_link(element_type_id, instruction_action_id)
VALUES 
((SELECT id FROM element_type WHERE name = 'Redis'), (SELECT id FROM instruction_action WHERE name = 'del')
);
INSERT INTO element_type_instruction_action_link(element_type_id, instruction_action_id)
VALUES 
((SELECT id FROM element_type WHERE name = 'Redis'), (SELECT id FROM instruction_action WHERE name = 'exists')
);
INSERT INTO element_type_instruction_action_link(element_type_id, instruction_action_id)
VALUES 
((SELECT id FROM element_type WHERE name = 'Redis'), (SELECT id FROM instruction_action WHERE name = 'get')
);
INSERT INTO element_type_instruction_action_link(element_type_id, instruction_action_id)
VALUES 
((SELECT id FROM element_type WHERE name = 'Redis'), (SELECT id FROM instruction_action WHERE name = 'hget')
);
INSERT INTO element_type_instruction_action_link(element_type_id, instruction_action_id)
VALUES 
((SELECT id FROM element_type WHERE name = 'Redis'), (SELECT id FROM instruction_action WHERE name = 'hgetAll')
);
INSERT INTO element_type_instruction_action_link(element_type_id, instruction_action_id)
VALUES 
((SELECT id FROM element_type WHERE name = 'Redis'), (SELECT id FROM instruction_action WHERE name = 'hlen')
);
INSERT INTO element_type_instruction_action_link(element_type_id, instruction_action_id)
VALUES 
((SELECT id FROM element_type WHERE name = 'Redis'), (SELECT id FROM instruction_action WHERE name = 'set')
);
