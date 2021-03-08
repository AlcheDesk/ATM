ALTER TABLE "run_set_test_case_link" ADD COLUMN system_requirement_pack_id bigint;
ALTER TABLE "run_set_test_case_link" ADD CONSTRAINT run_set_test_case_link_fk_system_requirement_pack FOREIGN KEY (system_requirement_pack_id) REFERENCES "system_requirement_pack" ("id");
----------------------------------------------------------------------------------
INSERT INTO system_requirement_type (id, name, is_multiselectable, is_active, is_predefined) VALUES (1, 'CORE', false, true, false);
INSERT INTO system_requirement_type (id, name, is_multiselectable, is_active, is_predefined) VALUES (2, 'RAM', false, true, false);
INSERT INTO system_requirement_type (id, name, is_multiselectable, is_active, is_predefined) VALUES (3, 'OS', false, true, false);
INSERT INTO system_requirement_type (id, name, is_multiselectable, is_active, is_predefined) VALUES (4, 'BANDWIDTH', false, true, false);
----------------------------------------------------------------------------------
INSERT INTO system_requirement (id, name, comment, is_predefined, system_requirement_type_id, value) VALUES (1, '2 CPU core', '2 core required', true, 1, 2);
INSERT INTO system_requirement (id, name, comment, is_predefined, system_requirement_type_id, value) VALUES (2, '4 CPU core', '4 core required', false, 1, 4);
INSERT INTO system_requirement (id, name, comment, is_predefined, system_requirement_type_id, value) VALUES (3, '8 CPU core', '8 core required', false, 1, 8);
INSERT INTO system_requirement (id, name, comment, is_predefined, system_requirement_type_id, value) VALUES (4, '16 CPU core', '16 core required', false, 1, 16);

INSERT INTO system_requirement (id, name, comment, is_predefined, system_requirement_type_id, value) VALUES (5, '2GB Ram', '2GB memory required', true, 2, 2);
INSERT INTO system_requirement (id, name, comment, is_predefined, system_requirement_type_id, value) VALUES (6, '4GB Ram', '4GB memory required', false, 2, 4);
INSERT INTO system_requirement (id, name, comment, is_predefined, system_requirement_type_id, value) VALUES (7, '8GB Ram', '8GB memory required', false, 2, 8);
INSERT INTO system_requirement (id, name, comment, is_predefined, system_requirement_type_id, value) VALUES (8, '16GB Ram', '16GB memory required', false, 2, 16);
INSERT INTO system_requirement (id, name, comment, is_predefined, system_requirement_type_id, value) VALUES (9, '32GB Ram', '32GB memory required', false, 2, 32);
INSERT INTO system_requirement (id, name, comment, is_predefined, system_requirement_type_id, value) VALUES (10, '64GB Ram', '64GB memory required', false, 2, 64);

INSERT INTO system_requirement (id, name, comment, is_predefined, system_requirement_type_id, value) VALUES (11, 'Any OS', 'no specific OS required', true, 3, 1);
INSERT INTO system_requirement (id, name, comment, is_predefined, system_requirement_type_id, value) VALUES (12, 'Windows 7', 'Windows 7 required', false, 3, 2);
INSERT INTO system_requirement (id, name, comment, is_predefined, system_requirement_type_id, value) VALUES (13, 'Windows 8', 'Windows 8 required', false, 3, 3);
INSERT INTO system_requirement (id, name, comment, is_predefined, system_requirement_type_id, value) VALUES (14, 'Windows 8.1', 'Windows 8.1 required', false, 3, 4);
INSERT INTO system_requirement (id, name, comment, is_predefined, system_requirement_type_id, value) VALUES (15, 'Windows 10', 'Windows 10 required', false, 3, 5);
INSERT INTO system_requirement (id, name, comment, is_predefined, system_requirement_type_id, value) VALUES (16, 'MACOSX', 'MACOSX required', false, 3, 6);
INSERT INTO system_requirement (id, name, comment, is_predefined, system_requirement_type_id, value) VALUES (17, 'Linux', 'Linux required', false, 3, 7);

INSERT INTO system_requirement (id, name, comment, is_predefined, system_requirement_type_id, value) VALUES (18, '10Mb', '10Mb required', false, 4, 10);
INSERT INTO system_requirement (id, name, comment, is_predefined, system_requirement_type_id, value) VALUES (19, '100Mb', '100Mb required', true, 4, 100);
INSERT INTO system_requirement (id, name, comment, is_predefined, system_requirement_type_id, value) VALUES (20, '1Gb', '1Gb required', false, 4, 1000);
INSERT INTO system_requirement (id, name, comment, is_predefined, system_requirement_type_id, value) VALUES (21, '10Gb', '10Gb required', false, 4, 10000);