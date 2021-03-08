--add element action id
ALTER TABLE "instruction" ADD COLUMN element_action_id bigint;
ALTER TABLE "instruction" ADD CONSTRAINT instruction_fk_element_action FOREIGN KEY (element_action_id) REFERENCES "element_action" ("id");

--add new action to the element action table
INSERT INTO element_action (id,name) VALUES (24,'DoubleClick');
INSERT INTO element_action (id,name) VALUES (25,'IsEnabled');
INSERT INTO element_action (id,name) VALUES (26,'NonExist');
INSERT INTO element_action (id,name) VALUES (30,'POST');
INSERT INTO element_action (id,name) VALUES (31,'GET');
INSERT INTO element_action (id,name) VALUES (32,'DELETE');
INSERT INTO element_action (id,name) VALUES (33,'PATCH');
INSERT INTO element_action (id,name) VALUES (34,'PUT');
INSERT INTO element_action (id,name) VALUES (35,'OPTIONS');
INSERT INTO element_action (id,name) VALUES (36,'HEAD');

--add active column to the element action
ALTER TABLE "element_action" ADD COLUMN active BOOLEAN;
UPDATE "element_action" SET active = true WHERE active IS NULL;
ALTER TABLE "element_action" ALTER COLUMN active SET NOT NULL;
ALTER TABLE "element_action" ALTER COLUMN active SET DEFAULT true;
--mark some to inactive
UPDATE "element_action" SET active = false WHERE name = 'Upload';
UPDATE "element_action" SET active = false WHERE name = 'Sleep';

--update the instruction table to fill the element action id column
--update all the match
UPDATE instruction ins SET element_action_id = (SELECT id FROM element_action ela WHERE lower(ela.name) = lower(ins.action));
