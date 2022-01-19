create table if not exists users (
    id bigserial primary key,
    username varchar(64) unique,
    password char(60) not null,
    email varchar(254) not null,
    enabled boolean not null default false
);

create table if not exists roles (
    id bigserial primary key,
    name varchar(64) not null unique
);

create table if not exists users_roles (
    user_id bigint references users(id),
    role_id bigint references roles(id)
);

INSERT INTO users (username, password, email, enabled)
VALUES
    ('admin', '$2a$12$VrDh.a3IPIc0SBujQ.f9iOqKEwaQV18RROnXn0IKvUvIM4J3Bagp6', 'admin@admin.ru', true),
    ('manager', '$2a$12$EFl4r/IPSmFcyHv1LKiYw.KgonH0xI.sTW22CDj8zZ22IMZbzr1Dm', 'manager@manager.ru', true),
    ('user', '$2a$12$BK1..9.ZCggRacKoBafiAulwN0ujIefHud8P0v0nsj9u4KqRDlDpi', 'user@user.ru', true);

INSERT INTO roles (name)
VALUES
    ('ROLE_ADMIN'),
    ('ROLE_MANAGER'),
    ('ROLE_USER');

INSERT INTO users_roles (user_id, role_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3);