package com.desolatetimelines.acct.reporting.repository;

import com.desolatetimelines.acct.common.model.Page;
import com.desolatetimelines.acct.reporting.model.AcctReport;
import com.desolatetimelines.acct.reporting.model.JpaAcctReport;
import com.desolatetimelines.acct.reporting.springrepository.JpaAcctReportsRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

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

    @Override
    public Page<AcctReport> findAllByReportUUIDIn(Set<String> reportUUIDs, int pageNumber, int pageSize) {
        // Get the page
        final org.springframework.data.domain.Page<JpaAcctReport> page =
            jpaAcctReportsRepository
                .findAllByReportUUIDIn(
                    reportUUIDs,
                    PageRequest.of(pageNumber, pageSize)
                );

        // Convert the page
        return
            new Page<>(
                page.stream().map(jpaAcctReport -> (AcctReport) jpaAcctReport).toList(),
                page.getNumberOfElements(),
                page.getTotalElements()
            );
    }

}
