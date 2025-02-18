package com.desolatetimelines.acct.currency.springrepository;

import com.desolatetimelines.acct.currency.model.JpaAcctMonitoredCurrency;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JpaAcctMonitoredCurrenciesRepository extends CrudRepository<JpaAcctMonitoredCurrency, Long> {

    Optional<JpaAcctMonitoredCurrency> findFirstByMonitoredCurrencyUUID(String monitoredCurrencyUUID);

}
