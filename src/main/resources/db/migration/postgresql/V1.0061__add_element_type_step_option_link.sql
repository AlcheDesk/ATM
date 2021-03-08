CREATE TABLE element_type_step_option_link
    (
        id bigserial NOT NULL,
        element_type_id bigint NOT NULL,
        step_option_id bigint NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT element_type_step_option_link_fk_element_type FOREIGN KEY (element_type_id) REFERENCES "element_type" ("id"),
        CONSTRAINT element_type_step_option_link_fk_step_option FOREIGN KEY (step_option_id) REFERENCES "step_option" ("id")
    );