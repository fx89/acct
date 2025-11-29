package com.desolatetimelines.acct.reporting.dataprovider.service;

import com.desolatetimelines.acct.reporting.dataprovider.exception.AcctReportingDataCompilerException;
import com.desolatetimelines.acct.reporting.dataprovider.exception.AcctReportingDataProviderInitializationException;
import com.desolatetimelines.acct.reporting.dataprovider.model.*;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

import static com.desolatetimelines.acct.reporting.dataprovider.mapper.AcctReportingDataProviderDataSetColumnDataTypeMapper.toSQLiteDataTypeName;
import static java.util.stream.Collectors.*;

/**
 * The report data compiler is responsible for the end to end report data generation process.
 * The process consists in the following stages: <ul>
 * <li><b>The provisioning stage</b><br />
 * In this stage, {@link AcctReportingDataProvider data provider} instances are acquired and
 * initialized with the properties defined for the report being compiled. Each instance is then
 * requested to provision the data based on the report's runtime parameters. </li>
 * </li>
 * <li><b>The data storing stage</b><br />
 * In this stage, all the data sets coming out of the provisioning stage are put into a new
 * ephemeral instance of an in-memory database. Each data set is uploaded into its own separate
 * table. The name of the data provider instance is used for the table name.
 * </li>
 * <li><b>The data crunching stage</b><br />
 * In this stage, the user-provisioned query is executed on the ephemeral in-memory database
 * created at the data storing stage. The query can join, aggregate, filter and sort and do
 * whatever the user needs to do. The query results are returned as a new
 * {@link AcctReportingDataProviderDataSet}.
 * </li>
 * </ul>
 */
@Service
public class AcctReportingDataCompiler {

    private static final DateFormat TIMESTAMP_FORMAT =
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * All the service providers from all the data provider modules are injected into this service. Each service
     * provider is asked to provide a set of {@link AcctReportingDataProviderId data provider IDs} for the supported
     * data providers. Then, the service providers are grouped by the {@link AcctReportingDataProviderId#uuid() UUIDs}
     * of the data providers and the result is stored in this map.
     */
    private final Map<UUID, AbstractAcctReportingDataProviderServiceProvider> serviceProvidersByDataProviderUUID;


    public AcctReportingDataCompiler(
        Set<AbstractAcctReportingDataProviderServiceProvider> dataProviderServiceProviders
    ) {
        this.serviceProvidersByDataProviderUUID = groupServiceProvidersByDataProviderUUID(dataProviderServiceProviders);
    }

    private static Map<UUID, AbstractAcctReportingDataProviderServiceProvider> groupServiceProvidersByDataProviderUUID(
        Set<AbstractAcctReportingDataProviderServiceProvider> dataProviderServiceProviders
    ) {
        // Check that the data providers are uniquely and distinctly identified across all service providers

        // See if there are any UUIDs identifying two or more data providers
        final Map<UUID, List<AcctReportingDataProviderId>> duplicateDataProviderIdsByUUID =
            dataProviderServiceProviders.stream()
                .map(AbstractAcctReportingDataProviderServiceProvider::getSupportedDataProviderIds)
                .flatMap(Set::stream)
                .collect(groupingBy(AcctReportingDataProviderId::uuid))
                .entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));

        // If there are any UUDs identifying two or more data providers, then report them
        if (!duplicateDataProviderIdsByUUID.isEmpty()) {
            final String errorMessage =
                "Two or more reporting data providers are identified by the same UUID. " +
                    duplicateDataProviderIdsByUUID.entrySet().stream()
                        .map(entry ->
                            "UUID " + entry.getKey() + " identifies the following data providers: " +
                                entry.getValue().stream()
                                    .map(AcctReportingDataProviderId::humanReadableName)
                                    .collect(joining(",")
                                    )
                        )
                        .collect(joining(". "));

            throw new IllegalStateException(errorMessage);
        }

        // Create a map in which to store the service providers by the UUIDs of the data providers that they provide
        final Map<UUID, AbstractAcctReportingDataProviderServiceProvider> ret = new HashMap<>();

        // Populate the map
        dataProviderServiceProviders.forEach(serviceProvider ->
            serviceProvider.getSupportedDataProviderIds()
                .forEach(dataProviderId ->
                    ret.put(dataProviderId.uuid(), serviceProvider)
                )
        );

        // Return a reference to the populated map
        return ret;
    }

    /**
     * Returns a set of {@link AcctReportingDataProviderId data provider IDs} from all the registered
     * {@link AbstractAcctReportingDataProviderServiceProvider service providers}.
     */
    public Set<AcctReportingDataProviderId> getDataProviderIds() {
        return
            serviceProvidersByDataProviderUUID.values().stream()
                .map(AbstractAcctReportingDataProviderServiceProvider::getSupportedDataProviderIds)
                .flatMap(Set::stream)
                .collect(toSet());
    }

    public Set<AcctReportingDataProviderReportParameterSpec> getReportParameters(
        Set<DataProviderInstanceSpecification> dataProviderInstances
    ) {
        // Get the set of data provider UUIDs
        final Set<UUID> dataProviderUUIDs =
            dataProviderInstances.stream()
                .map(DataProviderInstanceSpecification::dataProviderUUID)
                .collect(toSet());

        // Get the report parameters specified by the data providers
        final Stream<AcctReportingDataProviderReportParameterSpec> baseParameters =
            serviceProvidersByDataProviderUUID.values().stream()
                .flatMap(sp -> sp.getSupportedDataProviderIds().stream())
                .filter(id -> dataProviderUUIDs.contains(id.uuid()))
                .flatMap(id -> id.parameters().stream());

        // Get the instance-specific report parameters
        final Stream<AcctReportingDataProviderReportParameterSpec> instanceSpecificReportParameters =
            dataProviderInstances.stream()
                .map(DataProviderInstanceSpecification::additionalRuntimeParameters)
                .flatMap(Set::stream);

        // Combine the two data sets and return a reference to the combined data set
        return Stream.concat(baseParameters, instanceSpecificReportParameters).collect(toSet());
    }

    public AcctReportingDataProviderDataSet compileReport(ReportCompilationRequest request) {
        // Validate the request
        validateRequest(request);

        // Execute stage 1 - data provisioning
        // Retrieves data sets resulted from data provider instances mapped by instance name
        final Map<String, AcctReportingDataProviderDataSet> dataProviderInstanceDataSets =
            provisioningStage(request);

        // Crunch the data in a new SQLite database
        try (final AcctSQLiteJDBCConnection connection = new AcctSQLiteJDBCConnection()) {

            // Stage 2 - data storing
            dataProviderInstanceDataSets.forEach((dataSetName, dataSet) ->
                addDataSetIntoSQLite(connection, dataSetName, dataSet)
            );

            // Stage 3 - data crunching
            return connection.executeQuery(request.reportSqlStatement());
        }
        // If any exception occurs, then report it
        catch (Exception e) {
            throw new AcctReportingDataCompilerException("Unable to compile report.", e);
        }
    }

    private void validateRequest(ReportCompilationRequest request) {
        validateRequestMandatoryInstanceProperties(request);
        validateRequestInstancePropertyDataTypes(request);
        validateRequestMandatoryRuntimeParameters(request);
        validateRequestRuntimeParameterDataTypes(request);
    }

    private AbstractAcctReportingDataProviderServiceProvider findServiceProviderByDataProviderUUID(
        UUID dataProviderUUID
    ) {
        return
            Optional.ofNullable(serviceProvidersByDataProviderUUID.get(dataProviderUUID))
                .orElseThrow(() -> new AcctReportingDataCompilerException(
                    "Data provider with UUID = " + dataProviderUUID + " is not supported"
                ));
    }

    private void validateRequestMandatoryInstanceProperties(ReportCompilationRequest request) {
        // For each instance
        request.dataProviderInstances().forEach(instance -> {
            // Get the referred instance or throw an exception
            final AbstractAcctReportingDataProviderServiceProvider serviceProvider =
                findServiceProviderByDataProviderUUID(instance.dataProviderUUID());

            // Get a set of required instance property names from the service provider
            final Set<String> requiredInstancePropertyNames =
                serviceProvider.findSupportedDataProviderByDataProviderUUID(instance.dataProviderUUID())
                    .instanceProperties()
                    .stream()
                    .map(AcctReportingDataProviderInstancePropertySpec::name)
                    .collect(toSet());

            // Find the names of any instance properties that have not been provided with the request
            final Set<String> missingInstanceProperties =
                requiredInstancePropertyNames
                    .stream()
                    .filter(k -> !instance.dataProviderInstanceProperties().containsKey(k))
                    .collect(toSet());

            // If there are any instance properties that have not been provided with the request,
            // then throw an exception
            if (!missingInstanceProperties.isEmpty()) {
                throw new AcctReportingDataCompilerException(
                    "The following instance properties have not been provided for data provider instance " +
                        instance.instanceName() + ": " +
                        String.join(",", missingInstanceProperties)
                );
            }
        });
    }

    private void validateRequestInstancePropertyDataTypes(ReportCompilationRequest request) {
        // For each instance
        request.dataProviderInstances().forEach(instance -> {
            // Get the referred instance or throw an exception
            final AbstractAcctReportingDataProviderServiceProvider serviceProvider =
                findServiceProviderByDataProviderUUID(instance.dataProviderUUID());

            // Get the data provider configuration
            final AcctReportingDataProviderId dataProviderId =
                serviceProvider.findSupportedDataProviderByDataProviderUUID(instance.dataProviderUUID());

            // For each property
            instance.dataProviderInstanceProperties().forEach((k, v) -> {
                // Find the property configuration
                final AcctReportingDataProviderInstancePropertySpec instancePropertySpec =
                    dataProviderId.instanceProperties().stream()
                        .filter(ip -> Objects.equals(ip.name(), k))
                        .findFirst().orElseThrow();

                // Verify that the value set for the instance property can be converted to the data type
                // defined by the instance property specification
                try {
                    verifyPropertyDataType(instancePropertySpec.dataType(), v);
                } catch (Exception e) {
                    throw new AcctReportingDataCompilerException(
                        "The value of the instance property named " + instancePropertySpec.name() +
                            "cannot be converted to the " + instancePropertySpec.dataType() + " data type",
                        e
                    );
                }
            });
        });
    }

    private void validateRequestMandatoryRuntimeParameters(ReportCompilationRequest request) {
        // Get the mandatory report parameter names from all data provider instances
        final Set<String> mandatoryReportParameterNames =
            request.dataProviderInstances().stream()
                .map(instance -> {
                    // Get the referred instance or throw an exception
                    final AbstractAcctReportingDataProviderServiceProvider serviceProvider =
                        findServiceProviderByDataProviderUUID(instance.dataProviderUUID());

                    // Get a set of required report parameter names from the service provider
                    return
                        serviceProvider.findSupportedDataProviderByDataProviderUUID(instance.dataProviderUUID())
                            .parameters()
                            .stream()
                            .filter(AcctReportingDataProviderReportParameterSpec::mandatory)
                            .map(AcctReportingDataProviderReportParameterSpec::name)
                            .collect(toSet());
                })
                .flatMap(Set::stream)
                .collect(toSet());

        // Find the names of any report parameters that are mandatory but have not been provided
        // with the request
        final Set<String> missingReportParameterNames =
            mandatoryReportParameterNames
                .stream()
                .filter(k -> !request.reportParameters().containsKey(k))
                .collect(toSet());

        // If there are any report parameters that are mandatory but have not been provided with
        // the request, then throw an exception
        if (!missingReportParameterNames.isEmpty()) {
            throw new AcctReportingDataCompilerException(
                "The following mandatory report parameters have not been provided: " +
                    String.join(",", missingReportParameterNames)
            );
        }
    }

    private void validateRequestRuntimeParameterDataTypes(ReportCompilationRequest request) {
        // For each instance
        request.dataProviderInstances().forEach(instance -> {
            // Get the referred instance or throw an exception
            final AbstractAcctReportingDataProviderServiceProvider serviceProvider =
                findServiceProviderByDataProviderUUID(instance.dataProviderUUID());

            // For each report parameter specified by the data provider
            serviceProvider.findSupportedDataProviderByDataProviderUUID(instance.dataProviderUUID())
                .parameters().forEach(specifiedParameter -> {
                    // Get the parameter value from the request
                    final String providedParameterValue =
                        request.reportParameters().get(specifiedParameter.name());

                    // Verify that the value set for the instance property can be converted to the data type
                    // defined by the instance property specification
                    try {
                        verifyPropertyDataType(specifiedParameter.dataType(), providedParameterValue);
                    } catch (Exception e) {
                        throw new AcctReportingDataCompilerException(
                            "The value of the runtime property named " + specifiedParameter.name() +
                                "cannot be converted to the " + specifiedParameter.dataType() + " data type",
                            e
                        );
                    }
                });
        });
    }

    private void verifyPropertyDataType(
        AcctReportingDataProviderReportParameterType dataType,
        String value
    ) throws Exception {
        switch (dataType) {
            case BOOLEAN -> {
                if (!Set.of("true", "false").contains(value)) throw new RuntimeException("Not a boolean");
            }
            case DATETIME -> TIMESTAMP_FORMAT.parse(value);
            case NUMERIC -> Double.parseDouble(value);
        }
    }

    private Map<String, AcctReportingDataProviderDataSet> provisioningStage(ReportCompilationRequest request) {
        // Create and initialize data provider dataProviderInstances for the data providers referenced in the request
        final Map<String, AcctReportingDataProvider> dataProviderInstancesByInstanceName =
            initializeDataProviderInstancesByInstanceNameMap(request);

        // Fetch the data from the data providers (in parallel since data providers, by contract, do not
        // interfere wirth each other's operation)
        return
            dataProviderInstancesByInstanceName.entrySet().parallelStream()
                .collect(
                    toMap(
                        Map.Entry::getKey,
                        entry ->
                            Optional
                                .ofNullable(entry.getValue().provideData(request.reportParameters()))
                                .orElseGet(AcctReportingDataProviderDataSet::emptyDataSet)
                    )
                );
    }

    private Map<String, AcctReportingDataProvider> initializeDataProviderInstancesByInstanceNameMap(
        ReportCompilationRequest request
    ) {
        // Prepare a map to hold the initialized data provider dataProviderInstances by UUID
        final Map<String, AcctReportingDataProvider> instancesByDataProviderUUID
            = new HashMap<>(request.dataProviderInstances().size());

        // For each data provider UUID in the request...
        request.dataProviderInstances().forEach(instance -> {
            // Find the service provider or throw an exception
            final AbstractAcctReportingDataProviderServiceProvider serviceProvider =
                findServiceProviderByDataProviderUUID(instance.dataProviderUUID());

            // Create an instance
            AcctReportingDataProvider dataProviderInstance;
            try {
                dataProviderInstance =
                    serviceProvider.provideByUUIDAndInstanceProperties(
                        instance.dataProviderUUID(),
                        instance.dataProviderInstanceProperties()
                    );
            } catch (AcctReportingDataProviderInitializationException e) {
                throw new AcctReportingDataCompilerException(
                    "Unable to initialize the data provider instance named " + instance.instanceName() + ": " + e.getMessage(),
                    e
                );
            }

            // Add it to the map
            instancesByDataProviderUUID.put(instance.instanceName(), dataProviderInstance);
        });

        // Return a reference to the populated map
        return instancesByDataProviderUUID;
    }

    private static void addDataSetIntoSQLite(
        AcctSQLiteJDBCConnection connection,
        String dataSetName,
        AcctReportingDataProviderDataSet dataSet
    ) {
        // Get the column names
        final LinkedHashSet<AcctReportingDataProviderDataSetColumn> columns = dataSet.columns();

        // Generate and run the "create table" statement
        connection.executeUpdate("CREATE TABLE \"" + dataSetName + "\" (" +
            columns.stream()
                .map(column -> "\"" + column.name() + "\" " + toSQLiteDataTypeName(column.dataType()))
                .collect(joining(",")) +
            ")"
        );

        // Generate and run the "insert into" statement
        connection.executeUpdate("INSERT INTO \"" + dataSetName + "\"(" +
            columns.stream()
                .map(column -> "\"" + column.name() + "\"")
                .collect(joining(",")) +
            ") VALUES " +
            Arrays.stream(dataSet.data())
                .map(row -> "('" +
                    Arrays.stream(row)
                        .map(value -> value.replace("'", "`"))
                        .collect(joining("','")) +
                    "')"
                )
                .collect(joining(", "))
        );
    }

}
