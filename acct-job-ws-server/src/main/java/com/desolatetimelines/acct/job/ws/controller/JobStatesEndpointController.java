package com.desolatetimelines.acct.job.ws.controller;

import com.desolatetimelines.acct.job.service.AcctJobsService;
import com.desolatetimelines.acct.job.ws.mapper.JobStateMapper;
import com.desolatetimelines.acct.job.ws.spec.JobStatesEndpoint;
import com.desolatetimelines.acct.job.ws.spec.model.JobState;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.desolatetimelines.acct.job.privilegesprovider.model.JobPrivilegeIds.JOBS_STATES_GET;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/jobs/status")
public class JobStatesEndpointController implements JobStatesEndpoint {

    private final AcctJobsService jobsService;

    public JobStatesEndpointController(AcctJobsService jobsService) {
        this.jobsService = jobsService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + JOBS_STATES_GET + "')")
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public JobState getJobState(@RequestParam("jobUUID") String jobUUID) {
        return
            JobStateMapper.fromAcctJobStatus(
                jobsService.getJobStatus(jobUUID)
            );
    }

}
