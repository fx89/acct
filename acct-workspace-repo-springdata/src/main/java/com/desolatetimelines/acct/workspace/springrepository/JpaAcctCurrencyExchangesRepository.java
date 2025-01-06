package com.desolatetimelines.acct.workspace.springrepository;

import com.desolatetimelines.acct.workspace.model.JpaAcctAccountRecord;
import com.desolatetimelines.acct.workspace.model.JpaAcctCurrencyExchange;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface JpaAcctCurrencyExchangesRepository extends CrudRepository<JpaAcctCurrencyExchange, Long> {

    Collection<JpaAcctCurrencyExchange> findAllByCurrencyExchangeTargetAccountRecordIn(
        Collection<JpaAcctAccountRecord> currencyExchangeSourceAccountRecords
    );

    Optional<JpaAcctCurrencyExchange> findFirstByCurrencyExchangeTargetAccountRecordAccountRecordId(
        Long accountRecordId
    );

}