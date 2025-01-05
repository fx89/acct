package com.desolatetimelines.acct.workspace.springrepository;

import com.desolatetimelines.acct.workspace.model.AcctAccount;
import com.desolatetimelines.acct.workspace.model.AcctAccountRecord;
import com.desolatetimelines.acct.workspace.model.JpaAcctAccountRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface JpaAccountRecordsRepository extends CrudRepository<JpaAcctAccountRecord, Long> {

    Page<AcctAccountRecord> findAllByAccount(AcctAccount account, Pageable page);

    Page<AcctAccountRecord> findAllByAccountAndAccountRecordTextLike(
        AcctAccount account,
        String textPattern,
        Pageable page
    );

}
