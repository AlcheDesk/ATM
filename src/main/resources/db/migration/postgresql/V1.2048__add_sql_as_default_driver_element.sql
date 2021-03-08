-- SQL
INSERT INTO element (id,name,comment,locator_value,element_type_id,element_locator_type_id,is_driver) VALUES
(3,
'Database JDBC Driver',
'This is JDBC Driver, created by system',
NULL,
(SELECT id FROM element_type WHERE name = 'SQL'),
NULL,
true
);