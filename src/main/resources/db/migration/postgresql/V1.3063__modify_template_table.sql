ALTER TABLE "template" ADD COLUMN mode text;
UPDATE "template" SET mode = 'HTML' WHERE mode IS NULL;
ALTER TABLE "template" ALTER COLUMN mode SET NOT NULL;
ALTER TABLE "template" ALTER COLUMN mode SET DEFAULT 'HTML'::text;

ALTER TABLE "template" ADD COLUMN is_deleted BOOLEAN;
UPDATE "template" SET is_deleted = false WHERE is_deleted IS NULL;
ALTER TABLE "template" ALTER COLUMN is_deleted SET NOT NULL;
ALTER TABLE "template" ALTER COLUMN is_deleted SET DEFAULT false;

CREATE UNIQUE INDEX template_unique_index_name_is_deleted  ON template (name) WHERE (is_deleted IS FALSE);