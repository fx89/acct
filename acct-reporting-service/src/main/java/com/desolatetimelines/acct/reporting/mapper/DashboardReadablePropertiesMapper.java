package com.desolatetimelines.acct.reporting.mapper;

import com.desolatetimelines.acct.reporting.model.AcctDashboard;
import com.desolatetimelines.acct.reporting.model.DashboardReadableProperties;

/**
 * Provides mapper methods for the {@link DashboardReadableProperties} class
 */
public abstract class DashboardReadablePropertiesMapper {

    public static DashboardReadableProperties fromAcctDashboard(AcctDashboard acctDashboard) {
        return
            DashboardReadableProperties.builder()
                .withDashboardUUID(acctDashboard.getDashboardUUID())
                .withDashboardName(acctDashboard.getDashboardName())
                .withDashboardDescription(acctDashboard.getDashboardDescription())
                .withDashboardIconUUID(acctDashboard.getDashboardIconUUID())
                .build();
    }

}
