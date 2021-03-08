INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required) VALUES 
(500, 'host', 60, 'localhost', 'ignore', 'Hostname', 'string', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required) VALUES 
(501, 'port', 60, '6379', 'ignore', 'Redis port number', 'integer', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required) VALUES 
(502, 'timeout', 60, '1', 'ignore', 'General timeout', 'integer', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required) VALUES 
(503, 'connectionTimeout', 60, '1', 'ignore', 'Connection timeout', 'integer', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required) VALUES 
(504, 'soTimeout', 60, '1', 'ignore', 'Read timeout', 'integer', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required) VALUES 
(505, 'uri', 60, 'null', 'ignore', 'Connection URL', 'string', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required) VALUES 
(506, 'ssl', 60, 'false', 'ignore', 'SSL connection', 'boolean', false);

-- polling
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required) VALUES 
(507, 'pooling', 60, 'false', 'ignore', 'Enable connection pooling', 'boolean', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required) VALUES 
(508, 'maxTotal', 60, '10', 'ignore', 'Total connection number', 'integer', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required) VALUES 
(509, 'maxIdle', 60, 'null', 'ignore', 'Max idle connection number', 'integer', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required) VALUES 
(510, 'minIdle', 60, 'null', 'ignore', 'Min idle connection number', 'integer', false);
