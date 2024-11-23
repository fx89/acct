package com.desolatetimelines.acct.usage.repository;

import com.desolatetimelines.acct.usage.model.AcctService;
import com.desolatetimelines.acct.usage.model.JpaAcctService;
import com.desolatetimelines.acct.usage.springrepository.JpaServicesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.desolatetimelines.acct.usage.util.AcctUsageRepoSpringdataUtils.doWithJpaAcctService;
import static java.util.function.Function.identity;

/**
 * Implementation of the {@link ServicesRepository} using Spring Data
 */
@Service
public class SpringServicesRepository implements ServicesRepository {

    private final JpaServicesRepository jpaServicesRepository;

    public SpringServicesRepository(JpaServicesRepository jpaServicesRepository) {
        this.jpaServicesRepository = jpaServicesRepository;
    }

    @Override
    public AcctService createNew() {
        return new JpaAcctService();
    }

    @Override
    public Optional<AcctService> findFirstByServiceName(String serviceName) {
        return jpaServicesRepository.findFirstByServiceName(serviceName).map(identity());
    }

    @Override
    public AcctService save(AcctService service) {
        return doWithJpaAcctService(service, jpaServicesRepository::save);
    }

}
