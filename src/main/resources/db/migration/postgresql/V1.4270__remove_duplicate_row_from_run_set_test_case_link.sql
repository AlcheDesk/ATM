DELETE FROM run_set_test_case_link la USING (
      SELECT MIN(id) as id, run_set_id, test_case_id, driver_pack_id, test_case_overwrite_id, count(*) as count
        FROM run_set_test_case_link where driver_pack_id is not null and test_case_overwrite_id is not null
        GROUP BY run_set_id, test_case_id, driver_pack_id, test_case_overwrite_id HAVING COUNT(*) > 1
              ) lb
      WHERE la.run_set_id = lb.run_set_id 
      and la.test_case_id = lb.test_case_id 
      and la.driver_pack_id = lb.driver_pack_id 
      and la.test_case_overwrite_id = lb.test_case_overwrite_id
      AND la.id <> lb.id;
-----------------------------------------------------------------------      
DELETE FROM run_set_test_case_link la USING (
      SELECT MIN(id) as id, run_set_id, test_case_id, test_case_overwrite_id, count(*) as count
        FROM run_set_test_case_link where driver_pack_id is null and test_case_overwrite_id is not null
        GROUP BY run_set_id, test_case_id, test_case_overwrite_id HAVING COUNT(*) > 1
              ) lb
      WHERE la.run_set_id = lb.run_set_id 
      and la.test_case_id = lb.test_case_id 
      and la.test_case_overwrite_id = lb.test_case_overwrite_id
      AND la.id <> lb.id;
---------------------------------------------------------------------      
DELETE FROM run_set_test_case_link la USING (
      SELECT MIN(id) as id, run_set_id, test_case_id, driver_pack_id, count(*) as count
        FROM run_set_test_case_link where driver_pack_id is not null and test_case_overwrite_id is null
        GROUP BY run_set_id, test_case_id, driver_pack_id HAVING COUNT(*) > 1
              ) lb
      WHERE la.run_set_id = lb.run_set_id 
      and la.test_case_id = lb.test_case_id 
      and la.driver_pack_id = lb.driver_pack_id
      AND la.id <> lb.id;
---------------------------------------------------------------------      
DELETE FROM run_set_test_case_link la USING (
      SELECT MIN(id) as id, run_set_id, test_case_id, count(*) as count
        FROM run_set_test_case_link where driver_pack_id is null and test_case_overwrite_id is null
        GROUP BY run_set_id, test_case_id HAVING COUNT(*) > 1
              ) lb
      WHERE la.run_set_id = lb.run_set_id 
      and la.test_case_id = lb.test_case_id 
      AND la.id <> lb.id;
---------------------------------------------------------------------
ALTER TABLE "run_set_test_case_link" DROP CONSTRAINT "run_set_test_case_link_ix_run_set_test_case_overwrite_driver_pa";
CREATE UNIQUE INDEX run_set_test_case_link_ix_rs_tc_dp_tco ON run_set_test_case_link (run_set_id, test_case_id, driver_pack_id, test_case_overwrite_id)
WHERE driver_pack_id IS NOT NULL AND test_case_overwrite_id IS NOT NULL;
CREATE UNIQUE INDEX run_set_test_case_link_ix_rs_tc_dp ON run_set_test_case_link (run_set_id, test_case_id, driver_pack_id)
WHERE driver_pack_id IS NOT NULL AND test_case_overwrite_id IS NULL;
CREATE UNIQUE INDEX run_set_test_case_link_ix_rs_tc_tco ON run_set_test_case_link (run_set_id, test_case_id, test_case_overwrite_id)
WHERE driver_pack_id IS NULL AND test_case_overwrite_id IS NOT NULL;
CREATE UNIQUE INDEX run_set_test_case_link_ix_rs_tc ON run_set_test_case_link (run_set_id, test_case_id)
WHERE driver_pack_id IS NULL AND test_case_overwrite_id IS NULL;
ALTER TABLE "run_set_test_case_link" ADD CONSTRAINT run_set_test_case_link_ix_rs_rrs UNIQUE ("run_set_id", "ref_run_set_id");
