import { Injectable } from '@angular/core';
import { CatalogService } from './catalog.service';
import { map, Observable } from 'rxjs';
import { CardData } from '../components-gui/cards-list/card-data';
import { IconifiedCurrencyProperties } from '../model-acct/currency-properties';
import { IconifiedBankProperties } from '../model-acct/bank-properties';

/**
 * Extends the CardData type with the IconifiedCurrencyProperties of a given currency
 */
export type CurrencyCardData = CardData & { currency : IconifiedCurrencyProperties }

/**
 * Extends the CardData type with the IconifiedBankProperties of a given currency
 */
export type BankCardData = CardData & { bank : IconifiedBankProperties }

@Injectable({
  providedIn: 'root'
})
export class CardDataService {

  constructor(
    private catalogService : CatalogService
  ) { }

  /**
   * Returns an observable that produces an array of CurrencyCardData objects, which
   * contain the data to be set on a currency card and the iconified currency used as
   * source for the card data
   */
  public loadRegisteredCurrencies() : Observable<CurrencyCardData[]> {
    return this.catalogService.findAllCurrencies().pipe(
      map(currencies =>
        currencies.map(currency => {
          return {
            currency : currency,
            title    : currency.currencyCode,
            text     : currency.currencyName,
            imageRef : currency.imageData
          }
        })
      )
    )
  }

  /**
   * Returns an observable that produces an array of BankCardData objects, which contain the
   * data to be set on a bank card and the iconified bank used as source for the card data
   */
  public loadRegisteredBanks() : Observable<BankCardData[]> {
    return this.catalogService.findAllBanks().pipe(
      map(banks => 
        banks.map(bank => {
          return {
            bank     : bank,
            title    : bank.bankCode,
            text     : bank.bankName,
            imageRef : bank.imageData
          }
        })
      )
    )
  }
}
