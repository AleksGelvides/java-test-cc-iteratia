create table currency
(
    id       bigserial primary key,
    NumCode  varchar(12),
    CharCode varchar(12),
    nominal int4,
    name  varchar,
    value float8,
    date date
)