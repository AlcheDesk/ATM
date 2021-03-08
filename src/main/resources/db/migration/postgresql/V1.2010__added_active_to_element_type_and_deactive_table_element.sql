ALTER TABLE "element_type" ADD COLUMN active BOOLEAN;
UPDATE "element_type" SET active = true WHERE active IS NULL;
ALTER TABLE "element_type" ALTER COLUMN active SET NOT NULL;
ALTER TABLE "element_type" ALTER COLUMN active SET DEFAULT true;

UPDATE "element_type" SET active = false WHERE name = 'Table';