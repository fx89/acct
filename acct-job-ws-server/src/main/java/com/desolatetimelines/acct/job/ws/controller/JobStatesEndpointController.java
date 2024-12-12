package com.desolatetimelines.acct.job.ws.controller;

import com.desolatetimelines.acct.job.service.AcctJobsService;
import com.desolatetimelines.acct.job.ws.mapper.JobStateMapper;
import com.desolatetimelines.acct.job.ws.spec.JobStatesEndpoint;
import com.desolatetimelines.acct.job.ws.spec.model.JobState;
import com.desolatetimelines.acct.job.ws.spec.model.JobStateSetting;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.desolatetimelines.acct.job.privilegesprovider.model.JobPrivilegeIds.JOBS_STATES_GET;
import static com.desolatetimelines.acct.job.privilegesprovider.model.JobPrivilegeIds.JOBS_STATES_SET;
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

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + JOBS_STATES_SET + "')")
    @PutMapping(value = "/start", produces = APPLICATION_JSON_VALUE)
    public void recordJobStarted(@RequestParam("jobUUID") String jobUUID) {
        jobsService.recordJobStarted(jobUUID);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + JOBS_STATES_SET + "')")
    @PutMapping(value = "/end", produces = APPLICATION_JSON_VALUE)
    public void recordJobFinished(@RequestParam("jobUUID") String jobUUID, @RequestBody JobStateSetting setting) {
        jobsService.recordJobFinished(
            jobUUID,
            JobStateMapper.mapJobOutcome(setting.jobOutcome()),
            setting.errorMessage()
        );
    }

}
