# Job registry microservice requirements
The jobs registry service exposes ways for instances of background jobs to synchronize with each other.
It also keeps a record of job runs.

## Endpoints

### Jobs endpoint
The jobs endpoint allows other services to register job details, so that administrators may identify these jobs.

#### `JOB-01001` Get all registered jobs, for use by administrators
Returns a list of all the registered jobs.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/job/v1/jobs`

Response body example:
```
[
    {
        "jobUUID": "38e4b03e-60de-4f84-86b0-3e5b3e21114f",
        "jobServiceName": "Currency",
        "jobName": "Currency history collector",
        "jobDescription": "Collects the history of all the monitored currencies by means of the configured currency collectors"
    }
]
```



<br /><br />
#### `JOB-01002` Register job with the given jobUUID, for use by other services
Registers or a job for the given `jobUUID` and with the given details.

Example request URLs:
- `POST http://acct.desolatetimelines.com/service/job/v1/jobs?jobUUID=fed5b711-3287-4306-9a02-0b6f4b233652`

The `jobUUID` parameter is mandatory.
If there already is a job registered for the given `jobUUID`, an exception is thrown.

Request body example:
```
{
    "jobServiceName": "Currency",
    "jobName": "Currency history collector",
    "jobDescription": "Collects the history of all the monitored currencies by means of the configured currency collectors"
}
```



<br /><br />
### Job states endpoint
The job states endpoint allows other services to register the states of the background jobs that they are running.

#### `JOB-02001` Get state of job by jobUUID, for use by other services
Retrieves the state of the job with the given `jobUUID`.
If there is no such job defined, a 404 NOT FOUND status code is returned.
This lets the calling service know that the job must be registered first.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/job/v1/jobs/status?jobUUID=681b3fc6-0d34-45f5-8b57-82eb121b8ac0`

Response body example:
```
{
    "jobStatus": "RUNNING",
    "firstStartDate": "2020-01-01 13:44:18",
    "lastStartDate": "2020-01-01 18:59:42",
    "lastEndDate": "2020-01-01 18:59:55",
    "lastOutcome": "SUCCESS",
    "currentStartDate": "2020-01-01 21:03:24",
    "numberOfFailuresSinceLastSuccessfulOutcome": 0 
}
```



<br /><br />
#### `JOB-02002` Set state of job by jobUUID, for use by other services
Register or overwrites the state of the job with the given `jobUUID`.
The decision to create or update is determined by the existence of a previous database record in the `job_status` table.

Example requestURL:
- `PUT http://acct.desolatetimelines.com/service/job/v1/jobs/status?jobUUID=681b3fc6-0d34-45f5-8b57-82eb121b8ac0`

Request body example (successful run):
```
{
    "jobStatus": "SUCCESS"
}
```

Request body example (failed run):
```
{
    "jobStatus": "FAILURE"
    "errorMessage": "NullPointerException"
}
```

Possible values for the `jobStatus` property:
- RUNNING
- SUCCESS
- FAILURE

Depending on the value of the `jobStatus` property, the following properties are added or updated
in the `job_status` table:
- If `jobStatus` == RUNNING:
  - The `job_status` field is set to RUNNING
  - The `current_start_date` field is set to the current date
  - If this is a new record, the `first_start_date` field is set to the current date
  - If this is a new record, the `job_uuid` field is set to the value of the `jobUUID` parameter
- If `jobStatus` == SUCCESS:
  - The `last_outcome` field is set to the value of the `job_status` field
  - The `job_status` field is set to SUCCESS
  - The `last_start_date` field is set to the value of the `current_start_date` field
  - The `last_end_date` field is set to the current date
  - The `number_of_failures_since_last_successful_outcome` field is set to 0
- If `jobStatus` == FAILURE:
  - The `last_outcome` field is set to the value of the `job_status` field
  - The `job_status` field is set to FAILURE
  - The `last_start_date` field is set to the value of the `current_start_date` field
  - The `last_end_date` field is set to the current date
  - The `number_of_failures_since_last_successful_outcome` field is incremented
  - The `last_error_message` field is set to the value of the `errorMessage` parameter

A new record is also added into the `job_status_history` table as follows:
- The `job_uuid` field is set to the value of the `jobUUID` parameter
- The `job_status_date` field is set to the current date
- The `job_outcome` field is set to the value of the `jobStatus` property
- The `job_error_message` field is set to the value of the `errorMessage` property



<br /><br />
#### `JOB-02003` Get the states of all the jobs, for use by administrators
Retrieves a list of all the registered job states.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/job/v1/jobs/statusAll`

Response body example:
```
[
    {
        "jobUUID": "681b3fc6-0d34-45f5-8b57-82eb121b8ac0",
        "jobStatus": "RUNNING",
        "firstStartDate": "2020-01-01 13:44:18",
        "lastStartDate": "2020-01-01 18:59:42",
        "lastEndDate": "2020-01-01 18:59:55",
        "lastOutcome": "SUCCESS",
        "currentStartDate": "2020-01-01 21:03:24",
        "numberOfFailuresSinceLastSuccessfulOutcome": 0 
    }
]
```


<br /><br />
#### `JOB-02004` Get a sorted page of the status history of the job with the given jobUUID, for use by administrators
Retrieves a sorted page of the given `pageSize` with the given `pageNumber` of status history records
for the job with the given `jobUUID`.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/job/v1/jobs/status/history?jobUUID=681b3fc6-0d34-45f5-8b57-82eb121b8ac0&pageNumber=0&pageSize=5`

Response body example:
```
{
    "data": [
      {
          "jobStatusDate": "2021-02-01 14:45:37",
          "jobStatus": "FAILURE",
          "errorMessage": "Unknwon host: www.bnr.ro"
      },
      {
          "jobStatusDate": "2021-02-01 14:46:59",
          "jobStatus": "SUCCESS",
          "errorMessage": null
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
## Job registry client

### `JOB-03001` API client
Abstraction of the REST API calls to this microservice.
Provides methods that identify and call an instance of the jobs registry microservice,
along with an appropriate data model.



<br /><br />
### `JOB-03002` Jobs framework
The framework takes care of:
- registering jobs
- scheduling jobs in such a way that two or more microservices of the same type do not run the same job at the same time
- logging job execution timestamps and outcome



<br /><br />
## `JOB-04002` Privileges
This service exposes the following privileges:
- ADMIN_OPERATIONS