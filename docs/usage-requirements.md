# Usage microservice requirements
This service is a proxy for usage queries. 

<b>What is a usage query?</b><br />
Before deleting items, each service must check if any other service is referencing (or "using") the item
that's being deleted. It does that by querying the special "Usage endpoint", which is exposed by all the
services that reference or use items owned by other services. A usage query is an HTTP request to the
usage endpoint of a given service.

<b>The proxy functionality</b><br />
Instead of asking each service if it's using an item of a given type, the service that owns the item raises
the query to the proxy. The proxy, having previously registered a mapping between item types and owning
services, queries the usage endpoint of the service that owns the given item type and relays the response
back to the service that raised the query.

## Endpoints

### Usage endpoint

#### `USG-01001` Register types of items that may be in use
This API is meant to be called by each service instance when it boots up. Its aim is to register the types
of resources that may be used by the calling service so that it may be queried for the given resource type
when the "Get items in use of type" API is called.

Example request URL:
```
- `PUT http://acct.desolatetimelines.com/service/usage/v1/registerItemTypesForService`
```

Request body example:
```
{
    "serviceName": "workspace",
    "itemTypes": [
        "CURRENCY",
        "BANK",
        "INCOME_OR_EXPENSE_ITEM",
        "USER"
    ]
}
```

Steps:
1) Look up the service with the given `serviceName` in the configuration table.
   If not registered, register it now.
2) Update the list of item types mapped to the service identified previously,
   even if it's not the first time the service is registered.
3) Look up the FeignClient that's been internally mapped to the given `serviceName`.
   If not found, create one at this time.




<br /><br />
#### `USG-01002` Get items in use of type
Queries the "Get items in use of type" API of the service to which the given `objectType` has been mapped
and returns the response.

Example request URL:
- `POST http://acct.desolatetimelines.com/service/usage/v1/itemsInUse?objectType=BANK`

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

If the given object type is not mapped to any service, an exception is thrown.



<br /><br />
## `USG-02001` Privileges
This service exposes the following privileges:
- ADMIN_OPERATIONS