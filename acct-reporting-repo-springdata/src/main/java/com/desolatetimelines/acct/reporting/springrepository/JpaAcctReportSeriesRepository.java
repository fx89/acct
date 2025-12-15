package com.desolatetimelines.acct.reporting.springrepository;

import com.desolatetimelines.acct.reporting.model.JpaAcctReport;
import com.desolatetimelines.acct.reporting.model.JpaAcctReportSeries;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface JpaAcctReportSeriesRepository extends CrudRepository<JpaAcctReportSeries, Long> {

    Set<JpaAcctReportSeries> findAllByReport(JpaAcctReport report);

}
