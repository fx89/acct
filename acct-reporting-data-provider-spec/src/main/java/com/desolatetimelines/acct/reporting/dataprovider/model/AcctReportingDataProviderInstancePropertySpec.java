package com.desolatetimelines.acct.reporting.dataprovider.model;

import java.util.Objects;

/**
 * Allows a {@link com.desolatetimelines.acct.reporting.dataprovider.service.AcctReportingDataProvider data provider}
 * to specify the build-time properties that it requires to function
 *
 * @param name     The name of the parameter. Must be unique within the context of the data provider.
 * @param dataType The data type of the parameter. Allows validation.
 */
public record AcctReportingDataProviderInstancePropertySpec(
    String name,
    AcctReportingDataProviderReportParameterType dataType
) {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AcctReportingDataProviderInstancePropertySpec that = (AcctReportingDataProviderInstancePropertySpec) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
