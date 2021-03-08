UPDATE step_log_type SET name = 'FAIL' WHERE id = 4 AND name = 'ERROR';
DELETE FROM step_log_type WHERE name = 'ERROR';