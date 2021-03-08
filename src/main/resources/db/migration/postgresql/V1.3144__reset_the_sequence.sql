-- update the old data

-- disable instruction constraint temporarily
ALTER TABLE instruction
    DROP CONSTRAINT instruction_fk_instruction_action;

ALTER TABLE instruction
    DROP CONSTRAINT instruction_fk_instruction_type_instruction_action;

-- delete the old link data where instruction action is SwitchToNewtab with old id
DELETE FROM instruction_type_instruction_action_link
	WHERE instruction_type_id = (SELECT id FROM instruction_type WHERE name = 'WebBrowser') and
	instruction_action_id = (SELECT id FROM instruction_action WHERE name = 'SwitchToNewtab');

DELETE FROM element_type_instruction_action_link
	WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'WebBrowser') and
	instruction_action_id = (SELECT id FROM instruction_action WHERE name = 'SwitchToNewtab');

DELETE FROM instruction_action_instruction_option_link
	WHERE instruction_action_id = (SELECT id FROM instruction_action WHERE name = 'SwitchToNewtab') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE');

DELETE FROM instruction_action_instruction_option_link
	WHERE instruction_action_id = (SELECT id FROM instruction_action WHERE name = 'SwitchToNewtab') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE');

-- update instruction action SwitchToNewtab old id 1254 to new id 41
UPDATE instruction_action
	SET id=41
	WHERE id=1254;

-- insert the new link data where instruction action is SwitchToNewtab with new id
INSERT INTO instruction_type_instruction_action_link(
	id, instruction_type_id, instruction_action_id)
	VALUES (42, (SELECT id FROM instruction_type WHERE name = 'WebBrowser'), (SELECT id FROM instruction_action WHERE name = 'SwitchToNewtab'));

INSERT INTO element_type_instruction_action_link(
	id, element_type_id, instruction_action_id)
	VALUES (325, (SELECT id FROM element_type WHERE name = 'WebBrowser'), (SELECT id FROM instruction_action WHERE name = 'SwitchToNewtab'));

INSERT INTO instruction_action_instruction_option_link(
	id, instruction_action_id, instruction_option_id)
	VALUES (142, (SELECT id FROM instruction_action WHERE name = 'SwitchToNewtab'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));

INSERT INTO instruction_action_instruction_option_link(
	id, instruction_action_id, instruction_option_id)
	VALUES (143, (SELECT id FROM instruction_action WHERE name = 'SwitchToNewtab'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));

-- update instruction where action is SwitchToNewtab
UPDATE instruction
	SET instruction_action_id=41
	WHERE instruction_action_id=1254;

-- enable instruction constraint
ALTER TABLE instruction
    ADD CONSTRAINT instruction_fk_instruction_action FOREIGN KEY (instruction_action_id)
    REFERENCES instruction_action (id) ;

ALTER TABLE instruction
    ADD CONSTRAINT instruction_fk_instruction_type_instruction_action FOREIGN KEY (instruction_action_id, instruction_type_id)
    REFERENCES instruction_type_instruction_action_link (instruction_action_id, instruction_type_id) ;

-- reset the sequence
SELECT setval('element_type_instruction_option_link_id_seq', 999, true);
SELECT setval('instruction_action_instruction_option_link_id_seq', 999, true);
SELECT setval('instruction_type_element_type_link_id_seq', 999, true);

-- update the link data where id > 999
UPDATE instruction_type_instruction_action_link
	SET id=42
	WHERE id=1000;

UPDATE element_type_instruction_action_link
	SET id=325
	WHERE id=1000;