UPDATE instruction SET element_id = (SELECT id FROM element WHERE name = 'Browser') , element_type_id = (SELECT id FROM element_type WHERE name = 'WebBrowser') WHERE instruction_type_id = (SELECT id FROM instruction_type WHERE name = 'WebBrowser');
UPDATE instruction SET element_id = (SELECT id FROM element WHERE name = 'JavaScript Executor') , element_type_id = (SELECT id FROM element_type WHERE name = 'JavaScript') WHERE instruction_type_id = (SELECT id FROM instruction_type WHERE name = 'JavaScript');
UPDATE instruction SET element_id = (SELECT id FROM element WHERE name = 'Database JDBC Driver') , element_type_id = (SELECT id FROM element_type WHERE name = 'SQL') WHERE instruction_type_id = (SELECT id FROM instruction_type WHERE name = 'SQL');