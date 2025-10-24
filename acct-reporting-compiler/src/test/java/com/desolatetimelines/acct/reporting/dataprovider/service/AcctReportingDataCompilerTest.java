package com.desolatetimelines.acct.reporting.dataprovider.service;

import com.desolatetimelines.acct.reporting.dataprovider.exception.AcctReportingDataCompilerException;
import com.desolatetimelines.acct.reporting.dataprovider.exception.AcctReportingDataProviderInitializationException;
import com.desolatetimelines.acct.reporting.dataprovider.exception.AcctReportingDataProviderRuntimeException;
import com.desolatetimelines.acct.reporting.dataprovider.exception.AcctReportingUnsupportedDataProviderException;
import com.desolatetimelines.acct.reporting.dataprovider.model.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderReportParameterType.*;
import static org.junit.jupiter.api.Assertions.*;

public class AcctReportingDataCompilerTest {

    /**
     * This UUID identifies the data provider that is used for testing the
     * correct provisioning of instance properties
     */
    private static final UUID INSTANCE_PROPERTIES_TESTING_DATA_PROVIDER_UUID = UUID.randomUUID();

    /**
     * This UUID identifies the data provider that is used for testing the
     * correct provisioning of runtime parameters
     */
    private static final UUID RUNTIME_PARAMETERS_TESTING_DATA_PROVIDER_UUID = UUID.randomUUID();

    /**
     * This UUID identifies the data provider that is used for testing the
     * correct provisioning of joins
     */
    private static final UUID JOINS_TESTING_DATA_PROVIDER_UUID = UUID.randomUUID();

    /**
     * The name of the one of the data provider instances used by this test
     */
    private static final String DATA_PROVIDER_INSTANCE_A_NAME = "instance_a";

    /**
     * The name of the one of the data provider instances used by this test
     */
    private static final String DATA_PROVIDER_INSTANCE_B_NAME = "instance_b";

    /**
     * The name of the one of the data provider instances used by this test
     */
    private static final String DATA_PROVIDER_INSTANCE_C_NAME = "instance_c";

    /**
     * The name of one data provider instance property used by this test
     */
    public static final String INSTANCE_PROPERTY_1_NAME = "prop1";

    /**
     * The name of one data provider instance property used by this test
     */
    public static final String INSTANCE_PROPERTY_2_NAME = "prop2";

    /**
     * The name of the mandatory runtime parameter
     */
    private static final String MANDATORY_RUNTIME_PARAMETER = "param1";

    /**
     * The name of the optional runtime parameter
     */
    private static final String OPTIONAL_RUNTIME_PARAMETER = "param2";

    /**
     * The name of the optional runtime parameter that lets the data provider know
     * which data set to return
     */
    private static final String OPTIONAL_DATA_SET_CHOICE_PARAMETER = "dataSetChoice";

    /**
     * The name of the column that is common to datasets A and B
     */
    private static final String COMMON_COLUMN_NAME_A_B = "col_a_b";

    private static final String DUMMY_SQL_STATEMENT = "SELECT 1 col_name";

    /**
     * The data compiler is instantiated with the test data provider instances service provider
     */
    private final AcctReportingDataCompiler dataCompiler =
        new AcctReportingDataCompiler(Set.of(new TestDataProviderServiceProvider()));

    /**
     * All instance properties are mandatory. If any instance property is missing, then the
     * report compiler should throw an exception
     */
    @Test
    void testAcctReportingDataCompiler_enforcesProvisioningOfAllInstanceProperties() {
        // Create a new report compilation request that does not have all instance properties specified
        final ReportCompilationRequest request =
            ReportCompilationRequest.builder()
                .withInstance(
                    DataProviderInstanceSpecification.builder()
                        .withDataProviderUUID(INSTANCE_PROPERTIES_TESTING_DATA_PROVIDER_UUID)
                        .withInstanceName(DATA_PROVIDER_INSTANCE_A_NAME)
                        .withDataProviderInstanceProperty(INSTANCE_PROPERTY_1_NAME, "true")
                        .build()
                )
                .withReportSqlStatement(DUMMY_SQL_STATEMENT)
                .build();

        // Expect that the report compiler throws an exception due to missing instance property values
        // in the report compilation request
        assertThrows(
            AcctReportingDataCompilerException.class,
            () -> dataCompiler.compileReport(request)
        );
    }

    @Test
    void testAcctReportingDataCompiler_enforcesCorrectProvisioningOfInstancePropertyDataTypes() {
        // Create a new report compilation request that has all the instance properties, but does not
        // use values of the correct data type
        final ReportCompilationRequest request =
            ReportCompilationRequest.builder()
                .withInstance(
                    DataProviderInstanceSpecification.builder()
                        .withDataProviderUUID(INSTANCE_PROPERTIES_TESTING_DATA_PROVIDER_UUID)
                        .withInstanceName(DATA_PROVIDER_INSTANCE_A_NAME)
                        .withDataProviderInstanceProperty(INSTANCE_PROPERTY_1_NAME, "it's true")
                        .withDataProviderInstanceProperty(INSTANCE_PROPERTY_2_NAME, "happy string")
                        .build()
                )
                .withReportSqlStatement(DUMMY_SQL_STATEMENT)
                .build();

        // Expect that the report compiler throws an exception because the instance property value
        // cannot be converted to the expected data type
        assertThrows(
            AcctReportingDataCompilerException.class,
            () -> dataCompiler.compileReport(request)
        );
    }

    @Test
    void testAcctReportingDataCompiler_enforcesProvisioningOfAllMandatoryRuntimeParameters() {
        // Create a new report compilation request that does not specify the mandatory runtime
        // property
        final ReportCompilationRequest request =
            ReportCompilationRequest.builder()
                .withInstance(
                    DataProviderInstanceSpecification.builder()
                        .withDataProviderUUID(RUNTIME_PARAMETERS_TESTING_DATA_PROVIDER_UUID)
                        .withInstanceName(DATA_PROVIDER_INSTANCE_A_NAME)
                        .build()
                )
                .withReportParameter(OPTIONAL_RUNTIME_PARAMETER, "test")
                .withReportSqlStatement(DUMMY_SQL_STATEMENT)
                .build();

        // Attempt to compile the report without specifying the mandatory runtime property and
        // expect an exception to be thrown
        assertThrows(
            AcctReportingDataCompilerException.class,
            () -> dataCompiler.compileReport(request)
        );

        // Add the mandatory report parameter to the request
        request.reportParameters().put(MANDATORY_RUNTIME_PARAMETER, "13");

        // After adding the mandatory report parameter, expect that the report compiler does not
        // throw any exception
        assertDoesNotThrow(
            () -> dataCompiler.compileReport(request)
        );
    }

    @Test
    void testAcctReportingDataCompiler_enforcesCorrectProvisioningOfRuntimeParameterDataTypes() {
        // Create a new report compilation request that does dot provide a value that can be
        // parsed into the required data type for one of the parameters
        final ReportCompilationRequest request =
            ReportCompilationRequest.builder()
                .withInstance(
                    DataProviderInstanceSpecification.builder()
                        .withDataProviderUUID(RUNTIME_PARAMETERS_TESTING_DATA_PROVIDER_UUID)
                        .withInstanceName(DATA_PROVIDER_INSTANCE_A_NAME)
                        .build()
                )
                .withReportParameter(MANDATORY_RUNTIME_PARAMETER, "thirteen")
                .withReportParameter(OPTIONAL_RUNTIME_PARAMETER, "test")
                .withReportSqlStatement(DUMMY_SQL_STATEMENT)
                .build();

        // Attempt to compile the report with the faulty request and expect an exception to be thrown
        assertThrows(
            AcctReportingDataCompilerException.class,
            () -> dataCompiler.compileReport(request)
        );
    }

    @Test
    void testAcctReportingDataCompiler_worksProperly() {
        // Create a request that involves a single data provider instance and no additional
        // operations, to see if the data from the data provider is properly passed through
        // the report compiler
        final ReportCompilationRequest request =
            ReportCompilationRequest.builder()
                .withInstance(
                    DataProviderInstanceSpecification.builder()
                        .withDataProviderUUID(JOINS_TESTING_DATA_PROVIDER_UUID)
                        .withInstanceName(DATA_PROVIDER_INSTANCE_A_NAME)
                        .withDataProviderInstanceProperty(OPTIONAL_DATA_SET_CHOICE_PARAMETER, DATA_PROVIDER_INSTANCE_A_NAME)
                        .build()
                )
                .withInstance(
                    DataProviderInstanceSpecification.builder()
                        .withDataProviderUUID(JOINS_TESTING_DATA_PROVIDER_UUID)
                        .withInstanceName(DATA_PROVIDER_INSTANCE_B_NAME)
                        .withDataProviderInstanceProperty(OPTIONAL_DATA_SET_CHOICE_PARAMETER, DATA_PROVIDER_INSTANCE_B_NAME)
                        .build()
                )
                .withReportSqlStatement(
                    "SELECT " +
                        DATA_PROVIDER_INSTANCE_A_NAME + ".colA dpA_colA, " +
                        DATA_PROVIDER_INSTANCE_A_NAME + ".colB dpA_colB, " +
                        DATA_PROVIDER_INSTANCE_A_NAME + ".colC dpA_colC, " +
                        DATA_PROVIDER_INSTANCE_B_NAME + ".colA dpB_colA, " +
                        DATA_PROVIDER_INSTANCE_B_NAME + ".colE dpB_colE, " +
                        DATA_PROVIDER_INSTANCE_B_NAME + ".colF dpB_colF " +
                        "FROM " +
                        DATA_PROVIDER_INSTANCE_A_NAME + ", " +
                        DATA_PROVIDER_INSTANCE_B_NAME + " " +
                        "WHERE " +
                        DATA_PROVIDER_INSTANCE_A_NAME + "." + COMMON_COLUMN_NAME_A_B +
                        " = " +
                        DATA_PROVIDER_INSTANCE_B_NAME + "." + COMMON_COLUMN_NAME_A_B
                )
                .build();

        // Execute the request and get the resulted data set
        final AcctReportingDataProviderDataSet dataSet = dataCompiler.compileReport(request);

        // Expect the returned data set to have the proper size
        assertEquals(2, dataSet.recordCount());
        assertEquals(6, dataSet.columns().size());

        // Expect that the data is more or less accurate
        assertEquals("ds_a_B2", dataSet.data()[1][1]);

        // Expect that the column names are in the right order
        assertEquals("dpA_colA", dataSet.columns().getFirst().name());
        assertEquals("dpB_colF", dataSet.columns().getLast().name());
    }

    @Test
    void testAcctReportingDataCompiler_handlesDataTypesCorrectly() {
        // Create a report compilation request that references the data provider instance that
        // provides the data set with various column types
        final ReportCompilationRequest request =
            ReportCompilationRequest.builder()
                .withInstance(
                    DataProviderInstanceSpecification.builder()
                        .withDataProviderUUID(JOINS_TESTING_DATA_PROVIDER_UUID)
                        .withInstanceName(DATA_PROVIDER_INSTANCE_C_NAME)
                        .withDataProviderInstanceProperty(OPTIONAL_DATA_SET_CHOICE_PARAMETER, DATA_PROVIDER_INSTANCE_C_NAME)
                        .build()
                )
                .withReportSqlStatement("""
                    SELECT
                        MAX(ts_col) max_ts,
                        MIN(num_col) min_num,
                        MAX(str_col) max_str,
                        MIN(txt_col) min_txt
                    FROM
                        instance_c
                    """)
                .build();

        // Execute the request and get the resulted data set
        final AcctReportingDataProviderDataSet dataSet = dataCompiler.compileReport(request);

        // Check that the max/min operations returned the correct values
        assertEquals("2020-01-02", dataSet.data()[0][0]);
        assertEquals(10d, Double.parseDouble(dataSet.data()[0][1]));
    }

    /**
     * Service provider that supplies instances of the {@link TestDataProvider}
     */
    private static class TestDataProviderServiceProvider
        extends AbstractAcctReportingDataProviderServiceProvider {

        private final Set<AcctReportingDataProviderId> getSupportedDataProviderIds =
            Set.of(
                AcctReportingDataProviderId.builder()
                    .withUuid(INSTANCE_PROPERTIES_TESTING_DATA_PROVIDER_UUID)
                    .withHumanReadableName("Instance properties testing data provider")
                    .withDescription("A data provider that is used for testing the correct provisioning of instance properties")
                    .withInstanceProperty(
                        new AcctReportingDataProviderInstancePropertySpec(
                            INSTANCE_PROPERTY_1_NAME,
                            BOOLEAN
                        )
                    )
                    .withInstanceProperty(
                        new AcctReportingDataProviderInstancePropertySpec(
                            INSTANCE_PROPERTY_2_NAME,
                            STRING
                        )
                    )
                    .build(),

                AcctReportingDataProviderId.builder()
                    .withUuid(RUNTIME_PARAMETERS_TESTING_DATA_PROVIDER_UUID)
                    .withHumanReadableName("Runtime parameters testing data provider")
                    .withDescription("A data provider that is used for testing the correct provisioning of runtime parameters")
                    .withParameter(
                        new AcctReportingDataProviderReportParameterSpec(
                            MANDATORY_RUNTIME_PARAMETER,
                            NUMERIC,
                            true
                        )
                    )
                    .withParameter(
                        new AcctReportingDataProviderReportParameterSpec(
                            OPTIONAL_RUNTIME_PARAMETER,
                            STRING,
                            false
                        )
                    )
                    .build(),

                AcctReportingDataProviderId.builder()
                    .withUuid(JOINS_TESTING_DATA_PROVIDER_UUID)
                    .withHumanReadableName("Joins testing data provider")
                    .withDescription("A data provider that is used for testing the correct provisioning of joins")
                    .withInstanceProperty(
                        new AcctReportingDataProviderInstancePropertySpec(
                            OPTIONAL_DATA_SET_CHOICE_PARAMETER,
                            STRING
                        )
                    )
                    .build()
            );

        @Override
        public Set<AcctReportingDataProviderId> getSupportedDataProviderIds() {
            return getSupportedDataProviderIds;
        }

        @Override
        public AcctReportingDataProvider newInstance(UUID dataProviderUUID)
            throws AcctReportingUnsupportedDataProviderException {
            return new TestDataProvider();
        }
    }

    /**
     * Data provider that provides test data for specific test cases
     */
    private static class TestDataProvider implements AcctReportingDataProvider {

        private final AcctReportingDataProviderDataSet dataSetA =
            new TestAcctReportingDataProviderDataSet(
                new String[][]{
                    {"ds_a_A1", "ds_a_B1", "ds_a_C1", "common1"},
                    {"ds_a_A2", "ds_a_B2", "ds_a_C2", "common2"}
                },
                new LinkedHashSet<>(
                    List.of(
                        new AcctReportingDataProviderDataSetColumn("colA", AcctReportingDataProviderDataSetColumnDataType.STRING),
                        new AcctReportingDataProviderDataSetColumn("colB", AcctReportingDataProviderDataSetColumnDataType.STRING),
                        new AcctReportingDataProviderDataSetColumn("colC", AcctReportingDataProviderDataSetColumnDataType.STRING),
                        new AcctReportingDataProviderDataSetColumn(COMMON_COLUMN_NAME_A_B, AcctReportingDataProviderDataSetColumnDataType.STRING)
                    )
                )
            );

        private final AcctReportingDataProviderDataSet dataSetB =
            new TestAcctReportingDataProviderDataSet(
                new String[][]{
                    {"ds_b_A1", "ds_b_B1", "ds_b_C1", "common1"},
                    {"ds_b_A2", "ds_b_B2", "ds_b_C2", "common2"}
                },
                new LinkedHashSet<>(
                    List.of(
                        new AcctReportingDataProviderDataSetColumn("colA", AcctReportingDataProviderDataSetColumnDataType.STRING),
                        new AcctReportingDataProviderDataSetColumn("colE", AcctReportingDataProviderDataSetColumnDataType.STRING),
                        new AcctReportingDataProviderDataSetColumn("colF", AcctReportingDataProviderDataSetColumnDataType.STRING),
                        new AcctReportingDataProviderDataSetColumn(COMMON_COLUMN_NAME_A_B, AcctReportingDataProviderDataSetColumnDataType.STRING)
                    )
                )
            );

        private final AcctReportingDataProviderDataSet dataSetC =
            new TestAcctReportingDataProviderDataSet(
                new String[][]{
                    {"2020-01-01 12:46:29", "11.4700", "your string", "my text"},
                    {"2020-01-02", "10", "my string", "your text"}
                },
                new LinkedHashSet<>(
                    List.of(
                        new AcctReportingDataProviderDataSetColumn("ts_col", AcctReportingDataProviderDataSetColumnDataType.TIMESTAMP),
                        new AcctReportingDataProviderDataSetColumn("num_col", AcctReportingDataProviderDataSetColumnDataType.NUMERIC),
                        new AcctReportingDataProviderDataSetColumn("str_col", AcctReportingDataProviderDataSetColumnDataType.STRING),
                        new AcctReportingDataProviderDataSetColumn("txt_col", AcctReportingDataProviderDataSetColumnDataType.TEXT)
                    )
                )
            );

        private AcctReportingDataProviderDataSet dataSet = dataSetA;

        @Override
        public void initialize(Map<String, String> dataProviderInstanceProperties)
            throws AcctReportingDataProviderInitializationException {
            // Get the data set choice parameter
            final String dataSetChoice =
                dataProviderInstanceProperties.get(OPTIONAL_DATA_SET_CHOICE_PARAMETER);

            // If the data set choice is B, then return data set B
            if (Objects.equals(DATA_PROVIDER_INSTANCE_B_NAME, dataSetChoice)) {
                dataSet = dataSetB;
                return;
            }

            if (Objects.equals(DATA_PROVIDER_INSTANCE_C_NAME, dataSetChoice)) {
                dataSet = dataSetC;
                return;
            }

            // If the data set choice is A or there is no data set choice, then return data set A
            dataSet = dataSetA;
        }

        @Override
        public AcctReportingDataProviderDataSet provideData(Map<String, String> reportParameters)
            throws AcctReportingDataProviderRuntimeException {
            return dataSet;
        }
    }

    private record TestAcctReportingDataProviderDataSet(
        String[][] data,
        LinkedHashSet<AcctReportingDataProviderDataSetColumn> columns
    ) implements AcctReportingDataProviderDataSet {

        @Override
        public int recordCount() {
            return data.length;
        }
    }

}
