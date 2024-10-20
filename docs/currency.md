# Currency microservice requirements

## Endpoints

### Monitored currencies endpoint
The monitored currencies endpoint exposes operations related to monitored currencies management. 

#### `CUR-01001` Get monitored currencies
Returns a list of all monitored currencies.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/currency/v1/monitoredCurrencies`

Response body example:
```
[
    {
        "monitoredCurrencyUUID": "b9c06894-97b5-4dfb-a256-13117b235550",
        "bankUUID": "6b55063f-de66-41b1-a222-3343574607c7",
        "currencyUUID: "cc51d147-3620-4729-abc6-5ba94d968216",
        "quoteCurrencyUUID: "f1ec339a-e6b9-4651-a9ca-41f8e1dab185",
        "collectorName": "com.desolatetimelines.acct.currency.collector.BnrCurrencyHistoryCollector",
        "scheduledTimeHhMm": "14:00",
        "lastMonitoredCurrencyRecordDate": "2024-09-21",
        "lastMonitoredCurrencyRecordPurchaseValue": 4.9899,
        "lastMonitoredCurrencyRecordSaleValue": 5.0124
    }
]
```



<br /><br />
#### `CUR-01002` Get available monitored currency collectors
Returns a list of the names of the available monitored currency collectors.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/currency/v1/monitoredCurrencies/collectors`

Response body example:
```
[
    "com.desolatetimelines.acct.currency.collector.BnrCurrencyHistoryCollector",
    "com.desolatetimelines.acct.currency.collector.BtCurrencyHistoryCollector",
    "com.desolatetimelines.acct.currency.collector.BcrCurrencyHistoryCollector"
]
```



<br /><br />
#### `CUR-01003` Save monitored currency
Creates or updates a monitored currency item. The decision to create or update is based on the presence
of the `monitoredCurrencyUUID` request parameter. Returns the `monitoredCurrencyUUID` of
the created or updated monitored currency.

Example request URLs:
- `PUT http://acct.desolatetimelines.com/service/currency/v1/monitoredCurrencies?monitoredCurrencyUUID=b9c06894-97b5-4dfb-a256-13117b235550`
- `PUT http://acct.desolatetimelines.com/service/currency/v1/monitoredCurrencies`

Request body example:
```
{
    "bankUUID": "6b55063f-de66-41b1-a222-3343574607c7",
    "currencyUUID: "cc51d147-3620-4729-abc6-5ba94d968216",
    "quoteCurrencyUUID: "f1ec339a-e6b9-4651-a9ca-41f8e1dab185",
    "collectorName": "com.desolatetimelines.acct.currency.collector.BnrCurrencyHistoryCollector",
    "scheduledTimeHhMm": "14:00"
}
```

Example response body:
```
{
    "monitoredCurrencyUUID": "b9c06894-97b5-4dfb-a256-13117b235550"
}
```



<br /><br />
#### `CUR-01004` Delete monitored currency
Deletes the monitored currency identified by the given `monitoredCurrencyUUID` request parameter.

Example request URL:
- `DELETE http://acct.desolatetimelines.com/service/currency/v1/monitoredCurrencies?monitoredCurrencyUUID=b9c06894-97b5-4dfb-a256-13117b235550`



<br /><br />
#### `CUR-01005` Collect manually
Runs the collector for the monitored currency specified by the given `monitoredCurrencyUUID` request parameter.

This needs to abide by all the rules stated in `CUR-04001`, including recording the status in the database.
The only exception is that, instead of going through the jobs framework, the collector is run directly.

Example request URL:
- `POST http://acct.desolatetimelines.com/service/currency/v1/monitoredCurrencies/collectManually`



<br /><br />
#### `CUR-01006` Get monitored currency records
Returns the monitored currency records for the monitored currency identified by the given `monitoredCurrencyUUID`
request parameter. Pagination is not necessary even after 10 years. However, by date sorting is required.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/currency/v1/monitoredCurrencies/records`



<br /><br />
### Used items endpoint

#### `CUR-02001` Get types of items that may be in use
Returns a list of values that are accepted for the `objectType` to the "Get items in use of type" operation.

Example request URL:
- `GET http://acct.desolatetimelines.com/service/currency/v1/itemsInUse/objectTypesByService

Response body example:
```
{
    "objectTypesByService": [
        {
            "serviceName": "catalog",
            "objectTypes": [
                "BANK",
                "CURRENCY"
            ]
        }
    ]
}
```



<br /><br />
#### `CUR-02002` Get items in use of type
Returns a list of UUIDs of objects of the given `objectType` which are in use by this service.
Only the objects having their UUIDs in the given list are verified.

Example request URL:
- `POST http://acct.desolatetimelines.com/service/currency/v1/itemsInUse?objectType=CURRENCY`

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
## Monitored currencies client

### `CUR-03001` API client
Abstraction of the REST API calls to this microservice.
Provides methods that identify and call an instance of the currency microservice,
along with an appropriate data model.



<br /><br />
## Background functionality

### `CUR-04001` Collection framework
The collection framework provides a specification for monitored currency collectors. It also triggers collection jobs
and handles clustering of the background jobs.



<br /><br />
#### Running background jobs
The collection framework uses the jobs framework (JOB-03002) to run the collection job.

The collection job checks the configured job running times and statuses for each collector once every 10 seconds.

The collection for a given currency is due if the start time has passed and the value of the `last_collected_date`
field of the `monitored_currency` table is not the current date.

If any currency collector crashes, the job does not crash, but rather stores the error message into the
`collection_error_message` field of the `monitored_currency` table and moves on. On the other hand, if
the collection is successful, the `collection_error_message` field is set to null and the `last_collected_field`
is set to the current day.



<br /><br />
### Monitored currency collectors

#### `CUR-05001` BNR (EUR/RON, USD/RON, CHF/RON, GBP/RON)
Collects history records from the BNR API



<br /><br />
#### `CUR-05002` BT (EUR/RON, USD/RON, CHF/RON, GBP/RON)
Collects history records from the BT API



<br /><br />
#### `CUR-05003` BCR (EUR/RON, USD/RON, CHF/RON, GBP/RON)
Collects history records from the BCR API



<br /><br />
## `CUR-06001` Registration with the usage service
When an instance of this service boots up, it must call the `USG-01001` endpoint
to register the item types that can be in use by this service.



<br /><br />
## `CUR-07001` Privileges
This service exposes the following privileges:
- MONITORED_CURRENCY_READ
- MONITORED_CURRENCY_SAVE
- MONITORED_CURRENCY_DELETE
- ADMIN_OPERATIONS