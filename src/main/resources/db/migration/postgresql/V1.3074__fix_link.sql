DELETE FROM notification_email_notification_target_link WHERE notification_id is null;
DELETE FROM notification_email_notification_target_link WHERE email_notification_target_id is null;
ALTER TABLE notification_email_notification_target_link ALTER COLUMN notification_id SET NOT NULL;
ALTER TABLE notification_email_notification_target_link ALTER COLUMN email_notification_target_id SET NOT NULL;

DELETE FROM run_set_alias_link WHERE run_set_id is null;
DELETE FROM run_set_alias_link WHERE alias_id is null;
ALTER TABLE run_set_alias_link ALTER COLUMN run_set_id SET NOT NULL;
ALTER TABLE run_set_alias_link ALTER COLUMN alias_id SET NOT NULL;

DELETE FROM run_set_notification_link WHERE run_set_id is null;
DELETE FROM run_set_notification_link WHERE notification_id is null;
ALTER TABLE run_set_notification_link ALTER COLUMN run_set_id SET NOT NULL;
ALTER TABLE run_set_notification_link ALTER COLUMN notification_id SET NOT NULL;

DELETE FROM test_case_module_link WHERE test_case_id is null;
DELETE FROM test_case_module_link WHERE module_id is null;
ALTER TABLE test_case_module_link ALTER COLUMN test_case_id SET NOT NULL;
ALTER TABLE test_case_module_link ALTER COLUMN module_id SET NOT NULL;

DELETE FROM test_case_tag_link WHERE test_case_id is null;
DELETE FROM test_case_tag_link WHERE tag_id is null;
ALTER TABLE test_case_tag_link ALTER COLUMN test_case_id SET NOT NULL;
ALTER TABLE test_case_tag_link ALTER COLUMN tag_id SET NOT NULL;