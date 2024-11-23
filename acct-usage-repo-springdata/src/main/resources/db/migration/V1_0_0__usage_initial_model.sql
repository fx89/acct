create table "service" (
    service_id BIGSERIAL,
    service_name VARCHAR(200) NOT NULL,
    service_context_path VARCHAR(400) NOT NULL,

    primary key (service_id)
);

create unique index service_ukey on "service"(service_id);



create table used_item_type (
    used_item_type_id BIGSERIAL,
    service_id BIGINT NOT NULL,
    used_item_type_name VARCHAR(200) NOT NULL,

    primary key (used_item_type_id),
    constraint used_item_type_service_fk FOREIGN KEY(service_id) REFERENCES "service"(service_id)
);

create index used_item_type_used_item_type_name_idx on used_item_type(used_item_type_name);
