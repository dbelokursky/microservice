create table if not exists auth_user
(
    id       serial primary key,
    login    varchar(255) unique,
    password varchar(255),
    status   integer NOT NULL DEFAULT 1,
    created  date,
    updated  date
);

create table if not exists auth_role
(
    id      serial primary key,
    name    varchar(255) unique,
    created date,
    updated date
);

create table if not exists auth_user_auth_role
(
    auth_user integer,
    auth_role integer,
    primary key (auth_user, auth_role),
    constraint user_id_fk foreign key (auth_user) references auth_user (id),
    constraint role_id_fk foreign key (auth_role) references auth_role (id)
);

insert into auth_role (id, name, created, updated)
values (1, 'ROLE_USER', '2023-02-26', '2023-02-26');

insert into auth_role (id, name, created, updated)
values (2, 'ROLE_ADMIN', '2023-02-26', '2023-02-26');