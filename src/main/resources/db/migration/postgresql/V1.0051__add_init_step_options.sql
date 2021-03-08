ALTER TABLE "step_option" ADD COLUMN with_value BOOLEAN;
ALTER TABLE "step_option" ALTER COLUMN with_value SET NOT NULL;
ALTER TABLE "step_option" ALTER COLUMN with_value SET DEFAULT false;
ALTER TABLE "step_option" RENAME COLUMN "preset" TO preloaded;

INSERT INTO step_option (name,preloaded) VALUES ('RESULT_IGNORE',true) ON CONFLICT DO NOTHING;
INSERT INTO step_option (name,preloaded) VALUES ('IGNORE',true) ON CONFLICT DO NOTHING;
INSERT INTO step_option (name,preloaded) VALUES ('STOP',true) ON CONFLICT DO NOTHING;
INSERT INTO step_option (name,preloaded) VALUES ('IGNORE',true) ON CONFLICT DO NOTHING;
INSERT INTO step_option (name,preloaded) VALUES ('IGNORE_DISABLED_BUTTON',true) ON CONFLICT DO NOTHING;
INSERT INTO step_option (name,preloaded) VALUES ('BUTT_UNTIL_DISABLE',true) ON CONFLICT DO NOTHING;
INSERT INTO step_option (name,preloaded) VALUES ('BUTT_UNTIL_DISAPPEARS',true) ON CONFLICT DO NOTHING;
INSERT INTO step_option (name,preloaded) VALUES ('BUTT_UNTIL_POPUP',true) ON CONFLICT DO NOTHING;
INSERT INTO step_option (name,preloaded) VALUES ('BUTT_RANDOM_CLICK_ONE',true) ON CONFLICT DO NOTHING;
INSERT INTO step_option (name,preloaded) VALUES ('BUTTON_AlERT_OK',true) ON CONFLICT DO NOTHING;
INSERT INTO step_option (name,preloaded) VALUES ('FAIL_IGNORE_START',true) ON CONFLICT DO NOTHING;
INSERT INTO step_option (name,preloaded) VALUES ('FAIL_IGNORE_END',true) ON CONFLICT DO NOTHING;
INSERT INTO step_option (name,preloaded) VALUES ('NO_RADIO_VERIFY',true) ON CONFLICT DO NOTHING;
INSERT INTO step_option (name,preloaded) VALUES ('INSTRUCTION_NO_RADIO_VERIFY',true) ON CONFLICT DO NOTHING;

INSERT INTO step_option (name) VALUES ('BUTTON_AlERT_INFO') ON CONFLICT DO NOTHING;
INSERT INTO step_option (name) VALUES ('BUTTON_AlERT_INFO') ON CONFLICT DO NOTHING;
INSERT INTO step_option (name) VALUES ('LINK_FIND_TEXT') ON CONFLICT DO NOTHING;
INSERT INTO step_option (name) VALUES ('LINK_AlERT_INFO') ON CONFLICT DO NOTHING;
INSERT INTO step_option (name) VALUES ('SAVE_INPUT') ON CONFLICT DO NOTHING;
INSERT INTO step_option (name) VALUES ('SAVE_TEXT') ON CONFLICT DO NOTHING;