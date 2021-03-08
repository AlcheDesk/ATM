ALTER TABLE "test_case_share_folder_test_case_link" RENAME COLUMN "test_case_folder_id" TO test_case_share_folder_id;
ALTER TABLE "test_case_share_folder_test_case_link" DROP CONSTRAINT "test_case_folder_test_case_link_fk_test_case_folder";
ALTER TABLE "test_case_share_folder_test_case_link" DROP CONSTRAINT "test_case_test_case_folder_ix_test_case_test_case_folder";
ALTER TABLE "test_case_share_folder_test_case_link" ADD CONSTRAINT test_case_folder_test_case_link_fk_test_case_share_folder FOREIGN KEY (test_case_share_folder_id) REFERENCES "test_case_share_folder" ("id");
ALTER TABLE "test_case_share_folder_test_case_link" ADD CONSTRAINT test_case_test_case_folder_ix_test_case_test_case_share_folder UNIQUE ("test_case_id", test_case_share_folder_id);