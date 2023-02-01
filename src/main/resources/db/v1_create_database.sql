-- Database: toDoList
DROP DATABASE IF EXISTS "toDoList";
CREATE DATABASE "toDoList"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

--Drop table task
DROP TABLE  IF EXISTS public.task;
--Drop table list
DROP TABLE  IF EXISTS public.list;
-- Create the first table
CREATE TABLE public.list
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(20) NOT NULL,
    priority VARCHAR(8)
--     user_id INT,
--     CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES public.user (id)
);

-- Create the second table
CREATE TABLE public.task
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(50) NOT NULL,
    priority    VARCHAR(20),
    deadline    DATE,
    description VARCHAR(256),
    list_id INT,
    CONSTRAINT fk_list FOREIGN KEY (list_id) REFERENCES public.list (id)
);

--Drop table user
-- DROP TABLE  IF EXISTS public.user_entity;
-- Create the third table
-- CREATE TABLE public.user
-- (
--     id          SERIAL PRIMARY KEY,
--     login       VARCHAR(20),
--     password    VARCHAR(20)
-- );

DROP TABLE IF EXISTS public.permission CASCADE;
CREATE TABLE public.permission (
                                       id integer NOT NULL,
                                       name text NOT NULL,
                                       description text,
                                       created_by text,
                                       created_date date,
                                       last_modified_by text,
                                       last_modified_date date,
                                       CONSTRAINT permission_pk PRIMARY KEY (id),
                                       CONSTRAINT name_uq_1 UNIQUE (name)
);

DROP TABLE IF EXISTS public.role CASCADE;
CREATE TABLE public.role (
                             id integer NOT NULL,
                             name text NOT NULL,
                             created_by text,
                             created_date date,
                             last_modified_by text,
                             last_modified_date date,
                             CONSTRAINT roles_pk PRIMARY KEY (id),
                             CONSTRAINT name_uq UNIQUE (name)
);

DROP TABLE IF EXISTS public.many_role_has_many_permission CASCADE;
CREATE TABLE public.many_role_has_many_permission (
                                                      id_role integer NOT NULL,
                                                      id_permission integer NOT NULL,
                                                      CONSTRAINT many_role_has_many_permission_pk PRIMARY KEY (id_role,id_permission),
                                                      CONSTRAINT role_fk FOREIGN KEY (id_role)
                                                          REFERENCES public.role (id) MATCH FULL
                                                          ON DELETE CASCADE ON UPDATE CASCADE,
                                                      CONSTRAINT permission_fk FOREIGN KEY (id_permission)
                                                          REFERENCES public.permission (id) MATCH FULL
                                                          ON DELETE CASCADE ON UPDATE CASCADE
);