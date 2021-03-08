INSERT INTO driver_type (id, name, is_multiselectable, is_active, is_predefined) VALUES (10, 'Email', false, true, false);
INSERT INTO instruction_type (id, name, is_active, is_predefined, is_driver, driver_type_id, is_element_required, virtual_element_id, overridable_fields) VALUES 
(20, 'CheckEmail', true, true, false, 10, false, null, '@.input;@.parameter');
INSERT INTO driver_type_instruction_type_link (id, driver_type_id, instruction_type_id) VALUES (12, 10, 20);
INSERT INTO instruction_type_instruction_action_link (instruction_type_id, instruction_action_id) VALUES (20, 19);
INSERT INTO driver_vendor (id, name, driver_type_id, version, is_active, is_predefined) VALUES (83, 'EmailServerConfiguration', 10, 'null', true, true);

INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES 
(575, 'StoreServerType', 83, 'IMAP', 'ignore', 'Email Cnf Store Type', 'string', true, true, false);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES 
(576, 'Account', 83, '', 'ignore', 'Email Address', 'string', false, true, false);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES 
(577, 'Password', 83, '', 'ignore', 'Email Password', 'string', false, true, false);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES 
(578, 'ServerHost', 83, '', 'ignore', 'Email Receiving Server Host', 'string', false, true, false);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES 
(579, 'ServerPort', 83, '', 'ignore', 'Email Receiving Server Port', 'integer', false, true, false);
