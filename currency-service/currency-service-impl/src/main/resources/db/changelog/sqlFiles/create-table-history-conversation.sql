create table history_conversation
(
    id bigserial primary key,
    base bigserial,
    target bigserial,
    constraint fk_base foreign key(base) references currency(id),
    constraint fk_target foreign key(target) references currency(id),
    course numeric,
    data_conversation timestamp
)