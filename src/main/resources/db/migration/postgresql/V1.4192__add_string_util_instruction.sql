INSERT INTO instruction_type (id, name, is_active, is_predefined, is_driver, driver_type_id, is_element_required, virtual_element_id, overridable_fields) 
VALUES 
(16, 'StringUtil', true, true, false, null, false, null, null);
----------------------------------------------------------------------------------------------------------------------------------
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (54, 'Abbreviate', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (55, 'Capitalize', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (56, 'Chop', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (57, 'Chomp', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (58, 'DeleteWhitespace', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (59, 'Difference', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (60, 'GetDigits', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (61, 'Left', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (62, 'LowerCase', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (63, 'NormalizeSpace', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (64, 'Remove', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (65, 'RemoveEnd', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (66, 'RemoveStart', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (67, 'Replace', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (68, 'ReplaceOnce', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (69, 'Reverse', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (70, 'Strip', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (71, 'StripAccents', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (72, 'StripToEmpty', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (73, 'StripToNull', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (74, 'Substring', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (75, 'SwapCase', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (76, 'Trim', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (77, 'TrimToEmpty', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (78, 'Uncapitalize', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (79, 'RemoveAll', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (80, 'RemoveFirst', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (81, 'RemovePattern', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (82, 'ReplaceAll', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (83, 'ReplaceFirst', true, true);
INSERT INTO instruction_action (id, name, is_active, is_predefined) VALUES (84, 'ReplacePattern', true, true);
----------------------------------------------------------------------------------------------------------------------------------
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (51, 16, 54);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (52, 16, 55);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (53, 16, 56);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (54, 16, 57);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (55, 16, 58);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (56, 16, 59);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (57, 16, 60);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (58, 16, 61);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (59, 16, 62);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (60, 16, 63);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (61, 16, 64);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (62, 16, 65);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (63, 16, 66);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (64, 16, 67);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (65, 16, 68);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (66, 16, 69);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (67, 16, 70);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (68, 16, 71);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (69, 16, 72);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (70, 16, 73);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (71, 16, 74);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (72, 16, 75);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (73, 16, 76);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (74, 16, 77);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (75, 16, 78);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (76, 16, 79);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (77, 16, 80);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (78, 16, 81);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (79, 16, 82);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (80, 16, 83);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (81, 16, 84);