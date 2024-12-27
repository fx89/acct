package com.desolatetimelines.acct.common.ws.model;

import java.util.Map;

/**
 * Represents the structure of an error message thrown by ACCT web services
 *
 * @param errorCode       HEX code that uniquely represents the exception type across the ACCT ecosystem and
 *                        that can be translated into a human-readable error message in any supported language
 * @param errorParameters A key/value map containing the dynamic content of the human-readable error message
 */
public record AcctError(
    String errorCode,
    Map<String, String> errorParameters
) {
}
