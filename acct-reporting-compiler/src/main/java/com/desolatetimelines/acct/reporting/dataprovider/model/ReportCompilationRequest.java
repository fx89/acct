package com.desolatetimelines.acct.reporting.dataprovider.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.desolatetimelines.acct.common.utils.ValidationUtils.throwIfNull;
import static com.desolatetimelines.acct.common.utils.ValidationUtils.throwIfNullOrEmpty;
import static java.util.stream.Collectors.groupingBy;

/**
 * Tells the
 * {@link com.desolatetimelines.acct.reporting.dataprovider.service.AcctReportingDataCompiler report compiler}
 * how to compile a report. Contains configuration for all the various stages of report compilation: <ul>
 * <li><b>Data provisioning stage</b> - one or more data provider instances are created and used for fetching
 * the report data</li>
 * <li><b>Data storing stage</b><br />
 * Creates an ephemeral in-memory database and stores each of the data sets produced by the data
 * provisioning stage into its own table. The names of the tables are given by the names of the
 * data provider instances specified in this request.
 * </li>
 * <li><b>Data crunching stage</b><br />
 * Runs the given {@code reportSqlStatement} on the ephemeral in-memory database created at the data
 * storing stage and produces the final report.
 * </li>
 * </ul>
 *
 * @param dataProviderInstances Defines the instances of data providers to be used in the data provisioning
 *                              stage and their init-time properties.
 * @param reportParameters      Defines the runtime parameters to be used in the data provisioning stage.
 * @param reportSqlStatement    The SQL statement used in the data crunching stage. The result set produced
 *                              based on this SQL statement is the source of the data set produced by the
 *                              report compiler.
 */
public record ReportCompilationRequest(

    Set<DataProviderInstanceSpecification> dataProviderInstances,

    Map<String, String> reportParameters,

    String reportSqlStatement

) {

    public static void validate(ReportCompilationRequest request) {
        throwIfNull(request, () -> new IllegalArgumentException("Null request reference provided"));
        throwIfNullOrEmpty(request.dataProviderInstances(), () -> new IllegalArgumentException("Data providers not specified"));
        throwIfNull(request.reportSqlStatement(), () -> new IllegalArgumentException("Missing the report SQL statement"));

        request.dataProviderInstances().forEach(DataProviderInstanceSpecification::validate);
        validateUniqueInstanceNames(request.dataProviderInstances());
    }

    private static void validateUniqueInstanceNames(Set<DataProviderInstanceSpecification> instances) {
        final String nonUniqueInstanceNames =
            instances
                .stream()
                .collect(groupingBy(DataProviderInstanceSpecification::instanceName))
                .entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.joining(", "));

        if (!nonUniqueInstanceNames.isEmpty()) {
            throw new IllegalArgumentException(
                "The following data provider instance names are not unique: " + nonUniqueInstanceNames
            );
        }
    }

    /**
     * Returns a new builder for the {@link ReportCompilationRequest} type
     */
    public static ReportCompilationRequestBuilder builder() {
        return new ReportCompilationRequestBuilder();
    }

    /**
     * Builder for the {@link ReportCompilationRequest} type
     */
    public static class ReportCompilationRequestBuilder {
        Set<DataProviderInstanceSpecification> instances = new HashSet<>();

        private final Map<String, String> reportParameters = new HashMap<>();

        private String reportSqlStatement;

        /**
         * Adds an {@link DataProviderInstanceSpecification data provider instance specification}
         * to the builder. All data provider instance specifications added here will be part of
         * the constructed {@link ReportCompilationRequest report compilation request}. The
         * {@link DataProviderInstanceSpecification#instanceName() instance names} must be unique.
         */
        public ReportCompilationRequestBuilder withInstance(DataProviderInstanceSpecification instance) {
            DataProviderInstanceSpecification.validate(instance);
            instances.add(instance);
            return this;
        }

        /**
         * Adds one or more {@link DataProviderInstanceSpecification data provider instance specifications}
         * to the builder. All data provider instance specifications added here will be part of
         * the constructed {@link ReportCompilationRequest report compilation request}. The
         * {@link DataProviderInstanceSpecification#instanceName() instance names} must be unique.
         */
        public ReportCompilationRequestBuilder withInstances(Set<DataProviderInstanceSpecification> instances) {
            throwIfNull(instances, () -> new IllegalArgumentException("Null dataProviderInstances set reference"));
            instances.forEach(this::withInstance);
            return this;
        }

        /**
         * Adds an entry to the {@link ReportCompilationRequest#reportParameters() runtime parameters map}
         * of the {@link ReportCompilationRequest report compilation request} to be constructed.
         *
         * @param key   the entry key
         * @param value the entry value
         */
        public ReportCompilationRequestBuilder withReportParameter(String key, String value) {
            reportParameters.put(key, value);
            return this;
        }

        /**
         * Merges the referenced map into the
         * {@link ReportCompilationRequest#reportParameters() runtime parameters map} of the
         * {@link ReportCompilationRequest report compilation request} to be constructed.
         */
        public ReportCompilationRequestBuilder withReportParameters(Map<String, String> parameters) {
            reportParameters.putAll(parameters);
            return this;
        }

        /**
         * Sets the report SQL statement
         */
        public ReportCompilationRequestBuilder withReportSqlStatement(String reportSqlStatement) {
            this.reportSqlStatement = reportSqlStatement;
            return this;
        }

        /**
         * Constructs the {@link ReportCompilationRequest report compilation request} and
         * validates its contents.
         *
         * @return a reference to the constructed object
         */
        public ReportCompilationRequest build() {
            // Create the request
            final ReportCompilationRequest builtRequest =
                new ReportCompilationRequest(
                    instances,
                    reportParameters,
                    reportSqlStatement
                );

            // Validate the request - validation method raises exceptions
            ReportCompilationRequest.validate(builtRequest);

            // Return a reference to the request
            return builtRequest;
        }

    }

}
