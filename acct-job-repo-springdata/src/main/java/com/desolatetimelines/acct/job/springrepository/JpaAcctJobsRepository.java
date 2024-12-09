package com.desolatetimelines.acct.job.springrepository;

import com.desolatetimelines.acct.job.model.JpaAcctJob;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JpaAcctJobsRepository extends CrudRepository<JpaAcctJob, Long> {

    Optional<JpaAcctJob> findFirstByJobUUID(String jobUUID);

}
