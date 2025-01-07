package com.desolatetimelines.acct.workspace.util;

import com.desolatetimelines.acct.workspace.model.*;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Utility methods for working with the ACCT Workspace JPA data model
 */
public abstract class AcctWorkspaceRepoSpringDataUtils {

    public static JpaAcctWorkspace doWithJpaAcctWorkspaceReturning(
        AcctWorkspace acctWorkspace,
        Function<JpaAcctWorkspace, JpaAcctWorkspace> todo
    ) {
        if (acctWorkspace instanceof JpaAcctWorkspace jpaAcctWorkspace) {
            return todo.apply(jpaAcctWorkspace);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctWorkspace.class.getName() +
                " is not of type " + JpaAcctWorkspace.class.getCanonicalName()
        );
    }

    public static void doWithJpaAcctWorkspace(
        AcctWorkspace acctWorkspace,
        Consumer<JpaAcctWorkspace> todo
    ) {
        if (acctWorkspace instanceof JpaAcctWorkspace jpaAcctWorkspace) {
            todo.accept(jpaAcctWorkspace);
            return;
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctWorkspace.class.getName() +
                " is not of type " + JpaAcctWorkspace.class.getCanonicalName()
        );
    }

    public static JpaAcctAccount doWithJpaAcctAccountReturning(
        AcctAccount acctAccount,
        Function<JpaAcctAccount, JpaAcctAccount> todo
    ) {
        if (acctAccount instanceof JpaAcctAccount jpaAcctAccount) {
            return todo.apply(jpaAcctAccount);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctAccount.class.getName() +
                " is not of type " + JpaAcctAccount.class.getCanonicalName()
        );
    }

    public static void doWithJpaAcctAccount(
        AcctAccount acctAccount,
        Consumer<JpaAcctAccount> todo
    ) {
        if (acctAccount instanceof JpaAcctAccount jpaAcctAccount) {
            todo.accept(jpaAcctAccount);
            return;
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctAccount.class.getName() +
                " is not of type " + JpaAcctAccount.class.getCanonicalName()
        );
    }

    public static JpaAcctAccountRecord doWithJpaAcctAccountRecordReturning(
        AcctAccountRecord acctAccountRecord,
        Function<JpaAcctAccountRecord, JpaAcctAccountRecord> todo
    ) {
        if (acctAccountRecord instanceof JpaAcctAccountRecord jpaAcctAccountRecord) {
            return todo.apply(jpaAcctAccountRecord);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctAccountRecord.class.getName() +
                " is not of type " + JpaAcctAccountRecord.class.getCanonicalName()
        );
    }

    public static JpaAcctCurrencyExchange doWithJpaAcctCurrencyExchangeReturning(
        AcctCurrencyExchange acctCurrencyExchange,
        Function<JpaAcctCurrencyExchange, JpaAcctCurrencyExchange> todo
    ) {
        if (acctCurrencyExchange instanceof JpaAcctCurrencyExchange jpaAcctCurrencyExchange) {
            return todo.apply(jpaAcctCurrencyExchange);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctCurrencyExchange.class.getName() +
                " is not of type " + JpaAcctCurrencyExchange.class.getCanonicalName()
        );
    }

    public static JpaAcctDeposit doWithJpaAcctDepositReturning(
        AcctDeposit acctDeposit,
        Function<JpaAcctDeposit, JpaAcctDeposit> todo
    ) {
        if (acctDeposit instanceof JpaAcctDeposit jpaAcctDeposit) {
            return todo.apply(jpaAcctDeposit);
        }

        throw new IllegalArgumentException(
            "The referenced " + AcctDeposit.class.getName() +
                " is not of type " + JpaAcctDeposit.class.getCanonicalName()
        );
    }

}
