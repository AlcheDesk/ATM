DROP TABLE "project_type_link";
UPDATE "test_case_folder" SET type_id = 1 WHERE type_id IS NULL or type_id = 0;
ALTER TABLE "test_case_folder" RENAME COLUMN "type_id" TO test_case_folder_type_id;
ALTER TABLE "test_case_folder" ADD CONSTRAINT testcasefolder_fk_type_id FOREIGN KEY ("test_case_folder_type_id") REFERENCES "object_type" ("id");
ALTER TABLE "project" RENAME COLUMN "project_type_id" TO type_id;