-- modify instruction type table
ALTER TABLE "instruction_type" ADD COLUMN is_element_required BOOLEAN;
ALTER TABLE "instruction_type" ALTER COLUMN is_element_required SET DEFAULT false;

-- add init value for is_element_required column
UPDATE "instruction_type" SET is_element_required = false WHERE id IS NOT NULL;
UPDATE "instruction_type" SET is_element_required = true  WHERE name = 'WebFunction';
UPDATE "instruction_type" SET is_element_required = true  WHERE name = 'Performance';
UPDATE "instruction_type" SET is_element_required = true  WHERE name = 'REST_API';
UPDATE "instruction_type" SET is_element_required = true  WHERE name = 'AppFunction';
UPDATE "instruction_type" SET is_element_required = true  WHERE name = 'WebBrowser';
UPDATE "instruction_type" SET is_element_required = true  WHERE name = 'AppFunction';
UPDATE "instruction_type" SET is_element_required = true  WHERE name = 'SQL';
UPDATE "instruction_type" SET is_element_required = true  WHERE name = 'JavaScript';

-- set default value
ALTER TABLE "instruction_type" ALTER COLUMN is_element_required SET NOT NULL;