package com.desolatetimelines.acct.usermanagement.ws.mapper;

import com.desolatetimelines.acct.usermanagement.model.Page;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctPageInfo;

import static java.lang.Math.max;

/**
 * Provides mapping methods to and from the {@link AcctPageInfo} type
 */
public class AcctPageInfoMapper {

    public static AcctPageInfo fromPage(Page<?> page, int pageNumber) {
        return
            new AcctPageInfo(
                page.numElements(),
                page.maxElements(),
                (int) (page.maxElements() / (max(page.numElements(), 1))),
                pageNumber
            );
    }

}
