# Catalog microservice requirements

## Endpoints

### Icons endpoint
The icons endpoint exposes all operations related to icons.

#### `CAT-00001` Get icon categories
Returns a set of icon category names.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/catalog/v1/icons/iconCategories`

Response body example:
```
[
    "Icon cagtegory name 1",
    "Icon cagtegory name 2",
    "Icon cagtegory name 3",
    "Icon cagtegory name 4"
]
```



<br /><br />
#### `CAT-00002` Get icons count
Returns the number of icons registered in a given category that match the given `iconNamePattern`.
If the `iconCategoryName` parameter is null or missing, the count
of all the icons registered in the catalog is returned.

Example request URLs:
- `GET http://acct.desolatetimelines.com/service/catalog/v1/icons/count?iconNamePattern=Clo&iconCategoryName=Icon%20cagtegory%20name%204`
- `GET http://acct.desolatetimelines.com/service/catalog/v1/icons/count`

The given `iconNamePattern` must be null, empty or at least 3 characters long.

Response body example:
```
{
    "iconsCount": 100
}
```



<br /><br />
#### `CAT-00003` Get icons
Returns a page of icons with the specified 0-based page number and page size for the given icons category.
If the `iconCategoryName` parameter is null or missing then icons from all categories are considered.
The given `iconNamePattern` is used for filtering icons by name. It must be null, empty or at least 3 characters long.

Example request URLs:
- `GET http://acct.desolatetimelines.com/service/catalog/v1/icons?iconNamePattern=Clo&iconCategoryName=Icon%20cagtegory%20name%204&pageNumber=0&pageSize=5`
- `GET http://acct.desolatetimelines.com/service/catalog/v1/icons?iconNamePattern=Clo&pageNumber=0&pageSize=5`

Response body example:
```
{
    "data": [
        {
            "iconUUID": "4dc2a964-e97a-4a6e-b8f6-76d0e84a530a",
            "iconName": "Flower"
        },
        {
            "iconUUID": "827ebbb4-c002-4134-b7f3-e3f994d74a31",
            "iconName": "Sky"
        },
        {
            "iconUUID": "ea515a96-48fd-4d3f-8b95-94bc3c7db99c",
            "iconName": "Purple Unicorn"
        }
    ],
    "page": {
        "size" : 5,
        "totalElements" : 50,
        "totalPages" : 10,
        "number" : 0
    }
}
```



<br /><br />
#### `CAT-00004` Get icon bytes
Returns a string (`Content-Type: text/plain`) representing the base64-encoded bytes
of the icon with the given `iconUUID`.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/catalog/v1/icons/icon?iconUUID=64caa562-c077-4169-84db-5b52b8f7d44b`

Response body example:
```
/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgH
BwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIs
IxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwL
DBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIy
MjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAHx
............................................
truncated
```



<br /><br />
#### `CAT-00005` Create icon
Creates an icon in the category with the given `categoryName` with the given data.

Example request URL:
- `POST http://acct.desolatetimelines.com/service/catalog/v1/icons`

Request body example:
```
{
    "iconName": "Cloud in the sky",
    "iconBase64": "/9j/4AAQSkZJRg ... truncated"
}
```

Raises an exception with an icon with the same name already exists.

Returns the icon UUID.

Response body example:
```
{
    "iconUUID": "ea515a96-48fd-4d3f-8b95-94bc3c7db99c"
}
```



<br /><br />
#### `CAT-00006` Delete icons
Deletes the icons having the `iconUUID` in the given list. Operation fails if any one of the icons is in use
by any element in this service (i.e. currency) or by any other services (i.e. workspace service).

Example request URL:
- `DELETE http://acct.desolatetimelines.com/service/catalog/v1/icons`

Request body example:
```
{
    "iconUUIDs": [
        "4dc2a964-e97a-4a6e-b8f6-76d0e84a530a",
        "827ebbb4-c002-4134-b7f3-e3f994d74a31",
        "ea515a96-48fd-4d3f-8b95-94bc3c7db99c"
    ]
}
```



<br /><br />
### Items endpoint
The items endpoint exposes services for managing income or expense item categories and income or expense item subcategories.

#### `CAT-01001` Get income or expense item categories
Returns a list of income or expense item categories.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/catalog/v1/items/categories`

Response body example:
```
[
    {
        "incomeOrExpenseItemCategoryUUID": "71b0c368-f9cb-427a-a50f-fdb08c65b1fe",
        "incomeOrExpenseItemCategoryName": "Category 1",
        "incomeOrExpenseItemCategoryDescription": "Description of category 1",
        "incomeOrExpenseItemCategoryIconUUID": "4dc9a964-e97a-4a6e-b1f6-76d0e86a250a"
    },
    {
        "incomeOrExpenseItemCategoryUUID": "c9051afa-b78f-478b-a2ab-2f80cca17072",
        "incomeOrExpenseItemCategoryName": "Category 2",
        "incomeOrExpenseItemCategoryDescription": "Description of category 2",
        "incomeOrExpenseItemCategoryIconUUID": "b1caaad3-f13b-422b-977f-651d9cb43a77"
    },
    {
        "incomeOrExpenseItemCategoryUUID": "70b232a2-4946-41fe-9b5e-63d0dbd1cf72",
        "incomeOrExpenseItemCategoryName": "Category 3",
        "incomeOrExpenseItemCategoryDescription": "Description of category 3",
        "incomeOrExpenseItemCategoryIconUUID": "11b09b2e-1ac8-495b-9155-f1ae3b2f94be"
    }
]
```



<br /><br />
#### `CAT-01002` Save income or expense item category
This service creates or updates an income or expense item category and returns the `incomeOrExpenseItemCategoryUUID` of
the saved income or expense item category. The decision to create or update the category is based on the existence of
the `incomeOrExpenseItemCategoryUUID` request parameter.

Example request URLs:
- `PUT http://acct.desolatetimelines.com/service/catalog/v1/items/categories?incomeOrExpenseItemCategoryUUID=2bc9a476-e97a-4a6e-b1f6-76d0e86a230b`
- `PUT http://acct.desolatetimelines.com/service/catalog/v1/items/categories`

Request body example:
```
{
    "incomeOrExpenseItemCategoryName": "My New Category Name",
    "incomeOrExpenseItemCategoryDescription": "This is my new income or expense item category",
    "incomeOrExpenseItemCategoryIconUUID": "4dc9a964-e97a-4a6e-b1f6-76d0e86a250a"
}
```

Response body:
```
{
    "incomeOrExpenseItemCategoryUUID": "2bc9a476-e97a-4a6e-b1f6-76d0e86a230b"
}
```



<br /><br />
#### `CAT-01003` Delete income or expense item categories
This service deletes the income or expense item categories having the `incomeOrExpenseItemCategoryUUID`
within the given list of UUIDs. When an income or expense item category is deleted, all
the income or expense item subcategories contained by the category are deleted with it.
This operation fails if any of the income or expense item categories being deleted is in use by the
workspace service.

Example request URL:
- `DELETE http://acct.desolatetimelines.com/service/catalog/v1/items/categories`

Request body example:
```
{
    "incomeOrExpenseItemCategoryUUID": [
        "4dc9a964-e97a-4a6e-b1f6-76d0e86a230a",
        "2bc9a476-e97a-4a6e-b1f6-76d0e86a230b",
        "1da9a112-e97a-4a6e-b1f6-76d0e86a230c"
    ]
}
```



<br /><br />
#### `CAT-01004` Get income or expense item subcategories
Returns a list of income or expense item subcategories residing in the category having the given
`incomeOrExpenseItemCategoryUUID`.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/catalog/v1/items/subcategories?incomeOrExpenseItemCategoryUUID=5f3303c2-fcee-41cf-8038-b5b1febee3d8`

Response body example:
```
[
    {
        "incomeOrExpenseItemSubactegoryUUID": "d7ba562d-1574-400e-abda-da069220dd5e",
        "incomeOrExpenseItemSubcategoryName": "Subcategory 1",
        "incomeOrExpenseItemSubcategoryDescription": "Description of subcategory 1",
        "incomeOrExpenseItemSubcategoryIconUUID": "4dc9a964-e97a-4a6e-b1f6-76d0e86a250a"
    },
    {
        "incomeOrExpenseItemSubcategoryUUID": "78f89cbe-9beb-469a-b6b0-5fecfd2cf6b9",
        "incomeOrExpenseItemSubcategoryName": "Subcategory 2",
        "incomeOrExpenseItemSubcategoryDescription": "Description of subcategory 2",
        "incomeOrExpenseItemSubcategoryIconUUID": "b1caaad3-f13b-422b-977f-651d9cb43a77"
    },
    {
        "incomeOrExpenseItemSubcategoryUUID": "71b0c368-f9cb-427a-a50f-fdb08c65b1fe",
        "incomeOrExpenseItemSubcategoryName": "Subcategory 3",
        "incomeOrExpenseItemSubcategoryDescription": "Description of subcategory 3",
        "incomeOrExpenseItemSubcategoryIconUUID": "11b09b2e-1ac8-495b-9155-f1ae3b2f94be"
    }
]
```



<br /><br />
#### `CAT-01005` Save income or expense item subcategory
This service creates or updates an income or expense item subcategory within the category with the given
`incomeOrExpenseItemCategoryUUID` and returns the `incomeOrExpenseItemSubcategoryUUID` of the created or
updated income or expense item subcategory. The choice to create or update is based on the existence of
the `incomeOrExpenseItemSubcategoryUUID` request parameter.

Example request URLs:
- `PUT http://acct.desolatetimelines.com/service/catalog/v1/items/subcategories?incomeOrExpenseItemCategoryUUID=5f3303c2-fcee-41cf-8038-b5b1febee3d8&incomeOrExpenseItemSubcategoryUUID=2bc9a476-e97a-2b5d-b1f6-76d0e86a230b`
- `PUT http://acct.desolatetimelines.com/service/catalog/v1/items/subcategories?incomeOrExpenseItemCategoryUUID=5f3303c2-fcee-41cf-8038-b5b1febee3d8`

Request body example:
```
{
    "incomeOrExpenseItemSubcategoryName": "My New Subcategory Name",
    "incomeOrExpenseItemSubcategoryDescription": "This is my new income or expense item subcategory",
    "incomeOrExpenseItemSubcategoryIconUUID": "4dc9a964-e97a-4a6d-b1f6-76d0e86a250a"
}
```

Response body example:
```
{
    "incomeOrExpenseItemSubcategoryUUID": "2bc9a476-e97a-2b5d-b1f6-76d0e86a230b"
}
```



<br /><br />
#### `CAT-01006` Delete income or expense item subcategories
This service deletes the income or expense item subcategories having the `incomeOrExpenseItemSubcategoryUUID`
within the given list of UUIDs.

The operation fails if any of the income or expense item subcategories being deleted is in use by the
workspace service

Example request URL:
- `DELETE http://acct.desolatetimelines.com/service/catalog/v1/items/subcategories`

Request body example:
```
{
    "incomeOrExpenseItemSubcategoryUUID": [
        "4dc9a964-e97a-4a6e-b1f6-76d0e86a230a",
        "2bc9a476-e97a-4a6e-b1f6-76d0e86a230b",
        "1da9a112-e97a-4a6e-b1f6-76d0e86a230c"
    ]
}
```



<br /><br />
### Banks endpoint
The banks endpoint provides services for creating, updating and deleting banks.

#### `CAT-02001` Get banks
Returns the list of all banks registered in the system (shouldn't be that many)

Example request URL:
- `GET http://acct.desolatetimelines.com/service/catalog/v1/banks`

Response body example:
```
[
    {
        "bankUUID": "1ae3b629-c31d-2d4a-b1f6-76d1d20a149e",
        "bankCode": "BT",
        "bankName": "Banca Transilvania",
        "internetBankingURL": "https://ib.btrl.ro",
        "bankIconUUID": "4dc9a964-e97a-4a6d-b1f6-76d0e86a250a"
    },
    {
        "bankUUID": "053cd503-b4a3-4604-a2ca-8e6b8b576480",
        "bankCode": "ING",
        "bankName": "ING Bank",
        "internetBankingURL": "https://www.homebank.ro",
        "bankIconUUID": "8720e79a-15a5-45b3-a746-781589f67804"
    }
]
```



<br /><br />
#### `CAT-02002` Save bank
Allows users to create or update the properties of the bank with the given `bankUUID`.
If the `bankUUID` is missing then a new bank entry is created.

Example request URL:
- `PUT http://acct.desolatetimelines.com/service/catalog/v1/banks`
- `PUT http://acct.desolatetimelines.com/service/catalog/v1/banks?bankUUID=0669a55a-8d3a-49b4-a04c-7948dd22e091`

Request body example:
```
{
    "bankCode": "BNR",
    "bankName": "Banca Nationala Romana",
    "internetBankingURL": "www.bnr.ro",
    "bankIconUUID": "0669a55a-8d3a-49b4-a04c-7948dd22e091"
}
```

Returns the `bankUUID` of the saved bank.

Response body example:
```
{
    "bankUUID": "053cd503-b4a3-4604-a2ca-8e6b8b576480"
}
```



<br /><br />
#### `CAT-02003` Delete bank
Deletes the bank with the given `bankUUID`. 
The operation fails if the bank is in use by a monitored currency or by the workspace service.

Example request URL:
- `DELETE http://acct.desolatetimelines.com/service/catalog/v1/banks?bankUUID=a3bb33c5-c176-4402-a563-5c2ce9b0ec4b`



<br /><br />
### Currencies endpoint
The currencies endpoint allows management of the supported currencies.

#### `CAT-03001` Get currencies
Returns a list of all registered currencies.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/catalog/v1/currencies`

Response body example :
```
[
    {
        "currencyUUID": "238592b3-0bfb-4700-9d1b-fcbb9728008a",
        "currencyCode": "EUR",
        "currencyName": "Euro",
        "currencyIconUUID": "1840b1e9-8ebb-4f30-9ac0-b13d5a92ff11"
    },
    {
        "currencyUUID": "b450a9dc-817c-4dc1-8d19-bf8b6a4f2afb",
        "currencyCode": "RON",
        "currencyName": "Romanian Leu",
        "currencyIconUUID": "29dc0fd2-6205-4c0e-90f8-c3d6eebe052d"
    }
    {
        "currencyUUID": "198bbd0e-2490-40ec-adcb-173654052d51",
        "currencyCode": "USD",
        "currencyName": "US Dollar",
        "currencyIconUUID": "4c1577df-7f9a-4f4c-bfe5-24b7c1919045"
    }
]
```



<br /><br />
#### `CAT-03002` Save currency
Creates or updates the currency with the given `currencyUUID`. The decision to create or update is based on the
presence of the `currencyUUID`.

Example request URLs:
- `PUT http://acct.desolatetimelines.com/service/catalog/v1/currencies`
- `PUT http://acct.desolatetimelines.com/service/catalog/v1/currencies?currencyUUID=4d1332f1-6954-4dff-93ba-d028fbabf45e`

Request body example:
```
{
    "currencyUUID": "198bbd0e-2490-40ec-adcb-173654052d51",
    "currencyCode": "USD",
    "currencyName": "US Dollar",
    "currencyIconUUID": "4c1577df-7f9a-4f4c-bfe5-24b7c1919045"
}
```

Response body example:
```
{
    "currencyUUID": "4d1332f1-6954-4dff-93ba-d028fbabf45e"
}
```



<br /><br />
#### `CAT-03003` Delete currencies
Deletes the currencies having the `currencyUUID` in the given list.

Example request URL:
- `DELETE http://acct.desolatetimelines.com/service/catalog/v1/currencies`

Request body example:
```
{
    "currencyUUIDs": [
        "238592b3-0bfb-4700-9d1b-fcbb9728008a",
        "b450a9dc-817c-4dc1-8d19-bf8b6a4f2afb",
        "198bbd0e-2490-40ec-adcb-173654052d51"
    ]
}
```
The operation fails if any of the referenced currencies is in use by any service (i.e. workspace service, currency service).

http://acct.desolatetimelines.com/service/catalog/v1

<br /><br />
### Used items endpoint

#### `04001` Get types of items that may be in use
Returns a list of UUIDs of objects of the given `objectType` which are in use by this service.
Only the objects having their UUIDs in the given list are verified.

---
**NOTE**

The reporting service does not block any items from being deleted,
therefore it does not use any object type from any other service.

---



<br /><br />
#### `CAT-04002` Get items in use of type
Returns a list of UUIDs of objects of the given `objectType` which are in use by this service.
Only the objects having their UUIDs in the given list are verified.

Returns a list of UUIDs of objects of the given `objectType` which are in use by this service.
Only the objects having their UUIDs in the given list are verified.

---
**NOTE**

The reporting service does not block any items from being deleted,
therefore it does not use any object type from any other service.

---



<br /><br />
## Catalog client

### `CAT-05001` API client
Abstraction of the REST API calls to this microservice.
Provides methods that identify and call an instance of the currency microservice,
along with an appropriate data model.



<br /><br />
## `CAT-06001` Privileges
This service exposes the following privileges:
- ICONS_READ
- ICONS_SAVE
- ICONS_DELETE
- ITEMS_READ
- ITEMS_SAVE
- ITEMS_DELETE
- BANKS_READ
- BANKS_SAVE
- BANKS_DELETE
- CURRENCIES_READ
- CURRENCIES_SAVE
- CURRENCIES_DELETE
- ADMIN_OPERATIONS