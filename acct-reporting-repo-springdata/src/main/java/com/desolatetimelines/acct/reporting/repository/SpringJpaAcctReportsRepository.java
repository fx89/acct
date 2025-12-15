package com.desolatetimelines.acct.reporting.repository;

import com.desolatetimelines.acct.reporting.model.AcctReport;
import com.desolatetimelines.acct.reporting.model.JpaAcctReport;
import com.desolatetimelines.acct.reporting.springrepository.JpaAcctReportsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.desolatetimelines.acct.reporting.util.AcctReportingRepoSpringDataUtils.doWithJpaAcctReportReturning;
import static java.util.function.Function.identity;

/**
 * Implementation of the
 * {@link AcctReportsRepository reports repository}
 * that uses Spring Data JPA to persist data provider instances to the database
 */
@Service
public class SpringJpaAcctReportsRepository implements AcctReportsRepository {

    private final JpaAcctReportsRepository jpaAcctReportsRepository;

    public SpringJpaAcctReportsRepository(JpaAcctReportsRepository jpaAcctReportsRepository) {
        this.jpaAcctReportsRepository = jpaAcctReportsRepository;
    }

    @Override
    public AcctReport createNew() {
        return new JpaAcctReport();
    }

    @Override
    public AcctReport save(AcctReport report) {
        return doWithJpaAcctReportReturning(report, jpaAcctReportsRepository::save);
    }

    @Override
    public Optional<AcctReport> findFirstByReportUUID(String reportUUID) {
        return jpaAcctReportsRepository.findFirstByReportUUID(reportUUID).map(identity());
    }

}
