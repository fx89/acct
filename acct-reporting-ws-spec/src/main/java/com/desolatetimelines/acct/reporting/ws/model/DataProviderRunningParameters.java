package com.desolatetimelines.acct.reporting.ws.model;

import org.springframework.lang.Nullable;

import java.util.Map;

/**
 * @param instanceProperties Key/Value map containing the instance properties required by
 *                           the data provider.
 * @param runtimeParameters  Key/Value map containing the runtime parameters for the data
 *                           set compilation.
 */
public record DataProviderRunningParameters(
    @Nullable Map<String, String> instanceProperties,
    @Nullable Map<String, String> runtimeParameters
) {
}
