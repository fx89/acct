import { Component, EventEmitter, OnInit } from '@angular/core';
import { ItemsManagerCardPropertyExtractor, ItemsManagerComponent, ItemsManagerDataItem, ItemsManagerDataSet, ItemsManagerNewItemFormDirective } from '../../../components-acct/items-manager/items-manager.component';
import { complete, errorConsumingObservableOperation, errorConsumingObservableTransform } from '../../../utils-reusalbe/rxjs-utils';
import { forkJoin, Observable } from 'rxjs';
import { CatalogService } from '../../../services-acct/catalog.service';
import { CurrencyProperties, IconifiedCurrencyProperties } from '../../../model-acct/currency-properties';
import { isDefined } from '../../../utils-reusalbe/lang-utils';
import { InputComponent } from '../../../components-gui/input/input.component';
import { ButtonComponent } from '../../../components-gui/button/button.component';
import { IconProperties } from '../../../model-acct/icon-properties';
import { IconsManagerComponent } from '../../../components-acct/icons-manager/icons-manager.component';
import { DialogComponent } from '../../../components-gui/dialog/dialog.component';
import { BarComponent } from '../../../components-gui/bar/bar.component';
import { TableColumnDirective, TableComponent } from '../../../components-gui/table/table.component';
import { SelectComponent } from '../../../components-gui/select/select.component';
import { CardData } from '../../../components-gui/cards-list/card-data';
import { IconifiedBankProperties } from '../../../model-acct/bank-properties';
import { MonitoredCurrencyCollector } from '../../../model-acct/monitored-currency-collector';
import { CurrencyService } from '../../../services-acct/currency.service';
import { extractFirstToken, extractLastToken } from '../../../utils-reusalbe/string-utils';
import { TimeChooserComponent, TimeOfDay } from '../../../components-gui/time-chooser/time-chooser.component';
import { MonitoredCurrencyProperties } from '../../../model-acct/monitored-currency-properties';
import { MsgboxComponent } from '../../../components-gui/msgbox/msgbox.component';
import { MsgboxType } from '../../../components-gui/msgbox/msgbox-type';
import { CalendarButtonComponent } from '../../../components-gui/calendar-button/calendar-button.component';
import { LabelComponent } from '../../../components-gui/label/label.component';
import { BankCardData, CardDataService, CurrencyCardData } from '../../../services-acct/card-data.service';
import { dateToIsoString } from '../../../utils-reusalbe/date-utils';

type MonitoredCurrencyCollectorCardData = CardData & { monitoredCurrencyCollector : MonitoredCurrencyCollector }

type EnrichedMonitoredCurrencyProperties = MonitoredCurrencyProperties & {
  bankCode           : string,
  currencyCode       : string,
  quotedCurrencyCode : string,
  bankIcon           : string,
  currencyIcon       : string,
  quotedCurrencyIcon : string
}

@Component({
  selector: 'app-currencies',
  imports: [
    ItemsManagerComponent,
    ItemsManagerNewItemFormDirective,
    InputComponent,
    ButtonComponent,
    DialogComponent,
    IconsManagerComponent,
    BarComponent,
    TableComponent,
    TableColumnDirective,
    SelectComponent,
    TimeChooserComponent,
    MsgboxComponent,
    CalendarButtonComponent,
    LabelComponent
  ],
  templateUrl: './currencies.component.html',
  styleUrl: './currencies.component.less'
})
export class CurrenciesComponent implements OnInit {

  constructor(
    private catalogService : CatalogService,
    private currencyService : CurrencyService,
    private cardDataService : CardDataService
  ) {

  }

  currenciesListSelectedItem? : IconifiedCurrencyProperties

  currenciesListForceReloadEventEmitter : EventEmitter<void> = new EventEmitter<void>

  currencyIconChooserVisible : boolean = false

  monitoredCurrencyPropertiesDialogVisible : boolean = false;

  monitoredCurrencyDeletionConfirmationMsgBoxVisible : boolean = false

  monitoredCurrencyExchangeRatesInputDialogVisible : boolean = false

  monitoredCurrencyManualCollectionConfirmationMsgBoxVisible : boolean = false

  monitoredCurrencies : EnrichedMonitoredCurrencyProperties[] = []

  selectedMonitoredCurrency? : EnrichedMonitoredCurrencyProperties

  registeredBanks : BankCardData[] = []

  selectedBank? : BankCardData

  registeredCurrencies : CurrencyCardData[] = []

  selectedCurrency? : CurrencyCardData

  selectedQuotedCurrency? : CurrencyCardData

  registeredMonitoredCurrencyCollectors : MonitoredCurrencyCollectorCardData[] = []

  selectedMonitoredCurrencyCollector? : MonitoredCurrencyCollectorCardData

  selectedScheduledTime! : TimeOfDay

  selectedScheduledTimeDefined : boolean = false

  selectedCurrencyExchangeRecordDate : Date = new Date()

  selectedCurrencyExchangeRecordBuyPrice : string = ""

  selectedCurrencyExchangeRecordSellPrice : string = ""

  monitoredCurrencyDeletionConfirmationMsgBoxType : MsgboxType = MsgboxType.YES_NO

  monitoredCurrencyManualCollectionMsgBoxType : MsgboxType = MsgboxType.YES_NO


  ngOnInit() : void {
    // Load the dependencies of the monitored currencies
    forkJoin([
      this.loadRegisteredBanks(),
      this.loadRegisteredCurrencies(),
      this.loadRegisteredMonitoredCurrencyCollectors()
    ]).subscribe(() => {
      // Once the dependencies have been loaded, load the monitored currencies
      this.loadRegisteredMonitoredCurrencies()
    })
  }

  loadRegisteredBanks() : Observable<void> {
    return new Observable<void>(subscriber => {
      this.cardDataService.loadRegisteredBanks().subscribe({
        next: registeredBanks => {
          this.registeredBanks = registeredBanks
          complete(subscriber, undefined)
        },
        error: err => {
          // TODO: Toast
          console.log(err)
        }
      })
    })
  }

  loadRegisteredCurrencies() : Observable<void> {
    return new Observable<void>(subscriber => {
      this.cardDataService.loadRegisteredCurrencies().subscribe({
        next: registeredCurrencies => {
          this.registeredCurrencies = registeredCurrencies
          complete(subscriber, undefined)
        },
        error: err => {
          // TODO: Toast
          console.log(err)
        }
      })
    })
  }

  loadRegisteredMonitoredCurrencyCollectors() : Observable<void> {
    return new Observable<void>(subscriber => {
      this.currencyService.findAllAvailableMonitoredCurrencyCollectors().subscribe({
        next: monitoredCurrencyCollectors => {
          this.registeredMonitoredCurrencyCollectors = monitoredCurrencyCollectors.map(collector => {
            return {
              monitoredCurrencyCollector: collector,
              title: this.extrectCollectorName(collector.currencyCollectorName),
              text: "Supported banks: " + collector.supportedBankCodes
            }
          })

          complete(subscriber, undefined)
        },
        error: err => {
          // TODO: Toast
          console.log(err)
        }
      })
    })
  }

  loadRegisteredMonitoredCurrencies() : void {
    this.currencyService.findAllMonitoredCurrencies().subscribe({
      next: (monitoredCurrencies:MonitoredCurrencyProperties[]) => {
        this.monitoredCurrencies = monitoredCurrencies.map(monitoredCurrency => {
          const ret = monitoredCurrency as EnrichedMonitoredCurrencyProperties
          ret.bankCode = this.registeredBanks.filter(b => b.bank.bankUUID == ret.bankUUID).map(b => b.bank.bankCode)[0]
          ret.currencyCode = this.registeredCurrencies.filter(c => c.currency.currencyUUID == ret.currencyUUID).map(c => c.currency.currencyCode)[0]
          ret.quotedCurrencyCode = this.registeredCurrencies.filter(c => c.currency.currencyUUID == ret.quotedCurrencyUUID).map(c => c.currency.currencyCode)[0]
          ret.bankIcon = this.registeredBanks.filter(b => b.bank.bankUUID == ret.bankUUID).map(b => b.bank.imageData)[0]
          ret.currencyIcon = this.registeredCurrencies.filter(c => c.currency.currencyUUID == ret.currencyUUID).map(c => c.currency.imageData)[0]
          ret.quotedCurrencyIcon = this.registeredCurrencies.filter(c => c.currency.currencyUUID == ret.quotedCurrencyUUID).map(c => c.currency.imageData)[0]
          return ret
        })

        delete this.selectedMonitoredCurrency
      },
      error: err => {
        // TODO: Toast
        console.log(err)
      }
    })
  }

  currenciesListProducer : (() => Observable<ItemsManagerDataSet>) =
      () => errorConsumingObservableOperation(
        this.catalogService.findAllCurrencies(),
        err => {
          // TODO: Toast
          console.log(err)
        }
      )

  currencyCardImageRefExtractor : ItemsManagerCardPropertyExtractor =
    (item:IconifiedCurrencyProperties) => item.imageData

  currencyCardTitleExtractor : ItemsManagerCardPropertyExtractor =
    (item:IconifiedCurrencyProperties) => item.currencyCode

  currencyCardTextExtractor : ItemsManagerCardPropertyExtractor = 
    (item:IconifiedCurrencyProperties) => item.currencyName

  currencyDeletionConsumer : ((item:ItemsManagerDataItem<CurrencyProperties>) => Observable<void>) =
    (item:ItemsManagerDataItem<CurrencyProperties>) => errorConsumingObservableOperation(
      this.catalogService.deleteCurrency(item),
      err => {
        // TODO: toast
        console.log(err)
      }
    )

  newCurrencySupplier : (() => CurrencyProperties) = () => {
    // Create the currency
    const currency : CurrencyProperties = {
      currencyUUID: "",
      currencyCode: "",
      currencyName: "",
      currencyIconUUID: ""
    }

    // Store the currency item
    this.currenciesListSelectedItem = currency as IconifiedCurrencyProperties

    // Return a reference to the newly created currency
    return currency
  }

  currencySavingConsumer : ((item:CurrencyProperties) => Observable<void>) =
    (item:CurrencyProperties) => errorConsumingObservableTransform(
      this.catalogService.saveCurrency(item),
      () => {},
      err => {
        // TODO: toast
        console.log(err)
      }
    )

  currencyValidator : ((item:CurrencyProperties) => boolean) =
    (item:CurrencyProperties) => {
      if (item) {
        return (
          isDefined(item.currencyCode) &&
          isDefined(item.currencyIconUUID) &&
          isDefined(item.currencyName)
        )
      }

      return false
    }

  saveSelectedMonitoredCurrency() : Observable<void> {
    return this.currencyService.saveMonitoredCurrency({
      bankUUID           : this.selectedBank?.bank?.bankUUID ?? "",
      collectorName      : this.selectedMonitoredCurrencyCollector?.monitoredCurrencyCollector?.currencyCollectorName ?? "",
      currencyUUID       : this.selectedCurrency?.currency?.currencyUUID ?? "",
      quotedCurrencyUUID : this.selectedQuotedCurrency?.currency?.currencyUUID ?? "",
      scheduledTimeHhMm  : this.selectedScheduledTime?.toString() ?? ""
    })
  }

  clearSelectedMonitoredCurrency() : void {
    this.selectedBank = undefined
    this.selectedCurrency = undefined
    this.selectedQuotedCurrency = undefined
    this.selectedMonitoredCurrencyCollector = undefined
    this.selectedScheduledTime = new TimeOfDay(0, 0)
    this.selectedScheduledTimeDefined = false
  }

  onChooseCurrencyIconClick() : void {
    this.currencyIconChooserVisible = true
  }

  onCurrencyIconSelected(icon:IconProperties) : void {
    if (this.currenciesListSelectedItem) {
      const selectedCurrency : IconifiedCurrencyProperties = this.currenciesListSelectedItem

      // Set the icon UUID
      selectedCurrency.currencyIconUUID = icon.iconUUID

      // Apply the icon
      this.catalogService.applyIcon(
        () => selectedCurrency.currencyIconUUID,
        imageData => selectedCurrency.imageData = imageData
      ).subscribe()

      // Hide the dialog
      this.currencyIconChooserVisible = false
    } else {
      // TODO: Toast
      console.log("Bank not selected")
    }
  }

  onCollectManuallyButtonClick() : void {
    this.monitoredCurrencyManualCollectionConfirmationMsgBoxVisible = true
  }

  onManualCollectionConfirmed() : void {
    if (this.selectedMonitoredCurrency) {
      this.currencyService.manuallyCollectMonitoredCurrencyExchangeRates(this.selectedMonitoredCurrency).subscribe({
        next: () => {
          this.loadRegisteredMonitoredCurrencies()
        },
        error: err => {
          // TODO: Toast
          console.log(err)
        }
      })
    }
  }

  onDeleteMonitoredCurrencyButtonClick() : void {
    this.monitoredCurrencyDeletionConfirmationMsgBoxVisible = true
  }

  onMonitoredCurrencyDeletionConfirmed() : void {
    if (this.selectedMonitoredCurrency) {
      this.currencyService.deleteMonitoredCurrency(this.selectedMonitoredCurrency).subscribe({
        next: () => {
          this.loadRegisteredMonitoredCurrencies()
        },
        error: err => {
          // TODO: Toast
          console.log(err)
        }
      })
    }
  }

  onInputExchangeRatesButtonClick() : void {
    this.monitoredCurrencyExchangeRatesInputDialogVisible = true
  }

  onSaveSelectedCurrencyRecordButtonClick() : void {
    if (this.selectedMonitoredCurrency) {
      this.currencyService.addMonitoredCurrencyRecords(
        this.selectedMonitoredCurrency,
        [
          {
            monitoredCurrencyRecordDate          : new Date(this.getSelectedCurrencyExchangeRecordDateAsString() + "T00:00:00.000Z"),
            monitoredCurrencyRecordPurchaseValue : Number(this.selectedCurrencyExchangeRecordBuyPrice),
            monitoredCurrencyRecordSaleValue     : Number(this.selectedCurrencyExchangeRecordSellPrice)
          }
        ]
      ).subscribe(() => {
        this.monitoredCurrencyExchangeRatesInputDialogVisible = false
        this.selectedCurrencyExchangeRecordBuyPrice = ""
        this.selectedCurrencyExchangeRecordSellPrice = ""
        this.loadRegisteredMonitoredCurrencies()
      })
    }
  }

  onAddButtonClick() : void {
    this.monitoredCurrencyPropertiesDialogVisible = true
  }

  onSaveMonitoredCurrencyButtonClick() : void {
    this.saveSelectedMonitoredCurrency().subscribe({
      next: () => {
        this.clearSelectedMonitoredCurrency()
        this.loadRegisteredMonitoredCurrencies()
        this.monitoredCurrencyPropertiesDialogVisible = false
      },
      error: err => {
        // TODO: Toast
        console.log(err)
      }
    })
  }

  onSelectedScheduledTimeChange(value:TimeOfDay) {
    this.selectedScheduledTime = value
    this.selectedScheduledTimeDefined = true
  }

  extrectCollectorName(collectorFullName:string) : string {
    return extractLastToken(collectorFullName, ".")
  }

  isMonitoredCurrencySelected() : boolean {
    return isDefined(this.selectedMonitoredCurrency)
  }

  isSaveMonitoredCurrencyPropertiesButtonEnabled() : boolean {
    return (
      isDefined(this.selectedBank) &&
      isDefined(this.selectedCurrency) &&
      isDefined(this.selectedQuotedCurrency) &&
      this.selectedScheduledTimeDefined
    )
  }

  isSelectedCurrencyExchangeRecordBuyPriceValid() : boolean {
    return this.isNumericFieldValid(this.selectedCurrencyExchangeRecordBuyPrice)
  }

  isSelectedCurrencyExchangeRecordSellPriceValid() : boolean {
    return this.isNumericFieldValid(this.selectedCurrencyExchangeRecordSellPrice)
  }

  isSelectedCurrencyExchangeRecordValid() : boolean {
    return (
      this.isSelectedCurrencyExchangeRecordBuyPriceValid() &&
      this.isSelectedCurrencyExchangeRecordSellPriceValid()
    )
  }

  isNumericFieldValid(numericField?:string) : boolean {
    if (Number(numericField)) {
      return true
    }

    return false
  }

  extractDate(dateTime:string) : string {
    if (dateTime) {
      return extractFirstToken(dateTime, 'T')
    }

    return ""
  }

  getSelectedCurrencyExchangeRecordDateAsString() : string {
    return dateToIsoString(this.selectedCurrencyExchangeRecordDate)
  }

}
