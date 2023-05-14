-- Table: public.account

-- DROP TABLE IF EXISTS public.account;

CREATE TABLE IF NOT EXISTS public.account
(
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    balance bigint,
    CONSTRAINT account_pkey PRIMARY KEY (id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.account
    OWNER to postgres;