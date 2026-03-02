import { Component, EventEmitter, input, InputSignal, OnChanges, OnInit } from '@angular/core';
import { ReportingDataSet } from '../../model-acct/reporting-data-set';
import { ReportProperties, ReportSeriesType, ReportType } from '../../model-acct/report-properties';
import { TableColumnDirective, TableComponent } from '../../components-gui/table/table.component';
import {CategoryScale, Chart, LinearScale, LineController, LineElement, PointElement, BarController, BarElement, PolarAreaController, ArcElement, RadialLinearScale, ChartItem, ChartTypeRegistry, Legend, Tooltip, Filler} from 'chart.js';
import { v4 as uuid } from 'uuid';
import { getElementOrThrow } from '../../utils-reusalbe/dom-utils';

@Component({
  selector: 'app-report-viewer',
  imports: [
    TableComponent,
    TableColumnDirective
  ],
  templateUrl: './report-viewer.component.html',
  styleUrl: './report-viewer.component.less'
})
export class ReportViewerComponent implements OnInit, OnChanges {

  /**
   * The width of the report viewer container
   */
  width: InputSignal<string> = input.required()

  /**
   * The height of the report viewer container
   */
  height: InputSignal<string> = input.required()

  /**
   * These properties tell the report viewer how to render the data
   */
  reportProperties : InputSignal<ReportProperties> = input.required()

  /**
   * This is the data that the report viewer renders
   */
  dataSet : InputSignal<ReportingDataSet> = input.required()

  /**
   * This event emitter tells the data table to reload its data
   */
  dataTableReloadTrigger : EventEmitter<void> = new EventEmitter<void>()

  /**
   * The ID of this component - helps identify child components related to this component
   * in an environment where multiple instances of this component are on the same page
   */
  reportViewerId : string = "report_viewer_" + uuid()

  /**
   * The ID of the canvas component in this report viewer is based on this report viewer
   */
  reportViewerCanvasId : string = this.reportViewerId + "_canvas"

  /**
   * Interface to the hidden elements that are used by the workaround to get computed CSS
   * values into JS properties
   */
  cssPropertyValuesComputer : CssPropertyValuesComputer = new CssPropertyValuesComputer(this.reportViewerId)

  cachedWidth : string = "100px"
  cachedHeight : string = "100px"

  cachedReportProperties! : ReportProperties
  cachedDataSet! : ReportingDataSet
  cachedDataSetColumnNames! : string[]
  cachedDataSetData! : string[][]

  cachedChart? : Chart


  ngOnInit(): void {
    this.registerChartJsComponents()
    this.rebuildCache()
  }

  ngOnChanges(): void {
    setTimeout(() => {
      this.rebuildCache()
      setTimeout(() => this.dataTableReloadTrigger.emit(), 50)
    }, 50)
  }

  private rebuildCache() : void {
    // Cache the input parameters
    this.cachedWidth = this.width()
    this.cachedHeight = this.height()
    this.cachedReportProperties = this.reportProperties()
    this.cachedDataSet = this.dataSet()

    // Cache the table data
    this.cachedDataSetColumnNames = this.cachedDataSet?.columns.map(c => c.name) ?? []
    this.cachedDataSetData = this.cachedDataSet?.data ?? []

    // Cache the chart data
    if (this.isChart()) {
      this.loadChartStyles()
      setTimeout(() => this.rebuildChart(), 100)
    }
  }

  private registerChartJsComponents() : void {
    Chart.register([
      CategoryScale,
      LinearScale,
      RadialLinearScale,
      LineController,
      BarController,
      PolarAreaController,
      LineElement,
      PointElement,
      BarElement,
      ArcElement,
      Legend,
      Tooltip,
      Filler
    ]);
  }

  private loadChartStyles() : void {
    this.cssPropertyValuesComputer.compute()
  }

  private rebuildChart() : void {
    // Find the canas
    const canvas : HTMLElement | null = document.getElementById(this.reportViewerCanvasId)

    // If the canvas is present, then prepare the chart
    if (canvas) {
      // If the chart was previously cached, then remove it from cache
      if (this.cachedChart) {
        this.cachedChart.destroy()
        delete this.cachedChart
      }

      // Compute the category column index (required when setting the category labels)
      const categoryColIndex = 
        this.cachedDataSet.columns.findIndex(col => 
          col.name == this.cachedReportProperties.reportCategoryColumnName
        )

      // Compute the column indexes by series name and mid the specified order
      const columnIndexBySeriesName : Map<string,number> = new Map()
      this.cachedReportProperties.reportSeries
      .sort((s1, s2) => s1.reportSeriesOrder - s2.reportSeriesOrder)
      .forEach(series => {
        this.cachedDataSet.columns.forEach((col, colIndex) => {
          if (col.name == series.reportColumnName) {
            columnIndexBySeriesName.set(series.reportSeriesName, colIndex)
          }
        })
      })

      // Configure the chart type and data
      this.cachedChart = new Chart(
        <ChartItem> canvas,
        {
          // Limitation of the ChartJS library: can't have two series with different types
          type: this.reportSeriesTypeKey(this.cachedReportProperties.reportSeries[0].reportSeriesType),

          // Standard options for displaying the chart
          options: {
            plugins: {
              legend: {
                display: true,
                position: 'top',
                labels: {
                  color: this.cssPropertyValuesComputer.reportViewerLegendColor
                }
              },
              tooltip: {
                enabled: true
              }
            },
            scales: {
              x: {
                grid: {
                  color: this.cssPropertyValuesComputer.reportViewerGridColor
                },
                ticks: {
                  color: this.cssPropertyValuesComputer.reportViewerAxisLabelColor,
                  minRotation: 90,
                  maxRotation: 90
                }
              },
              y: {
                grid: {
                  color: this.cssPropertyValuesComputer.reportViewerGridColor
                },
                ticks: {
                  color: this.cssPropertyValuesComputer.reportViewerAxisLabelColor
                },
                
              }
            }
          },

          // Data is computed from the data set
          data: {
            // Category labels are taken from the category column
            labels: this.cachedDataSet.data.map(row => row[categoryColIndex]),

            // When getting the data, the category column must be skipped
            datasets: this.cachedReportProperties.reportSeries
              .map(series =>
                ({
                  // The series lable is the name of the column that supplies the series data
                  label: series.reportSeriesName,
                  // The each element of the series data array is taken from the proper column of each row
                  data: this.cachedDataSet.data.map((row, rowIndex) => 
                    ({
                      x: rowIndex,
                      y: <number><unknown>row[columnIndexBySeriesName.get(series.reportSeriesName) ?? 0]
                    })
                  ),
                  borderColor: this.cssPropertyValuesComputer.getSeriesColorElement(series.reportSeriesOrder).borderColorValue,
                  backgroundColor: this.cssPropertyValuesComputer.getSeriesColorElement(series.reportSeriesOrder).colorValue,
                  fill: series.reportSeriesType == ReportSeriesType.AREA
                })
              )
          }
        }
      );
    }
  }

  private reportSeriesTypeKey(reportSeriesType : ReportSeriesType) : keyof ChartTypeRegistry {
    if (reportSeriesType == ReportSeriesType.AREA) {
      return 'line'
    }

    if (reportSeriesType == ReportSeriesType.COLUMN) {
      return 'bar'
    }

    if (reportSeriesType == ReportSeriesType.LINE) {
      return "line"
    }

    throw new Error("Report series type not supported")
  }

  public getReportProperties() : ReportProperties {
    return this.cachedReportProperties
  }

  public getDataSet() : ReportingDataSet {
    return this.cachedDataSet
  }

  public getCachedDataSetColumnNames() : string[] {
    return this.cachedDataSetColumnNames
  }

  public getCachedDataSetData() : string[][] {
    return this.cachedDataSetData
  }

  public getWidth() : string {
    return this.cachedWidth
  }

  public getHeight() : string {
    return this.cachedHeight
  }

  isTable() : boolean {
    return this.cachedReportProperties.reportType == ReportType.TABLE
  }

  isChart() : boolean {
    return [ReportType.SERIES, ReportType.PIE].includes(this.cachedReportProperties.reportType)
  }
  
}

const COLOR_ELEMENTS_COUNT : number = 10

class CssPropertyValuesComputer {

  reportViewerLegendColor : string = ""
  reportViewerLegendColorPropertyComputerId : string

  reportViewerGridColor : string = ""
  reportViewerGridColorPropertyComputerId : string

  reportViewerAxisLineColor : string = ""
  reportViewerAxisLabelColor : string = ""
  reportViewerAxisColorPropertyComputerId : string

  seriesColorElements : ReportSeriesColorElement[] = []


  constructor(reportViewerId:string) {
    this.reportViewerLegendColorPropertyComputerId = reportViewerId + "_legend_color_property_computer"
    this.reportViewerGridColorPropertyComputerId = reportViewerId + "_grid_color_property_computer"
    this.reportViewerAxisColorPropertyComputerId = reportViewerId + "_axis_color_property_computer"

    for (let e : number = 0 ; e < COLOR_ELEMENTS_COUNT ; e++) {
      this.seriesColorElements.push({
        elementId: reportViewerId + "_series_" + e + "_color_property_computer",
        className : "series_" + e,
        colorValue: "",
        borderColorValue: ""
      })
    }
  }

  public compute() : void {
    this.reportViewerLegendColor = this.computeProperty(this.reportViewerLegendColorPropertyComputerId, 'color')
    this.reportViewerGridColor = this.computeProperty(this.reportViewerGridColorPropertyComputerId, 'color')
    this.reportViewerAxisLabelColor = this.computeProperty(this.reportViewerAxisColorPropertyComputerId, 'color')
    this.reportViewerAxisLineColor = this.computeProperty(this.reportViewerAxisColorPropertyComputerId, 'border-color')

    for (let e of this.seriesColorElements) {
      e.colorValue = this.computeProperty(e.elementId, 'color')
      e.borderColorValue = this.computeProperty(e.elementId, "border-color")
    }
  }

  public getSeriesColorElement(seriesNumber:number) : ReportSeriesColorElement {
    // Overflow loops back to start => modulus(%) is used
    return this.seriesColorElements[seriesNumber % COLOR_ELEMENTS_COUNT]
  }

  private computeProperty(elementId:string, propertyName:string) : string {
    // Attempt to get a reference to the element
    const div : HTMLElement | null = document.getElementById(elementId)

    // If the element is found, then get the property
    if (div) {
      return window.getComputedStyle(div).getPropertyValue(propertyName)
    }

    // If the element is not found, then return nothing
    return ""
  }

}

interface ReportSeriesColorElement {
  elementId : string,
  className : string,
  colorValue : string,
  borderColorValue : string
}