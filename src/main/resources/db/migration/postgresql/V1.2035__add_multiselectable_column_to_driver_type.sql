ALTER TABLE "driver_type" ADD COLUMN is_multiSelectable BOOLEAN;
UPDATE "driver_type" SET is_multiSelectable = false WHERE is_multiSelectable IS NULL;
UPDATE "driver_type" SET is_multiSelectable = true WHERE name = 'WebBrowser';
ALTER TABLE "driver_type" ALTER COLUMN is_multiSelectable SET NOT NULL;
ALTER TABLE "driver_type" ALTER COLUMN is_multiSelectable SET DEFAULT false;