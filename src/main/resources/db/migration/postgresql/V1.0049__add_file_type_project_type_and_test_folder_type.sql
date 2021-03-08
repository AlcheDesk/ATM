CREATE TABLE file_type
    (
        id bigserial NOT NULL,
        name text NOT NULL,
        CONSTRAINT file_type_uk_name UNIQUE (name)
    );

INSERT INTO file_type (name) VALUES ('SCREENSHOT') ON CONFLICT DO NOTHING;
INSERT INTO file_type (name) VALUES ('LOG') ON CONFLICT DO NOTHING;    
    
CREATE TABLE project_type
    (
        id bigserial NOT NULL,
        name text NOT NULL,
        CONSTRAINT project_type_uk_name UNIQUE (name)
    );

INSERT INTO project_type (name) VALUES ('GROUP') ON CONFLICT DO NOTHING;
INSERT INTO project_type (name) VALUES ('INDIVIDUAL') ON CONFLICT DO NOTHING;
    
CREATE TABLE test_case_folder_type
    (
        id bigserial NOT NULL,
        name text NOT NULL,
        CONSTRAINT test_case_folder_uk_name UNIQUE (name)
    );

INSERT INTO test_case_folder_type (name) VALUES ('GROUP') ON CONFLICT DO NOTHING;
INSERT INTO test_case_folder_type (name) VALUES ('INDIVIDUAL') ON CONFLICT DO NOTHING;
