-- rename default value to predefined value
ALTER TABLE "driver_property_default_value" RENAME TO driver_property_predefined_value;

-- add default value flag to driver_property_predefined_value
ALTER TABLE "driver_property_predefined_value" ADD COLUMN is_predered BOOLEAN;
ALTER TABLE "driver_property_predefined_value" ALTER COLUMN is_predered SET NOT NULL;
ALTER TABLE "driver_property_predefined_value" ALTER COLUMN is_predered SET DEFAULT false;

-- added browser resolution setting
INSERT INTO driver_property (id, driver_vendor_id, name, default_value, default_action, type, description) VALUES (61,1,'window.size','1366x768','default','string','Browser Screen Resolution');
INSERT INTO driver_property (id, driver_vendor_id, name, default_value, default_action, type, description) VALUES (62,2,'window.size','1366x768','default','string','Browser Screen Resolution');
INSERT INTO driver_property (id, driver_vendor_id, name, default_value, default_action, type, description) VALUES (63,3,'window.size','1366x768','default','string','Browser Screen Resolution');
INSERT INTO driver_property (id, driver_vendor_id, name, default_value, default_action, type, description) VALUES (64,4,'window.size','1366x768','default','string','Browser Screen Resolution');
INSERT INTO driver_property (id, driver_vendor_id, name, default_value, default_action, type, description) VALUES (65,5,'window.size','1366x768','default','string','Browser Screen Resolution');

-- browser resolution default values
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (1, '1920x1080', 61, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (2, '1366x768', 61, true);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (3, '1280x1024', 61, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (4, '1280x800', 61, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (5, '1024x768', 61, false);

INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (6, '1920x1080', 62, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (7, '1366x768', 62, true);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (8, '1280x1024', 62, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (9, '1280x800', 62, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (10, '1024x768', 62, false);

INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (11, '1920x1080', 63, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (12, '1366x768', 63, true);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (13, '1280x1024', 63, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (14, '1280x800', 63, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (15, '1024x768', 63, false);

INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (16, '1920x1080', 64, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (17, '1366x768', 64, true);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (18, '1280x1024', 64, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (19, '1280x800', 64, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (20, '1024x768', 64, false);

INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (21, '1920x1080', 65, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (22, '1366x768', 65, true);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (23, '1280x1024', 65, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (24, '1280x800', 65, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (25, '1024x768', 65, false);

INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (30, '0', 12, true);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (31, '1', 12, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (32, '0', 24, true);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (33, '1', 24, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (34, '0', 36, true);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (35, '1', 36, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (36, '0', 48, true);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (37, '1', 48, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (38, '0', 60, true);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (39, '1', 60, false);

INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (40, 'accept', 11, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (41, 'dismiss', 11, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (42, 'ignore', 11, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (43, 'accept', 23, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (44, 'dismiss', 23, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (45, 'ignore', 23, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (46, 'accept', 35, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (47, 'dismiss', 35, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (48, 'ignore', 35, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (49, 'accept', 47, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (50, 'dismiss', 47, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (51, 'ignore', 47, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (52, 'accept', 59, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (53, 'dismiss', 59, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_predered) VALUES (54, 'ignore', 59, false);

