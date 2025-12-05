package com.desolatetimelines.acct.reporting.ws.model;

import org.springframework.lang.NonNull;

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
}
