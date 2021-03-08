INSERT INTO driver_type_instruction_type_link (id, driver_type_id,instruction_type_id) 
VALUES 
(
7,
(SELECT id FROM driver_type WHERE name = 'Redis'), 
(SELECT id FROM instruction_type WHERE name = 'Redis')
);