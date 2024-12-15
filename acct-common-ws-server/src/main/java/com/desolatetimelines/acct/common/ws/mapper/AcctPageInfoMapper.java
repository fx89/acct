package com.desolatetimelines.acct.common.ws.mapper;

import com.desolatetimelines.acct.common.model.Page;
import com.desolatetimelines.acct.common.ws.model.AcctPageInfo;

/**
 * Provides mapping methods to and from the {@link AcctPageInfo} type
 */
public class AcctPageInfoMapper {

    public static AcctPageInfo fromPage(Page<?> page, int pageNumber) {
        return
            new AcctPageInfo(
                page.numElements(),
                page.maxElements(),
                (int) (page.maxElements() / (Math.max(page.numElements(), 1))),
                pageNumber
            );
    }

}
