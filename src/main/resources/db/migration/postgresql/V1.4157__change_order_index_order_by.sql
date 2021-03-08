CREATE OR REPLACE FUNCTION "instruction_update_order_index" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF TG_OP = 'UPDATE' AND NEW.order_index <> OLD.order_index THEN
		UPDATE instruction SET order_index = new_order_ins.seqnum 
		FROM ( SELECT id as ins_id, ROW_NUMBER() OVER (ORDER BY order_index asc , updated_at desc, id asc ) as seqnum 
		       FROM instruction WHERE test_case_id = NEW.test_case_id AND is_deleted IS FALSE 
		     ) AS new_order_ins 
        WHERE id = new_order_ins.ins_id;
    ELSIF TG_OP = 'INSERT' THEN
    	UPDATE instruction SET order_index = new_order_ins.seqnum 
		FROM ( SELECT id as ins_id, ROW_NUMBER() OVER (ORDER BY order_index asc , updated_at desc, id asc ) as seqnum 
		       FROM instruction WHERE test_case_id = NEW.test_case_id AND is_deleted IS FALSE 
		     ) AS new_order_ins 
        WHERE id = new_order_ins.ins_id;
	ENd IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;