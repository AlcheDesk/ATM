INSERT INTO driver_type (id, name, is_multiselectable, is_active, is_predefined) VALUES (8, 'ZooKeeper', false, true, false);
INSERT INTO instruction_type (id, name, is_active, is_predefined, is_driver, driver_type_id, is_element_required, virtual_element_id, overridable_fields) VALUES 
(15, 'RPC_Dubbo', true, true, false, 8, false, null, '@.input;@.parameter');
INSERT INTO driver_type_instruction_type_link (id, driver_type_id, instruction_type_id) VALUES (8, 8, 15);
INSERT INTO instruction_type_instruction_action_link (instruction_type_id, instruction_action_id) VALUES (15, 19);
INSERT INTO driver_vendor (id, name, driver_type_id, version, is_active, is_predefined) VALUES (82, 'ZooKeeper', 8, 'null', true, true);

INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES 
(510, 'host', 82, 'localhost', 'ignore', 'ZooKeeper Hostname', 'string', false, true, false);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES 
(511, 'port', 82, '2181', 'ignore', 'ZooKeeper port number', 'integer', false, true, false);
