import { Observable } from "rxjs";
import { CurrencyProperties } from "../../model-acct/currency-properties";
import { CurrencyUUIDResponse } from "../../model-acct/currency-uuid-response";
import { AcctCurrenciesRepository } from "../currencies-repository";

/**
 * Mock implementation of the AcctCurrenciesRepository
 */
export class MockAcctCurrenciesRepository extends AcctCurrenciesRepository {

    override findCurrencies(): Observable<CurrencyProperties[]> {
        throw new Error("Method not implemented.");
    }

    override deleteCurrencies(currencyUUIDs: string[]): Observable<void> {
        throw new Error("Method not implemented.");
    }

    override saveCurrency(curreny: CurrencyProperties): Observable<CurrencyUUIDResponse> {
        throw new Error("Method not implemented.");
    }
    
}