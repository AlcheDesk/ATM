ALTER TABLE "instruction_type" ADD COLUMN virtual_element_id bigint;
ALTER TABLE "instruction_type" ADD CONSTRAINT instruction_type_fk_virtual_element FOREIGN KEY (virtual_element_id) REFERENCES "element" ("id");

-- insert init data
UPDATE "instruction_type" SET virtual_element_id = (SELECT id FROM element WHERE name = 'Browser' AND is_driver IS TRUE AND id < 1000)  WHERE name = 'WebBrowser';
UPDATE "instruction_type" SET virtual_element_id = (SELECT id FROM element WHERE name = 'Database JDBC Driver' AND is_driver IS TRUE AND id < 1000)  WHERE name = 'SQL';
UPDATE "instruction_type" SET virtual_element_id = (SELECT id FROM element WHERE name = 'JavaScript Executor' AND is_driver IS TRUE AND id < 1000)  WHERE name = 'JavaScript';

-- add check
ALTER TABLE "instruction_type" ADD CONSTRAINT instruction_type_ck_virtual_element_id CHECK ((is_driver IS TRUE AND virtual_element_id < 1000 AND virtual_element_id IS NOT NULL) OR (is_driver IS FALSE AND virtual_element_id IS NULL));