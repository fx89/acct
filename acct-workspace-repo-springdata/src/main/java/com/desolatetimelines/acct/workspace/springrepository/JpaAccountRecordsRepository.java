package com.desolatetimelines.acct.workspace.springrepository;

import com.desolatetimelines.acct.workspace.model.AcctAccount;
import com.desolatetimelines.acct.workspace.model.AcctAccountRecord;
import com.desolatetimelines.acct.workspace.model.JpaAcctAccount;
import com.desolatetimelines.acct.workspace.model.JpaAcctAccountRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface JpaAccountRecordsRepository extends CrudRepository<JpaAcctAccountRecord, Long> {

    Page<AcctAccountRecord> findAllByAccount(AcctAccount account, Pageable page);

    Page<AcctAccountRecord> findAllByAccountAndAccountRecordTextLike(
        AcctAccount account,
        String textPattern,
        Pageable page
    );


    /**
     * Done using a JPA query since Spring apparently doesn't have a way of summing. See the
     * <a href="https://docs.spring.io/spring-data/jpa/reference/repositories/query-keywords-reference.html">Spring documentation</a>
     */
    @Query(value =
        "select sum(ar.accountRecordValue) " +
            "from JpaAcctAccountRecord ar " +
            "where ar.account = :account"
    )
    Double sumAccountRecordValueByAccount(@Param(value = "account") JpaAcctAccount account);
}
