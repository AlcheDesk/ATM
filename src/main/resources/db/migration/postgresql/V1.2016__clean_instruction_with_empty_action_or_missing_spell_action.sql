-- the action is empty and element type is sql
UPDATE instruction SET action = 'Execute' 
WHERE element_action_id IS NULL 
AND action = '' 
AND element_id IN (
                       SELECT id FROM element WHERE element_type_id = (
                           SELECT id FROM element_type WHERE name = 'SQL'
                       )
                  );
-- fix instruction with excute action
UPDATE instruction SET action = 'Execute' WHERE lower(action) = 'excute';
-- update the element action id again
UPDATE instruction ins SET element_action_id = (SELECT id FROM element_action ela WHERE lower(ela.name) = lower(ins.action));

--delete abnormal instructions. NO action and NOT reference, comment or manual type
DELETE FROM instruction_option_entry WHERE instruction_id IN
(SELECT id FROM instruction
WHERE element_action_id IS NULL AND action IS NULL
AND instruction_type_id NOT IN (SELECT id FROM instruction_type WHERE name = 'Reference' OR name = 'Comment' OR name = 'Manual'));

DELETE FROM  instruction
WHERE element_action_id IS NULL AND action IS NULL
AND instruction_type_id NOT IN (SELECT id FROM instruction_type WHERE name = 'Reference' OR name = 'Comment' OR name = 'Manual');