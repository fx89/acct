import { Component, OnInit } from '@angular/core';
import { BarComponent } from '../../components-gui/bar/bar.component';
import { ButtonComponent } from '../../components-gui/button/button.component';
import { SelectComponent } from '../../components-gui/select/select.component';
import { BankCardData, CardDataService } from '../../services-acct/card-data.service';
import { Observable } from 'rxjs';
import { complete } from '../../utils-reusalbe/rxjs-utils';
import { CardData } from '../../components-gui/cards-list/card-data';
import { SwitchComponent } from '../../components-gui/switch/switch.component';
import { TableColumnDirective, TableComponent } from '../../components-gui/table/table.component';
import { ScrollEvent } from '../../components-gui/directives/scrollable-content.directive';

@Component({
  selector: 'app-deposits',
  imports: [
    BarComponent,
    ButtonComponent,
    SelectComponent,
    SwitchComponent,
    TableComponent,
    TableColumnDirective
  ],
  templateUrl: './deposits.component.html',
  styleUrl: './deposits.component.less'
})
export class DepositsComponent implements OnInit {

  /**
   * Contains all the banks registered in the catalog, together with
   * their icons.
   */
  registeredBanks : BankCardData[] = []

  /**
   * The bank that's selected from the registered banks array, using the banks selection box
   */
  selectedBank? : BankCardData

  /**
   * Flag that is set by the capitalized deposits inclusion switch
   */
  includeCapitalizedDeposits : boolean = false

  constructor(
    private cardDataService : CardDataService
  ){}

  ngOnInit() : void {
    this.loadRegisteredBanks().subscribe()
  }
 
  loadRegisteredBanks() : Observable<void> {
    return new Observable<void>(subscriber => {
      this.cardDataService.loadRegisteredBanks().subscribe({
        next: registeredBanks => {
          // Assign the registered currencies array
          this.registeredBanks = registeredBanks

          // Notify subscribers that the task is done
          complete(subscriber, undefined)
        },
        error: err => {
          // TODO: Toast
          console.log(err)
        }
      })
    })
  }

  onSelectedBankChange(bank : CardData | undefined) : void {
    this.selectedBank = bank as BankCardData
  }

  onNewDepositButtonClick() : void {

  }

  onDepositsTableScroll(scrollEvent:ScrollEvent) : void {

  }

  getDepositRecordHeightPx() : string {
    return '50px'
  }

  getDeposits() : any[] {
    return []
  }

}
