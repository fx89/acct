package com.desolatetimelines.acct.reporting.dataprovider;

import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderDataSetColumnDataType;

import java.sql.Types;
import java.util.List;

import static java.sql.Types.*;

/**
 * Provides mappers for the {@link AcctReportingDataProviderDataSetColumnDataType} type
 */
public abstract class AcctReportingDataProviderDataSetColumnDataTypeMapper {

    /**
     * Translates the given {@link AcctReportingDataProviderDataSetColumnDataType column data type}
     * to a string that represents the name of the data type that accommodates the given column
     * data type into an SQLite database.
     */
    public static String toSQLiteDataTypeName(
        AcctReportingDataProviderDataSetColumnDataType columnDataType
    ) {
        return
            switch (columnDataType) {
                case STRING -> "CHAR(500)";
                case TEXT -> "TEXT";
                case TIMESTAMP -> "CHAR(50)";
                case NUMERIC -> "REAL";
            };
    }

    /**
     * Translates the given {@link Types JDBC column type} into the matching
     * {@link AcctReportingDataProviderDataSetColumnDataType column data type}.
     */
    public static AcctReportingDataProviderDataSetColumnDataType fromJdbcColumnType(int jdbcColumnType) {
        if (List.of(
            BIT, TINYINT, SMALLINT, INTEGER, BIGINT, FLOAT, REAL, DOUBLE, NUMERIC, DECIMAL
        ).contains(jdbcColumnType)
        ) {
            return AcctReportingDataProviderDataSetColumnDataType.NUMERIC;
        }

        if (List.of(DATE, TIME, TIMESTAMP).contains(jdbcColumnType)) {
            return AcctReportingDataProviderDataSetColumnDataType.TIMESTAMP;
        }

        if (List.of(CHAR, VARCHAR, LONGVARCHAR).contains(jdbcColumnType)) {
            return AcctReportingDataProviderDataSetColumnDataType.STRING;
        }

        return AcctReportingDataProviderDataSetColumnDataType.TEXT;
    }

}
