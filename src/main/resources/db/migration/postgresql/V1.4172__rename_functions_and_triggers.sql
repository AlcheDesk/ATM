ALTER TRIGGER "application_after_update_update_others" ON "application" RENAME TO application_after_update_change_others;
ALTER FUNCTION "application_update_others" ( ) RENAME TO application_update_change_others;
ALTER TRIGGER "project_after_update_update_others" ON "project" RENAME TO project_after_update_change_others;
ALTER FUNCTION "project_update_update_others" ( ) RENAME TO project_update_change_others;
ALTER TRIGGER "instruction_after_insert_update_others" ON "instruction" RENAME TO instruction_after_insert_change_others;
ALTER TRIGGER "instruction_after_update_update_others" ON "instruction" RENAME TO instruction_after_update_change_others;