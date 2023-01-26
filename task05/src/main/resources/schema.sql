    create table if not exists user_entity (
    id serial primary key,
    username varchar(255) unique,
    first_name varchar(100),
    last_name varchar(100),
    email varchar(255),
    phone varchar(50),
    user_status integer NOT NULL DEFAULT 1
);