package com.desolatetimelines.acct.catalog.ws.model;

/**
 * Contains the readable properties of currencies
 *
 * @param currencyUUID     Unique identifier for the currency in the ACCT ecosystem
 * @param currencyCode     Unique 3-letter code that identifies the currency
 * @param currencyName     Human-readable name of the currency
 * @param currencyIconUUID UUID that identifies the currency in the GUI
 */
public record CurrencyProperties(
    String currencyUUID,
    String currencyCode,
    String currencyName,
    String currencyIconUUID
) {

    public static CurrencyPropertiesBuilder builder() {
        return new CurrencyPropertiesBuilder();
    }

    public static final class CurrencyPropertiesBuilder {
        private String currencyUUID;
        private String currencyCode;
        private String currencyName;
        private String currencyIconUUID;

        private CurrencyPropertiesBuilder() {
        }

        public CurrencyPropertiesBuilder withCurrencyUUID(String currencyUUID) {
            this.currencyUUID = currencyUUID;
            return this;
        }

        public CurrencyPropertiesBuilder withCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
            return this;
        }

        public CurrencyPropertiesBuilder withCurrencyName(String currencyName) {
            this.currencyName = currencyName;
            return this;
        }

        public CurrencyPropertiesBuilder withCurrencyIconUUID(String currencyIconUUID) {
            this.currencyIconUUID = currencyIconUUID;
            return this;
        }

        public CurrencyProperties build() {
            return
                new CurrencyProperties(
                    currencyUUID,
                    currencyCode,
                    currencyName,
                    currencyIconUUID
                );
        }
    }
}
