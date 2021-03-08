ALTER TABLE "file_type" ADD PRIMARY KEY ("id");
ALTER TABLE "project_type" ADD PRIMARY KEY ("id");
ALTER TABLE "test_case_folder_type" ADD PRIMARY KEY ("id");

ALTER TABLE "file" DROP CONSTRAINT IF EXISTS "file_fk_type";
ALTER TABLE "file" ADD CONSTRAINT file_fk_type FOREIGN KEY ("file_type_id") REFERENCES "file_type" ("id");

ALTER TABLE "project" DROP CONSTRAINT IF EXISTS "project_fk_type";
ALTER TABLE "project" RENAME COLUMN "type_id" TO project_type_id;
ALTER TABLE "project" ADD CONSTRAINT project_fk_type FOREIGN KEY ("project_type_id") REFERENCES "project_type" ("id");

ALTER TABLE "test_case_folder" DROP CONSTRAINT IF EXISTS "testcasefolder_fk_type_id";
ALTER TABLE "test_case_folder" ADD CONSTRAINT test_case_folder_fk_type FOREIGN KEY ("test_case_folder_type_id") REFERENCES "test_case_folder_type" ("id");