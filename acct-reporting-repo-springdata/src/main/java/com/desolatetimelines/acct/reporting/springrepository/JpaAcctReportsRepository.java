package com.desolatetimelines.acct.reporting.springrepository;

import com.desolatetimelines.acct.reporting.model.JpaAcctReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface JpaAcctReportsRepository extends CrudRepository<JpaAcctReport, Long> {

    Optional<JpaAcctReport> findFirstByReportUUID(String reportUUID);

    Page<JpaAcctReport> findAllByReportUUIDIn(Set<String> reportUUIDs, Pageable page);

}
