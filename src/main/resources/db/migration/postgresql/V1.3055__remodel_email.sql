ALTER TABLE "email" DROP COLUMN "email_type_id";
ALTER TRIGGER "email_insert_create_at_update_at" ON "email" RENAME TO email_notification_target_insert_create_at_update_at;
ALTER TRIGGER "email_update_created_at_updated_at" ON "email" RENAME TO email_notification_target_update_created_at_updated_at;
ALTER INDEX "email_unique_index_email_address_is_deleted" RENAME TO email_notification_target_unique_index_email_address_is_deleted;
ALTER TABLE "email" RENAME TO email_notification_target;
ALTER SEQUENCE email_id_seq RENAME TO email_notification_target_id_seq;

ALTER TABLE "notification_email_link" RENAME COLUMN "email_id" TO email_notification_target_id;
ALTER TABLE "notification_email_link" RENAME CONSTRAINT "notification_email_link_fk_notification" TO notification_email_notification_target_link_fk_notification;
ALTER TABLE "notification_email_link" DROP CONSTRAINT "notification_email_link_fk_email";
ALTER TABLE "notification_email_link" RENAME TO notification_email_notification_target_link;
ALTER TABLE "notification_email_notification_target_link" ADD CONSTRAINT
	notification_email_notification_target_link_fk_email_notification_target FOREIGN KEY (email_notification_target_id)
	REFERENCES "email_notification_target" ("id");
ALTER SEQUENCE notification_email_link_id_seq RENAME TO notification_email_notification_target_link_id_seq;

DROP TABLE "email_type";