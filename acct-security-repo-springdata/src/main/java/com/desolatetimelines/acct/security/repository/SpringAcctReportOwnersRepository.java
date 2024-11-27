package com.desolatetimelines.acct.security.repository;

import com.desolatetimelines.acct.security.model.AcctReportOwner;
import com.desolatetimelines.acct.security.springrepository.JpaAcctReportOwnersRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of the {@link AcctReportOwnersRepository} using Spring Data
 */
@Service
public class SpringAcctReportOwnersRepository implements AcctReportOwnersRepository {

    private final JpaAcctReportOwnersRepository reportOwnersRepository;

    public SpringAcctReportOwnersRepository(JpaAcctReportOwnersRepository reportOwnersRepository) {
        this.reportOwnersRepository = reportOwnersRepository;
    }

    @Override
    public Set<AcctReportOwner> findAllByReportUUIDIn(Collection<String> reportUUIDs) {
        return new HashSet<>(reportOwnersRepository.findAllByReportUUIDIn(reportUUIDs));
    }
}
