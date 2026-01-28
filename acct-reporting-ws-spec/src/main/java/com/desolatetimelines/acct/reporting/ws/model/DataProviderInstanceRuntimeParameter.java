package com.desolatetimelines.acct.reporting.ws.model;

import org.springframework.lang.NonNull;

import java.util.Objects;

import static com.desolatetimelines.acct.common.utils.ValidationUtils.throwIfNull;
import static com.desolatetimelines.acct.common.utils.ValidationUtils.throwIfNullOrEmpty;

/**
 * Runtime parameters are give by users through the UI to the reporting back-end.
 * The result set returned by the reporting back-end are influenced by these parameters.
 * Unlike {@link DataProviderInstanceProperty instance properties}, which are the same
 * every time a {@link DataProviderInstanceProperties data provider instance} is invoked,
 * the runtime parameters may have different values from one invocation of the data
 * provider instance to another. <br />
 * <br />
 * <b>Important note:</b><br />
 * The runtime parameters defined by the data provider instance are additional parameters
 * to the ones already defined by the
 * {@link com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderId data provider}.
 * If a runtime parameter is already defined by the data provider, then it should not be
 * re-defined by the data provider instance. Conflict resolution is not guaranteed to yield
 * constant results. <br />
 *
 * @param parameterName         Uniquely identifies the runtime parameter within the set of runtime parameters
 *                              supported by the {@link DataProviderInstanceProperties data provider instance}.
 * @param parameterDefaultValue Is automatically filled in when rendered on the GUI.
 * @param parameterDataType     Tells the GUI how to render the input field for the parameter.
 * @param mandatory             If set to true, the GUI should not allow submitting the report request until
 *                              the field for this parameter is filled in. The data provider should also raise
 *                              an exception if this parameter is not provided.
 */
public record DataProviderInstanceRuntimeParameter(
    @NonNull String parameterName,
    @NonNull String parameterDefaultValue,
    @NonNull DataProviderParameterDataType parameterDataType,
    @NonNull Boolean mandatory
) {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DataProviderInstanceRuntimeParameter that = (DataProviderInstanceRuntimeParameter) o;
        return Objects.equals(parameterName, that.parameterName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(parameterName);
    }

    public static DataProviderInstanceRuntimeParameterBuilder builder() {
        return new DataProviderInstanceRuntimeParameterBuilder();
    }

    public static final class DataProviderInstanceRuntimeParameterBuilder {

        String parameterName;
        String parameterDefaultValue = "";
        DataProviderParameterDataType parameterDataType = DataProviderParameterDataType.STRING;
        Boolean mandatory = false;

        public DataProviderInstanceRuntimeParameterBuilder withParameterName(String parameterName) {
            this.parameterName = parameterName;
            return this;
        }

        public DataProviderInstanceRuntimeParameterBuilder withParameterDefaultValue(
            String parameterDefaultValue
        ) {
            this.parameterDefaultValue = parameterDefaultValue;
            return this;
        }

        public DataProviderInstanceRuntimeParameterBuilder withParameterDataType(
            DataProviderParameterDataType parameterDataType
        ) {
            this.parameterDataType = parameterDataType;
            return this;
        }

        public DataProviderInstanceRuntimeParameterBuilder withMandatory(Boolean mandatory) {
            this.mandatory = mandatory;
            return this;
        }

        public DataProviderInstanceRuntimeParameter build() {
            throwIfNullOrEmpty(parameterName, () -> new IllegalArgumentException("The parameter name is missing"));
            throwIfNull(parameterDataType, () -> new IllegalArgumentException("The parameter data type is missing"));
            throwIfNull(mandatory, () -> new IllegalArgumentException("The mandatory flag is missing"));

            if (mandatory) {
                throwIfNullOrEmpty(parameterDefaultValue, () -> new IllegalArgumentException("The parameter default value is missing"));
            }

            return
                new DataProviderInstanceRuntimeParameter(
                    parameterName,
                    parameterDefaultValue,
                    parameterDataType,
                    mandatory
                );
        }

    }

}
