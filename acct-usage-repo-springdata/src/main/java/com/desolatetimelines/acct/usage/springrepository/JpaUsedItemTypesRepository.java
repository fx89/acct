package com.desolatetimelines.acct.usage.springrepository;

import com.desolatetimelines.acct.usage.model.JpaAcctUsedItemType;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface JpaUsedItemTypesRepository extends CrudRepository<JpaAcctUsedItemType, Long> {

    void deleteByServiceServiceName(String serviceName);

    Collection<JpaAcctUsedItemType> findAllByUsedItemTypeNameIn(Collection<String> usedItemTypeNames);

}
