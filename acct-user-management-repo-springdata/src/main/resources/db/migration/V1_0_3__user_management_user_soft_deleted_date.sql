alter table "user" add soft_deleted_date TIMESTAMP NULL;
create index user_soft_deleted_date_idx on "user"(soft_deleted_date);