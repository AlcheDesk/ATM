INSERT INTO instruction_type_instruction_action_link(instruction_type_id, instruction_action_id)
VALUES 
((SELECT id FROM instruction_type WHERE name = 'Redis'), (SELECT id FROM instruction_action WHERE name = 'Redis_append')
);
INSERT INTO instruction_type_instruction_action_link(instruction_type_id, instruction_action_id)
VALUES 
((SELECT id FROM instruction_type WHERE name = 'Redis'), (SELECT id FROM instruction_action WHERE name = 'Redis_del')
);
INSERT INTO instruction_type_instruction_action_link(instruction_type_id, instruction_action_id)
VALUES 
((SELECT id FROM instruction_type WHERE name = 'Redis'), (SELECT id FROM instruction_action WHERE name = 'Redis_exists')
);
INSERT INTO instruction_type_instruction_action_link(instruction_type_id, instruction_action_id)
VALUES 
((SELECT id FROM instruction_type WHERE name = 'Redis'), (SELECT id FROM instruction_action WHERE name = 'Redis_get')
);
INSERT INTO instruction_type_instruction_action_link(instruction_type_id, instruction_action_id)
VALUES 
((SELECT id FROM instruction_type WHERE name = 'Redis'), (SELECT id FROM instruction_action WHERE name = 'Redis_hget')
);
INSERT INTO instruction_type_instruction_action_link(instruction_type_id, instruction_action_id)
VALUES 
((SELECT id FROM instruction_type WHERE name = 'Redis'), (SELECT id FROM instruction_action WHERE name = 'Redis_hgetAll')
);
INSERT INTO instruction_type_instruction_action_link(instruction_type_id, instruction_action_id)
VALUES 
((SELECT id FROM instruction_type WHERE name = 'Redis'), (SELECT id FROM instruction_action WHERE name = 'Redis_hlen')
);
INSERT INTO instruction_type_instruction_action_link(instruction_type_id, instruction_action_id)
VALUES 
((SELECT id FROM instruction_type WHERE name = 'Redis'), (SELECT id FROM instruction_action WHERE name = 'Redis_set')
);
