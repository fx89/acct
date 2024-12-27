package com.desolatetimelines.acct.common.util;

/**
 * Provides utility methods for working with error codes
 */
public class ErrorCodeUtils {

    /**
     * The radix in which the error code makes sense
     */
    private static final int RADIX = 16;

    /**
     * The number of digits reserved for the service identifier
     */
    private static final int SERVICE_DIGITS_COUNT = 2;

    /**
     * The number of digits reserved for the error category identifier
     */
    private static final int CATEGORY_DIGITS_COUNT = 3;

    /**
     * The number of digits reserved for the error identifier
     */
    private static final int ERROR_DIGITS_COUNT = 4;

    /**
     * Provides error codes for a referenced service, category and error number.<br />
     * <br />
     * An error code is made up of the following sections: <ul>
     * <li>Service number - uniquely identifies the service across the entire ecosystem</li>
     * <li>Category number - uniquely identifies the error category within the service</li>
     * <li>Error number - uniquely identifies the error number within the category</li>
     * </ul><br />
     * The error code is a HEX code that has the following format: {@code 0x{SS}{CCC}{EEEE}}
     *
     * @param serviceNbr  the service number
     * @param categoryNbr the category number
     * @param errorNbr    the error number
     * @return the error code
     */
    public static String computeErrorCode(int serviceNbr, int categoryNbr, int errorNbr) {
        return "0x" +
            (
                lPadCode(Integer.toString(serviceNbr, RADIX), SERVICE_DIGITS_COUNT) +
                lPadCode(Integer.toString(categoryNbr, RADIX), CATEGORY_DIGITS_COUNT) +
                lPadCode(Integer.toString(errorNbr, RADIX), ERROR_DIGITS_COUNT)
            ).toUpperCase();
    }

    private static String lPadCode(String code, int nDigits) {
        return "0".repeat(nDigits - code.length()) + code;
    }

}
