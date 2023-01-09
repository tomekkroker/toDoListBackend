-- Database: toDoList

CREATE DATABASE "toDoList"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- Create the first table
CREATE TABLE public.list
(
    id       INT PRIMARY KEY,
    name     VARCHAR(20) NOT NULL,
    priority VARCHAR(8),
    user_id INT,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES public.user (id)
);

-- Create the second table
CREATE TABLE public.task
(
    id          INT PRIMARY KEY,
    name        VARCHAR(50) NOT NULL,
    priority    VARCHAR(20),
    deadline    DATE,
    description VARCHAR(256),
    list_id INT,
    CONSTRAINT fk_list FOREIGN KEY (list_id) REFERENCES public.list (id)
);

-- Create the third table
CREATE TABLE public.user
(
    id          INT PRIMARY KEY,
    login       VARCHAR(20),
    password    VARCHAR(20)
);