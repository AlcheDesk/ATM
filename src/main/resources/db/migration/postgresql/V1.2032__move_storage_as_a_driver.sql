-- drop the storage and storage entry tables
DROP TABLE "storage_entry";
DROP TABLE "storage";

-- insert new type to driver type 
INSERT INTO "driver_type" (id, name) VALUES (6, 'Storage');

-- insert vendor list for storage driver
INSERT INTO "driver_vendor" (id, name, driver_type_id, version, active) VALUES (40, 'Samba', 6, null, true);
INSERT INTO "driver_vendor" (id, name, driver_type_id, version, active) VALUES (41, 'MongoDB', 6, null, false);

-- insert driver properties for different vendor
-- samba
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, force_predefined_value) VALUES 
(300, 'serverName', 40, 'null', 'required', 'Connection Server Name', 'string', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, force_predefined_value) VALUES 
(301, 'username', 40, 'null', 'required', 'Username', 'string', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, force_predefined_value) VALUES 
(302, 'password', 40, 'null', 'required', 'password', 'string', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, force_predefined_value) VALUES 
(303, 'domain', 40, 'null', 'required', 'domain', 'string', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, force_predefined_value) VALUES 
(304, 'ClientGuid', 40, 'null', 'ignore', 'Client UUID', 'string', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, force_predefined_value) VALUES 
(305, 'SigningRequired', 40, 'false', 'ignore', 'Required Signing', 'boolean', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, force_predefined_value) VALUES 
(306, 'ReadBufferSize', 40, '1', 'ignore', 'Read Buffer Size in MB', 'integer', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, force_predefined_value) VALUES 
(307, 'ReadTimeout', 40, '60', 'ignore', 'Read timeout in second', 'integer', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, force_predefined_value) VALUES 
(308, 'WriteBufferSize', 40, '1', 'ignore', 'Write buffer size in MB', 'integer', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, force_predefined_value) VALUES 
(309, 'WriteTimeout', 40, '60', 'ignore', 'Write timeout in second', 'integer', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, force_predefined_value) VALUES 
(310, 'TransactBufferSize', 40, '1', 'ignore', 'Transaction buffer size in MB', 'integer', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, force_predefined_value) VALUES 
(311, 'TransactTimeout', 40, '60', 'ignore', 'Transaction timeout in second', 'integer', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, force_predefined_value) VALUES 
(312, 'DfsEnabled', 40, '60', 'ignore', 'Enable Distributed File System (DFS) support', 'string', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, force_predefined_value) VALUES 
(313, 'MultiProtocolNegotiate', 40, 'false', 'ignore', 'Multi Protocol Negotiate', 'boolean', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, force_predefined_value) VALUES 
(314, 'BufferSize', 40, '1', 'ignore', 'Buffer size in MB', 'integer', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, force_predefined_value) VALUES 
(315, 'SoTimeout', 40, '0', 'ignore', 'Socket timeout in seconds', 'integer', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, force_predefined_value) VALUES 
(316, 'Timeout', 40, '60', 'ignore', 'Timeout in seconds', 'interger', false);

-- mongoDB
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, force_predefined_value) VALUES 
(320, 'connectionString', 41, 'null', 'required', 'The server FQDN or IP', 'string', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, force_predefined_value) VALUES 
(321, 'serverName', 41, '27017', 'default', 'The server FQDN or IP', 'integer', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, force_predefined_value) VALUES 
(322, 'username', 41, 'null', 'required', 'the user name', 'string', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, force_predefined_value) VALUES 
(323, 'password', 41, 'null', 'required', 'the password', 'string', false);
INSERT INTO "driver_property" (id, name, driver_vendor_id, default_value, default_action, description, value_type, force_predefined_value) VALUES 
(324, 'database', 41, 'null', 'required', 'the name of the database in which the user is defined', 'string', false);

