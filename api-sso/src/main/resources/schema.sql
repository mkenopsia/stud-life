create schema if not exists user_management;

create table if not exists user_management.t_user
(
    id       bigint primary key,
    c_username text not null check ( length(trim(c_username)) > 0 ),
    c_email    text not null check ( length(trim(c_email)) > 0 ),
    c_password text not null check ( length(trim(c_password)) > 0 ),
    c_role     text not null check ( length(trim(c_role)) > 0 )
)