package com.desolatetimelines.acct.catalog.ws.mapper;

import com.desolatetimelines.acct.catalog.model.AcctIcon;
import com.desolatetimelines.acct.catalog.ws.model.IconProperties;
import com.desolatetimelines.acct.common.model.Page;
import com.desolatetimelines.acct.common.ws.model.AcctPage;
import com.desolatetimelines.acct.common.ws.model.AcctPageInfo;

import java.util.Collection;

/**
 * Provides mapper methods for the {@link IconProperties} type
 */
public abstract class IconPropertiesMapper {

    public static IconProperties fromAcctIcon(AcctIcon acctIcon) {
        return
            IconProperties.builder()
                .withIconUUID(acctIcon.getIconUUID())
                .withIconName(acctIcon.getIconName())
                .withMimeType(acctIcon.getMimeType())
                .build();
    }

    public static Collection<IconProperties> fromCollectionOfAcctIcons(Collection<AcctIcon> acctIcons) {
        return
            acctIcons.stream()
                .map(IconPropertiesMapper::fromAcctIcon)
                .toList();
    }

    public static AcctPage<IconProperties> fromPageOfAcctIcons(Page<AcctIcon> page, int pageNumber, int pageSize) {
        return
            new AcctPage<>(
                fromCollectionOfAcctIcons(page.data()),
                new AcctPageInfo(
                    page.numElements(),
                    page.maxElements(),
                    pageSize == 0 ? 0 : (int) Math.ceil((double) page.maxElements() / (double) pageSize),
                    pageNumber
                )
            );
    }

}
