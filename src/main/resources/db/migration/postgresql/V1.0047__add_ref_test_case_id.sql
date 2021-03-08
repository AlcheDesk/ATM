DELETE FROM "instruction" WHERE instruction_type_id IS NULL OR order_index IS NULL;
ALTER TABLE "instruction" ALTER COLUMN instruction_type_id SET NOT NULL;
ALTER TABLE "instruction" ALTER COLUMN order_index SET NOT NULL;

CREATE OR REPLACE FUNCTION valid_ref_test_case_id(instruction_type_id bigint, ref_test_case_id bigint)
  RETURNS boolean AS
$func$
BEGIN
   IF instruction_type_id IN (SELECT id FROM instruction_type WHERE name = '引用') AND ref_test_case_id IS NULL THEN
      RETURN FALSE;
   ELSE
      RETURN TRUE;
   END IF;
END
$func$ LANGUAGE plpgsql;

ALTER TABLE "instruction" ADD COLUMN ref_test_case_id bigint;
ALTER TABLE "instruction" ADD CONSTRAINT instruction_fk_ref_test_case FOREIGN KEY (ref_test_case_id) REFERENCES "test_case" ("id");

DELETE FROM "instruction" WHERE instruction_type_id IN (SELECT id FROM instruction_type WHERE name = '引用') AND ref_test_case_id IS NULL;
ALTER TABLE "instruction" ADD CONSTRAINT instruction_ck_ref_test_case_id CHECK (valid_ref_test_case_id(instruction_type_id, ref_test_case_id));