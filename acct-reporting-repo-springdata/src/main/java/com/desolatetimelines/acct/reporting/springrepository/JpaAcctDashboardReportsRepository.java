package com.desolatetimelines.acct.reporting.springrepository;

import com.desolatetimelines.acct.reporting.model.AcctDashboard;
import com.desolatetimelines.acct.reporting.model.JpaAcctDashboard;
import com.desolatetimelines.acct.reporting.model.JpaAcctDashboardReport;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JpaAcctDashboardReportsRepository extends CrudRepository<JpaAcctDashboardReport, Long> {

    Optional<JpaAcctDashboardReport> findFirstByDashboardAndRowNumberAndColumnNumber(
        AcctDashboard dashboard,
        Integer rowNumber,
        Integer columnNumber
    );

    void deleteByDashboard(JpaAcctDashboard dashboard);

}
