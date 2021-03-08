--create the table first
CREATE TABLE step_log_type
    (
        id bigserial NOT NULL,
        name text NOT NULL,
        CONSTRAINT step_log_type_uk_name UNIQUE (name),
        PRIMARY KEY (id)
    );

-- add the step log type
INSERT INTO step_log_type (id,name) VALUES (1,'INSTRUCTION') ON CONFLICT DO NOTHING;
INSERT INTO step_log_type (id,name) VALUES (2,'INSTRUCTION_BEGIN') ON CONFLICT DO NOTHING;
INSERT INTO step_log_type (id,name) VALUES (3,'INSTRUCTION_END') ON CONFLICT DO NOTHING;
INSERT INTO step_log_type (id,name) VALUES (4,'ERROR') ON CONFLICT DO NOTHING;
INSERT INTO step_log_type (id,name) VALUES (5,'PASS') ON CONFLICT DO NOTHING;
INSERT INTO step_log_type (id,name) VALUES (6,'ELEMENT_NOT_FOUND') ON CONFLICT DO NOTHING;
INSERT INTO step_log_type (id,name) VALUES (7,'TIME_OUT') ON CONFLICT DO NOTHING;
INSERT INTO step_log_type (id,name) VALUES (8,'EXCEPTION') ON CONFLICT DO NOTHING;

-- add the new column to the step_log table
ALTER TABLE "step_log" ADD COLUMN step_log_type_id bigint;
ALTER TABLE "step_log" ADD CONSTRAINT step_log_fk_type FOREIGN KEY ("step_log_type_id") REFERENCES "step_log_type" ("id");

--move current step log
ALTER TABLE "step_log" disable TRIGGER "update_step_log_created_at_updated_at";
UPDATE step_log SET step_log_type_id = 1 WHERE message LIKE '%指令%';
UPDATE step_log SET step_log_type_id = 2 WHERE message LIKE '%指令开始%';
UPDATE step_log SET step_log_type_id = 3 WHERE message LIKE '%指令结束%';
UPDATE step_log SET step_log_type_id = 4 WHERE message LIKE '%错误%';
UPDATE step_log SET step_log_type_id = 5 WHERE message LIKE '%动作执行完毕%';
UPDATE step_log SET step_log_type_id = 6 WHERE message LIKE '%未能发现元素%';
UPDATE step_log SET step_log_type_id = 7 WHERE message LIKE '%超时%';


--clean up
DELETE FROM step_log WHERE step_log_type_id IS NULL;
ALTER TABLE step_log ALTER COLUMN step_log_type_id SET NOT NULL;

-- drop level column
ALTER TABLE "step_log" DROP CONSTRAINT IF EXISTS "log_level_fkey";    
ALTER TABLE "step_log" DROP COLUMN IF EXISTS "level_id";

--create execution log table
CREATE TABLE execution_log
    (
        id bigserial NOT NULL,
        MESSAGE text NOT NULL,
        updated_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        created_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        log_level_id bigint DEFAULT 3 NOT NULL,
        instruction_result_id bigint NOT NULL,
        run_id bigint NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT execution_log_fk_level FOREIGN KEY (log_level_id) REFERENCES "log_level" ("id"),
        CONSTRAINT execution_log_fk_instruction_result FOREIGN KEY (instruction_result_id) REFERENCES "instruction_result" ("id"),
        CONSTRAINT execution_log_fk_run FOREIGN KEY (run_id) REFERENCES "run" ("id")
    );

CREATE TRIGGER update_execution_log_created_at_updated_at BEFORE UPDATE ON execution_log FOR EACH ROW EXECUTE PROCEDURE update_created_at_updated_at_column();

-- update log level
UPDATE log_level SET name = 'ERROR' WHERE id = 1;
UPDATE log_level SET name = 'WARN' WHERE id = 2;
UPDATE log_level SET name = 'INFO' WHERE id = 3;
UPDATE log_level SET name = 'DEBUG' WHERE id = 4;
UPDATE log_level SET name = 'TRACE' WHERE id = 5;

DELETE FROM log_level WHERE id > 5;
ALTER TABLE "step_log" enable TRIGGER "update_step_log_created_at_updated_at";