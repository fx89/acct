# Security microservice requirements

## Endpoints

### Workspace ownership endpoint

#### `SEC-01001` Get workspaces owned by owner of the given type
Retrieves a list of workspace UUIDs for the workspaces owned by the owner of the given `ownerType`,
having the given `ownerUUID`.

Possible values for the `ownerType`:
- USER
- GROUP
- PUBLIC
- ANY

Example request URLs:
- `GET http://acct.desolatetimelines.com/service/security/v1/workspaceOwners/ownedWorkspaces?ownerType=USER&ownerUUID=962c6d5e-eb3d-431c-a93e-c9747596d998`
- `GET http://acct.desolatetimelines.com/service/security/v1/workspaceOwners/ownedWorkspaces?ownerType=GROUP&ownerUUID=962c6d5e-eb3d-431c-a93e-c9747596d998`
- `GET http://acct.desolatetimelines.com/service/security/v1/workspaceOwners/ownedWorkspaces?ownerType=PUBLIC&ownerUUID=962c6d5e-eb3d-431c-a93e-c9747596d998`
- `GET http://acct.desolatetimelines.com/service/security/v1/workspaceOwners/ownedWorkspaces?ownerType=ANY&ownerUUID=962c6d5e-eb3d-431c-a93e-c9747596d998`

Example response body:
```
[
    "491a8ffd-0728-402d-83b4-9f6d3f766657",
    "53031b13-6261-4c41-8561-d36d017d4172",
    "962d40b2-dd05-4fec-a855-a00de5e0f19c"
]
```



<br /><br />
#### `SEC-02002` Get workspaces owned by user and group assigned to the user
Retrieves a list of workspace UUIDs for the workspaces accessible to the user represented by the given `userUUID`
either directly or through a group that the user is part of. Also returns public workspaces.

To get the groups to which the user is assigned, `USR-02004` is invoked. Once a list of `groupUUID`'s is retrieved,
the `workspace_owner` table can be queried to get the workspaces owned by both the user and the groups (if any).

Example request URL:
- `GET http://acct.desolatetimelines.com/service/security/v1/workspaceOwners/userAccessibleWorkspaces?userUUID=962c6d5e-eb3d-431c-a93e-c9747596d998`

Example response body:
```
{
    "userWorkspaces": [
        "4c4031c1-fde5-446f-8571-32d991508ce9",
        "ca257524-6b49-4528-b44d-7aadb31dbed1",
        "f40221c1-4389-4bec-ae69-7fce1dfaa1d4"
    ],

    "groupWorkspaces": [
        "4c4031c1-fde5-446f-8571-32d991508ce9",
        "ca257524-6b49-4528-b44d-7aadb31dbed1",
        "f40221c1-4389-4bec-ae69-7fce1dfaa1d4"
    ],

    "publicWorkspaces": [
        "4c4031c1-fde5-446f-8571-32d991508ce9",
        "ca257524-6b49-4528-b44d-7aadb31dbed1",
        "f40221c1-4389-4bec-ae69-7fce1dfaa1d4"
    ]
}
```



<br /><br />
#### `SEC-01003` Add workspace owner
Creates a direct mapping between the owner of the given `ownerType`, reference by the given `ownerUUID`, 
and the workspace referenced by the given `workspaceUUID`.

Example request URL:
- `PUT http://acct.desolatetimelines.com/service/security/v1/workspaceOwners`

Example request body:
```
{
    "ownerType": "USER",
    "ownerUUID": "6f3b4a48-24eb-49b2-934b-5a80198cf311",
    "workspaceUUID": "2bf769d9-c911-472d-9162-93381a8d1128"
}
```

Possible values for the `ownerType`:
- USER
- GROUP
- PUBLIC
- ANY



<br /><br />
#### `SEC-01004` Remove workspace owner
Delete the workspace owner with the given `ownerType`,`ownerUUID` and `workspaceUUID`.

Example request URL:
- `DELETE http://acct.desolatetimelines.com/service/security/v1/workspaceOwners?ownerType=USER&ownerUUID=b171409a-4fb2-45f2-a5bd-2b95ccb9dedc&workspaceUUID=afb92971-69f0-49c6-b631-4c658eec1a3f`



<br /><br />
#### `SEC-01005` Check if workspace is accessible by user or by groups assigned to the user
Looks up the `workspace_owner` entity with for the given `workspaceUUID` and `userUUID`.
If found, returns true. If not found, returns false.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/security/v1/userAccessibleWorkspace?userUUID=b171409a-4fb2-45f2-a5bd-2b95ccb9dedc&workspaceUUID=afb92971-69f0-49c6-b631-4c658eec1a3f`

Response body example:
```
{
    "accessible": true,
    "isGroupWorkspace": false
}
```



<br /><br />
### Dashboard ownership endpoint

#### `SEC-02001` Get dashboards owned by owner of the given type
Retrieves a list of dashboard UUIDs for the dashboards owned by the owner of the given `ownerType`,
having the given `ownerUUID`.

Possible values for the `ownerType`:
- USER
- GROUP
- PUBLIC
- ANY

Example request URLs:
- `GET http://acct.desolatetimelines.com/service/security/v1/dashboardOwners/ownedDashboards?ownerType=USER&ownerUUID=962c6d5e-eb3d-431c-a93e-c9747596d998`
- `GET http://acct.desolatetimelines.com/service/security/v1/dashboardOwners/ownedDashboards?ownerType=GROUP&ownerUUID=962c6d5e-eb3d-431c-a93e-c9747596d998`
- `GET http://acct.desolatetimelines.com/service/security/v1/dashboardOwners/ownedDashboards?ownerType=PUBLIC&ownerUUID=962c6d5e-eb3d-431c-a93e-c9747596d998`
- `GET http://acct.desolatetimelines.com/service/security/v1/dashboardOwners/ownedDashboards?ownerType=ANY&ownerUUID=962c6d5e-eb3d-431c-a93e-c9747596d998`

Example response body:
```
[
    "491a8ffd-0728-402d-83b4-9f6d3f766657",
    "53031b13-6261-4c41-8561-d36d017d4172",
    "962d40b2-dd05-4fec-a855-a00de5e0f19c"
]
```



<br /><br />
#### `SEC-02002` Get dashboards owned by user and group assigned to the user
Returns the lists of the UUIDs of the dashboards that are accessible by the current user either directly or through a
group that the user is assigned to. This includes publicly accessible dashboards.
1) User dashboards = the dashboards created by the user or which are directly accessible by the user.
2) Group dashboards = the dashboards that are accessible by the current user because the user is part of a group that owns the dashboards.
3) Public dashboards = the publicly accessible dashboards

To get the groups to which the user is assigned, `USR-02004` is invoked.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/security/v1/dashboardOwners/userAccessibleDashboards?userUUID=962c6d5e-eb3d-431c-a93e-c9747596d998`

Example response body:
```
{
    "userDashboardUUIDs": [
        "c9e83074-f355-4ade-bfa7-74b2ccb3e3d6",
        "19342017-0c34-4f0e-b3c4-7934310c757e"
    ],
    "groupDashboardUUIDs": [
        "2eac183b-176b-493a-9ca1-e627c84a6298",
        "56327a36-630d-4491-8ec5-2b2c09b513e5",
        "763d565b-2511-408e-8300-9c93499438e9"
    ]
}
```



<br /><br />
#### `SEC-02003` Add dashboard owner
Creates a direct mapping between the owner of the given `ownerType`, reference by the given `ownerUUID`,
and the dashboard referenced by the given `dashboardUUID`.

Example request URL:
- `PUT http://acct.desolatetimelines.com/service/security/v1/dashboardOwners`

Example request body:
```
{
    "ownerType": "USER",
    "ownerUUID": "6f3b4a48-24eb-49b2-934b-5a80198cf311",
    "dashboardUUID": "2bf769d9-c911-472d-9162-93381a8d1128"
}
```

Possible values for the `ownerType`:
- USER
- GROUP
- PUBLIC
- ANY



<br /><br />
#### `SEC-02004` Remove dashboard owner
Delete the dashboard owner with the given `ownerType`,`ownerUUID` and `dashboardUUID`.

Example request URL:
- `DELETE http://acct.desolatetimelines.com/service/security/v1/dashboardOwners?ownerType=USER&ownerUUID=UUID=b171409a-4fb2-45f2-a5bd-2b95ccb9dedc&dashboardUUID=afb92971-69f0-49c6-b631-4c658eec1a3f`



<br /><br />
#### `SEC-02005` Check if dashboard is accessible by user or by groups assigned to the user
Looks up the `dashboard_owner` entity with for the given `dashboardUUID` and `userUUID`.
If found, returns true. If not found, returns false.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/security/v1/userAccessibleDashboard?userUUID=b171409a-4fb2-45f2-a5bd-2b95ccb9dedc&dashboardUUID=afb92971-69f0-49c6-b631-4c658eec1a3f`

Response body example:
```
{
    "accessible": true,
    "isGroupDashboard": false
}
```



<br /><br />
### Report ownership endpoint

#### `SEC-03001` Get reports owned by owner of the given type
Retrieves a list of report UUIDs for the reports owned by the owner of the given `ownerType`,
having the given `ownerUUID`.

Possible values for the `ownerType`:
- USER
- GROUP
- PUBLIC
- ANY

Example request URLs:
- `GET http://acct.desolatetimelines.com/service/security/v1/reportOwners/ownedReports?ownerType=USER&ownerUUID=962c6d5e-eb3d-431c-a93e-c9747596d998`
- `GET http://acct.desolatetimelines.com/service/security/v1/reportOwners/ownedReports?ownerType=GROUP&ownerUUID=962c6d5e-eb3d-431c-a93e-c9747596d998`
- `GET http://acct.desolatetimelines.com/service/security/v1/reportOwners/ownedReports?ownerType=PUBLIC&ownerUUID=962c6d5e-eb3d-431c-a93e-c9747596d998`
- `GET http://acct.desolatetimelines.com/service/security/v1/reportOwners/ownedReports?ownerType=ANY&ownerUUID=962c6d5e-eb3d-431c-a93e-c9747596d998`

Example response body:
```
[
    "491a8ffd-0728-402d-83b4-9f6d3f766657",
    "53031b13-6261-4c41-8561-d36d017d4172",
    "962d40b2-dd05-4fec-a855-a00de5e0f19c"
]
```



<br /><br />
#### `SEC-03002` Get reports owned by user and group assigned to the user
Retrieves lists of UUIDs of the reports accessible to the user with the given `userUUID` or to any group the
user is assigned to. Also includes public reports.

To get the groups to which the user is assigned, `USR-02004` is invoked.

Response body example:
```
{
    "userReportUUIDs": [
        "a8ef7ecc-b1b1-4733-ade9-ac1bdb562a78",
        "745686cd-2b65-4d7c-9b1c-2d6aa651176f",
        "906d6ca1-80f1-4c52-8dbb-f098073de82f"
    ],
    "groupReportUUIDs": [
        "4c3a8d1c-f3a7-4d6c-accb-0c8d385ed58b",
        "0f48dc10-0f5f-4b6a-a7e2-3b3a9518454c",
        "b4b8e7f5-6c15-4d9d-b2a0-1b7c2d96f068"
    ],
    "publicReportUUIDs": [
        "4c3a8d1c-f3a7-4d6c-accb-0c8d385ed58b",
        "0f48dc10-0f5f-4b6a-a7e2-3b3a9518454c",
        "b4b8e7f5-6c15-4d9d-b2a0-1b7c2d96f068"
    ]
}
```



<br /><br />
#### `SEC-03003` Add report owner
Creates a direct mapping between the owner of the given `ownerType`, reference by the given `ownerUUID`,
and the report referenced by the given `reportUUID`.

Example request URL:
- `PUT http://acct.desolatetimelines.com/service/security/v1/reportOwners`

Example request body:
```
{
    "ownerType": "USER",
    "ownerUUID": "6f3b4a48-24eb-49b2-934b-5a80198cf311",
    "reportUUID": "2bf769d9-c911-472d-9162-93381a8d1128"
}
```

Possible values for the `ownerType`:
- USER
- GROUP
- PUBLIC
- ANY


<br /><br />
#### `SEC-03004` Remove report owner
Delete the report owner with the given `ownerType`,`ownerUUID` and `reportUUID`.

Example request URL:
- `DELETE http://acct.desolatetimelines.com/service/security/v1/reportOwners?ownerType=USER&ownerUUID=UUID=b171409a-4fb2-45f2-a5bd-2b95ccb9dedc&reportUUID=afb92971-69f0-49c6-b631-4c658eec1a3f`



<br /><br />
#### `SEC-03005` Check if report is accessible by user or by groups assigned to the user
Looks up the `report_owner` entity with for the given `reportUUID` and `userUUID`.
If found, returns true. If not found, returns false.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/security/v1/userAccessibleReport?userUUID=b171409a-4fb2-45f2-a5bd-2b95ccb9dedc&reportUUID=afb92971-69f0-49c6-b631-4c658eec1a3f`

Response body example:
```
{
    "accessible": true,
    "isGroupReport": false
}
```



<br /><br />
### Privileges endpoint

#### `SEC-04001` Get privileges assigned to a group
Retrieves a list of privileges assigned to the group with the given `groupUUID`.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/security/v1/privileges/groupPrivileges?groupUUID=afb92971-69f0-49c6-b631-4c658eec1a3f`

Example response body:
[
    "WORKSPACE_OWNER_CREATE",
    "WORKSPACE_OWNER_DELETE",
    "WORKSPACE_OWNER_GET"
]



<br /><br />
#### `SEC-04002` Get privileges assigned to a user
Retrieves a set of distinct privileges assigned to all the groups that the user with the given `userUUID` is mapped to.
Example request URL:
- `GET http://acct.desolatetimelines.com/service/security/v1/privileges/userPrivileges?userUUID=e03095ac-3302-435a-afde-c5f4613d1ed8`

Example response body:
[
"WORKSPACE_OWNER_CREATE",
"WORKSPACE_OWNER_DELETE",
"WORKSPACE_OWNER_GET"
]



<br /><br />
#### `SEC-04003` Assign privileges to group
Assigns the named privilege to the group with the given `groupUUID`.

The `privilegeName` is validated against the privileges supplied by the plugged in implementations of the
`acct-privileges-provider-spec`.

Example request URL:
- `PUT http://acct.desolatetimelines.com/service/security/v1/privileges`

Example request body:
```
{
    "groupUUID": "afb92971-69f0-49c6-b631-4c658eec1a3f",
    "privilegeName": "WORKSPACE_OWNER_GET"
}
```


<br /><br />
#### `SEC-04004` Remove privileges from group
Removes the privileges in the given list from the group with the given `groupUUID`.

Example request URL:
- `DELETE http://acct.desolatetimelines.com/service/security/v1/privileges/groupPrivileges?groupUUID=882ad235-66a9-4a6f-a7cb-86aca3bc7b90`

Example request body:
```
[
    "WORKSPACE_OWNER_CREATE",
    "WORKSPACE_OWNER_DELETE",
    "WORKSPACE_OWNER_GET"
]
```



<br /><br />
### Used items endpoint

#### `SEC-05001` Get types of items that may be in use
Returns a list of values that are accepted for the `objectType` to the "Get items in use of type" operation.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/security/v1/itemsInUse/objectTypesByService

Response body example:
```
{
    "objectTypesByService": [
        {
            "serviceName": "user-management",
            "objectTypes": [
                "USER",
                "GROUP"
            ]
        },
        {
            "serviceName": "workspace",
            "objectTypes": [
                "WORKSPACE"
            ]
        },
        {
            "serviceName": "reporting",
            "objectTypes": [
                "DASHBOARD",
                "REPORT"
            ]
        }
    ]
}
```



<br /><br />
#### `SEC-05002` Get items in use of type
Returns a list of UUIDs of objects of the given `objectType` which are in use by this service.
Only the objects having their UUIDs in the given list are verified.

Example request URL:
- `POST http://acct.desolatetimelines.com/service/security/v1/itemsInUse?objectType=DASHBOARD`

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
## Security client

### `SEC-06001` API client
Abstraction of the REST API calls to this microservice.
Provides methods that identify and call an instance of the currency microservice,
along with an appropriate data model.



<br /><br />
### Authentication

#### `SEC-07001` OAuth2
OAuth2 is the protocol to use for authenticating users.



<br /><br />
## `SEC-08001` Registration with the usage service
When an instance of this service boots up, it must call the `USG-01001` endpoint
to register the item types that can be in use by this service.



<br /><br />
## `SEC-09001` Privileges
This service exposes the following privileges:
- WORKSPACE_OWNERS_READ
- WORKSPACE_OWNERS_SAVE
- WORKSPACE_OWNERS_READ
- DASHBOARD_OWNERS_READ
- DASHBOARD_OWNERS_SAVE
- DASHBOARD_OWNERS_DELETE
- REPORT_OWNERS_READ
- REPORT_OWNERS_SAVE
- REPORT_OWNERS_DELETE
- PRIVILEGES_READ
- PRIVILEGES_SAVE
- PRIVILEGES_DELETE
- ADMIN_OPERATIONS