-- delete the  instruction overwrite with wrong type
DELETE FROM instruction_overwrite WHERE instruction_type_id IN (SELECT id FROM instruction_type WHERE name = 'Manual' OR name = 'Comment');

-- delete the instruction overwrite with no value
DELETE FROM instruction_overwrite WHERE data::text = '[{"name":"input","jsonPath":"@.input"}]';
DELETE FROM instruction_overwrite WHERE data::text = '[{"name":"input","jsonPath":"@.input"},{"name":"locatorType","jsonPath":"@.locatorType"},{"name":"locatorValue","jsonPath":"@.locatorValue"}]';