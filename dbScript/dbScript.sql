CREATE TABLE public.backgrounds
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    user_id integer NOT NULL,
    user_photo character varying(500) COLLATE pg_catalog."default",
    linkedin_link character varying(100) COLLATE pg_catalog."default",
    github_link character varying(100) COLLATE pg_catalog."default",
    foreground character varying(600) COLLATE pg_catalog."default",
    CONSTRAINT backgrounds_pkey PRIMARY KEY (id),
    CONSTRAINT backgrounds_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.employee_users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.backgrounds
    OWNER to postgres;


CREATE TABLE public.cities
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name character varying(60) COLLATE pg_catalog."default" NOT NULL,
    country_id integer NOT NULL,
    CONSTRAINT cities_pkey PRIMARY KEY (id),
    CONSTRAINT cities_country_id_fkey FOREIGN KEY (country_id)
        REFERENCES public.countries (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.cities
    OWNER to postgres;


CREATE TABLE public.countries
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name character varying(70) COLLATE pg_catalog."default" NOT NULL,
    symbol character varying(8) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT countries_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.countries
    OWNER to postgres;


CREATE TABLE public.employee_users
(
    user_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    birth_date timestamp without time zone NOT NULL,
    first_name character varying(55) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(40) COLLATE pg_catalog."default" NOT NULL,
    identity_no character varying(11) COLLATE pg_catalog."default" NOT NULL,
    e_mail character varying(150) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT employee_users_pkey PRIMARY KEY (user_id),
    CONSTRAINT employee_users_e_mail_key UNIQUE (e_mail)
)

TABLESPACE pg_default;

ALTER TABLE public.employee_users
    OWNER to postgres;


CREATE TABLE public.employer_users
(
    user_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    company_name character varying(250) COLLATE pg_catalog."default" NOT NULL,
    company_web_site character varying(200) COLLATE pg_catalog."default",
    company_phone character varying(15) COLLATE pg_catalog."default" NOT NULL,
    e_mail character varying(255) COLLATE pg_catalog."default",
    is_old boolean,
    CONSTRAINT employer_users_pkey PRIMARY KEY (user_id)
)

TABLESPACE pg_default;

ALTER TABLE public.employer_users
    OWNER to postgres;


CREATE TABLE public.favorite_job_postings
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    employee_user_id integer NOT NULL,
    job_posting_id integer NOT NULL,
    added_date date NOT NULL,
    CONSTRAINT favorite_job_postings_pkey PRIMARY KEY (id),
    CONSTRAINT favorite_job_postings_employee_user_id_fkey FOREIGN KEY (employee_user_id)
        REFERENCES public.employee_users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT favorite_job_postings_job_posting_id_fkey FOREIGN KEY (job_posting_id)
        REFERENCES public.job_postings (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.favorite_job_postings
    OWNER to postgres;


CREATE TABLE public.job_experiences
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    workplace_name character varying(75) COLLATE pg_catalog."default" NOT NULL,
    job_position character varying(90) COLLATE pg_catalog."default" NOT NULL,
    background_id integer NOT NULL,
    beginning_year character varying(4) COLLATE pg_catalog."default" NOT NULL,
    ending_year character varying(4) COLLATE pg_catalog."default",
    CONSTRAINT job_experiences_pkey PRIMARY KEY (id),
    CONSTRAINT job_experiences_background_id_fkey FOREIGN KEY (background_id)
        REFERENCES public.backgrounds (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.job_experiences
    OWNER to postgres;


CREATE TABLE public.job_positions
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT job_positions_pkey PRIMARY KEY (id),
    CONSTRAINT job_positions_name_key UNIQUE (name)
)

TABLESPACE pg_default;

ALTER TABLE public.job_positions
    OWNER to postgres;


CREATE TABLE public.job_postings
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    job_position_id integer NOT NULL,
    job_description character varying(2000) COLLATE pg_catalog."default" NOT NULL,
    city_id integer NOT NULL,
    max_pay integer,
    min_pay integer,
    vacancy integer NOT NULL,
    active boolean NOT NULL,
    user_id integer NOT NULL,
    pay_type character varying(5) COLLATE pg_catalog."default",
    deadline date NOT NULL,
    added_date date NOT NULL,
    work_time character varying(5) COLLATE pg_catalog."default",
    work_place character varying(10) COLLATE pg_catalog."default",
    activated boolean,
    CONSTRAINT job_posting_pkey PRIMARY KEY (id),
    CONSTRAINT job_postings_city_id_fkey FOREIGN KEY (city_id)
        REFERENCES public.cities (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT job_postings_job_position_id_fkey FOREIGN KEY (job_position_id)
        REFERENCES public.job_positions (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT job_postings_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.employer_users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.job_postings
    OWNER to postgres;


CREATE TABLE public.language_experiences
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    language_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    language_grade smallint NOT NULL,
    background_id integer NOT NULL,
    CONSTRAINT language_experiences_pkey PRIMARY KEY (id),
    CONSTRAINT language_experiences_background_id_fkey FOREIGN KEY (background_id)
        REFERENCES public.backgrounds (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.language_experiences
    OWNER to postgres;


CREATE TABLE public.operation_types
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name character varying(80) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT operation_types_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.operation_types
    OWNER to postgres;


CREATE TABLE public.schools
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    school_name character varying(200) COLLATE pg_catalog."default" NOT NULL,
    graduation_year character varying(4) COLLATE pg_catalog."default",
    background_id integer NOT NULL,
    CONSTRAINT schools_pkey PRIMARY KEY (id),
    CONSTRAINT schools_background_id_fkey FOREIGN KEY (background_id)
        REFERENCES public.backgrounds (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.schools
    OWNER to postgres;


CREATE TABLE public.system_users
(
    user_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    first_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(40) COLLATE pg_catalog."default" NOT NULL,
    job_position_id integer NOT NULL,
    birth_date date,
    identity_no character varying(11) COLLATE pg_catalog."default" NOT NULL,
    email character varying(80) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT system_users_pkey1 PRIMARY KEY (user_id),
    CONSTRAINT system_users_identity_no_key UNIQUE (identity_no),
    CONSTRAINT fkk3vym2puxkqyp1dap87yb28h6 FOREIGN KEY (job_position_id)
        REFERENCES public.job_positions (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.system_users
    OWNER to postgres;


CREATE TABLE public.user_operations
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    operation_type_id integer NOT NULL,
    new_user_id integer,
    old_user_id integer NOT NULL,
    action_date date NOT NULL,
    activated boolean NOT NULL,
    CONSTRAINT user_actions_pkey PRIMARY KEY (id),
    CONSTRAINT user_operations_new_user_id_fkey FOREIGN KEY (new_user_id)
        REFERENCES public.employer_users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT user_operations_old_user_id_fkey FOREIGN KEY (old_user_id)
        REFERENCES public.employer_users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT user_operations_operation_type_id_fkey FOREIGN KEY (operation_type_id)
        REFERENCES public.operation_types (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.user_operations
    OWNER to postgres;


CREATE TABLE public.users
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    user_id integer NOT NULL,
    e_mail character varying(150) COLLATE pg_catalog."default" NOT NULL,
    password character varying(20) COLLATE pg_catalog."default" NOT NULL,
    status boolean NOT NULL,
    vaildaiton_code character varying(6) COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT "UK_user_id" UNIQUE (user_id)
)

TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to postgres;
