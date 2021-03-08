DELETE FROM notification WHERE id is not null;
ALTER TABLE notification DROP CONSTRAINT notification_fk_run_set;
ALTER TABLE notification DROP COLUMN run_set_id;

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