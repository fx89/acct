package com.desolatetimelines.acct.reporting.dataprovider.model;

import java.util.Map;
import java.util.Objects;

/**
 * Allows a {@link com.desolatetimelines.acct.reporting.dataprovider.service.AcctReportingDataProvider data provider}
 * to specify the runtime parameters that it uses when
 * {@link com.desolatetimelines.acct.reporting.dataprovider.service.AcctReportingDataProvider#provideData(Map) compiling}
 * the report data
 *
 * @param name
 * @param mandatory
 */
public record AcctReportingDataProviderReportParameterSpec(
    String name,
    AcctReportingDataProviderReportParameterType dataType,
    boolean mandatory
) {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AcctReportingDataProviderReportParameterSpec that = (AcctReportingDataProviderReportParameterSpec) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
