create table "dashboard_report"(
    dashboard_report_id BIGSERIAL NOT NULL,
    dashboard_id BIGSERIAL NOT NULL,
    report_id BIGSERIAL NOT NULL,
    row_number INT NOT NULL,
    column_number INT NOT NULL,
    container_name VARCHAR(500) NOT NULL,
    container_height_px INT NOT NULL,

    primary key (dashboard_report_id)
);

create unique index dashboard_report_ukey on "dashboard_report"(dashboard_id, row_number, column_number);



create table "dashboard_report_filter"(
    dashboard_report_filter_id BIGSERIAL NOT NULL,
    dashboard_report_id BIGSERIAL NOT NULL,
    filter_name VARCHAR(500) NOT NULL,
    report_column_name VARCHAR(500) NOT NULL,

    primary key (dashboard_report_filter_id)
);

create index dashboard_report_filter_dashboard_report_id on "dashboard_report_filter"(dashboard_report_id);