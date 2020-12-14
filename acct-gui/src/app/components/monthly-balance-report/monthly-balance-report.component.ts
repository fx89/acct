import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { StateService } from 'src/app/services/state-service/state.service';
import { GoogleChartsStyle } from 'src/assets/skins/dark-times/google-charts-style';
import { MonthlyBalanceReportRecord } from 'src/app/model/monthly-balance-report-record';

@Component({
  selector: 'app-monthly-balance-report',
  templateUrl: './monthly-balance-report.component.html',
  styleUrls: ['./monthly-balance-report.component.css']
})
export class MonthlyBalanceReportComponent implements OnInit, OnChanges {

    @Input() sourceDate : MonthlyBalanceReportRecord[];

    @Input() width : number;
    @Input() height : number;
    @Input() hideDates : boolean;

    private chartData = [];
    private chartColumnNames = ['Month', 'Incoming', 'Outgoing','Balance'];
    private chartOptions = {};

    constructor() { }

    ngOnInit() {

    }

    ngOnChanges() {
        this.initChartOptions();

        if (this.sourceDate) {
            this.chartData = [];

            let min : number = 0;
            let max : number = 0;

            for (let rec of this.sourceDate) {
                this.chartData.push([
                    rec.recordYearMonth.toString(),
                    rec.incomingBalance,
                    rec.outgoingBalance,
                    rec.remainingBalance
                ]);

                min = rec.incomingBalance < min ? rec.incomingBalance : min;
                min = rec.outgoingBalance < min ? rec.outgoingBalance : min;
                min = rec.remainingBalance < min ? rec.remainingBalance : min;

                max = rec.incomingBalance > max ? rec.incomingBalance : max;
                max = rec.outgoingBalance > max ? rec.outgoingBalance : max;
                max = rec.remainingBalance > max ? rec.remainingBalance : max;
            }

            (<any>(this.chartOptions)).vAxis.viewWindow.min = min;
            (<any>(this.chartOptions)).vAxis.viewWindow.max = max;
        }
    }

    private initChartOptions() {
        this.chartOptions = GoogleChartsStyle.createOptions();

        (<any>(this.chartOptions)).seriesType = 'bars';
        (<any>(this.chartOptions)).series = {2: {type: 'line'}};

        (<any>(this.chartOptions)).width = this.width;
        (<any>(this.chartOptions)).height = this.height;

        if (this.hideDates) {
            (<any>(this.chartOptions)).hAxis.textPosition = 'none';
        }
    }

}
