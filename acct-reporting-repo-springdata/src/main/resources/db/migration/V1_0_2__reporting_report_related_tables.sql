create table "report"(
    report_id BIGSERIAL NOT NULL,
    report_uuid VARCHAR(36) NOT NULL,
    report_name VARCHAR(500) NOT NULL,
    report_description VARCHAR(4000),
    report_type VARCHAR(200) NOT NULL,
    report_sql_statement VARCHAR(4000) NOT NULL,
    report_category_column_name VARCHAR(200),

    primary key (report_id)
);

create unique index report_ukey on "report"(report_uuid);



create table "report_series"(
    report_series_id BIGSERIAL NOT NULL,
    report_id BIGSERIAL NOT NULL,
    report_column_name VARCHAR(200) NOT NULL,
    report_series_name VARCHAR(500) NOT NULL,
    report_series_type VARCHAR(200) NOT NULL,

    primary key (report_series_id)
);

create index report_series_report_id_idx ON "report_series"(report_id);



create table "report_data_provider_instance"(
    report_data_provider_instance_id BIGSERIAL NOT NULL,
    report_id BIGSERIAL NOT NULL,
    data_provider_instance_id BIGSERIAL NOT NULL,

    primary key (report_data_provider_instance_id)
);

create index report_data_provider_instance_report_id_idx ON "report_data_provider_instance"(report_id);
