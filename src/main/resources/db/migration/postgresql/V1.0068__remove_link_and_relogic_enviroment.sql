DROP TABLE "test_case_storage_link";
ALTER TABLE "storage_entry" ADD PRIMARY KEY ("id");

ALTER TABLE "environment" DROP COLUMN "value";
ALTER TABLE "environment" ADD COLUMN COMMENT text;
ALTER TABLE "environment" ADD COLUMN with_value BOOLEAN;
ALTER TABLE "environment" ALTER COLUMN with_value SET NOT NULL;
ALTER TABLE "environment" ALTER COLUMN with_value SET DEFAULT true;
ALTER TABLE "environment" ALTER COLUMN default_flag SET NOT NULL;
ALTER TABLE "environment" RENAME COLUMN "default_flag" TO reserved;
ALTER TABLE "environment" RENAME CONSTRAINT "enviroment_pkey" TO system_requirement_pkey;
ALTER TABLE "environment" DROP CONSTRAINT IF EXISTS "env_name_value_ukey";
ALTER SEQUENCE "environment_id_seq" RENAME TO system_requirement_id_seq;
ALTER TABLE "environment" RENAME TO system_requirement;

CREATE TABLE system_requirement_entry
    (
        test_case_id bigint NOT NULL,
        value text,
        name bigserial NOT NULL,
        id bigserial NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT system_requirement_entry_fk_test_case FOREIGN KEY (test_case_id) REFERENCES "test_case" ("id")
    );