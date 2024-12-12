package com.desolatetimelines.accy.job.ws.client;

import com.desolatetimelines.acct.job.ws.spec.JobStatesEndpoint;
import com.desolatetimelines.acct.job.ws.spec.model.JobState;
import com.desolatetimelines.acct.job.ws.spec.model.JobStateSetting;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
    contextId = "${JOBS_REGISTRY_APPLICATION_NAME}-groups",
    name = "${JOBS_REGISTRY_APPLICATION_NAME}/${JOBS_REGISTRY_SERVER_CONTEXT_PATH}/jobs/status"
)
public interface RESTJobStatesEndpointClient extends JobStatesEndpoint {

    @Override
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    JobState getJobState(@RequestParam("jobUUID") String jobUUID);

    @Override
    @PutMapping(value = "/start", produces = APPLICATION_JSON_VALUE)
    void recordJobStarted(@RequestParam("jobUUID") String jobUUID);

    @Override
    @PutMapping(value = "/end", produces = APPLICATION_JSON_VALUE)
    void recordJobFinished(@RequestParam("jobUUID") String jobUUID, @RequestBody JobStateSetting setting);

}
