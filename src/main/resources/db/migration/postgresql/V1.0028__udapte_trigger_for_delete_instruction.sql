CREATE OR REPLACE FUNCTION trigger_order_index_by_intruction_update() RETURNS trigger AS 
$$
BEGIN
	update instruction
	set order_index = t.rn - 1
	from (select test_case_id, id, row_number() over (order by order_index, updated_at) as rn
	      from instruction where active = true and test_case_id = OLD.test_case_id) t where t.id = instruction.id;
    RETURN NEW;   
END;
$$  
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION trigger_order_index_by_intruction_insert() RETURNS trigger AS 
$$
BEGIN
	update instruction
	set order_index = t.rn - 1
	from (select test_case_id, id, row_number() over (order by order_index, updated_at) as rn
	      from instruction where active = true and test_case_id = OLD.test_case_id) t where t.id = instruction.id;
    RETURN NEW;   
END;
$$ 
LANGUAGE 'plpgsql';