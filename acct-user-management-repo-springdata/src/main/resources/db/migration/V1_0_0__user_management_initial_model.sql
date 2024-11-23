create table "user"(
	user_id BIGSERIAL,
	user_uuid VARCHAR(36) not null,
	user_login_name VARCHAR(200) not null,
	user_encrypted_password VARCHAR(500) not null,
	user_password_expiry_date TIMESTAMP,
	user_name VARCHAR(200),
	user_icon_uuid VARCHAR(36),
	default_workspace_uuid VARCHAR(36),
	soft_deleted BOOLEAN default false,

	primary key (user_id)
);

create unique index user_ukey on "user"(user_uuid);

create unique index user_login_name_ukey on "user"(user_login_name);





create table "group"(
	group_id BIGSERIAL,
	group_uuid VARCHAR(36) not null,
	group_name VARCHAR(200),
	group_description VARCHAR(4000),
	group_icon_uuid VARCHAR(36),

	primary key (group_id)
);

create unique index group_ukey on "group"(group_uuid);






create table user_group(
	user_id BIGINT,
	group_id BIGINT,

	primary key (user_id, group_id)
);