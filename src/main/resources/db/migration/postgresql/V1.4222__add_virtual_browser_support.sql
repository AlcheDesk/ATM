-- driver_type
INSERT INTO driver_type ( id, name, is_multiselectable, is_active, is_predefined ) VALUES ( 9, 'ChromeHeadless', false, true, true);

-- instruction_type
INSERT INTO instruction_type ( id, name, is_active, is_predefined, is_driver, driver_type_id, is_element_required, virtual_element_id, overridable_fields )
VALUES
(17, 'VirtualWebFunction', true, true, false, 9, true, null, '@.input;@.locatorType;@.locatorValue');

-- driver_type_instruction_type_link
ALTER TABLE "driver_type_instruction_type_link" DROP CONSTRAINT "driver_type_instruction_type_link_ix_instruction_type";
INSERT INTO driver_type_instruction_type_link ( id, driver_type_id, instruction_type_id ) VALUES ( 9, 9, 17 );
INSERT INTO driver_type_instruction_type_link ( id, driver_type_id, instruction_type_id ) VALUES ( 10, 9, 8 );
INSERT INTO driver_type_instruction_type_link ( id, driver_type_id, instruction_type_id ) VALUES ( 11, 9, 10 );

-- instruction_type_instruction_action_link
UPDATE instruction_type_instruction_action_link SET id = 82 WHERE id = 1001;
UPDATE instruction_type_instruction_action_link SET id = 83 WHERE id = 1002;
UPDATE instruction_type_instruction_action_link SET id = 84 WHERE id = 1003;
UPDATE instruction_type_instruction_action_link SET id = 85 WHERE id = 1004;
UPDATE instruction_type_instruction_action_link SET id = 86 WHERE id = 1005;
UPDATE instruction_type_instruction_action_link SET id = 87 WHERE id = 1006;
UPDATE instruction_type_instruction_action_link SET id = 88 WHERE id = 1007;
UPDATE instruction_type_instruction_action_link SET id = 89 WHERE id = 1008;
UPDATE instruction_type_instruction_action_link SET id = 90 WHERE id = 1009;

INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (91, 17, 1);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (92, 17, 2);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (93, 17, 4);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (94, 17, 5);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (95, 17, 6);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (96, 17, 7);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (97, 17, 8);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (98, 17, 9);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (99, 17, 12);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (100, 17, 13);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (101, 17, 14);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (102, 17, 17);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (103, 17, 18);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (104, 17, 20);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (105, 17, 21);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (106, 17, 22);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (107, 17, 23);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (108, 17, 24);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (109, 17, 25);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (111, 17, 26);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (112, 17, 37);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (113, 17, 38);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (114, 17, 39);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (115, 17, 40);
-- instruction_type_element_type_link
INSERT INTO instruction_type_element_type_link (id, instruction_type_id, element_type_id) VALUES (16, 17, 1);
INSERT INTO instruction_type_element_type_link (id, instruction_type_id, element_type_id) VALUES (17, 17, 2);
INSERT INTO instruction_type_element_type_link (id, instruction_type_id, element_type_id) VALUES (18, 17, 3);
INSERT INTO instruction_type_element_type_link (id, instruction_type_id, element_type_id) VALUES (19, 17, 4);
INSERT INTO instruction_type_element_type_link (id, instruction_type_id, element_type_id) VALUES (20, 17, 5);
INSERT INTO instruction_type_element_type_link (id, instruction_type_id, element_type_id) VALUES (21, 17, 6);
INSERT INTO instruction_type_element_type_link (id, instruction_type_id, element_type_id) VALUES (22, 17, 7);
INSERT INTO instruction_type_element_type_link (id, instruction_type_id, element_type_id) VALUES (23, 17, 8);
INSERT INTO instruction_type_element_type_link (id, instruction_type_id, element_type_id) VALUES (24, 17, 10);

-- driver_vendor
INSERT INTO driver_vendor (id, name, driver_type_id, version, is_active, is_predefined) VALUES (7, 'ChromeHeadless', 9, null, true, true);

-- driver_property
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES (550, 'unexpectedAlertBehaviour', 7, 'accept', 'ignore', 'What the browser should do with an unhandled alert before throwing out the UnhandledAlertException. Possible values are \"accept\", \"dismiss\" and \"ignore\"', 'string', true, true, true);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES (551, 'elementScrollBehavior', 7, '0', 'ignore', 'Allows the user to specify whether elements are scrolled into the viewport for interaction to align with the top (0) or bottom (1) of the viewport. The default value is to align with the top of the viewport. Supported in IE and Firefox (since 2.36)', 'integer', true, true, true);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES (552, 'args', 7, 'null', 'ignore', 'List of command-line arguments to use when starting Chrome. Arguments with an associated value should be separated by a ''='' sign (e.g., [''start-maximized'', ''user-data-dir=/tmp/temp_profile'']). See here for a list of Chrome arguments.', 'list of strings', false, true, true);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES (553, 'binary', 7, 'null', 'ignore', 'Path to the Chrome executable to use (on Mac OS X, this should be the actual binary, not just the app. e.g., ''/Applications/Google Chrome.app/Contents/MacOS/Google Chrome'')', 'string', false, true, true);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES (554, 'extensions', 7, 'null', 'ignore', 'A list of Chrome extensions to install on startup. Each item in the list should be a base-64 encoded packed Chrome extension (.crx)', 'list of strings', false, true, true);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES (555, 'localState', 7, 'null', 'ignore', 'A dictionary with each entry consisting of the name of the preference and its value. These preferences are applied to the Local State file in the user data folder.', 'dictionary', false, true, true);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES (556, 'prefs', 7, 'null', 'ignore', 'A dictionary with each entry consisting of the name of the preference and its value. These preferences are only applied to the user profile in use. See the ''Preferences'' file in Chrome''s user data directory for examples.', 'dictionary', false, true, true);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES (557, 'detach', 7, 'false', 'ignore', 'If false, Chrome will be quit when ChromeDriver is killed, regardless of whether the session is quit. If true, Chrome will only be quit if the session is quit (or closed). Note, if true, and the session is not quit, ChromeDriver cannot clean up the temporary user data directory that the running Chrome instance is using.', 'boolean', false, true, true);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES (558, 'debuggerAddress', 7, 'null', 'ignore', 'An address of a Chrome debugger server to connect to, in the form of <hostname/ip:port>, e.g. ''127.0.0.1:38947''', 'string', false, true, true);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES (559, 'excludeSwitches', 7, 'null', 'ignore', 'List of Chrome command line switches to exclude that ChromeDriver by default passes when starting Chrome.  Do not prefix switches with --.', 'list of strings', false, true, true);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES (560, 'minidumpPath', 7, 'null', 'ignore', 'Directory to store Chrome minidumps . (Supported only on Linux.)', 'string', false, true, true);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES (561, 'perfLoggingPrefs	', 7, 'null', 'ignore', 'An optional dictionary that specifies performance logging preferences. See below for more information.', 'dictionary', false, true, true);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES (562, 'windowTypes', 7, 'null', 'ignore', 'A list of window types that will appear in the list of window handles. For access to <webview> elements, include "webview" in this list.', 'list of strings', false, true, true);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES (563, 'acceptSslCerts', 7, 'true', 'ignore', 'Whether the session should accept all SSL certs by default.', 'boolean', false, true, true);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES (564, 'rotatable', 7, 'true', 'ignore', 'Whether the session can rotate the current page''s current layout between portrait and landscape orientations (only applies to mobile platforms).', 'boolean', false, true, true);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES (565, 'databaseEnabled', 7, 'true', 'ignore', 'Whether the session can interact with database storage.', 'boolean', false, true, true);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES (566, 'window.size', 7, '1366x768', 'default', 'Browser Screen Resolution', 'string', true, true, true);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES (567, 'javascriptEnabled', 7, 'true', 'ignore', 'Whether the session supports executing user supplied JavaScript in the context of the current page (only on HTMLUnitDriver).', 'boolean', false, true, true);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES (568, 'locationContextEnabled', 7, 'true', 'ignore', 'Whether the session can set and query the browser''s location context.', 'boolean', false, true, true);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES (569, 'applicationCacheEnabled', 7, 'true', 'ignore', 'Whether the session can interact with the application cache.', 'boolean', false, true, true);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES (570, 'browserConnectionEnabled', 7, 'true', 'ignore', 'Whether the session can query for the browser''s connectivity and disable it if desired.', 'boolean', false, true, true);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES (571, 'webStorageEnabled', 7, 'true', 'ignore', 'Whether the session supports interactions with storage objects. https://www.w3.org/TR/2009/WD-webstorage-20091029/', 'boolean', false, true, true);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES (572, 'nativeEvents', 7, 'true', 'ignore', 'Whether the session is capable of generating native events when simulating user input.', 'boolean', false, true, true);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES (573, 'proxy', 7, 'null', 'ignore', 'Details of any proxy to use. If no proxy is specified, whatever the system''s current or default state is used. The format is specified under Proxy JSON Object.', 'proxy object', false, true, true);
INSERT INTO driver_property (id, name, driver_vendor_id, default_value, default_action, description, value_type, is_predefined_value_required, is_active, is_predefined) VALUES (574, 'mobileEmulation', 7, 'iPhone 6/7/8 Plus', 'ignore', 'A dictionary with either a value for “deviceName,” or values for “deviceMetrics” and “userAgent.” Refer to Mobile Emulation for more information.', 'string', true, true, true);

INSERT INTO driver_property_predefined_value (id, value, driver_property_id, comment, is_prefered) VALUES (62, '1920x1080', 566, null, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, comment, is_prefered) VALUES (63, '1366x768', 566, null, true);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, comment, is_prefered) VALUES (64, '1280x1024', 566, null, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, comment, is_prefered) VALUES (65, '1280x800', 566, null, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, comment, is_prefered) VALUES (66, '1024x768', 566, null, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, comment, is_prefered) VALUES (67, '0', 551, null, true);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, comment, is_prefered) VALUES (68, '1', 551, null, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, comment, is_prefered) VALUES (69, 'accept', 550, null, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, comment, is_prefered) VALUES (70, 'dismiss', 550, null, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, comment, is_prefered) VALUES (71, 'ignore', 550, null, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, comment, is_prefered) VALUES (72, 'iPhone 6/7/8 Plus', 574, null, true);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, comment, is_prefered) VALUES (73, 'Galaxy S5', 574, null, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, comment, is_prefered) VALUES (74, 'iPhone 6/7/8', 574, null, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, comment, is_prefered) VALUES (75, 'iPad Mini', 574, null, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, comment, is_prefered) VALUES (76, 'iPad Pro', 574, null, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, comment, is_prefered) VALUES (77, 'Nexus 7', 574, null, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, comment, is_prefered) VALUES (78, 'iPad', 574, null, false);
