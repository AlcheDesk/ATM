ALTER TABLE "section" DROP COLUMN IF EXISTS "application_id";
ALTER TABLE "section" ADD COLUMN application_id bigint;
ALTER TABLE "section" ADD CONSTRAINT section_fk_application FOREIGN KEY (application_id) REFERENCES "application" ("id");
--UPDATE "section" target SET application_id = (SELECT application_id FROM application_section_link WHERE section_id = target.id);
UPDATE
    section
SET
    application_id = link.application_id
FROM
    application_section_link link
WHERE
    section.id = link.section_id
    AND link.active = true;
DROP TABLE "application_section_link" CASCADE;

ALTER TABLE "file" ADD COLUMN instruction_result_id bigint;
ALTER TABLE "file" ADD CONSTRAINT file_fk_instruction_result FOREIGN KEY (instruction_result_id) REFERENCES "instruction_result" ("id");
--UPDATE "file" target SET instruction_result_id = (SELECT instruction_result_id FROM instruction_result_file_link WHERE instruction_result_file_link.file_uuid = target.uuid LIMIT 1);
UPDATE
    file
SET
    instruction_result_id = link.instruction_result_id
FROM
    instruction_result_file_link link
WHERE
    file.uuid = link.file_uuid;
DROP TABLE "instruction_result_file_link" CASCADE;

ALTER TABLE "step_log" ADD COLUMN instruction_result_id bigint;
ALTER TABLE "step_log" ADD CONSTRAINT step_log_fk_instruction_result FOREIGN KEY (instruction_result_id) REFERENCES "instruction_result" ("id");
--UPDATE "step_log" target SET instruction_result_id = (SELECT instruction_result_id FROM instruction_result_step_log_link WHERE step_log_id = target.id LIMIT 1);
UPDATE
    step_log
SET
    instruction_result_id = link.instruction_result_id
FROM
    instruction_result_step_log_link link
WHERE
    step_log.id = link.step_log_id;
DROP TABLE "instruction_result_step_log_link" CASCADE;

ALTER TABLE "instruction_result" ADD COLUMN run_id bigint;
ALTER TABLE "instruction_result" ADD CONSTRAINT instruction_result_fk_run FOREIGN KEY (run_id) REFERENCES "run" ("id");
--UPDATE "instruction_result" target SET run_id = (SELECT run_id FROM run_instruction_result_link WHERE instruction_result_id = target.id);
UPDATE
    instruction_result
SET
    run_id = link.run_id
FROM
    run_instruction_result_link link
WHERE
    instruction_result.id = link.instruction_result_id;
DROP TABLE "run_instruction_result_link" CASCADE;

ALTER TABLE "section_line" ADD COLUMN section_id bigint;
ALTER TABLE "section_line" ADD CONSTRAINT section_line_fk_section FOREIGN KEY (section_id) REFERENCES "section" ("id");
--UPDATE "section_line" target SET section_id = (SELECT section_id FROM section_section_line_link WHERE section_line_id = target.id);
UPDATE
    section_line
SET
    section_id = link.section_id
FROM
    section_section_line_link link
WHERE
    section_line.id = link.section_line_id;
DROP TABLE "section_section_line_link" CASCADE;

ALTER TABLE "instruction" ADD COLUMN test_case_id bigint;
ALTER TABLE "instruction" ADD CONSTRAINT instruction_fk_test_case FOREIGN KEY (test_case_id) REFERENCES "test_case" ("id");
--UPDATE "instruction" target SET test_case_id = (SELECT test_case_id FROM test_case_instruction_link WHERE instruction_id = target.id);
UPDATE
    instruction
SET
    test_case_id = link.test_case_id
FROM
    test_case_instruction_link link
WHERE
    instruction.id = link.instruction_id
    AND link.active = true;   
DROP TABLE "test_case_instruction_link" CASCADE;

ALTER TABLE "run" ADD COLUMN test_case_id bigint;
ALTER TABLE "run" ADD CONSTRAINT run_fk_test_case FOREIGN KEY (test_case_id) REFERENCES "test_case" ("id");
--UPDATE "run" target SET test_case_id = (SELECT test_case_id FROM test_case_run_link WHERE run_id = target.id LIMIT 1);
UPDATE
    run
SET
    test_case_id = link.test_case_id
FROM
    test_case_run_link link
WHERE
    run.id = link.run_id;
DROP TABLE "test_case_run_link" CASCADE;