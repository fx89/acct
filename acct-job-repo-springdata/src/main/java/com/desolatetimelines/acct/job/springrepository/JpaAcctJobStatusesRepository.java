package com.desolatetimelines.acct.job.springrepository;

import com.desolatetimelines.acct.job.model.AcctJob;
import com.desolatetimelines.acct.job.model.JpaAcctJobStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JpaAcctJobStatusesRepository extends CrudRepository<JpaAcctJobStatus, Long> {

    Optional<JpaAcctJobStatus> findFirstByJob(AcctJob job);

}
