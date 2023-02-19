create table if not exists auth_user
(
    id       serial primary key,
    login    varchar(255) unique,
    password varchar(255),
    status   integer NOT NULL DEFAULT 1
);

create table if not exists auth_role
(
    id       serial primary key,
    name    varchar(255) unique
);