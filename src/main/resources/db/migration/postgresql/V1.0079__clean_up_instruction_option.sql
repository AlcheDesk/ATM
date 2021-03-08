ALTER TABLE "instruction_option_entry" DROP CONSTRAINT IF EXISTS "instruction_option_entry_fk_name";

DELETE FROM instruction_option WHERE name = 'BUTTON_AlERT_INFO';
DELETE FROM instruction_option WHERE name = 'BUTTON_FIND_TEXT';
DELETE FROM instruction_option WHERE name = 'LINK_FIND_TEXT';
DELETE FROM instruction_option WHERE name = 'LINK_AlERT_INFO';

UPDATE instruction_option SET id = 1, name = 'RES_IGNORE' WHERE name = 'RESULT_IGNORE';
UPDATE instruction_option SET id = 2, name = 'SYS_STOP' WHERE name = 'STOP';
UPDATE instruction_option SET id = 3, name = 'BTN_IGNORE_DISABLED' WHERE name = 'IGNORE_DISABLED_BUTTON';
UPDATE instruction_option SET id = 4, name = 'BTN_CLICK_UNTIL_DISABLE' WHERE name = 'BUTT_UNTIL_DISABLE';
UPDATE instruction_option SET id = 5, name = 'BTN_CLICK_UNTIL_DISAPPEARS' WHERE name = 'BUTT_UNTIL_DISAPPEARS';
UPDATE instruction_option SET id = 6, name = 'BTN_CLICK_UNTIL_POPUP' WHERE name = 'BUTT_UNTIL_POPUP';
UPDATE instruction_option SET id = 7, name = 'BTN_RANDOM_CLICK_ONE' WHERE name = 'BUTT_RANDOM_CLICK_ONE';
UPDATE instruction_option SET id = 8, name = 'BTN_AlERT_CLICK_OK' WHERE name = 'BUTTON_AlERT_OK';
UPDATE instruction_option SET id = 9, name = 'RES_IGNORE_FAIL_START' WHERE name = 'FAIL_IGNORE_START';
UPDATE instruction_option SET id = 10, name = 'RES_IGNORE_FAIL_END' WHERE name = 'FAIL_IGNORE_END';
UPDATE instruction_option SET id = 11, name = 'OPT_NO_RADIO_VERIFY' WHERE name = 'NO_RADIO_VERIFY';
UPDATE instruction_option SET id = 12, name = 'OPT_NO_DROPDOWN_VERIFY' WHERE name = 'INSTRUCTION_NO_RADIO_VERIFY';
UPDATE instruction_option SET id = 13, name = 'DTA_SAVE_INPUT' WHERE name = 'SAVE_INPUT';
UPDATE instruction_option SET id = 14, name = 'DTA_SAVE_TEXT' WHERE name = 'SAVE_TEXT';
UPDATE instruction_option SET id = 15, name = 'INS_IGNORE' WHERE name = 'IGNORE';

----------------------------------------------------------------------------------------------------------
DELETE FROM instruction_option_entry WHERE name = 'BUTTON_AlERT_INFO';
DELETE FROM instruction_option_entry WHERE name = 'BUTTON_FIND_TEXT';
DELETE FROM instruction_option_entry WHERE name = 'LINK_FIND_TEXT';
DELETE FROM instruction_option_entry WHERE name = 'LINK_AlERT_INFO';

UPDATE instruction_option_entry SET name = 'RES_IGNORE' WHERE name = 'RESULT_IGNORE';
UPDATE instruction_option_entry SET name = 'SYS_STOP' WHERE name = 'STOP';
UPDATE instruction_option_entry SET name = 'BTN_IGNORE_DISABLED' WHERE name = 'IGNORE_DISABLED_BUTTON';
UPDATE instruction_option_entry SET name = 'BTN_CLICK_UNTIL_DISABLE' WHERE name = 'BUTT_UNTIL_DISABLE';
UPDATE instruction_option_entry SET name = 'BTN_CLICK_UNTIL_DISAPPEARS' WHERE name = 'BUTT_UNTIL_DISAPPEARS';
UPDATE instruction_option_entry SET name = 'BTN_CLICK_UNTIL_POPUP' WHERE name = 'BUTT_UNTIL_POPUP';
UPDATE instruction_option_entry SET name = 'BTN_RANDOM_CLICK_ONE' WHERE name = 'BUTT_RANDOM_CLICK_ONE';
UPDATE instruction_option_entry SET name = 'BTN_AlERT_CLICK_OK' WHERE name = 'BUTTON_AlERT_OK';
UPDATE instruction_option_entry SET name = 'RES_IGNORE_FAIL_START' WHERE name = 'FAIL_IGNORE_START';
UPDATE instruction_option_entry SET name = 'RES_IGNORE_FAIL_END' WHERE name = 'FAIL_IGNORE_END';
UPDATE instruction_option_entry SET name = 'OPT_NO_RADIO_VERIFY' WHERE name = 'NO_RADIO_VERIFY';
UPDATE instruction_option_entry SET name = 'OPT_NO_DROPDOWN_VERIFY' WHERE name = 'INSTRUCTION_NO_RADIO_VERIFY';
UPDATE instruction_option_entry SET name = 'DTA_SAVE_INPUT' WHERE name = 'SAVE_INPUT';
UPDATE instruction_option_entry SET name = 'DTA_SAVE_TEXT' WHERE name = 'SAVE_TEXT';
UPDATE instruction_option_entry SET name = 'INS_IGNORE' WHERE name = 'IGNORE';

ALTER TABLE "instruction_option_entry" ADD CONSTRAINT instruction_option_entry_fk_name FOREIGN KEY ("name") REFERENCES "instruction_option" ("name");

INSERT INTO instruction_option (id,name,with_value) VALUES (16,'ELE_RAPLCE_DATA',true);