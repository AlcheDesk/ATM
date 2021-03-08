--disable triggers
ALTER TABLE "dev_step_log" disable TRIGGER "dev_step_log_update_created_at_updated_at";
ALTER TABLE "dev_file" disable TRIGGER "dev_file_update_created_at_updated_at";
ALTER TABLE "dev_execution_log" disable TRIGGER "dev_execution_log_update_created_at_updated_at";
ALTER TABLE "prod_step_log" disable TRIGGER "prod_step_log_update_created_at_updated_at";
ALTER TABLE "prod_file" disable TRIGGER "prod_file_update_created_at_updated_at";
ALTER TABLE "prod_execution_log" disable TRIGGER "prod_execution_log_update_created_at_updated_at";
ALTER TABLE "run" disable TRIGGER "update_run_created_at_updated_at";
-- add new column
ALTER TABLE "run" ADD COLUMN start_at TIMESTAMP WITH TIME zone;
ALTER TABLE "run" ALTER COLUMN start_at SET DEFAULT now();
ALTER TABLE "run" ADD COLUMN end_at TIMESTAMP WITH TIME zone;
ALTER TABLE "run" ALTER COLUMN end_at SET DEFAULT now();

ALTER TABLE "prod_instruction_result" ADD COLUMN start_at TIMESTAMP WITH TIME zone;
ALTER TABLE "prod_instruction_result" ALTER COLUMN start_at SET DEFAULT now();
ALTER TABLE "prod_instruction_result" ADD COLUMN end_at TIMESTAMP WITH TIME zone;
ALTER TABLE "prod_instruction_result" ALTER COLUMN end_at SET DEFAULT now();

ALTER TABLE "dev_instruction_result" ADD COLUMN start_at TIMESTAMP WITH TIME zone;
ALTER TABLE "dev_instruction_result" ALTER COLUMN start_at SET DEFAULT now();
ALTER TABLE "dev_instruction_result" ADD COLUMN end_at TIMESTAMP WITH TIME zone;
ALTER TABLE "dev_instruction_result" ALTER COLUMN end_at SET DEFAULT now();


-- delete duplicate
ALTER TABLE "section_element_link" DROP CONSTRAINT "section_element_link_fk_element";
ALTER TABLE "section_element_link" ADD CONSTRAINT section_element_link_fk_element FOREIGN KEY ("element_id") REFERENCES "element" ("id") ON DELETE CASCADE;

delete FROM section_element_link WHERE element_id IN  
(
  SELECT dup.id FROM 
  (
    SELECT ele2.id ,ROW_NUMBER() OVER (partition BY ele2.name ORDER BY ele2.id) AS rnum FROM element ele2 WHERE ele2.name IN   
      (
       SELECT ele.name FROM element ele INNER JOIN section_element_link link ON ele.id = link.element_id WHERE ele.active IS TRUE GROUP BY ele.name, link.section_id HAVING count(*)>1
      )
  ) dup WHERE dup.rnum > 1
);


ALTER TABLE "section_element_link" DROP CONSTRAINT "section_element_link_fk_element";
ALTER TABLE "section_element_link" ADD CONSTRAINT section_element_link_fk_element FOREIGN KEY ("element_id") REFERENCES "element" ("id");

-- SET instruction result start and end time
UPDATE prod_instruction_result SET start_at = up.new_start_at , end_at = up.new_end_at FROM
(    
  SELECT * FROM prod_instruction_result ins2 INNER JOIN 
  (
    SELECT ins.id AS ins_id, ins.created_at AS new_start_at ,max(log.UPDATEd_at) AS new_end_at FROM prod_instruction_result ins INNER  JOIN prod_step_log log ON log.instruction_result_id = ins.id GROUP BY ins.id
  ) sne_time ON ins2.id = sne_time.ins_id
) up WHERE prod_instruction_result.id = up.ins_id;

UPDATE dev_instruction_result SET start_at = up.new_start_at , end_at = up.new_end_at FROM
(    
  SELECT * FROM dev_instruction_result ins2 INNER JOIN 
  (
    SELECT ins.id AS ins_id, ins.created_at AS new_start_at ,max(log.UPDATEd_at) AS new_end_at FROM dev_instruction_result ins INNER  JOIN dev_step_log log ON log.instruction_result_id = ins.id GROUP BY ins.id
  ) sne_time ON ins2.id = sne_time.ins_id
) up WHERE dev_instruction_result.id = up.ins_id;

-- SET run start and end time
UPDATE run SET start_at = up.new_start_at , end_at = up.new_end_at FROM
(    
  SELECT * FROM run r2 INNER  JOIN 
  (
    SELECT r.id AS r_id, r.created_at AS new_start_at ,max(ins.end_at) AS new_end_at FROM run r INNER JOIN prod_instruction_result ins ON ins.run_id = r.id WHERE r.run_type_id = 1 GROUP BY r.id
  ) sne_time ON r2.id = sne_time.r_id
) up WHERE run.id = up.r_id;

UPDATE run SET start_at = up.new_start_at , end_at = up.new_end_at FROM
(    
  SELECT * FROM run r2 INNER  JOIN 
  (
    SELECT r.id AS r_id, r.created_at AS new_start_at ,max(ins.end_at) AS new_end_at FROM run r INNER JOIN dev_instruction_result ins ON ins.run_id = r.id WHERE r.run_type_id = 2 GROUP BY r.id
  ) sne_time ON r2.id = sne_time.r_id
) up WHERE run.id = up.r_id;

-- enable triggers
--disable triggers
ALTER TABLE "dev_step_log" enable TRIGGER "dev_step_log_update_created_at_updated_at";
ALTER TABLE "dev_file" enable TRIGGER "dev_file_update_created_at_updated_at";
ALTER TABLE "dev_execution_log" enable TRIGGER "dev_execution_log_update_created_at_updated_at";
ALTER TABLE "prod_step_log" enable TRIGGER "prod_step_log_update_created_at_updated_at";
ALTER TABLE "prod_file" enable TRIGGER "prod_file_update_created_at_updated_at";
ALTER TABLE "prod_execution_log" enable TRIGGER "prod_execution_log_update_created_at_updated_at";
ALTER TABLE "run" enable TRIGGER "update_run_created_at_updated_at";
