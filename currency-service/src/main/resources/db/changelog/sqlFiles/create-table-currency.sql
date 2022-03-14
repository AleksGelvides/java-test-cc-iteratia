create table currency
(
    id       bigserial primary key,
    NumCode  varchar(4),
    CharCode varchar(5),
    Name     varchar(32)
)