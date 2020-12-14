import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { StateService } from 'src/app/services/state-service/state.service';
import { GoogleChartsStyle } from 'src/assets/skins/dark-times/google-charts-style';
import { MonthlyBalanceReportRecord } from 'src/app/model/monthly-balance-report-record';

@Component({
  selector: 'app-monthly-savings-report',
  templateUrl: './monthly-savings-report.component.html',
  styleUrls: ['./monthly-savings-report.component.css']
})
export class MonthlySavingsReportComponent implements OnInit, OnChanges {
    @Input() sourceDate : any[];
    @Input() sourceDataXValueMapper : (rec) => any;
    @Input() sourceDataYValueMapper : (rec) => any;

    @Input() width : number;
    @Input() height : number;
    @Input() hideDates : boolean;

    @Input() chartType : string;
    @Input() forceMin : number;
    @Input() forceMax : number;

    @Input() chartColumnNames : string[];

    private chartData = [];
    private chartOptions = {};

    constructor() { }

    ngOnInit() {}

    ngOnChanges() {
        this.initChartOptions();

        if (this.sourceDate) {
            this.chartData = [];

            let min : number = null;
            let max : number = null;

            for (let rec of this.sourceDate) {
                let val = this.sourceDataYValueMapper ? this.sourceDataYValueMapper(rec) : 0;

                this.chartData.push([
                    this.sourceDataXValueMapper ? this.sourceDataXValueMapper(rec) : "0",
                    val
                ]);

                if (max == null) {
                    max = val;
                }

                if (min == null) {
                    min = val;
                }

                max = (val > max ? val : max);
                min = (val < min ? val : min);
            }

            (<any>(this.chartOptions)).vAxis.viewWindow.min = this.forceMin ? this.forceMin : min;
            (<any>(this.chartOptions)).vAxis.viewWindow.max = this.forceMax ? this.forceMax : max;
        }
    }

    private initChartOptions() {
        this.chartOptions = GoogleChartsStyle.createOptions();

        (<any>(this.chartOptions)).width = this.width;
        (<any>(this.chartOptions)).height = this.height;

        if (this.hideDates) {
            (<any>(this.chartOptions)).hAxis.textPosition = 'none';
        }
  }
}
