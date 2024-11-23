package com.desolatetimelines.acct.usage.repository;

import com.desolatetimelines.acct.usage.model.AcctService;
import com.desolatetimelines.acct.usage.model.AcctUsedItemType;
import com.desolatetimelines.acct.usage.model.JpaAcctUsedItemType;
import com.desolatetimelines.acct.usage.springrepository.JpaUsedItemTypesRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.desolatetimelines.acct.usage.util.AcctUsageRepoSpringdataUtils.doWithJpaAcctUsedItemType;
import static java.util.function.Function.identity;

/**
 * Implementation of the {@link UsedItemTypesRepository} using Spring Data
 */
@Service
public class SpringUsedItemTypesRepository implements UsedItemTypesRepository {

    private final JpaUsedItemTypesRepository jpaUsedItemTypesRepository;

    public SpringUsedItemTypesRepository(
        JpaUsedItemTypesRepository jpaUsedItemTypesRepository
    ) {
        this.jpaUsedItemTypesRepository = jpaUsedItemTypesRepository;
    }

    @Override
    public AcctUsedItemType createNew() {
        return new JpaAcctUsedItemType();
    }

    @Override
    public void deleteByService(AcctService service) {
        jpaUsedItemTypesRepository.deleteByServiceServiceName(service.getServiceName());
    }

    @Override
    public void saveAll(Collection<AcctUsedItemType> usedItemTypes) {
        jpaUsedItemTypesRepository.saveAll(
            usedItemTypes.stream()
                .map(usedItemType -> doWithJpaAcctUsedItemType(usedItemType, identity()))
                .toList()
        );
    }

    @Override
    public Collection<AcctUsedItemType> findAllByUsedItemTypeNameIn(Collection<String> usedItemTypeNames) {
        return
            jpaUsedItemTypesRepository.findAllByUsedItemTypeNameIn(usedItemTypeNames)
                .stream()
                .map(jpaAcctUsedItemType -> (AcctUsedItemType) jpaAcctUsedItemType)
                .toList();
    }
}
