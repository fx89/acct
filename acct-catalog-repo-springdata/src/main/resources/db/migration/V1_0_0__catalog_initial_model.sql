create table "icon_category"(
    icon_category_id BIGSERIAL,
    icon_category_name VARCHAR(200) NOT NULL,

    primary key (icon_category_id)
);

create unique index icon_category_ukey on "icon_category"(icon_category_name);



create table "icon"(
    icon_id BIGSERIAL,
    icon_uuid VARCHAR(36) NOT NULL,
    icon_category_id BIGINT NOT NULL,
    icon_name VARCHAR(200) NOT NULL,
    icon_base64 VARCHAR(8000) NOT NULL,

    constraint icon_icon_category_fk FOREIGN KEY(icon_category_id) REFERENCES "icon_category"(icon_category_id),
    primary key (icon_id)
);

create unique index icon_ukey on "icon"(icon_uuid);
create unique index icon_ukey_cat_and_name on "icon"(icon_category_id, icon_name);



create table "bank"(
    bank_id BIGSERIAL,
    bank_uuid VARCHAR(36) NOT NULL,
    bank_code VARCHAR(10) NOT NULL,
    bank_name VARCHAR(200) NOT NULL,
    bank_icon_uuid VARCHAR(36) NOT NULL,
    internet_banking_url VARCHAR(2000),

    primary key (bank_id)
);

create unique index bank_ukey on "bank"(bank_uuid);



create table "currency"(
    currency_id BIGSERIAL,
    currency_uuid VARCHAR(36) NOT NULL,
    currency_code VARCHAR(10) NOT NULL,
    currency_name VARCHAR(200) NOT NULL,
    currency_icon_uuid VARCHAR(36),

    primary key (currency_id)
);

create unique index currency_ukey on "currency"(currency_uuid);



create table "income_or_expense_category"(
    income_or_expense_category_id BIGSERIAL,
    income_or_expense_category_uuid VARCHAR(36) NOT NULL,
    income_or_expense_category_name VARCHAR(200) NOT NULL,
    income_or_expense_category_description VARCHAR(2000),
    income_or_expense_category_icon_uuid VARCHAR(36),

    primary key (income_or_expense_category_id)
);

create unique index income_or_expense_category_ukey on "income_or_expense_category"(income_or_expense_category_uuid);
create unique index income_or_expense_category_ukey_name on "income_or_expense_category"(income_or_expense_category_name);



create table "income_or_expense_subcategory"(
    income_or_expense_subcategory_id BIGSERIAL,
    income_or_expense_category_id BIGINT NOT NULL,
    income_or_expense_subcategory_uuid VARCHAR(36) NOT NULL,
    income_or_expense_subcategory_name VARCHAR(200) NOT NULL,
    income_or_expense_subcategory_description VARCHAR(2000),
    income_or_expense_subcategory_icon_uuid VARCHAR(36),

    constraint income_or_expense_subcategory_income_or_expense_category_fk FOREIGN KEY(income_or_expense_category_id) REFERENCES "income_or_expense_category"(income_or_expense_category_id),
    primary key (income_or_expense_subcategory_id)
);

create unique index income_or_expense_subcategory_ukey on "income_or_expense_subcategory"(income_or_expense_subcategory_uuid);
create unique index income_or_expense_subcategory_ukey_cat_and_name on "income_or_expense_subcategory"(income_or_expense_category_id, income_or_expense_subcategory_name);



create table "income_or_expense_item"(
    income_or_expense_item_id BIGSERIAL,
    income_or_expense_subcategory_id BIGINT NOT NULL,
    income_or_expense_item_uuid VARCHAR(36) NOT NULL,
    income_or_expense_item_name VARCHAR(200),
    income_or_expense_item_description VARCHAR(2000),
    income_or_expense_item_icon_uuid VARCHAR(36),

    constraint income_or_expense_item_income_or_expense_subcategory_fk FOREIGN KEY(income_or_expense_subcategory_id) REFERENCES "income_or_expense_subcategory"(income_or_expense_subcategory_id),
    primary key (income_or_expense_item_id)
);

create unique index income_or_expense_item_ukey on "income_or_expense_item"(income_or_expense_item_uuid);
create unique index income_or_expense_item_ukey_subcat_and_name on "income_or_expense_item"(income_or_expense_subcategory_id, income_or_expense_item_name);



