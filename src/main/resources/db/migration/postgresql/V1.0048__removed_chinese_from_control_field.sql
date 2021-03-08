UPDATE instruction_type set name = 'FUNCTIONAL' where name = '功能';
UPDATE instruction_type set name = 'PERFORMANCE' where name = '压力';
UPDATE instruction_type set name = 'API' where name = '接口';
UPDATE instruction_type set name = 'MANUAL' where name = '手动';
UPDATE instruction_type set name = 'REFRENCE' where name = '引用';
UPDATE instruction_type set name = 'COMMENT' where name = '注释';

UPDATE color set name = 'BLUE' where name = '蓝';
UPDATE color set name = 'GREEN' where name = '绿';
UPDATE color set name = 'ORANGE' where name = '橙';
UPDATE color set name = 'RED' where name = '红';
UPDATE color set name = 'GREY' where name = '灰';

CREATE OR REPLACE FUNCTION valid_ref_test_case_id(instruction_type_id bigint, ref_test_case_id bigint)
  RETURNS boolean AS
$func$
BEGIN
   IF instruction_type_id IN (SELECT id FROM instruction_type WHERE name = 'REFRENCE') AND ref_test_case_id IS NULL THEN
      RETURN FALSE;
   ELSE
      RETURN TRUE;
   END IF;
END
$func$ LANGUAGE plpgsql;