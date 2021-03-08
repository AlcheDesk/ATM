ALTER TABLE notification DROP COLUMN email;

ALTER TABLE notification ADD COLUMN emails text NOT NULL;

ALTER TABLE notification ADD COLUMN subject text;

ALTER TABLE notification ADD COLUMN messages text;

ALTER TABLE notification ADD COLUMN attachments text;