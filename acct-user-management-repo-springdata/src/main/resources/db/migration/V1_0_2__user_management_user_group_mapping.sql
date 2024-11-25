alter table user_group drop constraint user_group_pkey;
alter table user_group add user_group_id BIGSERIAL;
alter table user_group add primary key (user_group_id);