ALTER TABLE "test_case_folder" DROP COLUMN "test_case_folder_type_id";
ALTER TABLE "test_case_folder" RENAME COLUMN "comment" TO description;

ALTER SEQUENCE test_case_folder_id_seq RENAME TO test_case_share_folder_id_seq;
ALTER SEQUENCE test_case_folder_test_case_link_id_seq RENAME TO test_case_share_folder_test_case_link_id_seq;

ALTER TABLE "test_case_folder" RENAME TO test_case_share_folder;
ALTER TABLE "test_case_folder_test_case_link" RENAME TO test_case_share_folder_test_case_link;
