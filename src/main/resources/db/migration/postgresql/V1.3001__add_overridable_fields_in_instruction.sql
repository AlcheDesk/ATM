ALTER TABLE "instruction_type" ADD COLUMN overridable_fields text;

-- add the values for some instruction type, this field is control buy the system, and cannot be changed
UPDATE "instruction_type" SET overridable_fields = '@.input' WHERE name = 'WebBrowser';
UPDATE "instruction_type" SET overridable_fields = '@.input;@.locatorType;@.locatorValue' WHERE name = 'WebFunction';
UPDATE "instruction_type" SET overridable_fields = '@.input' WHERE name = 'REST_API';
UPDATE "instruction_type" SET overridable_fields = '$'||'{TestCaseOverride.id}' WHERE name = 'Reference';