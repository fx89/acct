create table "job"(
	job_id BIGSERIAL,
	job_uuid VARCHAR(36) not null,
	job_service_name VARCHAR(200) not null,
	job_name VARCHAR(200) not null,
	job_description VARCHAR(2000) null,
	primary key (job_id)
);

create unique index job_ukey on "job"(job_uuid);


create table "job_status"(
	job_status_id BIGSERIAL,
	job_id BIGINT not null,
	job_status VARCHAR(200) not null,
    first_start_date TIMESTAMP,
    LAST_START_DATE TIMESTAMP,
    LAST_END_DATE TIMESTAMP,
    LAST_OUTCOME VARCHAR(200),
    CURRENT_START_DATE TIMESTAMP,
    NUMBER_OF_FAILURES_SINCE_LAST_SUCCESSFUL_OUTCOME INT,
	primary key (job_status_id)
);


create table "job_status_history"(
    job_status_history_id BIGSERIAL,
    job_id BIGINT not null,
    job_status_date TIMESTAMP not null,
    job_status VARCHAR(200) not null,
    job_error_message VARCHAR(5000),
    primary key (job_status_history_id)
);