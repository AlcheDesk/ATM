-- add is driver column
ALTER TABLE "element" ADD COLUMN is_driver BOOLEAN;
UPDATE element SET is_driver = false;
ALTER TABLE "element" ALTER COLUMN is_driver SET NOT NULL;
ALTER TABLE "element" ALTER COLUMN is_driver SET DEFAULT false;

ALTER TABLE "element" ALTER COLUMN "locator_value" DROP NOT NULL;
ALTER TABLE "element" ALTER COLUMN "element_locator_type_id" DROP NOT NULL;
ALTER TABLE "element" ADD CONSTRAINT element_ck_locator_type CHECK ((is_driver AND element_locator_type_id IS NULL) OR (NOT is_driver AND element_locator_type_id IS NOT NULL));
ALTER TABLE "element" ADD CONSTRAINT element_ck_locator_value CHECK ((is_driver AND locator_value IS NULL) OR (NOT is_driver AND locator_value IS NOT NULL));

-- add default drvier elements
-- web browser
INSERT INTO element (id,name,comment,locator_value,element_type_id,element_locator_type_id,is_driver) VALUES
(1,
'Browser',
'This is the web browser, created by system',
NULL,
(SELECT id FROM element_type WHERE name = 'WebBrowser'),
NULL,
true
);
-- JavaScript
INSERT INTO element (id,name,comment,locator_value,element_type_id,element_locator_type_id,is_driver) VALUES
(2,
'JavaScript Executor',
'This is JavaScript executor, created by system',
NULL,
(SELECT id FROM element_type WHERE name = 'JavaScript'),
NULL,
true
);