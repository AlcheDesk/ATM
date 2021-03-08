-- create table
CREATE TABLE
    driver_type_element_type_link
    (
        id bigserial NOT NULL,
        driver_type_id bigint NOT NULL,
        element_type_id bigint NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT driver_type_element_type_link_ix_driver_type_element_type UNIQUE (driver_type_id, element_type_id),
        CONSTRAINT driver_type_element_type_link_fk_driver_type FOREIGN KEY (driver_type_id) REFERENCES "driver_type" ("id"),
        CONSTRAINT driver_type_element_type_link_fk_element_type FOREIGN KEY (element_type_id) REFERENCES "element_type" ("id")
    );

-- set sequence value   
SELECT setval(pg_get_serial_sequence('driver_type_element_type_link', 'id'), 1000) FROM driver_type_element_type_link;
    
-- insert data
INSERT INTO driver_type_element_type_link (id, driver_type_id, element_type_id) VALUES (1, (SELECT id FROM driver_type WHERE name = 'WebBrowser'), (SELECT id FROM element_type WHERE name = 'WebButton'));
INSERT INTO driver_type_element_type_link (id, driver_type_id, element_type_id) VALUES (2, (SELECT id FROM driver_type WHERE name = 'WebBrowser'), (SELECT id FROM element_type WHERE name = 'WebLink'));
INSERT INTO driver_type_element_type_link (id, driver_type_id, element_type_id) VALUES (3, (SELECT id FROM driver_type WHERE name = 'WebBrowser'), (SELECT id FROM element_type WHERE name = 'WebDropdown'));
INSERT INTO driver_type_element_type_link (id, driver_type_id, element_type_id) VALUES (4, (SELECT id FROM driver_type WHERE name = 'WebBrowser'), (SELECT id FROM element_type WHERE name = 'WebRadio'));
INSERT INTO driver_type_element_type_link (id, driver_type_id, element_type_id) VALUES (5, (SELECT id FROM driver_type WHERE name = 'WebBrowser'), (SELECT id FROM element_type WHERE name = 'WebCheckbox'));
INSERT INTO driver_type_element_type_link (id, driver_type_id, element_type_id) VALUES (6, (SELECT id FROM driver_type WHERE name = 'WebBrowser'), (SELECT id FROM element_type WHERE name = 'WebTable'));
INSERT INTO driver_type_element_type_link (id, driver_type_id, element_type_id) VALUES (7, (SELECT id FROM driver_type WHERE name = 'WebBrowser'), (SELECT id FROM element_type WHERE name = 'WebFile'));
INSERT INTO driver_type_element_type_link (id, driver_type_id, element_type_id) VALUES (8, (SELECT id FROM driver_type WHERE name = 'WebBrowser'), (SELECT id FROM element_type WHERE name = 'WebFrame'));
INSERT INTO driver_type_element_type_link (id, driver_type_id, element_type_id) VALUES (9, (SELECT id FROM driver_type WHERE name = 'WebBrowser'), (SELECT id FROM element_type WHERE name = 'WebBrowser'));
INSERT INTO driver_type_element_type_link (id, driver_type_id, element_type_id) VALUES (10, (SELECT id FROM driver_type WHERE name = 'WebBrowser'), (SELECT id FROM element_type WHERE name = 'WebTextbox'));
INSERT INTO driver_type_element_type_link (id, driver_type_id, element_type_id) VALUES (11, (SELECT id FROM driver_type WHERE name = 'WebBrowser'), (SELECT id FROM element_type WHERE name = 'JavaScript'));

INSERT INTO driver_type_element_type_link (id, driver_type_id, element_type_id) VALUES (30, (SELECT id FROM driver_type WHERE name = 'JDBC'), (SELECT id FROM element_type WHERE name = 'SQL'));

INSERT INTO driver_type_element_type_link (id, driver_type_id, element_type_id) VALUES (40, (SELECT id FROM driver_type WHERE name = 'API'), (SELECT id FROM element_type WHERE name = 'REST_API'));
INSERT INTO driver_type_element_type_link (id, driver_type_id, element_type_id) VALUES (41, (SELECT id FROM driver_type WHERE name = 'API'), (SELECT id FROM element_type WHERE name = 'SOAP_API'));
INSERT INTO driver_type_element_type_link (id, driver_type_id, element_type_id) VALUES (42, (SELECT id FROM driver_type WHERE name = 'API'), (SELECT id FROM element_type WHERE name = 'RPC_API'));
