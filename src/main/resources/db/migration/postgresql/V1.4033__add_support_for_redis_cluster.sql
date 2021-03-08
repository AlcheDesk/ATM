DELETE FROM driver_property WHERE id IN (508, 509, 510);
UPDATE driver_property SET id = 508, name = 'Cluster' WHERE id = 507;
UPDATE driver_property SET id = 507 WHERE id = 511;