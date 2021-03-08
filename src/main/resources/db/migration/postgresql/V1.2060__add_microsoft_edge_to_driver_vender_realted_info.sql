-- shift the current vendor
ALTER TABLE "driver_property" DROP CONSTRAINT "driver_property_fk_driver_vendor";
-- shift the database vendors
UPDATE driver_vendor SET id = id + 40 WHERE id > 9;
UPDATE driver_property SET driver_vendor_id = driver_vendor_id + 40 WHERE driver_vendor_id > 9;
UPDATE driver_vendor SET id = id + 20 WHERE id > 5 AND id < 9;
UPDATE driver_property SET driver_vendor_id = driver_vendor_id + 20 WHERE driver_vendor_id > 5 AND driver_vendor_id < 9;
-- ad back the constraint
ALTER TABLE ONLY driver_property ADD CONSTRAINT driver_property_fk_driver_vendor FOREIGN KEY (driver_vendor_id) REFERENCES driver_vendor (id);

-- insert the edge info
INSERT INTO driver_vendor (id, name, driver_type_id, is_active, is_predefined) VALUES (6, 'Edge', 1, true, true);

--insert property
INSERT INTO driver_property (id, driver_vendor_id, name, default_value, default_action, value_type, description) VALUES (66,6,'window.size','1366x768','default','string','Browser Screen Resolution');
INSERT INTO driver_property (id, driver_vendor_id, name, default_value, default_action, value_type, description) VALUES (70,6,'javascriptEnabled','true','ignore','boolean','Whether the session supports executing user supplied JavaScript in the context of the current page (only on HTMLUnitDriver).');
INSERT INTO driver_property (id, driver_vendor_id, name, default_value, default_action, value_type, description) VALUES (71,6,'databaseEnabled','true','ignore','boolean','Whether the session can interact with database storage.');
INSERT INTO driver_property (id, driver_vendor_id, name, default_value, default_action, value_type, description) VALUES (72,6,'locationContextEnabled','true','ignore','boolean','Whether the session can set and query the browser''s location context.');
INSERT INTO driver_property (id, driver_vendor_id, name, default_value, default_action, value_type, description) VALUES (73,6,'applicationCacheEnabled','true','ignore','boolean','Whether the session can interact with the application cache.');
INSERT INTO driver_property (id, driver_vendor_id, name, default_value, default_action, value_type, description) VALUES (74,6,'browserConnectionEnabled','true','ignore','boolean','Whether the session can query for the browser''s connectivity and disable it if desired.');
INSERT INTO driver_property (id, driver_vendor_id, name, default_value, default_action, value_type, description) VALUES (75,6,'webStorageEnabled','true','ignore','boolean','Whether the session supports interactions with storage objects. https://www.w3.org/TR/2009/WD-webstorage-20091029/');
INSERT INTO driver_property (id, driver_vendor_id, name, default_value, default_action, value_type, description) VALUES (76,6,'acceptSslCerts','true','ignore','boolean','Whether the session should accept all SSL certs by default.');
INSERT INTO driver_property (id, driver_vendor_id, name, default_value, default_action, value_type, description) VALUES (77,6,'rotatable','true','ignore','boolean','Whether the session can rotate the current page''s current layout between portrait and landscape orientations (only applies to mobile platforms).');
INSERT INTO driver_property (id, driver_vendor_id, name, default_value, default_action, value_type, description) VALUES (78,6,'nativeEvents','true','ignore','boolean','Whether the session is capable of generating native events when simulating user input.');
INSERT INTO driver_property (id, driver_vendor_id, name, default_value, default_action, value_type, description) VALUES (79,6,'proxy','null','ignore','proxy object','Details of any proxy to use. If no proxy is specified, whatever the system''s current or default state is used. The format is specified under Proxy JSON Object.');
INSERT INTO driver_property (id, driver_vendor_id, name, default_value, default_action, value_type, description) VALUES (80,6,'unexpectedAlertBehaviour','accept','ignore','string','What the browser should do with an unhandled alert before throwing out the UnhandledAlertException. Possible values are \"accept\", \"dismiss\" and \"ignore\"');
INSERT INTO driver_property (id, driver_vendor_id, name, default_value, default_action, value_type, description) VALUES (81,6,'elementScrollBehavior','0','ignore','integer','Allows the user to specify whether elements are scrolled into the viewport for interaction to align with the top (0) or bottom (1) of the viewport. The default value is to align with the top of the viewport. Supported in IE and Firefox (since 2.36)');
