DELETE FROM user_activity_log WHERE user_uuid = '00112233-4455-6677-8899-aabbccddeeff';
ALTER TABLE "user_activity_log" ADD COLUMN user_name text;
UPDATE "user_activity_log" SET user_name = 'N/A' WHERE user_name IS NULL;
ALTER TABLE "user_activity_log" ALTER COLUMN user_name SET NOT NULL;