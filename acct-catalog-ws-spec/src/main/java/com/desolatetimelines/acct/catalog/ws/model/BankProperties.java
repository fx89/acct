package com.desolatetimelines.acct.catalog.ws.model;

/**
 * Container for the readable properties of banks
 *
 * @param bankUUID           unique identifier for the bank in the ACCT ecosystem
 * @param bankCode           The unique code given to the bank in the ACCT ecosystem (i.e. ING, BNR, BT, BCR, etc.)
 * @param bankName           The unique human-readable name of the bank
 * @param internetBankingURL The optional internet banking URL for the bank
 * @param bankIconUUID       The UUID of the optional icon that represents the bank on the ACCT GUI
 */
public record BankProperties(
    String bankUUID,
    String bankCode,
    String bankName,
    String internetBankingURL,
    String bankIconUUID
) {

    public static AcctBankBuilder builder() {
        return new AcctBankBuilder();
    }

    public static final class AcctBankBuilder {
        private String bankUUID;
        private String bankCode;
        private String bankName;
        private String internetBankingURL;
        private String bankIconUUID;

        private AcctBankBuilder() {
        }

        public AcctBankBuilder withBankUUID(String bankUUID) {
            this.bankUUID = bankUUID;
            return this;
        }

        public AcctBankBuilder withBankCode(String bankCode) {
            this.bankCode = bankCode;
            return this;
        }

        public AcctBankBuilder withBankName(String bankName) {
            this.bankName = bankName;
            return this;
        }

        public AcctBankBuilder withInternetBankingURL(String internetBankingURL) {
            this.internetBankingURL = internetBankingURL;
            return this;
        }

        public AcctBankBuilder withBankIconUUID(String bankIconUUID) {
            this.bankIconUUID = bankIconUUID;
            return this;
        }

        public BankProperties build() {
            return
                new BankProperties(
                    bankUUID,
                    bankCode,
                    bankName,
                    internetBankingURL,
                    bankIconUUID
                );
        }
    }
}
