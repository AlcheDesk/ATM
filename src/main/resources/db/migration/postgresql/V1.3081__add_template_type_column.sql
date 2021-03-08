ALTER TABLE "template" ALTER COLUMN type SET DEFAULT 'FreeMaker'::text;
UPDATE "template" SET type = 'FreeMaker'::text WHERE id IS NOT NULL;
ALTER TABLE "template" ALTER COLUMN type SET NOT NULL;
ALTER TABLE "template" ADD CONSTRAINT template_ck_template_type CHECK (type IN ('FreeMaker','ThymeLeaf'));
