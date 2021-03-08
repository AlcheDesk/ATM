-- create the instruction type element type table
CREATE TABLE instruction_type_element_type_link
    (
        id bigserial NOT NULL,
        instruction_type_id bigint NOT NULL,
        element_type_id bigint NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT instruction_type_element_type_link_fk_instruction_type FOREIGN KEY (instruction_type_id) 
        	REFERENCES "instruction_type" ("id"),
        CONSTRAINT instruction_type_element_type_link_fk_element_type FOREIGN KEY (element_type_id)
        	REFERENCES "element_type" ("id"),
        CONSTRAINT instruction_type_element_type_link_ix_instruction_type_element_type UNIQUE
        (instruction_type_id, element_type_id)
    );

-- insert the data to the instruction type to element type table
INSERT INTO instruction_type_element_type_link (id,instruction_type_id,element_type_id) 
VALUES 
(1,
(SELECT id FROM instruction_type WHERE name = 'WebFunction'), 
(SELECT id FROM element_type WHERE name = 'WebButton')
);

INSERT INTO instruction_type_element_type_link (id,instruction_type_id,element_type_id) 
VALUES 
(2,
(SELECT id FROM instruction_type WHERE name = 'WebFunction'), 
(SELECT id FROM element_type WHERE name = 'WebLink')
);

INSERT INTO instruction_type_element_type_link (id,instruction_type_id,element_type_id) 
VALUES 
(3,
(SELECT id FROM instruction_type WHERE name = 'WebFunction'), 
(SELECT id FROM element_type WHERE name = 'WebDropdown')
);

INSERT INTO instruction_type_element_type_link (id,instruction_type_id,element_type_id) 
VALUES 
(4,
(SELECT id FROM instruction_type WHERE name = 'WebFunction'), 
(SELECT id FROM element_type WHERE name = 'WebRadio')
);

INSERT INTO instruction_type_element_type_link (id,instruction_type_id,element_type_id) 
VALUES 
(5,
(SELECT id FROM instruction_type WHERE name = 'WebFunction'), 
(SELECT id FROM element_type WHERE name = 'WebCheckbox')
);

INSERT INTO instruction_type_element_type_link (id,instruction_type_id,element_type_id) 
VALUES 
(6,
(SELECT id FROM instruction_type WHERE name = 'WebFunction'), 
(SELECT id FROM element_type WHERE name = 'WebTable')
);

INSERT INTO instruction_type_element_type_link (id,instruction_type_id,element_type_id) 
VALUES 
(7,
(SELECT id FROM instruction_type WHERE name = 'WebFunction'), 
(SELECT id FROM element_type WHERE name = 'WebFile')
);

INSERT INTO instruction_type_element_type_link (id,instruction_type_id,element_type_id) 
VALUES 
(8,
(SELECT id FROM instruction_type WHERE name = 'WebFunction'), 
(SELECT id FROM element_type WHERE name = 'WebFrame')
);

INSERT INTO instruction_type_element_type_link (id,instruction_type_id,element_type_id) 
VALUES 
(9,
(SELECT id FROM instruction_type WHERE name = 'WebFunction'), 
(SELECT id FROM element_type WHERE name = 'WebTextbox')
);

INSERT INTO instruction_type_element_type_link (id,instruction_type_id,element_type_id) 
VALUES 
(10,
(SELECT id FROM instruction_type WHERE name = 'REST_API'), 
(SELECT id FROM element_type WHERE name = 'REST_API')
);

-- set the id start with 1000 for non meowlomo data
ALTER SEQUENCE instruction_type_element_type_link_id_seq START WITH 1000;