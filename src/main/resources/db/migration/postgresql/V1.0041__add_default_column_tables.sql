ALTER TABLE "environment" ADD COLUMN default_flag BOOLEAN;
ALTER TABLE "environment" ALTER COLUMN default_flag SET DEFAULT false;
ALTER TABLE "engine" ADD COLUMN default_flag BOOLEAN;
ALTER TABLE "engine" ALTER COLUMN default_flag SET DEFAULT false;
ALTER TABLE "storage" ADD COLUMN default_flag BOOLEAN;
ALTER TABLE "storage" ALTER COLUMN default_flag SET DEFAULT false;