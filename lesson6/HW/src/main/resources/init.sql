drop table if exists customer cascade;
create table if not exists customer
(
    id              bigserial primary key,
    name            varchar(255)
);

drop table if exists product cascade;
create table if not exists product
(
    id              bigserial primary key,
    title           varchar(255),
    price           int
);

drop table if exists orders cascade;
create table if not exists orders
(
    id              bigserial primary key,
    customer_id     bigint,

    foreign key (customer_id) references customer (id)
);

drop table if exists order_details cascade;
create table if not exists order_details
(
    order_id        bigint,
    product_id      bigint,
    product_count   int,
    price           int,

    foreign key (order_id) references orders (id),
    foreign key (product_id) references product (id),
    primary key (order_id, product_id)
);

insert into customer (name)
values  ('Андрей'),
        ('Иван'),
        ('Наталья');

insert into product (title, price)
values  ('Молоко', 60),
        ('Хлеб', 50),
        ('Сметана', 100),
        ('Майонез', 120),
        ('Кетчуп', 140);

