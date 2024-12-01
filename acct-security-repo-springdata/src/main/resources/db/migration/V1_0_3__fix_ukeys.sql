drop index workspace_owner_ukey;
create unique index workspace_owner_ukey on workspace_owner(workspace_uuid, owner_uuid);

drop index dashboard_owner_ukey;
create unique index dashboard_owner_ukey on dashboard_owner(dashboard_uuid, owner_uuid);

drop index report_owner_ukey;
create unique index report_owner_ukey on report_owner(report_uuid, owner_uuid);