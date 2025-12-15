package com.desolatetimelines.acct.reporting.ws.model;

import java.util.Objects;

/**
 * Defines a key/value pair that works as a report runtime parameter.
 *
 * @param parameterName  The key.
 * @param parameterValue The value.
 */
public record ReportParameter(
    String parameterName,
    String parameterValue
) {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ReportParameter that = (ReportParameter) o;
        return Objects.equals(parameterName, that.parameterName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(parameterName);
    }
}
