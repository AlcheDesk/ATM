ALTER TABLE "instruction_type" ADD COLUMN is_driver BOOLEAN;
UPDATE instruction_type SET is_driver = false WHERE is_driver IS NULL;
ALTER TABLE "instruction_type" ALTER COLUMN is_driver SET NOT NULL;
ALTER TABLE "instruction_type" ALTER COLUMN is_driver SET DEFAULT false;
UPDATE instruction_type SET is_driver = true WHERE name = 'SQL' OR name = 'JavaScript' OR name = 'WebBrowser';