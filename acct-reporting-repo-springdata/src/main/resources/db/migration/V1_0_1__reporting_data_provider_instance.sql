create table "data_provider_instance"(
    data_provider_instance_id BIGSERIAL,
    data_provider_instance_uuid VARCHAR(36) NOT NULL,
    data_provider_instance_name VARCHAR(500) NOT NULL,
    data_provider_uuid VARCHAR(36) NOT NULL,

    primary key (data_provider_instance_id)
);

create unique index data_provider_instance_ukey on "data_provider_instance"(data_provider_instance_uuid);
create index data_provider_instance_name_idx ON "data_provider_instance"(data_provider_instance_name);



create table "data_provider_instance_property"(
    data_provider_instance_property_id BIGSERIAL,
    data_provider_instance_id BIGSERIAL NOT NULL,
    property_name VARCHAR(500) NOT NULL,
    property_value VARCHAR(4000) NOT NULL,

    constraint data_provider_instance_property_data_provider_instance_fk FOREIGN KEY(data_provider_instance_id) REFERENCES "data_provider_instance"(data_provider_instance_id),
    primary key (data_provider_instance_property_id)
);

create unique index data_provider_instance_property_ukey on "data_provider_instance_property"(data_provider_instance_id, property_name);



create table "data_provider_instance_runtime_parameter"(
    data_provider_instance_runtime_parameter_id BIGSERIAL,
    data_provider_instance_id BIGSERIAL NOT NULL,
    parameter_name VARCHAR(500) NOT NULL,
    parameter_default_value VARCHAR(4000) NOT NULL,
    parameter_data_type VARCHAR(100) NOT NULL,
    mandatory BOOLEAN,

    constraint data_provider_instance_runtime_parameter_data_provider_instance_fk FOREIGN KEY(data_provider_instance_id) REFERENCES "data_provider_instance"(data_provider_instance_id),
    primary key (data_provider_instance_runtime_parameter_id)
);

create unique index data_provider_instance_runtime_parameter_ukey on "data_provider_instance_runtime_parameter"(data_provider_instance_id, parameter_name);
