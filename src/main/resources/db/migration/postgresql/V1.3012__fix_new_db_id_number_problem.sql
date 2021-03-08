-- element id
CREATE OR REPLACE FUNCTION update_element_id() RETURNS void
VOLATILE
AS $$
DECLARE
   max_id bigint;
BEGIN
   IF (select max(id) from element) < 1000 THEN
		EXECUTE 'SELECT setval(''element_id_seq'', 1000, true)';
   END IF;
END
$$ LANGUAGE plpgsql;

SELECT  update_element_id();
DROP FUNCTION "update_element_id" ( );

-- driver_property id
CREATE OR REPLACE FUNCTION update_driver_property_id() RETURNS void
VOLATILE
AS $$
DECLARE
   max_id bigint;
BEGIN
   IF (select max(id) from driver_property) < 1000 THEN
		EXECUTE 'SELECT setval(''driver_property_id_seq'', 1000, true)';
   END IF;
END
$$ LANGUAGE plpgsql;

SELECT  update_driver_property_id();
DROP FUNCTION "update_driver_property_id" ( );

-- driver_type id
CREATE OR REPLACE FUNCTION update_driver_type_id() RETURNS void
VOLATILE
AS $$
DECLARE
   max_id bigint;
BEGIN
   IF (select max(id) from driver_type) < 1000 THEN
		EXECUTE 'SELECT setval(''driver_type_id_seq'', 1000, true)';
   END IF;
END
$$ LANGUAGE plpgsql;

SELECT  update_driver_type_id();
DROP FUNCTION "update_driver_type_id" ( );

-- driver_vendor id
CREATE OR REPLACE FUNCTION update_driver_vendor_id() RETURNS void
VOLATILE
AS $$
DECLARE
   max_id bigint;
BEGIN
   IF (select max(id) from driver_vendor) < 1000 THEN
		EXECUTE 'SELECT setval(''driver_vendor_id_seq'', 1000, true)';
   END IF;
END
$$ LANGUAGE plpgsql;

SELECT  update_driver_vendor_id();
DROP FUNCTION "update_driver_vendor_id" ( );

-- log_level id
CREATE OR REPLACE FUNCTION update_log_level_id() RETURNS void
VOLATILE
AS $$
DECLARE
   max_id bigint;
BEGIN
   IF (select max(id) from log_level) < 1000 THEN
		EXECUTE 'SELECT setval(''log_level_id_seq'', 1000, true)';
   END IF;
END
$$ LANGUAGE plpgsql;

SELECT  update_log_level_id();
DROP FUNCTION "update_log_level_id" ( );

-- property id
CREATE OR REPLACE FUNCTION update_property_id() RETURNS void
VOLATILE
AS $$
DECLARE
   max_id bigint;
BEGIN
   IF (select max(id) from property) < 1000 THEN
		EXECUTE 'SELECT setval(''property_id_seq'', 1000, true)';
   END IF;
END
$$ LANGUAGE plpgsql;

SELECT  update_property_id();
DROP FUNCTION "update_property_id" ( );

-- run_type id
CREATE OR REPLACE FUNCTION update_run_type_id() RETURNS void
VOLATILE
AS $$
DECLARE
   max_id bigint;
BEGIN
   IF (select max(id) from run_type) < 1000 THEN
		EXECUTE 'SELECT setval(''run_type_id_seq'', 1000, true)';
   END IF;
END
$$ LANGUAGE plpgsql;

SELECT  update_run_type_id();
DROP FUNCTION "update_run_type_id" ( );

-- status id
CREATE OR REPLACE FUNCTION update_status_id() RETURNS void
VOLATILE
AS $$
DECLARE
   max_id bigint;
BEGIN
   IF (select max(id) from status) < 1000 THEN
		EXECUTE 'SELECT setval(''status_id_seq'', 1000, true)';
   END IF;
END
$$ LANGUAGE plpgsql;

SELECT  update_status_id();
DROP FUNCTION "update_status_id" ( );

-- step_log_type id
CREATE OR REPLACE FUNCTION update_step_log_type_id() RETURNS void
VOLATILE
AS $$
DECLARE
   max_id bigint;
BEGIN
   IF (select max(id) from step_log_type) < 1000 THEN
		EXECUTE 'SELECT setval(''step_log_type_id_seq'', 1000, true)';
   END IF;
END
$$ LANGUAGE plpgsql;

SELECT  update_step_log_type_id();
DROP FUNCTION "update_step_log_type_id" ( );

-- test_case_task_type id
CREATE OR REPLACE FUNCTION update_test_case_task_type_id() RETURNS void
VOLATILE
AS $$
DECLARE
   max_id bigint;
BEGIN
   IF (select max(id) from test_case_task_type) < 1000 THEN
		EXECUTE 'SELECT setval(''test_case_task_type_id_seq'', 1000, true)';
   END IF;
END
$$ LANGUAGE plpgsql;

SELECT  update_test_case_task_type_id();
DROP FUNCTION "update_test_case_task_type_id" ( );
