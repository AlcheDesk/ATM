ALTER TABLE public.storage ADD COLUMN run_id bigint;

ALTER TABLE public.storage ADD CONSTRAINT storage_run_id_fkey FOREIGN KEY (run_id)
    REFERENCES public.run (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;