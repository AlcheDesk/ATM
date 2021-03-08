-- detailed functional 
UPDATE instruction_type SET name = 'WebFunction' WHERE id = 1 AND name = 'Functional';
UPDATE instruction_type SET name = 'AppFunction' WHERE id = 5 AND name = 'APP';
UPDATE instruction_type SET name = 'REST_API' WHERE id = 3 AND name = 'API';

-- disable unused instruction type
UPDATE instruction_type SET is_active = false WHERE name = 'Performance' AND id = 2;
UPDATE instruction_type SET is_active = false WHERE name = 'AppFunction' AND id = 5;

-- add preset driver type
ALTER TABLE "instruction_type" ADD COLUMN driver_type_id bigint;

-- insert driver type value
UPDATE instruction_type SET driver_type_id = (SELECT id FROM driver_type WHERE name = 'WebBrowser') WHERE name = 'WebFunction';
--UPDATE instruction_type SET driver_type_id = (SELECT id FROM driver_type WHERE name = 'Performance') WHERE name = 'Performance';
UPDATE instruction_type SET driver_type_id = (SELECT id FROM driver_type WHERE name = 'API') WHERE name = 'REST_API';
--UPDATE instruction_type SET driver_type_id = (SELECT id FROM driver_type WHERE name = 'WebBrowser') WHERE name = 'Manual';
UPDATE instruction_type SET driver_type_id = (SELECT id FROM driver_type WHERE name = 'APP') WHERE name = 'AppFunction';
--UPDATE instruction_type SET driver_type_id = (SELECT id FROM driver_type WHERE name = 'WebBrowser') WHERE name = 'Reference';
--UPDATE instruction_type SET driver_type_id = (SELECT id FROM driver_type WHERE name = 'WebBrowser') WHERE name = 'Comment';
UPDATE instruction_type SET driver_type_id = (SELECT id FROM driver_type WHERE name = 'WebBrowser') WHERE name = 'WebBrowser';
UPDATE instruction_type SET driver_type_id = (SELECT id FROM driver_type WHERE name = 'JDBC') WHERE name = 'SQL';
UPDATE instruction_type SET driver_type_id = (SELECT id FROM driver_type WHERE name = 'WebBrowser') WHERE name = 'JavaScript';

-- 