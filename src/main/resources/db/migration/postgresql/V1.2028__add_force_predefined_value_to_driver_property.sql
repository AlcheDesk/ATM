ALTER TABLE "driver_property" ADD COLUMN force_predefined_value BOOLEAN;
UPDATE "driver_property" SET force_predefined_value = false WHERE force_predefined_value IS NULL;
ALTER TABLE "driver_property" ALTER COLUMN force_predefined_value SET NOT NULL;
ALTER TABLE "driver_property" ALTER COLUMN force_predefined_value SET DEFAULT false;

-- set some settings to use predefined value only
UPDATE "driver_property" SET force_predefined_value = true WHERE name = 'window.size';
UPDATE "driver_property" SET force_predefined_value = true WHERE name = 'unexpectedAlertBehaviour';
UPDATE "driver_property" SET force_predefined_value = true WHERE name = 'elementScrollBehavior';