package com.desolatetimelines.acct.reporting.springrepository;

import com.desolatetimelines.acct.reporting.model.JpaAcctReport;
import com.desolatetimelines.acct.reporting.model.JpaAcctReportDataProviderInstance;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface JpaAcctReportDataProviderInstancesRepository
    extends CrudRepository<JpaAcctReportDataProviderInstance, Long> {

    Set<JpaAcctReportDataProviderInstance> findAllByReportIn(Set<JpaAcctReport> report);

}
