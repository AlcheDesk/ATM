ALTER SEQUENCE driver_id_seq START WITH 1000;
ALTER SEQUENCE driver_property_id_seq START WITH 1000;
ALTER SEQUENCE driver_property_default_value_id_seq RENAME TO driver_property_predefined_value_id_seq;
ALTER SEQUENCE driver_property_predefined_value_id_seq START WITH 1000;
ALTER SEQUENCE driver_type_id_seq START WITH 1000;
ALTER SEQUENCE driver_vendor_id_seq START WITH 1000;