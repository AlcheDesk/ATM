CREATE TABLE
    project_element_link
    (
        id bigserial NOT NULL,
        project_id bigint NOT NULL,
        element_id bigint NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT project_element_link_fk_project FOREIGN KEY (project_id) REFERENCES "project" ("id"),
        CONSTRAINT project_element_link_fk_element FOREIGN KEY (element_id) REFERENCES "element" ("id")
    )