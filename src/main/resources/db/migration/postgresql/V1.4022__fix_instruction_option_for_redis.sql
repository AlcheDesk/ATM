INSERT INTO instruction_action_instruction_option_link(instruction_option_id, instruction_action_id)
VALUES 
((SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'), (SELECT id FROM instruction_action WHERE name = 'Redis_append')
);
INSERT INTO instruction_action_instruction_option_link(instruction_option_id, instruction_action_id)
VALUES 
((SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'), (SELECT id FROM instruction_action WHERE name = 'Redis_del')
);
INSERT INTO instruction_action_instruction_option_link(instruction_option_id, instruction_action_id)
VALUES 
((SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'), (SELECT id FROM instruction_action WHERE name = 'Redis_exists')
);
INSERT INTO instruction_action_instruction_option_link(instruction_option_id, instruction_action_id)
VALUES 
((SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'), (SELECT id FROM instruction_action WHERE name = 'Redis_get')
);
INSERT INTO instruction_action_instruction_option_link(instruction_option_id, instruction_action_id)
VALUES 
((SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'), (SELECT id FROM instruction_action WHERE name = 'Redis_hget')
);
INSERT INTO instruction_action_instruction_option_link(instruction_option_id, instruction_action_id)
VALUES 
((SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'), (SELECT id FROM instruction_action WHERE name = 'Redis_hgetAll')
);
INSERT INTO instruction_action_instruction_option_link(instruction_option_id, instruction_action_id)
VALUES 
((SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'), (SELECT id FROM instruction_action WHERE name = 'Redis_hlen')
);
INSERT INTO instruction_action_instruction_option_link(instruction_option_id, instruction_action_id)
VALUES 
((SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'), (SELECT id FROM instruction_action WHERE name = 'Redis_set')
);
