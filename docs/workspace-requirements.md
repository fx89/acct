# Workspace microservice requirements

## Endpoints

### Workspaces endpoint

<br /><br />
#### `WKS-01001` Get workspaces accessible to current user
Returns a list of workspaces available to the current user. Includes:
- workspaces directly accessible by the current user
- workspaces accessible to the current user via the groups the user is a member of
- public workspaces

The UUIDs of the workspaces available to the current user are retrieved from `SEC-01002`.

After the lists of workspace UUIDs are retrieved, the details of the workspaces with the retrieved UUIDs can be queried.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/workspace/v1/workspaces/currentUser`

The response body looks like this:
```
{
    "userWorkspaces": [
        {
            "workspaceUUID": "36b32889-d4d8-4e9b-bcb4-78d4563bbf6c",
            "workspaceName": "My Personal Workspace",
            "workspaceDescription": "A workspace for my personal finances",
            "workspaceIconUUID": "80125c7d-f32c-472e-8aac-4a5aa5936297",
            "defaultCurrencyUUID": "e092ef00-a1b9-4e3b-8d5d-bf6a88b933e2"
        }
    ],
    "groupWorkspaces": [
    ],
    "publicWorkspaces": [
    ]
}
```


<br /><br />
#### `WKS-01002` Get workspaces accessible to user with the given userUUID
Returns a list of workspaces available to the user with the given `userUUID`.

The UUIDs of the workspaces available to the user with the given `userUUID` are retrieved by calling `SEC-01001`.

After the list of workspace UUIDs is retrieved, the details of the workspaces with the retrieved UUIDs can be queried.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/workspace/v1/workspaces/user?userUUID=94d43ec1-e1f8-446a-9895-80de09b40bb9`

Example response body:

```
[
    {
        "workspaceUUID": "36b32889-d4d8-4e9b-bcb4-78d4563bbf6c",
        "workspaceName": "My Personal Workspace",
        "workspaceDescription": "A workspace for my personal finances"
    }
]
```


<br /><br />
#### `WKS-01003` Get workspaces accessible to group with the given groupUUID
Returns a list of workspaces available to the group with the given `groupUUID`.

The UUIDs of the workspaces available to the group with the given `groupUUID` are retrieved by calling `SEC-01001`.

After the list of workspace UUIDs is retrieved, the details of the workspaces with the retrieved UUIDs can be queried.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/workspace/v1/workspaces/group?groupUUID=94d43ec1-e1f8-446a-9895-80de09b40bb9`

Example response body:

```
[
    {
        "workspaceUUID": "36b32889-d4d8-4e9b-bcb4-78d4563bbf6c",
        "workspaceName": "My Personal Workspace",
        "workspaceDescription": "A workspace for my personal finances"
    }
]
```


<br /><br />
#### `WKS-01004` Save workspace
Saves the workspace with the given `workspaceUUID`. If there is on `workspaceUUID` given, a new workspace is created.

Example request URLs:
- `PUT http://acct.desolatetimelines.com/service/workspace/v1/workspaces?workspaceUUID=dd7617bf-9030-4f78-9991-c39923273d42`
- `PUT http://acct.desolatetimelines.com/service/workspace/v1/workspaces`

Request body example:
```
{
    "workspaceName": "My Personal Workspace",
    "workspaceDescription": "Just for me and no one else",
    "workspaceIconUUID": "4c602ecb-7ddc-45d3-805f-f56409001093",
    "defaultCurrencyUUID": "f477662a-63d7-4e0d-85c4-6f7c451dd993"
}
```

Response body example:
```
{
    "workspaceUUID": "dd7617bf-9030-4f78-9991-c39923273d42"
}
```


<br /><br />
#### `WKS-01005` Delete workspace
Deletes the workspace with the given `workspaceUUID`.

Users can delete user workspaces that belong to them and nobody else.

Users can delete group workspaces owned by the group that they are part of, provided they have privileges.

Users who pare part of groups that have privileges to delete any workspace (i.e. admins) may delete any worksapce.

Example request URL:
- `DELETE http://acct.desolatetimelines.com/service/workspace/v1/workspaces?workspaceUUID=dd7617bf-9030-4f78-9991-c39923273d42`



<br /><br />
### Accounts endpoint


<br /><br />
#### `WKS-02001` Get accounts in workspace
Returns a list of all the accounts in the workspace with the given `workspaceUUID`.

Raises an exception if the workspace is not accessible by the user.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/workspace/v1/accounts?workspaceUUID=dd7617bf-9030-4f78-9991-c39923273d42`

Example response body:
```
[
    {
        "accountUUID": "f966d973-ffad-474a-857f-6555a1ade70f",
        "accountName": "ING RON",
        "accountIconUUID": "5985fb54-fa5a-4479-8075-650cc0d15b30",
        "accountNUmber": "RO 00 INGB 1029 3829 3839 8483",
        "currencyUUID": "cd4583a8-781a-4aac-90c5-dec57dcad3e6",
        "bankUUID": "acd9cc68-d0bb-4156-9ef5-0bb42d9edcb8"
    }
]
```

<br /><br />
#### `WKS-02002` Save account into workspace
Saves the account with the given `accountUUID`. If there is no `accountUUID` given then a new account is created
in the workspace with the given `workspaceUUID`.

Raises an exception if the account is not part of a workspace accessible by the user.

Example request URLs:
- `PUT http://acct.desolatetimelines.com/service/workspace/v1/accounts?workspaceUUID=dd7617bf-9030-4f78-9991-c39923273d42`
- `PUT http://acct.desolatetimelines.com/service/workspace/v1/accounts?workspaceUUID=dd7617bf-9030-4f78-9991-c39923273d42&accountUUID=f966d973-ffad-474a-857f-6555a1ade70f`

Example request body:
```
{
    "accountName": "ING RON",
    "accountIconUUID": "5985fb54-fa5a-4479-8075-650cc0d15b30",
    "accountNUmber": "RO 00 INGB 1029 3829 3839 8483",
    "currencyUUID": "cd4583a8-781a-4aac-90c5-dec57dcad3e6",
    "bankUUID": "acd9cc68-d0bb-4156-9ef5-0bb42d9edcb8"
}
```

Example response body:
```
{
    "accountUUID": "f966d973-ffad-474a-857f-6555a1ade70f"
}
```


<br /><br />
#### `WKS-02003` Delete account from workspace
Deletes the account with the given `accountUUID`.

Raises an exception if the account is not part of a workspace accessible by the user.

Example request URL:
- `DELETE http://acct.desolatetimelines.com/service/workspace/v1/accounts?accountUUID=eae46ade-1ce8-4fb3-ab6b-cc36f78d35e9`



<br /><br />
#### `WKS-02004` Get account balance
Returns the balance of the account with the given `accountUUID`.

Raises an exception if the account is not part of a workspace accessible by the user.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/workspace/v1/accounts/balance?accountUUID=eae46ade-1ce8-4fb3-ab6b-cc36f78d35e9`

Example response body:
```
{
    "accountBalance": 999999999.99
}
```

<br /><br />
### Account records endpoint

<br /><br />
#### `WKS-03001` Find sorted page of account records by account and text content
Returns a sorted page of account records belonging to the account with the given `accountUUID` and for which the
account record text matches the optional `pattern` (if given).

Raises an exception if the account is not part of a workspace accessible by the user.

Example request URLs:
- `GET http://acct.desolatetimelines.com/service/workspace/v1/accountRecords?accountUUID=eae46ade-1ce8-4fb3-ab6b-cc36f78d35e9&pageNumber=0&pageSize=5`
- `GET http://acct.desolatetimelines.com/service/workspace/v1/accountRecords?accountUUID=eae46ade-1ce8-4fb3-ab6b-cc36f78d35e9&pageNumber=0&pageSize=5&pattern=Cond`

Example response body:
```
    "data": [
        {
            "accountRecordId": 24,
            "accountRecordDate": "2020-01-01",
            "recordedByUserUUID": "8246a0ba-56de-4878-b48c-fdc167739911",
            "incomeOrExpenseItemUUID": "3ec9f1e4-4d06-4529-9f77-f638b34b9134",
            "accountRecordText": "Beer",
            "accountRecordValue": 50.73,
            "lastModifiedDate": "2020-01-01",
            "lastModifiedByUserId": "8246a0ba-56de-4878-b48c-fdc167739911",
            "purchasePrice": 300.19
        }
    ],
    "page": {
        "size" : 5,
        "totalElements" : 50,
        "totalPages" : 10,
        "number" : 0
    }
```

If the currency of the account is not the same as the workspace default currency then the optional `purchasePrice` field
is returned. The value is taken from the currency exchange record related to the account record.



<br /><br />
#### `WKS-03002` Save account record
Saves the account record with the given `accountRecordId`. If there is no `accountRecordId` given then a new account
record is created in the account with the given `accountUUID`.

Raises an exception if the account represented by the given `accountUUID` is not part of a workspace that's accessible
to the current user.

Example request URLs:
- `PUT http://acct.desolatetimelines.com/service/workspace/v1/accountRecords?accountUUID=eae46ade-1ce8-4fb3-ab6b-cc36f78d35e9&accountRecordId=13`
- `PUT http://acct.desolatetimelines.com/service/workspace/v1/accountRecords?accountUUID=eae46ade-1ce8-4fb3-ab6b-cc36f78d35e9`

Example request body:
```
{
    "incomeOrExpenseItemUUID": "3ec9f1e4-4d06-4529-9f77-f638b34b9134",
    "accountRecordText": "Beer",
    "accountRecordValue": 50.73
}
```

If this is a new account record then:
- the `accountRecordDate` is set to the current date
- the `recordedByUserUUID` is set to the current user's `userUUID`
- the `lastModifiedDate` is set to the current date
- the `lastModifiedByUserId` is set to the current user's `userUUID`

If this is an existing record then:
- the `lastModifiedDate` is set to the current date
- the `lastModifiedByUserId` is set to the current user's `userUUID`

If the currency of the account is not the same as the workspace default currency then a currency exchange record is
created. If the amount is negative then the record related to the positive amount is identified and the two records
are made to reference each other through the `optionalReverseCurrencyExchangeId` field.

Example response body:
```
{
    "accountRecordId": 13
}
```



<br /><br />
#### `WKS-03003` Transfer amount between accounts
Transfers a given amount of money from one account to another.

Raises an exception if the accounts are not in the same workspace.

Raises an exception if any of the two accounts is not part of a workspace accessible to the current user.

Raises an exception if the source account does not have enough money.

Raises an exception if the transfer is attempted between accounts with different currencies. This kind of transfer
is made by manually adding the records in the two accounts.

Example request URL:
- `POST http://acct.desolatetimelines.com/service/workspace/v1/accountRecords/transfer`

Example request body:
```
{
    "sourceAccountUUID": "99cabb35-0c17-4174-88ca-2319514b0eeb",
    "targetAccountUUID": "9c16496a-9d95-47ce-bb63-3c25d0a9e17d",
    "amount": 100.54
}
```


<br /><br />
### Deposits endpoint


<br /><br />
#### `WKS-04001` Get sorted page of deposits by workspace and bank (optionally including capitalized ones)
Returns a sorted page of deposits in the workspace referenced by the given `workspaceUUID`. If a `bankUUID` is given
then the query also filters for the referenced bank.

Raises an exception if the referenced workspace is not accessible by the current user.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/workspace/v1/deposits/transfer`

Example response body:
```
"data": [
    {
        "depositUUID": "d81b2a32-ef1c-4dde-a9d5-5d444f026d7a",
        "sourceAccountUUID": "f70f6060-4a32-416e-98bf-325f9ed49d52",
        "depositAccountNumber": "4928 9382 9382 234",
        "currencyUUID": "16bdf22d-63d6-4c2c-bd6d-8e9bae4e30a6",
        "bankUUID": "d1dc44a6-4421-43b5-b763-fe2f260cd55c",
        "depositValue": 2000,
        "depositInterestPercent": 5.2,
        "depositStartDate": "2020-01-01",
        "depositProjectedEndDate": "2021-01-01"
    }
],
"page": {
    "size" : 5,
    "totalElements" : 50,
    "totalPages" : 10,
    "number" : 0
}
```

The `depositStartDate` is taken from the account record pointed to by the `deposit_creation_account_record_id` field.



<br /><br />
#### `WKS-04002` Create new deposit from source bank account
Creates a new deposit with the given `depositAccountNumber`, `amount`, `projectedEndDate` and `interestPct`
from the bank account with the given`sourceAccountUUID`.

If the source account is not part of a workspace that is accessible by the current user, an error is thrown.

If the source account does not have sufficient funds, an error is thrown.

Example request URL:
- `PUT http://acct.desolatetimelines.com/service/workspace/v1/deposits`

Example request body:
```
{
    "sourceAccountUUID": "6b862cfd-184f-4012-aeeb-2a4144885a31",
    "depositAccountNumber": "0399 3929 3039 393",
    "amount": 2000,
    "projectedEndDate": "2020-01-01"
    "interestPct": 5.2
}
```

The `currency_uuid` of the deposit is that of the source account.

A new account record is created in the source account for the deposit creation transaction and ahe `deposit_creation_account_record` of the deposit is set to point to that record.

Example response body:
```
{
    "depositUUID": "d81b2a32-ef1c-4dde-a9d5-5d444f026d7a"
}
```


<br /><br />
#### `WKS-04003` Edit existing deposit (deposit value is not modified)

Allows editing the `deposit_account_number` and/or the `projected_end_date` of the deposit with the given `depositUUID`.

Raises an exception if the deposit is not part of a workspace that's accessible to the current user.

Example request URL:
- `POST http://acct.desolatetimelines.com/service/workspace/v1/deposits?depositUUID=0870fe21-8868-4175-b57c-ca7d5547ed16`

Example request body:
```
{
    "depositAccountNumber": "0399 3929 3039 393",
    "projectedEndDate": "2020-01-01"
}
```



<br /><br />
#### `WKS-04004` Get sorted page of deposits to capitalize

Returns a sorted page of the deposits, from the workspace with the given `workspaceUUID`,
that have reached maturity and are ready to be capitalized. These are the deposits
for which the `deposit_projected_end_date` is before the current date
and which do not have the `deposit_interest_account_record_id` set.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/workspace/v1/deposits?workspaceUUID=4507b837-8a31-49b6-b440-391dc8ac4cfd&pageNUmber=0&pageSize=5`

Example response body:
```
{
    "data": [
    {
        "depositUUID": "d81b2a32-ef1c-4dde-a9d5-5d444f026d7a",
        "depositAccountNumber": "4928 9382 9382 234",
        "currencyUUID": "16bdf22d-63d6-4c2c-bd6d-8e9bae4e30a6",
        "bankUUID": "d1dc44a6-4421-43b5-b763-fe2f260cd55c",
        "depositValue": 2000,
        "depositInterestPercent": 5.2,
        "depositStartDate": "2020-01-01",
        "depositProjectedEndDate": "2021-01-01"
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
#### `WKS-04005` Capitalize deposit
Capitalizes the deposit with the given `depositUUID` with the given return value.

Example request URL:
- `POST http://acct.desolatetimelines.com/service/workspace/v1/deposits/capitalize?depositUUID=b61072cd-6929-4817-a071-d33c290f27ac`

Request body example:
```
{
    "returnValue": 2500
}
```

Capitalization involves the following actions:
- Create a record in the source account of the deposit where the value of the record pointed to by the `deposit_creation_account_record_id` is returned and set the `deposit_return_account_record_id` to point to this new record.
- Create a record in the source account with the interest amount (the given `returnValue` minus the value of the record pointed to by `deposit_creation_account_record_id`) and set the `deposit_interest_account_record_id` to point to this new record.



<br /><br />
### Autocomplete endpoint


<br /><br />
#### `WKS-05001` Get the account record autocomplete data for workspace, income or expense item and account record text pattern
Returns the `account_record_text` of the first account record matching the following selection criteria:
- The `account_uuid` of the account pointed to by the `account_id` equals the given `accountUUID`
- The `income_or_expense_item_uuid` matches the given `incomeOrExpenseItemUUID`
- The `account_record_text` begins with the given `textPattern` that's at least `3` letters long

Raises an error if the account referenced by the given `accountUUID` is not part of a workspace accessible to the user.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/workspace/v1/autocomplete?accountUUID=de9bb5cc-2c55-44af-9784-68c2d7964250&incomeOrExpenseItemUUID=b8f610d3-1b94-413a-9848-6ec8dd94ed98&textPattern=Car`

Example response body:
```
{
    "accountRecordText": "Car paint"
}
```



<br /><br />
### Used items endpoint

#### `WKS-06001` Get types of items that may be in use
Returns a list of values that are accepted for the `objectType` to the "Get items in use of type" operation.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/workspace/v1/itemsInUse/objectTypesByService

Response body example:
```
{
    "objectTypesByService": [
        {
            "serviceName": "catalog",
            "objectTypes": [
                "CURRENCY",
                "BANK",
                "INCOME_OR_EXPENSE_ITEM"
            ]
        },
        {
            "serviceName": "user-management",
            "objectTypes": [
                "USER"
            ]
        }
    ]
}
```



<br /><br />
#### `WKS-06002` Get items in use of type
Returns a list of UUIDs of objects of the given `objectType` which are in use by this service.
Only the objects having their UUIDs in the given list are verified.

Example request URL:
- `POST http://acct.desolatetimelines.com/service/workspace/v1/itemsInUse?objectType=USER`

Request body example:
```
[
    "22379c4c-1105-4945-8838-70786719c3ad",
    "5092908c-6104-452f-801c-6c88bd7d0d36",
    "43ba4abf-0fd1-49f9-b879-fccb2a4a4c7e"
]
```

Response body example:
```
[
    "22379c4c-1105-4945-8838-70786719c3ad"
]
```



<br /><br />
## Workspace client

### `WKS-07001` API client
Abstraction of the REST API calls to this microservice.
Provides methods that identify and call an instance of the workspace microservice,
along with an appropriate data model.



<br /><br />
## `WKS-08001` Registration with the usage service
When an instance of this service boots up, it must call the `USG-01001` endpoint
to register the item types that can be in use by this service.



<br /><br />
## `WKS-09001` Privileges
This service exposes the following privileges:
- WORKSPACES_READ_OWN
- WORKSPACES_READ
- WORKSPACES_SAVE_OWN
- WORKSPACES_SAVE
- WORKSPACE_DELETE_OWN
- WORKSPACES_DELETE
- ACCOUNTS_READ
- ACCOUNTS_SAVE
- ACCOUNTS_DELETE
- ACCOUNT_RECORDS_READ
- ACCOUNT_RECORDS_SAVE
- ACCOUNT_RECORDS_TRANSFER
- DEPOSITS_READ
- DEPOSITS_SAVE
- DEPOSITS_CAPITALIZE
- AUTOCOMPLETE_READ
- ADMIN_OPERATIONS