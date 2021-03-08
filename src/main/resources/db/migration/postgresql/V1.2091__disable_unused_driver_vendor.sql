-- disable driver
UPDATE driver_vendor SET is_active = FALSE WHERE name = 'SOAP' AND id = 71;
UPDATE driver_vendor SET is_active = FALSE WHERE name = 'RPC' AND id = 72;

-- clean up driver table
DELETE FROM driver_pack_driver_link WHERE driver_id IN (SELECT id FROM driver WHERE name = 'SOAP' OR name = 'RPC');
DELETE FROM driver WHERE name = 'SOAP' OR name = 'RPC';