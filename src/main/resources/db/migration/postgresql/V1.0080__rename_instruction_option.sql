ALTER TABLE "instruction_option_entry" DROP CONSTRAINT IF EXISTS "instruction_option_entry_fk_name";

UPDATE instruction_option SET name = 'ELE_REPLACE_DATA' WHERE name = 'ELE_RAPLCE_DATA' AND id = 16;
UPDATE instruction_option_entry SET name = 'ELE_REPLACE_DATA' WHERE name = 'ELE_RAPLCE_DATA';

ALTER TABLE "instruction_option_entry" ADD CONSTRAINT instruction_option_entry_fk_name FOREIGN KEY ("name") REFERENCES "instruction_option" ("name");

INSERT INTO instruction_option (id,name,with_value) VALUES (17,'BTN_CLICK_UNTIL_VERIFY_ALERT_TEXT',true);
INSERT INTO instruction_option (id,name,with_value) VALUES (18,'LNK_CLICK_UNTIL_VERIFY_ALERT_TEXT',true);
INSERT INTO instruction_option (id,name,with_value) VALUES (19,'BTN_CLICK_THEN_FIND_TEXT',true);
INSERT INTO instruction_option (id,name,with_value) VALUES (20,'LNK_CLICK_THEN_FIND_TEXT',true);
