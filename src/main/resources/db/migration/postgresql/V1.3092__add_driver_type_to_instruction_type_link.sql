-- create the driver type instruction type table
CREATE TABLE driver_type_instruction_type_link
    (
        id bigserial,
        driver_type_id bigserial NOT NULL,
        instruction_type_id bigserial NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT driver_type_instruction_type_link_fk_driver_type FOREIGN KEY (driver_type_id)
        REFERENCES "driver_type" ("id"),
        CONSTRAINT driver_type_instruction_type_link_fk_instruction_type FOREIGN KEY
        (instruction_type_id) REFERENCES "instruction_type" ("id"),
        CONSTRAINT driver_type_instruction_type_link_ix_driver_type_instruction_type UNIQUE
        (driver_type_id, instruction_type_id)
    );

--reinsert the driver type instruction type data
INSERT INTO driver_type_instruction_type_link (driver_type_id, instruction_type_id) 
VALUES ((SELECT id FROM driver_type WHERE name = 'WebBrowser'), (SELECT id FROM instruction_type WHERE name = 'WebFunction'));
INSERT INTO driver_type_instruction_type_link (driver_type_id, instruction_type_id) 
VALUES ((SELECT id FROM driver_type WHERE name = 'WebBrowser'), (SELECT id FROM instruction_type WHERE name = 'WebBrowser'));
INSERT INTO driver_type_instruction_type_link (driver_type_id, instruction_type_id) 
VALUES ((SELECT id FROM driver_type WHERE name = 'WebBrowser'), (SELECT id FROM instruction_type WHERE name = 'JavaScript'));
INSERT INTO driver_type_instruction_type_link (driver_type_id, instruction_type_id) 
VALUES ((SELECT id FROM driver_type WHERE name = 'JDBC'), (SELECT id FROM instruction_type WHERE name = 'SQL'));
INSERT INTO driver_type_instruction_type_link (driver_type_id, instruction_type_id) 
VALUES ((SELECT id FROM driver_type WHERE name = 'API'), (SELECT id FROM instruction_type WHERE name = 'REST_API'));

--add driver type to instruction
ALTER TABLE "instruction" ADD COLUMN driver_type_id bigint;

UPDATE instruction ins
    SET driver_type_id = link.driver_type_id
    FROM driver_type_instruction_type_link link
    WHERE ins.instruction_type_id = link.instruction_type_id;
--ALTER TABLE "instruction"
--    ALTER COLUMN driver_type_id SET NOT NULL;
ALTER TABLE "instruction"
    ADD CONSTRAINT instruction_fk_driver_type_instruction_type FOREIGN KEY (driver_type_id, instruction_type_id)
    REFERENCES driver_type_instruction_type_link (driver_type_id, instruction_type_id);

-- drop the driver to element link
 ALTER TABLE "element" DROP CONSTRAINT "element_fk_driver_type_element_type";
 DROP TABLE "driver_type_element_type_link";
 -- drop column driver_type_id from element table
 ALTER TABLE "element" DROP COLUMN "driver_type_id";