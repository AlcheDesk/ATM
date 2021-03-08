-- add email table
CREATE TABLE email
	(
	    id bigserial,
	    name text,
	    email_address text NOT NULL,
	    comment text,
	    updated_at timestamp with time zone NOT NULL,
	    created_at timestamp with time zone NOT NULL,
	    is_deleted BOOLEAN DEFAULT false NOT NULL,
	    PRIMARY KEY (id)
	);

CREATE UNIQUE INDEX email_unique_index_email_address_is_deleted
    ON email USING btree
    (email_address ASC NULLS LAST)
    WHERE is_deleted IS FALSE;

CREATE TRIGGER email_insert_create_at_update_at BEFORE INSERT ON "email" 
FOR EACH ROW EXECUTE PROCEDURE "insert_updated_at_created_at_column"();
CREATE TRIGGER email_update_created_at_updated_at BEFORE UPDATE ON "email" 
FOR EACH ROW EXECUTE PROCEDURE "update_created_at_updated_at_column"();

-- add data to email table
insert into email (email_address) select emails from notification on conflict do nothing;

UPDATE email SET name='备份邮箱' WHERE name is null;
ALTER TABLE email ALTER COLUMN name SET NOT NULL;

-- add link table
CREATE TABLE notification_email_link
    (
        id bigserial,
        notification_id bigint,
        email_id bigint,
        PRIMARY KEY (id),
        CONSTRAINT notification_email_link_fk_notification FOREIGN KEY (notification_id) REFERENCES "notification" ("id"),
        CONSTRAINT notification_email_link_fk_email FOREIGN KEY (email_id) REFERENCES "email" ("id"),
        CONSTRAINT notification_email_link_ix_notification_email UNIQUE (notification_id, email_id)
    );

-- add data to link table
insert into notification_email_link (notification_id) select id from notification;

UPDATE
    notification_email_link
SET
    email_id = (select distinct id from email where email_address = (select distinct emails from notification where id =notification_email_link.notification_id))
WHERE  notification_email_link.email_id is null;

-- drop emails from notification table
ALTER TABLE notification DROP COLUMN emails;