----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "element_type_instruction_option_link_delete_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	------------------------------------
	UPDATE instruction_option_map SET element_type_ids = 
	(SELECT case when array_agg(distinct(link.element_type_id)) = '{null}'::bigint[] then '{}'::bigint[] else array_agg(distinct(link.element_type_id)) end
	from instruction_option insop
	full outer join element_type_instruction_option_link link on link.instruction_option_id = OLD.id
	group by insop.id)
	WHERE id = OLD.instruction_option_id;
	------------------------------------  
	RETURN NULL;
END;
$body$ LANGUAGE plpgsql;
CREATE TRIGGER "element_type_instruction_option_link_after_delete_change_others"
  AFTER DELETE ON element_type_instruction_option_link
  FOR EACH ROW
EXECUTE PROCEDURE element_type_instruction_option_link_delete_change_others();
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "instruction_action_instruction_option_link_delete_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	------------------------------------
	UPDATE instruction_option_map SET instruction_action_ids = 
	(SELECT case when array_agg(distinct(link.instruction_action_id)) = '{null}'::bigint[] then '{}'::bigint[] else array_agg(distinct(link.instruction_action_id)) end
	from instruction_option insop
	full outer join instruction_action_instruction_option_link link on link.instruction_option_id = OLD.id
	group by insop.id)
	WHERE id = OLD.instruction_option_id;
	------------------------------------  
	RETURN NULL;
END;
$body$ LANGUAGE plpgsql;
CREATE TRIGGER "instruction_action_instruction_option_link_after_delete_change_others"
  AFTER DELETE ON instruction_action_instruction_option_link
  FOR EACH ROW
EXECUTE PROCEDURE instruction_action_instruction_option_link_delete_change_others();
----------------------------------------------------------------------------------------------------------------------------------
