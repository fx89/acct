package com.desolatetimelines.acct.reporting.springrepository;

import com.desolatetimelines.acct.reporting.model.JpaAcctReport;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JpaAcctReportsRepository extends CrudRepository<JpaAcctReport, Long> {

    Optional<JpaAcctReport> findFirstByReportUUID(String reportUUID);

}
