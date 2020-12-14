import { Component, OnInit } from '@angular/core';
import { StateService } from 'src/app/services/state-service/state.service';
import { MonitoredCurrency } from 'src/app/model/monitored-currency';

@Component({
  selector: 'app-currencies-manager',
  templateUrl: './currencies-manager.component.html',
  styleUrls: ['./currencies-manager.component.css']
})
export class CurrenciesManagerComponent implements OnInit {

    private selectedSupportedCurrency : String;

    private selectedMonitoredCurrency : MonitoredCurrency;

    private loading : boolean = false;

    constructor( private state : StateService ) { }

    ngOnInit() {
        this.state.loadMonitoredCurrency();
    }

    private selectSupportedCurrency(item : String) {
        this.selectedSupportedCurrency = item;
    }

    private selectMonitoredCurrency(item : MonitoredCurrency) {
        this.selectedMonitoredCurrency = item;
    }

    private addButtonClicked() {
        this.state.monitorCurrency(this.selectedSupportedCurrency);
        this.selectedSupportedCurrency = null;
    }

    private deleteButtonClicked() {
        this.state.unMonitorCurrency(this.selectedMonitoredCurrency);
        this.selectedMonitoredCurrency = null;
    }

    private collectButtonClicked() {
        if (!(this.loading)) {
            this.loading = true;

            this.state.updateCurrenciesFromSource().subscribe(
                result => {
                    this.loading = false;
                }
            );
        }
    }
}
