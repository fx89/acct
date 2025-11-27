package com.desolatetimelines.acct.reporting.dataprovider.service;

/**
 * Constants used by the currency history data provider services
 */
public abstract class Constants {

    /**
     * The name of the instance property that determines which currency is
     * queried
     */
    public static final String INSTANCE_PROPERTY_NAME_CURRENCY_CODE = "currency_code";

    /**
     * The name of the instance property that determines how far in the past
     * the currency history goes
     */
    public static final String INSTANCE_PROPERTY_NAME_NUM_DAYS_AGO = "num_days_ago";

}
