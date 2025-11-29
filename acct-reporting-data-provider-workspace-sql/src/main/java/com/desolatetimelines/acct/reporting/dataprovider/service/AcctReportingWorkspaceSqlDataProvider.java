package com.desolatetimelines.acct.reporting.dataprovider.service;

import com.desolatetimelines.acct.common.lang.Container;
import com.desolatetimelines.acct.reporting.dataprovider.exception.AcctReportingDataProviderInitializationException;
import com.desolatetimelines.acct.reporting.dataprovider.exception.AcctReportingDataProviderRuntimeException;
import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderDataSet;
import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderDataSetColumn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;

import static com.desolatetimelines.acct.common.utils.JDBCUtils.resultSetToDataArray;
import static com.desolatetimelines.acct.reporting.dataprovider.service.Constants.INSTANCE_PROPERTY_NAME_SQL;
import static com.desolatetimelines.acct.reporting.dataprovider.utils.ResultSetUtils.getColumnsFromResultSet;

public class AcctReportingWorkspaceSqlDataProvider implements AcctReportingDataProvider {

    private static final int ESTIMATED_RESULT_SET_SIZE = 100000;

    private final JdbcTemplate jdbcTemplate;

    private String sql = "SELECT 1 the_one";

    public AcctReportingWorkspaceSqlDataProvider(
        AcctWorkspaceDataSourceProvider dataSourceProvider
    ) {
        jdbcTemplate = new JdbcTemplate(dataSourceProvider.createNewDataSource());
    }

    @Override
    public void initialize(Map<String, String> dataProviderInstanceProperties) throws AcctReportingDataProviderInitializationException {
        // Get the SQL parameter
        sql =
            Optional.ofNullable(dataProviderInstanceProperties.get(INSTANCE_PROPERTY_NAME_SQL))
                .orElseThrow(() -> new AcctReportingDataProviderInitializationException(
                    "The required " + INSTANCE_PROPERTY_NAME_SQL + " parameter was not provided"
                ));
    }

    @Override
    public AcctReportingDataProviderDataSet provideData(Map<String, String> reportParameters) throws AcctReportingDataProviderRuntimeException {

        // Create the SQL parameter source for the parameters map
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValues(reportParameters);

        // Prepare the container for the data set
        final Container<AcctReportingDataProviderDataSet> dataSetContainer = new Container<>();


        // Run the SQL query using the SQL parameter source and compile the data set out of the
        // JDBC ResultSet that's been retrieved by the JDBC template
        jdbcTemplate.queryForList(
            sql,
            namedParameters,
            (ResultSetExtractor<AcctReportingDataProviderDataSet>) resultSet -> {

                dataSetContainer.set(
                    new AcctReportingDataProviderDataSet() {

                        private final LinkedHashSet<AcctReportingDataProviderDataSetColumn> columns =
                            getColumnsFromResultSet(resultSet);

                        private final String[][] data =
                            resultSetToDataArray(
                                resultSet,
                                ESTIMATED_RESULT_SET_SIZE,
                                new LinkedHashSet<>(columns.stream().map(AcctReportingDataProviderDataSetColumn::name).toList())
                            );

                        @Override
                        public int recordCount() {
                            return data.length;
                        }

                        @Override
                        public LinkedHashSet<AcctReportingDataProviderDataSetColumn> columns() {
                            return columns;
                        }

                        @Override
                        public String[][] data() {
                            return data;
                        }
                    }
                );

                return dataSetContainer.get();
            }
        );

        // Return a reference to the data set
        return dataSetContainer.get();
    }


}
