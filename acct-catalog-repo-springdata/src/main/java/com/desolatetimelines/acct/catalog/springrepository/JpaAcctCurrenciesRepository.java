package com.desolatetimelines.acct.catalog.springrepository;

import com.desolatetimelines.acct.catalog.model.JpaAcctCurrency;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface JpaAcctCurrenciesRepository extends CrudRepository<JpaAcctCurrency, Long> {

    Optional<JpaAcctCurrency> findFirstByCurrencyUUID(String currencyUUID);

    Collection<JpaAcctCurrency> findAllByCurrencyUUIDIn(Collection<String> currencyUUIDs);

}
