create table if not exists user_entity (
    id integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username varchar(255) unique,
    first_name varchar(100),
    last_name varchar(100),
    email varchar(255),
    phone varchar(50),
    user_status integer
);