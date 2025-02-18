create table "monitored_currency"(
    monitored_currency_id BIGSERIAL,
    monitored_currency_uuid VARCHAR(36) NOT NULL,
    bank_uuid VARCHAR(36) NOT NULL,
    currency_uuid VARCHAR(36) NOT NULL,
    quoted_currency_uuid VARCHAR(36) NOT NULL,
    collector_name VARCHAR(4000) NULL,
    scheduled_time_hh_mm VARCHAR(5) NULL,
    last_collection_date TIMESTAMP NULL,
    collection_error_message VARCHAR(4000) NULL,
    last_monitored_currency_record_date TIMESTAMP NULL,
    last_monitored_currency_record_purchase_value NUMERIC NULL,
    last_monitored_currency_record_sale_value NUMERIC NULL,

    primary key (monitored_currency_id)
);

create unique index monitored_currency_ukey on "monitored_currency"(bank_uuid, currency_uuid, quoted_currency_uuid);



create table "monitored_currency_record"(
    monitored_currency_record_id BIGSERIAL,
    monitored_currency_id BIGINT NOT NULL,
    monitored_currency_record_date TIMESTAMP NOT NULL,
    monitored_currency_record_purchase_value NUMERIC NULL,
    monitored_currency_record_sale_value NUMERIC NULL,

    primary key (monitored_currency_record_id),

    constraint monitored_currency_record_monitored_currency_fk foreign key(monitored_currency_id) references "monitored_currency"(monitored_currency_id)
);