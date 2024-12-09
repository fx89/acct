package com.desolatetimelines.accy.job.ws.client;

import com.desolatetimelines.acct.job.ws.spec.JobsEndpoint;
import com.desolatetimelines.acct.job.ws.spec.model.JobRegistrationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
    contextId = "${JOBS_REGISTRY_APPLICATION_NAME}-groups",
    name = "${JOBS_REGISTRY_APPLICATION_NAME}/${JOBS_REGISTRY_SERVER_CONTEXT_PATH}/jobs"
)
public interface RESTJobsEndpointClient extends JobsEndpoint {

    @Override
    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    void registerJob(
        @RequestParam("jobUUID") String jobUUID,
        @RequestBody JobRegistrationRequest request
    );

}
