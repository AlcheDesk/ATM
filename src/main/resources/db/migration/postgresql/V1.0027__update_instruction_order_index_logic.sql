ALTER TABLE "instruction" ALTER COLUMN "order_index" DROP NOT NULL;
UPDATE instruction set order_index = null WHERE active = false;

CREATE OR REPLACE FUNCTION trigger_order_index_by_intruction_update() RETURNS trigger AS 
$$
DECLARE
    old_test_case_id bigint;
    new_test_case_id bigint;
    old_order_index bigint;
    new_order_index bigint;
BEGIN
	new_test_case_id := NEW.test_case_id;
	old_test_case_id := OLD.test_case_id;
	new_order_index := NEW.order_index;
	old_order_index := OLD.order_index;
	
	IF NEW.order_index <= 0 THEN
		NEW.order_index := 1;
	END IF;
	
	--doing delete
    IF OLD.active = true and NEW.active = false THEN
    	UPDATE instruction SET order_index = order_index - 1 WHERE order_index > old_order_index AND test_case_id = new_test_case_id AND id <> NEW.id;
    END IF;
    -- doing move down
    IF OLD.active = NEW.active and old_order_index > new_order_index THEN
      -- move old test_case
      	
    	UPDATE instruction SET order_index = order_index + 1 
    	WHERE order_index >=  old_order_index + (new_order_index - old_order_index) 
    	AND order_index < old_order_index 
    	AND test_case_id = old_test_case_id
    	AND id <> NEW.id;
    END IF;
    
    -- doing move up
    IF OLD.active = NEW.active and old_order_index < new_order_index THEN
    	-- move old test case
        UPDATE instruction SET order_index = order_index - 1 
    	WHERE order_index >  old_order_index
    	AND order_index <= old_order_index + (new_order_index - old_order_index) 
    	AND test_case_id = old_test_case_id
    	AND id <> NEW.id;
    END IF;
    RETURN NEW;   
END;
$$  
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION trigger_order_index_by_intruction_insert() RETURNS trigger AS 
$$
DECLARE
    new_test_case_id bigint;
    new_order_index bigint;
    max_order_index bigint;
BEGIN
	new_test_case_id := NEW.test_case_id;
	new_order_index := NEW.order_index;
	SELECT max(order_index) into max_order_index FROM instruction WHERE test_case_id = new_test_case_id;
	
	IF NEW.order_index <= 0 THEN
		NEW.order_index := 1;
	END IF;
	
	IF (NEW.order_index - max_order_index) > 1 THEN
		NEW.order_index := max_order_index + 1;
	END IF;
	
        UPDATE instruction SET order_index = order_index + 1 
    	WHERE order_index >=  new_order_index
    	AND test_case_id = new_test_case_id AND id <> NEW.id;
    RETURN NEW;   
END;
$$ 
LANGUAGE 'plpgsql';


CREATE TRIGGER insert_trigger_order_index_update AFTER INSERT ON instruction FOR EACH ROW EXECUTE PROCEDURE trigger_order_index_by_intruction_insert();
CREATE TRIGGER update_trigger_order_index_update AFTER UPDATE ON instruction FOR EACH ROW EXECUTE PROCEDURE trigger_order_index_by_intruction_update();

