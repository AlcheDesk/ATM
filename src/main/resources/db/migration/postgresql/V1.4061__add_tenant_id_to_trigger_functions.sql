--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "update_test_case_execution_info_after_test_case_insert" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	insert into test_case_execution_info (
	    test_case_id,
	    test_case_name,
	    test_case_created_at,
	    tenant_id
	) values (
	    NEW.id,
	    NEW.name,
	    NEW.created_at,
	    NEW.tenant_id
	);
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;