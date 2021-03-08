ALTER TABLE "run_set_test_case_link" ADD COLUMN test_case_overwrite_id bigint;
ALTER TABLE "run_set_test_case_link" ADD COLUMN driver_pack_id bigint;
ALTER TABLE "run_set_test_case_link" ADD CONSTRAINT run_set_test_case_link_fk_test_case_overwrite FOREIGN KEY (test_case_overwrite_id) REFERENCES "test_case_overwrite" ("id");
ALTER TABLE "run_set_test_case_link" ADD CONSTRAINT run_set_test_case_link_fk_driver_pack FOREIGN KEY (driver_pack_id) REFERENCES "driver_pack" ("id");
