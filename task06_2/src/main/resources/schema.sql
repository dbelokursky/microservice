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
SELECT 1, 'ROLE_USER', '2023-02-26', '2023-02-26'
WHERE NOT EXISTS (SELECT 1 FROM auth_role WHERE id = 1);

insert into auth_role (id, name, created, updated)
SELECT 2, 'ROLE_ADMIN', '2023-02-26', '2023-02-26'
WHERE NOT EXISTS (SELECT 1 FROM auth_role WHERE id = 2);

insert into auth_user(id, login, password)
SELECT 1, 'user', '$2a$12$TOPvPWHUeIJzoyFy.j8f8eVyyKKCjOV/8PJKTGQNcTFXmb3beiTxS'
WHERE NOT EXISTS (SELECT 1 FROM auth_user WHERE id = 1);

insert into auth_user(id, login, password)
SELECT 2, 'admin', '$2a$12$2n0m/62GIUWyH4fWIKTADuFERPC2JkAETTsuU2IJorughaXF58Hsu'
WHERE NOT EXISTS (SELECT 1 FROM auth_user WHERE id = 2);


insert into auth_user_auth_role(auth_user, auth_role)
SELECT 1, 1
WHERE NOT EXISTS (SELECT 1 FROM auth_user_auth_role WHERE auth_user = 1 and auth_role = 1);

insert into auth_user_auth_role(auth_user, auth_role)
SELECT 2, 1
WHERE NOT EXISTS (SELECT 1 FROM auth_user_auth_role WHERE auth_user = 2 and auth_role = 1);

insert into auth_user_auth_role(auth_user, auth_role)
SELECT 2, 2
WHERE NOT EXISTS (SELECT 1 FROM auth_user_auth_role WHERE auth_user = 2 and auth_role = 2);

-- insert into auth_role (id, name, created, updated)
-- values (1, 'ROLE_USER', '2023-02-26', '2023-02-26');

-- insert into auth_role (id, name, created, updated)
-- values (2, 'ROLE_ADMIN', '2023-02-26', '2023-02-26');

-- insert into auth_user(id, login, password)
-- values (1, 'user', '$2a$12$TOPvPWHUeIJzoyFy.j8f8eVyyKKCjOV/8PJKTGQNcTFXmb3beiTxS');

-- insert into auth_user(id, login, password)
-- values (2, 'admin', '$2a$12$2n0m/62GIUWyH4fWIKTADuFERPC2JkAETTsuU2IJorughaXF58Hsu');

-- insert into auth_user_auth_role(auth_user, auth_role)
-- values (1, 1);

-- insert into auth_user_auth_role(auth_user, auth_role)
-- values (2, 1);

-- insert into auth_user_auth_role(auth_user, auth_role)
-- values (2, 2);
