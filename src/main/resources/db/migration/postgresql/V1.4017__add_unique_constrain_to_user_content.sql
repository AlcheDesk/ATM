ALTER TABLE "user_content" ADD CONSTRAINT user_content_ix_sa256_name UNIQUE ("sha256", "original_name");

ALTER TABLE "user_content" ADD COLUMN path text;
UPDATE "user_content" SET path = '/' WHERE path IS NULL;
ALTER TABLE "user_content" ALTER COLUMN path SET NOT NULL;