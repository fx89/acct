create table "workspace"(
    workspace_id BIGSERIAL,
    workspace_uuid VARCHAR(36) not null,
    workspace_name VARCHAR(200) not null,
    workspace_description VARCHAR(4000),
    workspace_icon_uuid VARCHAR(36),
    default_currency_uuid VARCHAR(36),

    primary key (workspace_id)
);

create unique index workspace_ukey on "workspace"(workspace_uuid);

create index workspace_workspace_icon_uuid_idx on "workspace"(workspace_icon_uuid);
create index workspace_default_currency_uuid_idx on "workspace"(default_currency_uuid);



create table "account"(
    account_id BIGSERIAL,
    account_uuid VARCHAR(36) not null,
    workspace_id BIGINT,
    account_name VARCHAR(200) not null,
    account_icon_uuid VARCHAR(36),
    account_number VARCHAR(200) not null,
    currency_uuid VARCHAR(36) not null,
    bank_uuid VARCHAR(36) not null,

    primary key (account_id),

    constraint account_workspace_fk foreign key(workspace_id) references "workspace"(workspace_id)
);

create unique index account_ukey on "account"(account_uuid);

create index account_account_icon_uuid_idx on "account"(account_icon_uuid);
create index account_currency_uuid_idx on "account"(currency_uuid);
create index account_bank_uuid_idx on "account"(bank_uuid);



create table "account_record"(
    account_record_id BIGSERIAL,
    account_id BIGINT not null,
    account_record_date TIMESTAMP,
    recorded_by_user_uuid VARCHAR(36) not null,
    income_or_expense_item_uuid VARCHAR(36) not null,
    account_record_text VARCHAR(4000) not null,
    account_record_value NUMERIC not null,
    last_modified_date TIMESTAMP not null,
    last_modified_by_user_uuid VARCHAR(36) not null,

    primary key (account_record_id),

    constraint account_record_account_fk foreign key(account_id) references "account"(account_id)
);

create index account_record_account_id_account_record_date_idx on "account_record"(account_id, account_record_date);
create index account_record_recorded_by_user_uuid_idx on "account_record"(recorded_by_user_uuid);
create index account_record_last_modified_by_user_uuid_idx on "account_record"(last_modified_by_user_uuid);
create index account_record_income_or_expense_item_uuid_idx on "account_record"(income_or_expense_item_uuid);



create table "deposit"(
    deposit_id BIGSERIAL,
    deposit_uuid VARCHAR(36) not null,
    deposit_account_number VARCHAR(200) not null,
    currency_uuid VARCHAR(36) not null,
    bank_uuid VARCHAR(36) not null,
    deposit_value NUMERIC not null,
    deposit_interest_percent NUMERIC not null,
    deposit_creation_account_record_id BIGINT not null,
    deposit_return_account_record_id BIGINT,
    deposit_interest_account_record_id BIGINT,
    deposit_projected_end_date TIMESTAMP,

    primary key (deposit_id),

    constraint deposit_account_record_fk1_creation foreign key(deposit_creation_account_record_id) references "account_record"(account_record_id),
    constraint deposit_account_record_fk2_return foreign key(deposit_return_account_record_id) references "account_record"(account_record_id),
    constraint deposit_account_record_fk3_interest foreign key(deposit_interest_account_record_id) references "account_record"(account_record_id)
);

create unique index deposit_ukey on "deposit"(deposit_uuid);

create index deposit_currency_uuid_idx on "deposit"(currency_uuid);



create table "account_record_autocomplete_data"(
    account_record_autocomplete_data_id BIGSERIAL,
    account_id BIGINT,
    income_or_expense_item_uuid VARCHAR(36) not null,
    account_record_text VARCHAR(4000) not null,
    last_used_account_record_value NUMERIC not null,

    primary key (account_record_autocomplete_data_id),

    constraint account_record_autocomplete_data_account_fk foreign key(account_id) references "account"(account_id)
);



create table "currency_exchange"(
    currency_exchange_id BIGSERIAL,
    currency_exchange_source_account_record_id BIGINT not null,
    currency_exchange_target_account_record_id BIGINT not null,
    currency_exchange_rate NUMERIC not null,
    purchase_price NUMERIC not null,
    optional_reverse_currency_exchange_id BIGINT not null,

    primary key (currency_exchange_id),

    constraint currency_exchange_self_fk foreign key(optional_reverse_currency_exchange_id) references "currency_exchange"(currency_exchange_id)
);


