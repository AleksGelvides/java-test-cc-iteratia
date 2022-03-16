create table history_conversation
(
    id bigserial primary key,
    base bigint,
    target bigint,
    constraint fk_base foreign key (base) references currency(id),
    constraint fk_target foreign key(target) references currency(id),
    course float8,
    date_conversation date
)