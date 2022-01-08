create table if not exists student
(
    id bigserial primary key,
    name varchar(255),
    score integer
);

insert into student (name, score)
values ('Николай', 80),
       ('Екатерина', 85),
       ('Алексей', 92);