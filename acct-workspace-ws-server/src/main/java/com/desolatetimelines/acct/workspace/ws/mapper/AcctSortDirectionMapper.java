package com.desolatetimelines.acct.workspace.ws.mapper;

import com.desolatetimelines.acct.common.ws.model.AcctSortDirection;
import com.desolatetimelines.acct.workspace.model.SortDirection;

public abstract class AcctSortDirectionMapper {

    public static SortDirection toSortDirection(AcctSortDirection acctSortDirection) {
        if (acctSortDirection == AcctSortDirection.ASCENDING) {
            return SortDirection.ASCENDING;
        }

        if (acctSortDirection == AcctSortDirection.DESCENDING) {
            return SortDirection.DESCENDING;
        }

        throw new IllegalStateException("Unhandled sort direction");
    }

}
