-- add new column to tell the instruction is using built in element
ALTER TABLE "instruction" ADD COLUMN with_driver_element BOOLEAN;
UPDATE instruction SET with_driver_element = false WHERE with_driver_element IS NULL;
ALTER TABLE "instruction" ALTER COLUMN with_driver_element SET NOT NULL;
ALTER TABLE "instruction" ALTER COLUMN with_driver_element SET DEFAULT false;


-- add column to element type to show the type is driver with at least one elemnet 
ALTER TABLE "element_type" ADD COLUMN is_driver BOOLEAN;
UPDATE element_type SET is_driver = false WHERE is_driver IS NULL;
ALTER TABLE "element_type" ALTER COLUMN is_driver SET NOT NULL;
ALTER TABLE "element_type" ALTER COLUMN is_driver SET DEFAULT false;

UPDATE element_type SET is_driver = true WHERE name = 'WebBrowser';
UPDATE element_type SET is_driver = true WHERE name = 'SQL';
UPDATE element_type SET is_driver = true WHERE name = 'JavaScript';
