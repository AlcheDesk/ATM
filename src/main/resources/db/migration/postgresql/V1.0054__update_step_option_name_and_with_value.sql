--update step option name
DELETE FROM step_option WHERE name='BUTTON_AlERT_INFO';

INSERT INTO step_option (name) VALUES ('BUTTON_AlERT_INFO') ON CONFLICT DO NOTHING;
INSERT INTO step_option (name) VALUES ('BUTTON_FIND_TEXT') ON CONFLICT DO NOTHING;
--update step option with value
UPDATE step_option SET with_value=true WHERE name='LINK_FIND_TEXT';
UPDATE step_option SET with_value=true WHERE name='LINK_AlERT_INFO';
UPDATE step_option SET with_value=true WHERE name='SAVE_INPUT';
UPDATE step_option SET with_value=true WHERE name='SAVE_TEXT';
UPDATE step_option SET with_value=true WHERE name='BUTTON_AlERT_INFO';
UPDATE step_option SET with_value=true WHERE name='BUTTON_FIND_TEXT';