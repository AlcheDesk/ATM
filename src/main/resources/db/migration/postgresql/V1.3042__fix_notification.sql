DELETE FROM run_set_notification_link WHERE id is not null;
DROP TABLE run_set_notification_link;

DELETE FROM notification WHERE id is not null;
DROP TABLE notification;

CREATE TABLE notification
    (
        id bigserial,
        emails text,
        subject text,
        messages text,
        attachments text,
        COMMENT text,
        run_set_id bigint,
        log text,
        updated_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        created_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        is_deleted BOOLEAN DEFAULT false NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT notification_fk_run_set FOREIGN KEY (run_set_id) REFERENCES run_set (id)
    );

CREATE TRIGGER notification_insert_create_at_update_at BEFORE INSERT ON "notification" 
FOR EACH ROW EXECUTE PROCEDURE "insert_updated_at_created_at_column"();
CREATE TRIGGER notification_update_created_at_updated_at BEFORE UPDATE ON "notification" 
FOR EACH ROW EXECUTE PROCEDURE "update_created_at_updated_at_column"();