ALTER TABLE notification RENAME emails TO email;

ALTER TABLE notification ADD CONSTRAINT notification_uc_email UNIQUE (email);