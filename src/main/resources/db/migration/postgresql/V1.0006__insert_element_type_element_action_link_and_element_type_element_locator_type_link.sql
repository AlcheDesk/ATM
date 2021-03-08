
--insert element_type_element_action_link

INSERT INTO element_type_element_action_link(element_type_id, element_action_id)
	VALUES ((SELECT id FROM element_type WHERE name = 'button'), 
            (SELECT id FROM element_action WHERE name = 'Click'))ON CONFLICT DO NOTHING;
INSERT INTO element_type_element_action_link(element_type_id, element_action_id)

	VALUES ((SELECT id FROM element_type WHERE name = 'link'), 
            (SELECT id FROM element_action WHERE name = 'Click'))ON CONFLICT DO NOTHING;
INSERT INTO element_type_element_action_link(element_type_id, element_action_id)
	VALUES ((SELECT id FROM element_type WHERE name = 'link'), 
            (SELECT id FROM element_action WHERE name = 'Exist'))ON CONFLICT DO NOTHING;

INSERT INTO element_type_element_action_link(element_type_id, element_action_id)
	VALUES ((SELECT id FROM element_type WHERE name = 'dropdown'), 
            (SELECT id FROM element_action WHERE name = 'Select'))ON CONFLICT DO NOTHING;	
INSERT INTO element_type_element_action_link(element_type_id, element_action_id)
	VALUES ((SELECT id FROM element_type WHERE name = 'dropdown'), 
            (SELECT id FROM element_action WHERE name = 'Verify'))ON CONFLICT DO NOTHING;

INSERT INTO element_type_element_action_link(element_type_id, element_action_id)
	VALUES ((SELECT id FROM element_type WHERE name = 'radio'), 
            (SELECT id FROM element_action WHERE name = 'Select'))ON CONFLICT DO NOTHING;	
INSERT INTO element_type_element_action_link(element_type_id, element_action_id)
	VALUES ((SELECT id FROM element_type WHERE name = 'radio'), 
            (SELECT id FROM element_action WHERE name = 'Verify'))ON CONFLICT DO NOTHING;	

INSERT INTO element_type_element_action_link(element_type_id, element_action_id)
	VALUES ((SELECT id FROM element_type WHERE name = 'checkbox'), 
            (SELECT id FROM element_action WHERE name = 'Check'))ON CONFLICT DO NOTHING;
INSERT INTO element_type_element_action_link(element_type_id, element_action_id)
	VALUES ((SELECT id FROM element_type WHERE name = 'checkbox'), 
            (SELECT id FROM element_action WHERE name = 'Verify'))ON CONFLICT DO NOTHING;   

INSERT INTO element_type_element_action_link(element_type_id, element_action_id)
	VALUES ((SELECT id FROM element_type WHERE name = 'frame'), 
            (SELECT id FROM element_action WHERE name = 'SwitchToFrame'))ON CONFLICT DO NOTHING; 

INSERT INTO element_type_element_action_link(element_type_id, element_action_id)
	VALUES ((SELECT id FROM element_type WHERE name = 'browser'), 
            (SELECT id FROM element_action WHERE name = 'Wait'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_action_link(element_type_id, element_action_id)
	VALUES ((SELECT id FROM element_type WHERE name = 'browser'), 
            (SELECT id FROM element_action WHERE name = 'Navigate'))ON CONFLICT DO NOTHING; 	
INSERT INTO element_type_element_action_link(element_type_id, element_action_id)
	VALUES ((SELECT id FROM element_type WHERE name = 'browser'), 
            (SELECT id FROM element_action WHERE name = 'Sleep'))ON CONFLICT DO NOTHING; 	
INSERT INTO element_type_element_action_link(element_type_id, element_action_id)
	VALUES ((SELECT id FROM element_type WHERE name = 'browser'), 
            (SELECT id FROM element_action WHERE name = 'Back'))ON CONFLICT DO NOTHING; 

INSERT INTO element_type_element_action_link(element_type_id, element_action_id)
	VALUES ((SELECT id FROM element_type WHERE name = 'textbox'), 
            (SELECT id FROM element_action WHERE name = 'Enter'))ON CONFLICT DO NOTHING;    	
INSERT INTO element_type_element_action_link(element_type_id, element_action_id)
	VALUES ((SELECT id FROM element_type WHERE name = 'textbox'), 
            (SELECT id FROM element_action WHERE name = 'Modify'))ON CONFLICT DO NOTHING;  
INSERT INTO element_type_element_action_link(element_type_id, element_action_id)
	VALUES ((SELECT id FROM element_type WHERE name = 'textbox'), 
            (SELECT id FROM element_action WHERE name = 'Clear'))ON CONFLICT DO NOTHING;  
INSERT INTO element_type_element_action_link(element_type_id, element_action_id)
	VALUES ((SELECT id FROM element_type WHERE name = 'textbox'), 
            (SELECT id FROM element_action WHERE name = 'EnterTextReadonly'))ON CONFLICT DO NOTHING;    
INSERT INTO element_type_element_action_link(element_type_id, element_action_id)
	VALUES ((SELECT id FROM element_type WHERE name = 'textbox'), 
            (SELECT id FROM element_action WHERE name = 'Verify'))ON CONFLICT DO NOTHING;   

--insert element_type_element_locator_type_link

INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'button'), 
            (SELECT id FROM element_locator_type WHERE name = 'name'))ON CONFLICT DO NOTHING;  
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'button'), 
            (SELECT id FROM element_locator_type WHERE name = 'css'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'button'), 
            (SELECT id FROM element_locator_type WHERE name = 'tag'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'button'), 
            (SELECT id FROM element_locator_type WHERE name = 'xpath'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'button'), 
            (SELECT id FROM element_locator_type WHERE name = 'id'))ON CONFLICT DO NOTHING;       

INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'link'), 
            (SELECT id FROM element_locator_type WHERE name = 'name'))ON CONFLICT DO NOTHING;  
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'link'), 
            (SELECT id FROM element_locator_type WHERE name = 'css'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'link'), 
            (SELECT id FROM element_locator_type WHERE name = 'tag'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'link'), 
            (SELECT id FROM element_locator_type WHERE name = 'xpath'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'link'), 
            (SELECT id FROM element_locator_type WHERE name = 'id'))ON CONFLICT DO NOTHING;      

INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'textbox'), 
            (SELECT id FROM element_locator_type WHERE name = 'name'))ON CONFLICT DO NOTHING;  
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'textbox'), 
            (SELECT id FROM element_locator_type WHERE name = 'css'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'textbox'), 
            (SELECT id FROM element_locator_type WHERE name = 'tag'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'textbox'), 
            (SELECT id FROM element_locator_type WHERE name = 'xpath'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'textbox'), 
            (SELECT id FROM element_locator_type WHERE name = 'id'))ON CONFLICT DO NOTHING;  

INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'dropdown'), 
            (SELECT id FROM element_locator_type WHERE name = 'name'))ON CONFLICT DO NOTHING;  
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'dropdown'), 
            (SELECT id FROM element_locator_type WHERE name = 'css'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'dropdown'), 
            (SELECT id FROM element_locator_type WHERE name = 'tag'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'dropdown'), 
            (SELECT id FROM element_locator_type WHERE name = 'xpath'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'dropdown'), 
            (SELECT id FROM element_locator_type WHERE name = 'id'))ON CONFLICT DO NOTHING;   

INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'radio'), 
            (SELECT id FROM element_locator_type WHERE name = 'name'))ON CONFLICT DO NOTHING;  
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'radio'), 
            (SELECT id FROM element_locator_type WHERE name = 'css'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'radio'), 
            (SELECT id FROM element_locator_type WHERE name = 'tag'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'radio'), 
            (SELECT id FROM element_locator_type WHERE name = 'xpath'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'radio'), 
            (SELECT id FROM element_locator_type WHERE name = 'id'))ON CONFLICT DO NOTHING; 

INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'checkbox'), 
            (SELECT id FROM element_locator_type WHERE name = 'name'))ON CONFLICT DO NOTHING;  
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'checkbox'), 
            (SELECT id FROM element_locator_type WHERE name = 'css'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'checkbox'), 
            (SELECT id FROM element_locator_type WHERE name = 'tag'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'checkbox'), 
            (SELECT id FROM element_locator_type WHERE name = 'xpath'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'checkbox'), 
            (SELECT id FROM element_locator_type WHERE name = 'id'))ON CONFLICT DO NOTHING;   

INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'table'), 
            (SELECT id FROM element_locator_type WHERE name = 'name'))ON CONFLICT DO NOTHING;  
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'table'), 
            (SELECT id FROM element_locator_type WHERE name = 'css'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'table'), 
            (SELECT id FROM element_locator_type WHERE name = 'tag'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'table'), 
            (SELECT id FROM element_locator_type WHERE name = 'xpath'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'table'), 
            (SELECT id FROM element_locator_type WHERE name = 'id'))ON CONFLICT DO NOTHING;   

INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'filedown'), 
            (SELECT id FROM element_locator_type WHERE name = 'name'))ON CONFLICT DO NOTHING;  
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'filedown'), 
            (SELECT id FROM element_locator_type WHERE name = 'css'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'filedown'), 
            (SELECT id FROM element_locator_type WHERE name = 'tag'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'filedown'), 
            (SELECT id FROM element_locator_type WHERE name = 'xpath'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'filedown'), 
            (SELECT id FROM element_locator_type WHERE name = 'id'))ON CONFLICT DO NOTHING; 

INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'fileup'), 
            (SELECT id FROM element_locator_type WHERE name = 'name'))ON CONFLICT DO NOTHING;  
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'fileup'), 
            (SELECT id FROM element_locator_type WHERE name = 'css'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'fileup'), 
            (SELECT id FROM element_locator_type WHERE name = 'tag'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'fileup'), 
            (SELECT id FROM element_locator_type WHERE name = 'xpath'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'fileup'), 
            (SELECT id FROM element_locator_type WHERE name = 'id'))ON CONFLICT DO NOTHING;  

INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'frame'), 
            (SELECT id FROM element_locator_type WHERE name = 'name'))ON CONFLICT DO NOTHING;  
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'frame'), 
            (SELECT id FROM element_locator_type WHERE name = 'css'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'frame'), 
            (SELECT id FROM element_locator_type WHERE name = 'tag'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'frame'), 
            (SELECT id FROM element_locator_type WHERE name = 'xpath'))ON CONFLICT DO NOTHING; 
INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'frame'), 
            (SELECT id FROM element_locator_type WHERE name = 'id'))ON CONFLICT DO NOTHING;    

INSERT INTO element_type_element_locator_type_link(element_type_id, element_locator_type_id)
      VALUES ((SELECT id FROM element_type WHERE name = 'browser'), 
            (SELECT id FROM element_locator_type WHERE name = 'name'))ON CONFLICT DO NOTHING;                                  	