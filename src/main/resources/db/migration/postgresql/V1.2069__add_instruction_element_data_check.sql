DELETE FROM "instruction" WHERE NOT ((is_driver IS TRUE AND element_id IS NOT NULL AND element_id < 1000) OR (is_driver IS FALSE AND (element_id > 999 OR element_id IS NULL)));

ALTER TABLE "instruction" ADD CONSTRAINT instruction_ck_element_id_is_driver CHECK ((is_driver IS TRUE AND element_id IS NOT NULL AND element_id < 1000) OR (is_driver IS FALSE AND (element_id > 999 OR element_id IS NULL)));
ALTER TABLE "element" ADD CONSTRAINT element_ck_id_is_driver CHECK ((is_driver IS TRUE AND id < 1000) OR (is_driver IS FALSE AND id > 999));