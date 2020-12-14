import { Component, OnInit } from '@angular/core';
import { StateService } from 'src/app/services/state-service/state.service';
import { ExchangeRateHistoryRecord } from 'src/app/model/ExchangeRateHistoryRecord';

@Component({
  selector: 'app-section-exchange-rates',
  templateUrl: './section-exchange-rates.component.html',
  styleUrls: ['./section-exchange-rates.component.css']
})
export class SectionExchangeRatesComponent implements OnInit {
    private currencies : string[] = [];
    private chartData : ExchangeRateHistoryRecord[] = [];

    private sourceXValueMapper : (rec) => any = (rec) => { return rec.date.toString().split("T")[0]; }
    private sourceYValueMapper : (rec) => any = (rec) => { return rec.value; }

    constructor( private state : StateService ) { }

    ngOnInit() {
        this.state.loadExchangeRatesHistory();
    }

    private getCurrencies() : string[] {

        if (this.currencies.length == 0) {
            this.state.exchageRatesHistory.forEach((val, key) => {
                this.currencies.push(key.valueOf());
            });
        }

        return this.currencies;
    }

    private getChartData(crName : string) {
        let ret = this.chartData[crName];

        if (!ret) {
            ret = this.state.exchageRatesHistory.get(crName);
            this.chartData.push(ret);
        }

        return ret;
    }
}
