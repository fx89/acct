ALTER TABLE "currency_exchange"
ADD CONSTRAINT currency_exchange_source_account_record_fk FOREIGN KEY (currency_exchange_source_account_record_id)
REFERENCES "account_record" (account_record_id);

ALTER TABLE "currency_exchange"
ADD CONSTRAINT currency_exchange_target_account_record_fk FOREIGN KEY (currency_exchange_target_account_record_id)
REFERENCES "account_record" (account_record_id);