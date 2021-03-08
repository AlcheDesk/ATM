update instruction ins set target = (select name from application where id = ins.application_id )||'.'||(select name from section where id = ins.section_id)||'.'||(select name from element where id = ins.element_id);

CREATE OR REPLACE FUNCTION generate_instrcution_target() RETURNS trigger AS
$$
BEGIN
	NEW.target = (select name from application where id = NEW.application_id )||'.'||(select name from section where id = NEW.section_id)||'.'||(select name from element where id = NEW.element_id);
    RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER insert_target_trigger AFTER INSERT ON instruction FOR EACH ROW EXECUTE PROCEDURE generate_instrcution_target();
CREATE TRIGGER update_target_trigger AFTER UPDATE ON instruction FOR EACH ROW EXECUTE PROCEDURE generate_instrcution_target();
