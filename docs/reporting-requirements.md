# Reporting microservice requirements

## Endpoints

### Dashboards endpoint

#### `REP-01001` Get dashboards accessible to user
This endpoint calls `SEC-02001` to get the UUIDs of the user dashboards to be returned for the given `userUUID`.

Then it queries for the details of the dashboards having the UUIDs retrieved previously.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/reporting/v1/dashboards/userDashboards?userUUID=d48d6159-bfe4-40c4-a4e4-9203b8203284`

Response body example:
```
[
    {
        "dashboardUUID": "c9e83074-f355-4ade-bfa7-74b2ccb3e3d6",
        "dashboardName": "My Personal Dashboard",
        "dashboardDescription": "This is a dashboard that's meant for my personal use only."
    }
]
```



<br /><br />
#### `REP-01002` Get dashboards accessible to group
This endpoint calls `SEC-02001` to get the UUIDs of the group dashboards to be returned for the given `userUUID`.

Then it queries for the details of the dashboards having the UUIDs retrieved previously.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/reporting/v1/dashboards/groupDashboards?groupUUID=cecccf1c-c26f-40ea-9626-e5abef41646f`

Response body example:
```
[
    {
        "dashboardUUID": "c9e83074-f355-4ade-bfa7-74b2ccb3e3d6",
        "dashboardName": "My Personal Dashboard",
        "dashboardDescription": "This is a dashboard that's meant for my personal use only."
    }
]
```



<br /><br />
#### `REP-01003` Get user accessible dashboards in workspace (user dashboards and group dashboards)
This endpoint calls `SEC-02002` to get the UUIDs of the user and group dashboards to be returned to the current user.

Then it queries for the details of the dashboards having the UUIDs retrieved previously.
The filter for the given `workspaceUUID` is applied at this point.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/reporting/v1/dashboards/inWorkspace?workspaceUUID=e301b44d-3c15-40db-bf81-6615fb56e6db&userUUID=d48d6159-bfe4-40c4-a4e4-9203b8203284`

Response body example:
```
{
    "userDashboards": [
        {
            "dashboardUUID": "c9e83074-f355-4ade-bfa7-74b2ccb3e3d6",
            "dashboardName": "My Personal Dashboard",
            "dashboardDescription": "This is a dashboard that's meant for my personal use only.",
            "dashboardIconUUID": "576a18cd-2ff5-479f-ad59-c1ffa2252d65"
        }
    ],
    "groupDashboards": [
        {
            "dashboardUUID": "5f6f3cca-a020-4506-b1fa-e4836b91c806",
            "dashboardName": "Monthly spendings",
            "dashboardDescription": "This dashboard summarizes monthly spendings.",
            "dashboardIconUUID": "de09f3a1-2d20-4b02-bd7e-58178f5f9bc5"
        }
    ]
}
```



<br /><br />
#### `REP-01004` Edit dashboard (user or group) - create if UUID not specified
Creates a new dashboard or updates the details of the dashboard with the given `dashboardUUID`.
It all happens in the workspace with the given `workspaceUUID`.

If a `dashboardUUID` is given, it first makes sure the current user has access to the dashboards.
It does that by calling `SEC-02005`.

If a `dashboardUUID` is not given and a new dashboard is created, the UUID of the new dashboard is mapped to the
UUID of the current user in the security service by means of calling `SEC-02003` with `ownerType`='USER'.

Example request URLs:
- `PUT http://acct.desolatetimelines.com/service/reporting/v1/dashboards?workspaceUUID=e301b44d-3c15-40db-bf81-6615fb56e6db&dashboardUUID=f5e2b6c6-a2d0-4e0d-9549-80149d3c2dca`
- `PUT http://acct.desolatetimelines.com/service/reporting/v1/dashboards?workspaceUUID=e301b44d-3c15-40db-bf81-6615fb56e6db`

Request body example:
```
{
    "dashboardName": "Monthly spendings",
    "dashboardDescription": "This dashboard summarizes monthly spendings.",
    "dashboardIconUUID": "de09f3a1-2d20-4b02-bd7e-58178f5f9bc5"
}
```

Response body example:
```
{
    "dashboardUUID": "f5e2b6c6-a2d0-4e0d-9549-80149d3c2dca"
}
```



<br /><br />
#### `REP-01005` Delete dashboard (user or group(with special permission for deleting group dashboards))
Deletes the dashboard with the given `dashboardUUID`.

Fails if the dashboard is not owned by the user either directly or through a group that the user is part of.
See `SEC-02005`.

If the `isGroupDashboard` property in the response of `SEC-02005` is set to true then a special permission
is required to let the operation proceed.

Example request URL:
- `DELETE http://acct.desolatetimelines.com/service/reporting/v1/dashboards?dashboardUUID=5f23ceac-7094-416d-970a-f0e938bdb85c`



<br /><br />
#### `REP-01006` Get dashboard reports (including filters, reports and report series)
Returns the reports in the dashboard with the given `dashboardUUID`.

Fails if the dashboard is not owned by the current user. Uses `SEC-02005` to find out.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/reporting/v1/dashboards/reports?dashboardUUID=5f23ceac-7094-416d-970a-f0e938bdb85c`

Response body example:
```
[
    {
        "rowNumber": 0,
        "columnNumber": 0,
        "containerHeightPx": 140,
        "report": {
            "reportName": "Monthly account balance",
            "reportDescription": "Shows the monthly balance as a line and the incoming / outgoing amounts as a column",
            "reportType": "series",
            "commonReportColumnName": "month"
            "series": [
                {
                    "reportColumnName": "balance",
                    "reportSeriesName": "Balance",
                    "reportSeriesType": "line"
                },
                {
                    "reportColumnName": "delta",
                    "reportSeriesName": "Delta",
                    "reportSeriesType": "column"
                }
            ]
        },
        "reportFilters": [
            {
                "filterName": "Month",
                "reportColumnName": "month"
            }
        ]
    }
]
```



<br /><br />
### Reports endpoint

#### `REP-02001` Get reports accessible to user
Retrieves the reports accessible to the user with the given `userUUID`.

First step is to call `SEC-03001` to retrieve the UUIDs of the reports accessible to the user.

Then the details of the reports are retrieved.

Example response body:

```
[
    {
        "reportUUID": "dc0ed31d-d61a-462e-b057-afa18e0a1032",
        "reportName": "Future Interest By Bank",
        "reportDescription": "Displays the cumulated monthly estimated future interest grouped by bank"
    }
]
```



<br /><br />
#### `REP-02002` Get reports accessible to group

Retrieves the reports accessible to the user with the given `groupUUID`.

First step is to call `SEC-03001` to retrieve the UUIDs of the reports accessible to the group.

Then the details of the reports are retrieved.

Example response body:

```
[
    {
        "reportUUID": "dc0ed31d-d61a-462e-b057-afa18e0a1032",
        "reportName": "Future Interest By Bank",
        "reportDescription": "Displays the cumulated monthly estimated future interest grouped by bank"
    }
]
```



<br /><br />
#### `REP-02003` Get sorted page of user accessible reports (user reports and group reports)
This endpoint returns two lists of reports:
1) Reports owned by or accessible directly by the current user
2) Reports accessible by the current user via the groups the user is part of

The first step is to call `SEC-03002`. This returns the UUIDs of the accessible user and group reports.

The second step is to query for the details of the reports with the UUIDs retrieved at the first step.

Example response body:
```
{
    "userReports": [
        {
            "reportUUID": "2e4b1dda-d023-429d-afae-b39e14957ec6",
            "reportName": "Sum monthly expenses by category",
            "reportDescription": "This report sums up the expenses from all the accounts grouping by month and category"
        }
    ],
    "groupReports": [
        {
            "reportUUID": "97676ffe-6584-4e75-919b-9e736be67ca0",
            "reportName": "BNR USD/RON daily currency history",
            "reportDescription": "This report shows the day to day USD/RON exchange rate at BNR"
        }
    ]
}
```



<br /><br />
#### `REP-02004` Save report for user or group (reports without UUID are considered to be new) - includes series
Creates a new report or updates the details of the report with the given `reportUUID`.
It all happens in the workspace with the given `workspaceUUID`.

If a `reportUUID` is given, it first makes sure the current user has access to the report.
It does that by calling `SEC-03005`.

If a `reportUUID` is not given and a new report is created, the UUID of the new report is mapped to the
UUID of the current user in the security service by means of calling `SEC-03003` with `ownerType`='USER'.

Example request URLs:
- `PUT http://acct.desolatetimelines.com/service/reporting/v1/reports?workspaceUUID=e301b44d-3c15-40db-bf81-6615fb56e6db&reportUUID=4e01326c-8d23-485b-bc42-cd942286da14`
- `PUT http://acct.desolatetimelines.com/service/reporting/v1/reports?workspaceUUID=e301b44d-3c15-40db-bf81-6615fb56e6db`

Request body example:
```
{
    "reportName": "BNR USD/RON daily currency history",
    "reportDescription": "This report shows the day to day USD/RON exchange rate at BNR"
}
```

Response body example:
```
{
    "reportUUID": "4e01326c-8d23-485b-bc42-cd942286da14"
}
```



<br /><br />
#### `REP-02005` Get report data with filters
Runs the report compilers bound to the report with the given `reportUUID` and retrieves the results
joined by the `common_report_column_name` defined in the `report` table. 

The operation fails if the report is not owned by the current user. Finds out by calling `SEC-03005`.

Example request URL:
- `POST http://acct.desolatetimelines.com/service/reporting/v1/reports/data?reportUUID=4e01326c-8d23-485b-bc42-cd942286da14`

Request body example:
```
{
    "filters": [
        {
            "reportColumnName": "month",
            "reportColumnValue": "2021-03"
        }
    ]
}
```

Response body example:
```
{
    "reportHeader": [
        "month", "balance", "delta"
    ],

    "reportData": [
        {
            "columnValues": [
                "2020-01",
                "100.00",
                "10.00"
            ]
        },
        {
            "columnValues": [
                "2020-02",
                "110.00",
                "-10.00"
            ]
        },
        {
            "columnValues": [
                "2020-03",
                "100.00",
                "0.00"
            ]
        }
    ]
}
```



<br /><br />
### Used items endpoint

#### `REP-03001` Get types of items that may be in use
Returns a list of values that are accepted for the `objectType` to the "Get items in use of type" operation.

---
**NOTE**

The reporting service does not block any items from being deleted,
therefore it does not use any object type from any other service.

---

Example request URL:
- `GET http://acct.desolatetimelines.com/service/reporting/v1/itemsInUse/objectTypesByService

Response body example:
```
{
    "objectTypesByService": [
    ]
}
```



<br /><br />
#### `REP-03002` Get items in use of type
Returns a list of UUIDs of objects of the given `objectType` which are in use by this service.
Only the objects having their UUIDs in the given list are verified.

---
**NOTE**

The reporting service does not block any items from being deleted,
therefore it does not use any object type from any other service.

---



<br /><br />
## Reporting client

### `REP-04001` API client
Abstraction of the REST API calls to this microservice.
Provides methods that identify and call an instance of the reporting microservice,
along with an appropriate data model.



<br /><br />
## `REP-05001` Privileges
This service exposes the following privileges:
- DASHBOARDS_READ
- DASHBOARDS_SAVE
- DASHBOARDS_DELETE
- REPORTS_READ
- REPORTS_SAVE
- REPORTS_DELETE
- ADMIN_OPERATIONS