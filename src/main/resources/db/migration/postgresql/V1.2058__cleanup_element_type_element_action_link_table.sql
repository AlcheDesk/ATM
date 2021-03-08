-- delete the old data
DELETE FROM element_type_element_action_link WHERE id IS NOT NULL;

-- insert the new data
--browser
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (1,(SELECT id FROM element_type WHERE name = 'WebBrowser'), (SELECT id FROM element_action WHERE name = 'Navigate'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (2,(SELECT id FROM element_type WHERE name = 'WebBrowser'), (SELECT id FROM element_action WHERE name = 'Wait'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (3,(SELECT id FROM element_type WHERE name = 'WebBrowser'), (SELECT id FROM element_action WHERE name = 'Back'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (4,(SELECT id FROM element_type WHERE name = 'WebBrowser'), (SELECT id FROM element_action WHERE name = 'Close'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (5,(SELECT id FROM element_type WHERE name = 'WebBrowser'), (SELECT id FROM element_action WHERE name = 'Forward'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (6,(SELECT id FROM element_type WHERE name = 'WebBrowser'), (SELECT id FROM element_action WHERE name = 'Refresh'));

-- button
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (7,(SELECT id FROM element_type WHERE name = 'WebButton'), (SELECT id FROM element_action WHERE name = 'DoubleClick'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (8,(SELECT id FROM element_type WHERE name = 'WebButton'), (SELECT id FROM element_action WHERE name = 'Click'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (9,(SELECT id FROM element_type WHERE name = 'WebButton'), (SELECT id FROM element_action WHERE name = 'IsEnable'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (10,(SELECT id FROM element_type WHERE name = 'WebButton'), (SELECT id FROM element_action WHERE name = 'IsDisable'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (11,(SELECT id FROM element_type WHERE name = 'WebButton'), (SELECT id FROM element_action WHERE name = 'Count'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (12,(SELECT id FROM element_type WHERE name = 'WebButton'), (SELECT id FROM element_action WHERE name = 'Exist'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (13,(SELECT id FROM element_type WHERE name = 'WebButton'), (SELECT id FROM element_action WHERE name = 'NonExist'));

-- textbox
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (14,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM element_action WHERE name = 'Enter'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (15,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM element_action WHERE name = 'Match'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (16,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM element_action WHERE name = 'InputInPageText'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (17,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM element_action WHERE name = 'InputContainsPageText'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (18,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM element_action WHERE name = 'EnterReadonly'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (19,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM element_action WHERE name = 'Clear'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (20,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM element_action WHERE name = 'IsEnable'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (21,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM element_action WHERE name = 'IsDisable'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (22,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM element_action WHERE name = 'Count'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (23,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM element_action WHERE name = 'Exist'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (24,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM element_action WHERE name = 'NonExist'));

-- web radio
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (25,(SELECT id FROM element_type WHERE name = 'WebRadio'), (SELECT id FROM element_action WHERE name = 'Select'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (26,(SELECT id FROM element_type WHERE name = 'WebRadio'), (SELECT id FROM element_action WHERE name = 'IsEnable'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (27,(SELECT id FROM element_type WHERE name = 'WebRadio'), (SELECT id FROM element_action WHERE name = 'IsDisable'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (28,(SELECT id FROM element_type WHERE name = 'WebRadio'), (SELECT id FROM element_action WHERE name = 'Count'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (29,(SELECT id FROM element_type WHERE name = 'WebRadio'), (SELECT id FROM element_action WHERE name = 'Exist'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (30,(SELECT id FROM element_type WHERE name = 'WebRadio'), (SELECT id FROM element_action WHERE name = 'NonExist'));

-- web link
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (31,(SELECT id FROM element_type WHERE name = 'WebLink'), (SELECT id FROM element_action WHERE name = 'Click'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (32,(SELECT id FROM element_type WHERE name = 'WebLink'), (SELECT id FROM element_action WHERE name = 'DoubleClick'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (33,(SELECT id FROM element_type WHERE name = 'WebLink'), (SELECT id FROM element_action WHERE name = 'IsEnable'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (34,(SELECT id FROM element_type WHERE name = 'WebLink'), (SELECT id FROM element_action WHERE name = 'IsDisable'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (35,(SELECT id FROM element_type WHERE name = 'WebLink'), (SELECT id FROM element_action WHERE name = 'Count'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (36,(SELECT id FROM element_type WHERE name = 'WebLink'), (SELECT id FROM element_action WHERE name = 'Exist'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (37,(SELECT id FROM element_type WHERE name = 'WebLink'), (SELECT id FROM element_action WHERE name = 'NonExist'));

-- web checkbox
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (38,(SELECT id FROM element_type WHERE name = 'WebCheckbox'), (SELECT id FROM element_action WHERE name = 'Check'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (39,(SELECT id FROM element_type WHERE name = 'WebCheckbox'), (SELECT id FROM element_action WHERE name = 'IsEnable'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (40,(SELECT id FROM element_type WHERE name = 'WebCheckbox'), (SELECT id FROM element_action WHERE name = 'IsDisable'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (41,(SELECT id FROM element_type WHERE name = 'WebCheckbox'), (SELECT id FROM element_action WHERE name = 'Count'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (42,(SELECT id FROM element_type WHERE name = 'WebCheckbox'), (SELECT id FROM element_action WHERE name = 'Exist'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (43,(SELECT id FROM element_type WHERE name = 'WebCheckbox'), (SELECT id FROM element_action WHERE name = 'NonExist'));

-- web dropdown
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (44,(SELECT id FROM element_type WHERE name = 'WebDropdown'), (SELECT id FROM element_action WHERE name = 'Select'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (45,(SELECT id FROM element_type WHERE name = 'WebDropdown'), (SELECT id FROM element_action WHERE name = 'IsEnable'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (46,(SELECT id FROM element_type WHERE name = 'WebDropdown'), (SELECT id FROM element_action WHERE name = 'IsDisable'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (47,(SELECT id FROM element_type WHERE name = 'WebDropdown'), (SELECT id FROM element_action WHERE name = 'Count'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (48,(SELECT id FROM element_type WHERE name = 'WebDropdown'), (SELECT id FROM element_action WHERE name = 'Exist'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (49,(SELECT id FROM element_type WHERE name = 'WebDropdown'), (SELECT id FROM element_action WHERE name = 'NonExist'));

-- web file
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (50,(SELECT id FROM element_type WHERE name = 'WebFile'), (SELECT id FROM element_action WHERE name = 'FileUpload'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (51,(SELECT id FROM element_type WHERE name = 'WebFile'), (SELECT id FROM element_action WHERE name = 'FileUploadByWindow'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (52,(SELECT id FROM element_type WHERE name = 'WebFile'), (SELECT id FROM element_action WHERE name = 'FileDownload'));

-- web frame
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (53,(SELECT id FROM element_type WHERE name = 'WebFrame'), (SELECT id FROM element_action WHERE name = 'SwitchTo'));

-- SQL
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (100,(SELECT id FROM element_type WHERE name = 'SQL'), (SELECT id FROM element_action WHERE name = 'Execute'));

-- JavaScript
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (200,(SELECT id FROM element_type WHERE name = 'JavaScript'), (SELECT id FROM element_action WHERE name = 'Execute'));

-- rest api
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (300,(SELECT id FROM element_type WHERE name = 'REST_API'), (SELECT id FROM element_action WHERE name = 'POST'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (301,(SELECT id FROM element_type WHERE name = 'REST_API'), (SELECT id FROM element_action WHERE name = 'GET'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (302,(SELECT id FROM element_type WHERE name = 'REST_API'), (SELECT id FROM element_action WHERE name = 'DELETE'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (303,(SELECT id FROM element_type WHERE name = 'REST_API'), (SELECT id FROM element_action WHERE name = 'PATCH'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (304,(SELECT id FROM element_type WHERE name = 'REST_API'), (SELECT id FROM element_action WHERE name = 'PUT'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (305,(SELECT id FROM element_type WHERE name = 'REST_API'), (SELECT id FROM element_action WHERE name = 'OPTIONS'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (306,(SELECT id FROM element_type WHERE name = 'REST_API'), (SELECT id FROM element_action WHERE name = 'HEAD'));

-- reset the sequence
SELECT setval(pg_get_serial_sequence('element_type_element_action_link', 'id'), 999) FROM element_type_element_action_link;