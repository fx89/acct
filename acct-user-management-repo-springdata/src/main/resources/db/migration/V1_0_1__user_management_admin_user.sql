insert into "user" (
	user_uuid,
	user_login_name,
	user_encrypted_password,
	user_name,
	soft_deleted
) values (
	'348835f6-c4dc-4c4c-a454-77d5e1cf5e22',
	'admin',
	'{bcrypt}$2a$10$Hn2BqXMmnVrjnTyu/2MwqubaNUeR7gnbPXEPs.YpdmIas1LjkKT5e', -- admin
	'Administartor',
	false
);

insert into "group" (
	group_uuid,
	group_name,
	group_description
) values (
	'3acddb9f-6485-455a-b320-76c2021c942d',
	'Administrators',
	'Group that provides administrative privileges'
);

insert into user_group (
	user_id,
	group_id
) values (
	(select max(user_id) from "user" where user_uuid = '348835f6-c4dc-4c4c-a454-77d5e1cf5e22'),
	(select max(group_id) from "group" where group_uuid = '3acddb9f-6485-455a-b320-76c2021c942d')
);
