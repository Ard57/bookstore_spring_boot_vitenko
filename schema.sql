CREATE TABLE books
(
    id             bigserial primary key,
    name           varchar(255) not null,
    author         varchar(255),
    isbn           varchar(25),
    pages          integer,
    year_published integer,
    price          numeric(10, 2),
    cover       varchar(25)
);

CREATE TABLE users
(
    id bigserial primary key,
    email varchar(255) not null unique,
    first_name varchar(255),
    last_name varchar(255),
    password varchar(50) not null,
    role varchar(25)
);

CREATE TABLE orders
(
    id bigserial primary key,
    user_id bigint references users,
    status varchar(25) not null
);

CREATE TABLE order_items
(
    id bigserial primary key,
    order_id bigint references orders,
    book_id bigint references books,
    price numeric(10, 2),
    amount int not null
)