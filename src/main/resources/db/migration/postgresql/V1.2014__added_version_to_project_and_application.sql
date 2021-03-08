ALTER TABLE "project" ADD COLUMN version text;
ALTER TABLE "application" ADD COLUMN version text;


ALTER TABLE "project" ADD CONSTRAINT project_ix_name_version UNIQUE ("name", version);
ALTER TABLE "application" ADD CONSTRAINT application_ix_name_version UNIQUE ("name", version);