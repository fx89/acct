create table workspace_owner (
    workspace_owner_id BIGSERIAL,
    workspace_owner_uuid VARCHAR(36) not null,
    owner_type VARCHAR(10) not null,
    owner_uuid VARCHAR(36) not null,

    primary key (workspace_owner_id)
);

create unique index workspace_owner_ukey on workspace_owner(workspace_owner_uuid);

create index workspace_owner_owner_uuid on workspace_owner(owner_uuid);





create table dashboard_owner (
    dashboard_owner_id BIGSERIAL,
    dashboard_owner_uuid VARCHAR(36) not null,
    owner_type VARCHAR(10) not null,
    owner_uuid VARCHAR(36) not null,

    primary key (dashboard_owner_id)
);

create unique index dashboard_owner_ukey on dashboard_owner(dashboard_owner_uuid);

create index dashboard_owner_owner_uuid on dashboard_owner(owner_uuid);





create table report_owner (
    report_owner_id BIGSERIAL,
    report_owner_uuid VARCHAR(36) not null,
    owner_type VARCHAR(10) not null,
    owner_uuid VARCHAR(36) not null,

    primary key (report_owner_id)
);

create unique index report_owner_ukey on report_owner(report_owner_uuid);

create index report_owner_owner_uuid on report_owner(owner_uuid);





create table group_privilege (
    group_privilege_id BIGSERIAL,
    group_uuid VARCHAR(36),
    privilege_name VARCHAR(200)
);

create index group_privilege_group_uuid on group_privilege(group_uuid);

