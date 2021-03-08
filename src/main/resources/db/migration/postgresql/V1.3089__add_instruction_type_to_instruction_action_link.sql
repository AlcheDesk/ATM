-- create the instruction type instruction action table
CREATE TABLE instruction_type_instruction_action_link
    (
        id bigserial NOT NULL,
        instruction_type_id bigint NOT NULL,
        instruction_action_id bigint NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT instruction_type_instruction_action_link_fk_instruction_type FOREIGN KEY
        (instruction_type_id) REFERENCES "instruction_type" ("id"),
        CONSTRAINT instruction_type_instruction_action_link_fk_instruction_action FOREIGN KEY
        (instruction_action_id) REFERENCES "instruction_action" ("id"),
        CONSTRAINT instruction_type_instruction_action_link_ix_instruction_type_instruction_action
        UNIQUE (instruction_type_id, instruction_action_id)
    );

INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (1, (SELECT id FROM instruction_type WHERE name = 'WebFunction'), (SELECT id FROM instruction_action WHERE name = 'Click'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (2, (SELECT id FROM instruction_type WHERE name = 'WebFunction'), (SELECT id FROM instruction_action WHERE name = 'Enter'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (3, (SELECT id FROM instruction_type WHERE name = 'WebFunction'), (SELECT id FROM instruction_action WHERE name = 'Clear'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (4, (SELECT id FROM instruction_type WHERE name = 'WebFunction'), (SELECT id FROM instruction_action WHERE name = 'EnterReadonly'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (5, (SELECT id FROM instruction_type WHERE name = 'WebFunction'), (SELECT id FROM instruction_action WHERE name = 'Select'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (6, (SELECT id FROM instruction_type WHERE name = 'WebFunction'), (SELECT id FROM instruction_action WHERE name = 'Check'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (7, (SELECT id FROM instruction_type WHERE name = 'WebFunction'), (SELECT id FROM instruction_action WHERE name = 'FileDownload'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (8, (SELECT id FROM instruction_type WHERE name = 'WebFunction'), (SELECT id FROM instruction_action WHERE name = 'Upload'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (9, (SELECT id FROM instruction_type WHERE name = 'WebFunction'), (SELECT id FROM instruction_action WHERE name = 'Exist'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (10, (SELECT id FROM instruction_type WHERE name = 'WebFunction'), (SELECT id FROM instruction_action WHERE name = 'Match'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (11, (SELECT id FROM instruction_type WHERE name = 'WebFunction'), (SELECT id FROM instruction_action WHERE name = 'SwitchTo'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (12, (SELECT id FROM instruction_type WHERE name = 'WebFunction'), (SELECT id FROM instruction_action WHERE name = 'Count'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (13, (SELECT id FROM instruction_type WHERE name = 'WebFunction'), (SELECT id FROM instruction_action WHERE name = 'IsDisable'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (14, (SELECT id FROM instruction_type WHERE name = 'WebFunction'), (SELECT id FROM instruction_action WHERE name = 'InputContainsPageText'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (15, (SELECT id FROM instruction_type WHERE name = 'WebFunction'), (SELECT id FROM instruction_action WHERE name = 'InputInPageText'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (16, (SELECT id FROM instruction_type WHERE name = 'WebFunction'), (SELECT id FROM instruction_action WHERE name = 'FileUpload'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (17, (SELECT id FROM instruction_type WHERE name = 'WebFunction'), (SELECT id FROM instruction_action WHERE name = 'FileUploadByWindow'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (18, (SELECT id FROM instruction_type WHERE name = 'WebFunction'), (SELECT id FROM instruction_action WHERE name = 'DoubleClick'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (19, (SELECT id FROM instruction_type WHERE name = 'WebFunction'), (SELECT id FROM instruction_action WHERE name = 'IsEnable'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (20, (SELECT id FROM instruction_type WHERE name = 'WebFunction'), (SELECT id FROM instruction_action WHERE name = 'NonExist'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (21, (SELECT id FROM instruction_type WHERE name = 'WebFunction'), (SELECT id FROM instruction_action WHERE name = 'ClickOnHideButton'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (22, (SELECT id FROM instruction_type WHERE name = 'WebFunction'), (SELECT id FROM instruction_action WHERE name = 'JsExcuteForElement'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (23, (SELECT id FROM instruction_type WHERE name = 'WebFunction'), (SELECT id FROM instruction_action WHERE name = 'MoveToElement'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (24, (SELECT id FROM instruction_type WHERE name = 'WebFunction'), (SELECT id FROM instruction_action WHERE name = 'DragAndDrop'));

INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (25, (SELECT id FROM instruction_type WHERE name = 'REST_API'), (SELECT id FROM instruction_action WHERE name = 'DELETE'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (26, (SELECT id FROM instruction_type WHERE name = 'REST_API'), (SELECT id FROM instruction_action WHERE name = 'PATCH'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (27, (SELECT id FROM instruction_type WHERE name = 'REST_API'), (SELECT id FROM instruction_action WHERE name = 'PUT'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (28, (SELECT id FROM instruction_type WHERE name = 'REST_API'), (SELECT id FROM instruction_action WHERE name = 'HEAD'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (29, (SELECT id FROM instruction_type WHERE name = 'REST_API'), (SELECT id FROM instruction_action WHERE name = 'OPTIONS'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (30, (SELECT id FROM instruction_type WHERE name = 'REST_API'), (SELECT id FROM instruction_action WHERE name = 'POST'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (31, (SELECT id FROM instruction_type WHERE name = 'REST_API'), (SELECT id FROM instruction_action WHERE name = 'GET'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (32, (SELECT id FROM instruction_type WHERE name = 'WebBrowser'), (SELECT id FROM instruction_action WHERE name = 'Refresh'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (33, (SELECT id FROM instruction_type WHERE name = 'WebBrowser'), (SELECT id FROM instruction_action WHERE name = 'Navigate'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (34, (SELECT id FROM instruction_type WHERE name = 'WebBrowser'), (SELECT id FROM instruction_action WHERE name = 'Back'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (35, (SELECT id FROM instruction_type WHERE name = 'WebBrowser'), (SELECT id FROM instruction_action WHERE name = 'Forward'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (36, (SELECT id FROM instruction_type WHERE name = 'WebBrowser'), (SELECT id FROM instruction_action WHERE name = 'Wait'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (37, (SELECT id FROM instruction_type WHERE name = 'WebBrowser'), (SELECT id FROM instruction_action WHERE name = 'Close'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (38, (SELECT id FROM instruction_type WHERE name = 'SQL'), (SELECT id FROM instruction_action WHERE name = 'Execute'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (39, (SELECT id FROM instruction_type WHERE name = 'JavaScript'), (SELECT id FROM instruction_action WHERE name = 'Execute'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (40, (SELECT id FROM instruction_type WHERE name = 'StringDataProcessor'), (SELECT id FROM instruction_action WHERE name = 'Execute'));
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) 
VALUES (41, (SELECT id FROM instruction_type WHERE name = 'MathExpressionProcessor'), (SELECT id FROM instruction_action WHERE name = 'Execute'));

ALTER SEQUENCE instruction_type_instruction_action_link_id_seq START WITH 1000;
ALTER SEQUENCE instruction_type_instruction_action_link_id_seq RESTART;  
