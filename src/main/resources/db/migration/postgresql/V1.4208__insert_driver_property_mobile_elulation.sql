-- added mobile emulation setting for chrome web driver
UPDATE driver_property SET is_predefined_value_required=true,value_type='string',default_value='iPhone 6/7/8 Plus' where driver_vendor_id=2 and name='mobileEmulation' and id=129;

-- mobile emulation default values
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_prefered) VALUES (55, 'iPhone 6/7/8 Plus', 129, true);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_prefered) VALUES (56, 'Galaxy S5', 129, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_prefered) VALUES (57, 'iPhone 6/7/8', 129, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_prefered) VALUES (58, 'iPad Mini', 129, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_prefered) VALUES (59, 'iPad Pro', 129, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_prefered) VALUES (60, 'Nexus 7', 129, false);
INSERT INTO driver_property_predefined_value (id, value, driver_property_id, is_prefered) VALUES (61, 'iPad', 129, false);