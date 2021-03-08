ALTER TABLE "test_case" ADD COLUMN priority INTEGER;
UPDATE "test_case" SET priority=10 WHERE priority IS NULL;
ALTER TABLE "test_case" ALTER COLUMN priority SET NOT NULL;
ALTER TABLE "test_case" ALTER COLUMN priority SET DEFAULT 10;

--update group id
UPDATE "test_case" SET group_id=1 WHERE group_id IS NULL;
ALTER TABLE "test_case" ALTER COLUMN group_id SET DEFAULT 1;

CREATE TABLE
    task_type
    (
        id BIGSERIAL NOT NULL,
        name TEXT NOT NULL,
        preloaded BOOLEAN DEFAULT false NOT NULL,
        PRIMARY KEY (id)
    );
    
INSERT INTO task_type (name,preloaded) VALUES ('Mixed Test',true)  ON CONFLICT DO NOTHING;
INSERT INTO task_type (name,preloaded) VALUES ('Web UI Test',true)  ON CONFLICT DO NOTHING;
INSERT INTO task_type (name,preloaded) VALUES ('Load Test',true)  ON CONFLICT DO NOTHING;
INSERT INTO task_type (name,preloaded) VALUES ('SOAP API Test',true)  ON CONFLICT DO NOTHING;
INSERT INTO task_type (name,preloaded) VALUES ('REST API Test',true)  ON CONFLICT DO NOTHING;
INSERT INTO task_type (name,preloaded) VALUES ('Script',true)  ON CONFLICT DO NOTHING;
INSERT INTO task_type (name,preloaded) VALUES ('Android Test',true)  ON CONFLICT DO NOTHING;
INSERT INTO task_type (name,preloaded) VALUES ('iOS Test',true)  ON CONFLICT DO NOTHING;



ALTER TABLE "test_case" ADD COLUMN type_id bigint;
UPDATE "test_case" SET type_id=1 WHERE type_id IS NULL;
ALTER TABLE "test_case" ALTER COLUMN type_id SET NOT NULL;
ALTER TABLE "test_case" ALTER COLUMN type_id SET DEFAULT 1;
ALTER TABLE "test_case" ADD CONSTRAINT testcase_fk_type FOREIGN KEY ("type_id") REFERENCES "task_type" ("id");


--Job Type
CREATE TABLE
    run_set_type
    (
        id BIGSERIAL NOT NULL,
        name TEXT NOT NULL,
        preloaded BOOLEAN DEFAULT false NOT NULL,
        PRIMARY KEY (id)
    );

-- insert new preloaded job type
INSERT INTO "run_set_type" (name,preloaded) VALUES ('ATM',true) ON CONFLICT DO NOTHING;
INSERT INTO "run_set_type" (name,preloaded) VALUES ('EXTERNAL',true) ON CONFLICT DO NOTHING;

ALTER TABLE "run_set" ADD COLUMN type_id bigint;
UPDATE "run_set" SET type_id=1 WHERE type_id IS NULL;
ALTER TABLE "run_set" ALTER COLUMN type_id SET NOT NULL;
ALTER TABLE "run_set" ALTER COLUMN type_id SET DEFAULT 1;
ALTER TABLE "run_set" ADD CONSTRAINT run_set_fk_type FOREIGN KEY ("type_id") REFERENCES "run_set_type" ("id");

