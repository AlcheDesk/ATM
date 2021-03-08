DROP TABLE "test_case_environment_link" CASCADE;
ALTER TABLE "system_requirement" ADD CONSTRAINT system_requirement_uk_name UNIQUE ("name");
ALTER TABLE "system_requirement_entry" ALTER COLUMN "name" TYPE text;
ALTER TABLE "system_requirement_entry" ADD CONSTRAINT system_requirement_entry_fk_name FOREIGN KEY ("name") REFERENCES "system_requirement" ("name");