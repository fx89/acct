# User management microservice requirements

## Endpoints

### Users endpoint
The users endpoint has to do with retrieving, creating and updating user information.

<br /><br />
#### `USR-01001` Get current user
Returns the details of the current user.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/user-management/v1/users/currentUser`

Example response body:
```
{
    "userLoginName": "rL34ifJMsa",
    "userName": "Mr. Anonymous",
    "defaultWorkspaceUUID": "fc77c88e-c51c-41b1-a7a7-a23d537e9e46",
    "userIconUUID": "b14506b6-23d6-4d06-a2bd-e75fc062f3fc",
    "userGroups": [
        {
            "groupUUID": "9c463412-4b83-4d71-8876-8ebd415e32cc",
            "groupName": "Administrators"
        },
        {
            "groupUUID": "d381effa-4961-41d4-9d16-c6af7722e9aa",
            "groupName": "Report Maintainers"
        }
    ]
}
```



<br /><br />
#### `USR-01002` Find sorted page of users by login name pattern or by name pattern

Returns a sorted page of users for which the login name or username matches the given `pattern`.
The given pattern must have at least 3 letters.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/user-management/v1/users?pattern=nad&pageNumber=0&pageSize=5`

Example response body:
```
{
    "data": [
        {
            "userUUID": "e96641ef-471a-4a81-9560-4ac0f8d79e43",
            "userName": "John Doe",
            "userLoginName": "nad093jFhLf382na"
        },
        {
            "userUUID": "5bade1d7-eb0d-4355-9679-8ba5a7d5f373",
            "userName": "Anonymous Jane",
            "userLoginName": "nad@J9fkNeH93nal2na"
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
#### `USR-01003` Save user
Saves the given user details. Password is not included. If a `userUUID` is not given, a new user is created.

Example request URLs:
- `PUT http://acct.desolatetimelines.com/service/user-management/v1/users?userUUID=ceb4e897-b250-445e-bcf4-90f6040bbf26`
- `PUT http://acct.desolatetimelines.com/service/user-management/v1/users`

Example request body:
```
{
    "userLoginName": "",
    "userName": "",
    "userIconUUID": "",
    "defaultWorkspaceUUID": ""
}
```

Example response body:
```
{
    "userUUID": "ceb4e897-b250-445e-bcf4-90f6040bbf26"
}
```



<br /><br />
#### `USR-01004` Set password for the current user
Allows the current user to change the password.

Example request URL:
- `POST http://acct.desolatetimelines.com/service/user-management/v1/users/currentUser`

Example request body:
```
{
    "userPassword": "edij230djqwe40rj2d"
}
```



<br /><br />
#### `USR-01005` Reset user password
Resets the password of the user referenced by the given `userUUID`.

The password expiry date is set to the current date plus the configured interval.

Example request URL:
- `PUT http://acct.desolatetimelines.com/service/user-management/v1/users?userUUID=ceb4e897-b250-445e-bcf4-90f6040bbf26`

Response body example:
```
{
    "userPassword": "dfkn2oifn23irjh230"
}
```



<br /><br />
#### `USR-01006` Soft delete user
Sets the softDeleted flag of the user with the given `userUUID` to `true`.

Example request URL:
- `DELETE http://acct.desolatetimelines.com/service/user-management/v1/users?userUUID=ceb4e897-b250-445e-bcf4-90f6040bbf26`


<br /><br />
#### `USR-01007` Undelete user
Sets the softDeleted flag of the user with the given `userUUID` to `false`.

Example request URL:
- `PUT http://acct.desolatetimelines.com/service/user-management/v1/users/undelete?userUUID=ceb4e897-b250-445e-bcf4-90f6040bbf26`



<br /><br />
#### `USR-01008` Get user by UUID
Returns public details about the user with the given `userUUID`.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/user-management/v1/users/user?userUUID=ceb4e897-b250-445e-bcf4-90f6040bbf26`

Example response body:
```
{
    "userLoginName": "mAnon101",
    "userName": "Mr. Anonymous",
    "userIconUUID": "6e5921e1-8a4b-44c8-8689-f439ce3c8c0c"
}
```



<br /><br />
### Groups endpoint

<br /><br />
#### `USR-02001` Find sorted page of groups by login name pattern or by name pattern
Returns a sorted page of groups for which the group name or description matches the given pattern.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/user-management/v1/groups?pageNumber=0&pageSize=5`

Example response body:
```
{
    "data": [
        {
            "groupUUID": "e96641ef-471a-4a81-9560-4ac0f8d79e43",
            "groupName": "Administrators",
            "groupDescription": "All admins are part of this group",
            "groupIconUUID": "f5a94921-5a92-468f-91fc-a38ba5803175"
        },
        {
            "groupUUID": "5bade1d7-eb0d-4355-9679-8ba5a7d5f373",
            "groupName": "Company Workspace Users",
            "groupDescription": "Only privileged users have access to the company workspace",
            "groupIconUUID": "6fcfc2e9-dd4a-456b-b49d-a2f94a76e5bc"
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
#### `USR-02002` Save group
Saves the given group. If a `groupUUID` is not given, a new group is created.

Example request URL:
- `PUT http://acct.desolatetimelines.com/service/user-management/v1/groups?groupUUID=03c5840e-4f5f-46f0-b997-ba4435eb9904`
- `PUT http://acct.desolatetimelines.com/service/user-management/v1/groups`

Example request body:
```
[
    {
        "groupName": "My Favorite Group",
        "groupDescription": "The group of my choice",
        "groupIconUUID": "efbdf0dd-dbce-4fb5-9145-6c6586db2fdf"
    }
]
```

Example response body:
```
{
    "groupUUID": "03c5840e-4f5f-46f0-b997-ba4435eb9904"
}
```



<br /><br />
#### `USR-02003` Delete group
Removes the group with the given `groupUUID`.

Example request URL:
- `DELETE http://acct.desolatetimelines.com/service/user-management/v1/groups?groupUUID=03c5840e-4f5f-46f0-b997-ba4435eb9904`



<br /><br />
#### `USR-02004` Get user groups
Retrieves the list of groups mapped to the user with the given `userUUID`.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/user-management/v1/groups/userGroups?userUUID=03c5840e-4f5f-46f0-b997-ba4435eb9904`

Response body example:
```
[
    {
        "groupUUID": "ed5d974b-f328-407c-ad87-b711e134fe25",
        "groupName": "Administrators",
        "groupDescription": "Administrators are responsible for user and group management",
        "groupIconUUID": "b44859e4-a9cc-457d-8a19-3d3be53cfb06"
    }
]
```



<br /><br />
### Custom operations endpoint

#### `USR-03001` Set default workspace for the current user
Updates the `defaultWorkspaceUUID` for the user with the given `userUUID` with the value of the given `workspaceUUID`
parameter.

Throws an exception if the referenced workspace is not accessible by the user. Calls `SEC-01003` to find out.

Example request URL:
- `PUT http://acct.desolatetimelines.com/service/user-management/v1/users/defaultWorkspace?userUUID=03c5840e-4f5f-46f0-b997-ba4435eb9904`

Example request body:
```
{
    "workspaceUUID": "75e5f4d7-d541-48d7-a711-0f26713a6cb8"
}
```



<br /><br />
### Used items endpoint

#### `USR-04001` Get types of items that may be in use
Returns a list of values that are accepted for the `objectType` to the "Get items in use of type" operation.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/user-management/v1/itemsInUse/objectTypesByService

Response body example:
```
{
    "objectTypesByService": [
        {
            "serviceName": "workspace",
            "objectTypes": [
                "WORKSPACE"
            ]
        }
    ]
}
```


<br /><br />
#### `USR-04002` Get items in use of type
Returns a list of UUIDs of objects of the given `objectType` which are in use by this service.
Only the objects having their UUIDs in the given list are verified.

Example request URL:
- `POST http://acct.desolatetimelines.com/service/user-management/v1/itemsInUse?objectType=WORKSPACE`

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
## User management client

### `USR-05001` API client
Abstraction of the REST API calls to this microservice.
Provides methods that identify and call an instance of the user management microservice,
along with an appropriate data model.



<br /><br />
## Default users

### `USR-06001` The service account
There has to be a service account that is utilized by other microservices. This user needs to have access to everything.
These privileges are assigned via a special group called SERVICE_ACCOUNTS



<br /><br />
## Jobs

### `USR-07001` Delete soft deleted users
This job uses the jobs framework (JOB-03002).

Soft-deleted users may be undeleted for a configurable amount of days. After the configurable amount of days has passed,
soft-deleted users that are older than that are removed together with all the workspaces, dashboards and reports that
data, currency exchange records and any other entities in the deleted workspaces are removed together with the workspace.



<br /><br />
## `USR-08001` Registration with the usage service
When an instance of this service boots up, it must call the `USG-01001` endpoint
to register the item types that can be in use by this service.



<br /><br />
## `USR-09001` Privileges
This service exposes the following privileges:
- USERS_READ_CURRENT
- USERS_READ
- USERS_SAVE_CURRENT
- USERS_SAVE
- USERS_SOFT_DELETE
- USERS_UNDELETE
- USERS_RESET_PASSWORD
- GROUPS_READ_OWN
- GROUPS_READ
- GROUPS_SAVE
- GROUPS_DELETE
- ADMIN_OPERATIONS