-- Database: toDoList
DROP DATABASE IF EXISTS toDoList;
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