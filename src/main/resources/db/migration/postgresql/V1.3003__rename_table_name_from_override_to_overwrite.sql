ALTER TABLE "test_case_override" RENAME CONSTRAINT "test_case_override_pkey" TO test_case_overwrite_pkey;
ALTER TABLE "test_case_override" RENAME CONSTRAINT "test_case_input_fk_test_case" TO test_case_overwrite_fk_test_case;
ALTER TABLE "test_case_override" RENAME TO test_case_overwrite;
ALTER SEQUENCE test_case_override_id_seq RENAME TO test_case_overwrite_id_seq;

ALTER TABLE "instruction_override" RENAME CONSTRAINT "instruction_override_pkey" TO instruction_overwrite_pkey;
ALTER TABLE "instruction_override" RENAME COLUMN "test_case_override_id" TO test_case_overwrite_id;
ALTER TABLE "instruction_override" RENAME COLUMN "override_fields" TO overwrite_fields;
ALTER TABLE "instruction_override" RENAME CONSTRAINT "instruction_override_fk_test_case_override" TO instruction_overwrite_fk_test_case_overwrite;
ALTER TABLE "instruction_override" RENAME CONSTRAINT "instruction_override_ix_test_case_overide" TO instruction_overwrite_ix_test_case_overwrite;
ALTER TABLE "instruction_override" RENAME TO instruction_overwrite;
ALTER SEQUENCE instruction_override_id_seq RENAME TO instruction_overwrite_id_seq;

ALTER TRIGGER "test_case_override_insert_create_at_update_at" ON "test_case_overwrite"
RENAME TO test_case_overwrite_insert_create_at_update_at;
ALTER TRIGGER "test_case_override_update_created_at_updated_at" ON "test_case_overwrite"
RENAME TO test_case_overwrite_update_created_at_updated_at;
