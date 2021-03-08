CREATE TABLE notification
    (
        id bigserial,
        email text,
        COMMENT text,
        updated_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        created_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        is_deleted BOOLEAN DEFAULT false NOT NULL,
        PRIMARY KEY (id)
    );

CREATE TRIGGER notification_insert_create_at_update_at BEFORE INSERT ON "notification" 
FOR EACH ROW EXECUTE PROCEDURE "insert_updated_at_created_at_column"();
CREATE TRIGGER notification_update_created_at_updated_at BEFORE UPDATE ON "notification" 
FOR EACH ROW EXECUTE PROCEDURE "update_created_at_updated_at_column"();

CREATE TABLE run_set_notification_link
    (
        id bigserial,
        run_set_id bigint,
        notification_id bigint,
        PRIMARY KEY (id),
        CONSTRAINT run_set_notification_link_fk_run_set FOREIGN KEY (run_set_id) REFERENCES "run_set" ("id"),
        CONSTRAINT run_set_notification_link_fk_notification FOREIGN KEY (notification_id) REFERENCES "notification" ("id"),
        CONSTRAINT run_set_notification_link_ix_run_set_notification UNIQUE (run_set_id, notification_id)
    );

-- tag
CREATE TRIGGER tag_insert_create_at_update_at BEFORE INSERT ON "tag" 
FOR EACH ROW EXECUTE PROCEDURE "insert_updated_at_created_at_column"();
CREATE TRIGGER tag_update_created_at_updated_at BEFORE UPDATE ON "tag" 
FOR EACH ROW EXECUTE PROCEDURE "update_created_at_updated_at_column"();