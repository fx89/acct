package com.desolatetimelines.acct.workspace.springrepository;

import com.desolatetimelines.acct.workspace.model.JpaAcctAccount;
import com.desolatetimelines.acct.workspace.model.JpaAcctAccountRecordAutocompleteData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JpaAcctAccountRecordAutocompleteDataRepository
    extends CrudRepository<JpaAcctAccountRecordAutocompleteData, Long> {

    Page<JpaAcctAccountRecordAutocompleteData> findAllByAccountAndIncomeExpenseItemUUIDAndAccountRecordTextLike(
        JpaAcctAccount account,
        String incomeOrExpenseItemUUID,
        String accountRecordTextPattern,
        Pageable pageable
    );

    Optional<JpaAcctAccountRecordAutocompleteData> findFirstByAccountAndIncomeExpenseItemUUIDAndAccountRecordText(
        JpaAcctAccount account,
        String incomeOrExpenseItemUUID,
        String accountRecordText
    );

}
