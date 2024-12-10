package com.desolatetimelines.acct.job.ws.controller;

import com.desolatetimelines.acct.job.service.AcctJobsService;
import com.desolatetimelines.acct.job.ws.mapper.JobSummaryMapper;
import com.desolatetimelines.acct.job.ws.spec.JobsEndpoint;
import com.desolatetimelines.acct.job.ws.spec.model.JobRegistrationRequest;
import com.desolatetimelines.acct.job.ws.spec.model.JobSummary;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

import static com.desolatetimelines.acct.job.privilegesprovider.model.JobPrivilegeIds.JOBS_LIST_ALL;
import static com.desolatetimelines.acct.job.privilegesprovider.model.JobPrivilegeIds.JOBS_REGISTER;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/jobs")
public class JobsEndpointController implements JobsEndpoint {

    private final AcctJobsService jobsService;

    public JobsEndpointController(AcctJobsService jobsService) {
        this.jobsService = jobsService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + JOBS_REGISTER + "')")
    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public void registerJob(
        @RequestParam("jobUUID") String jobUUID,
        @RequestBody JobRegistrationRequest request
    ) {
        jobsService.registerJob(jobUUID, request.jobServiceName(), request.jobName(), request.jobDescription());
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + JOBS_LIST_ALL + "')")
    @GetMapping("")
    public Collection<JobSummary> getAllRegisteredJobs() {
        return
            jobsService.getAllRegisteredJobs()
                .stream()
                .map(JobSummaryMapper::fromAcctJob)
                .collect(Collectors.toSet());
    }

}
