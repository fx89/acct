create table "dashboard"(
    dashboard_id BIGSERIAL,
    workspace_uuid VARCHAR(36) NOT NULL,
    dashboard_uuid VARCHAR(36) NOT NULL,
    dashboard_name VARCHAR(500) NOT NULL,
    dashboard_description VARCHAR(4000) NOT NULL,
    dashboard_icon_uuid VARCHAR(36) NOT NULL,

    primary key (dashboard_id)
);

create unique index dashboard_ukey on "dashboard"(dashboard_uuid);
create index dashboard_workspace_idx ON "dashboard"(workspace_uuid);
create index dashboard_name_idx ON "dashboard"(dashboard_name);