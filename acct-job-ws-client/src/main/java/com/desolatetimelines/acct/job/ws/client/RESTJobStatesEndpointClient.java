package com.desolatetimelines.acct.job.ws.client;

import com.desolatetimelines.acct.common.ws.model.AcctPage;
import com.desolatetimelines.acct.job.ws.spec.JobStatesEndpoint;
import com.desolatetimelines.acct.job.ws.spec.model.JobState;
import com.desolatetimelines.acct.job.ws.spec.model.JobStateHistoryRecord;
import com.desolatetimelines.acct.job.ws.spec.model.JobStateSetting;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
    contextId = "${JOBS_REGISTRY_APPLICATION_NAME}-jobStates",
    name = "${JOBS_REGISTRY_APPLICATION_NAME}/${JOBS_REGISTRY_SERVER_CONTEXT_PATH}/jobs/status"
)
public interface RESTJobStatesEndpointClient extends JobStatesEndpoint {

    @Override
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    JobState getJobState(@RequestParam("jobUUID") String jobUUID);

    @Override
    @GetMapping(value = "/all", produces = APPLICATION_JSON_VALUE)
    Collection<JobState> getAllJobStates();

    @Override
    @PutMapping(value = "/start", produces = APPLICATION_JSON_VALUE)
    void recordJobStarted(@RequestParam("jobUUID") String jobUUID);

    @Override
    @PutMapping(value = "/end", produces = APPLICATION_JSON_VALUE)
    void recordJobFinished(@RequestParam("jobUUID") String jobUUID, @RequestBody JobStateSetting setting);

    @Override
    @GetMapping(value = "/history", produces = APPLICATION_JSON_VALUE)
    AcctPage<JobStateHistoryRecord> getJobStateHistoryRecordsPage(
        @RequestParam(name = "jobUUID") String jobUUID,
        @RequestParam(name = "pageNumber") int pageNumber,
        @RequestParam(name = "pageSize") int pageSize
    );
}
