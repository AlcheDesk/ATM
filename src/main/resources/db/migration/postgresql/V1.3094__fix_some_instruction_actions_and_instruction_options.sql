-- set some instruction actions inactive
UPDATE instruction_action SET is_active = false WHERE name='OPTIONS';
UPDATE instruction_action SET is_active = false WHERE name='HEAD';

-- set some instruction options inactive
DELETE FROM element_type_instruction_option_link WHERE instruction_option_id IN (
SELECT id FROM instruction_option WHERE name IN ('LNK_CLICK_UNTIL_VERIFY_ALERT_TEXT')
);

DELETE FROM instruction_action_instruction_option_link WHERE instruction_option_id IN (
SELECT id FROM instruction_option WHERE name IN ('LNK_CLICK_UNTIL_VERIFY_ALERT_TEXT')
);

UPDATE instruction_option SET is_active = false WHERE name='LNK_CLICK_UNTIL_VERIFY_ALERT_TEXT';

-- delete some element type instruction option link
DELETE FROM element_type_instruction_option_link WHERE element_type_id IN (
SELECT id FROM element_type WHERE name IN ('SQL')) and instruction_option_id IN (
SELECT id FROM instruction_option WHERE name IN ('RES_IGNORE'));

DELETE FROM element_type_instruction_option_link WHERE element_type_id IN (
SELECT id FROM element_type WHERE name IN ('SQL')) and instruction_option_id IN (
SELECT id FROM instruction_option WHERE name IN ('INS_IGNORE'));