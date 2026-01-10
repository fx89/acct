import { Component, EventEmitter, OnInit } from '@angular/core';
import { map, Observable, switchMap } from 'rxjs';
import { ItemsManagerCardAction, ItemsManagerCardPropertyExtractor, ItemsManagerComponent, ItemsManagerDataItem, ItemsManagerDataSet, ItemsManagerNewItemFormDirective } from '../../components-acct/items-manager/items-manager.component';
import { complete, errorConsumingObservableOperation, errorConsumingObservableTransform,  waitForCondition } from '../../utils-reusalbe/rxjs-utils';
import { ReportingService } from '../../services-acct/reporting.service';
import { isDefined } from '../../utils-reusalbe/lang-utils';
import { DataProviderInstance } from '../../model-acct/data-provider-instance';
import { allDataProviderPropertyDataTypes, DataProvider, DataProviderPropertyDataType } from '../../model-acct/data-provider';
import { DataProviderInstanceProperty, DataProviderInstanceRuntimeParameter } from '../../model-acct/data-provider-instance-properties';
import { InputComponent } from '../../components-gui/input/input.component';
import { ButtonComponent } from '../../components-gui/button/button.component';
import { DialogComponent } from '../../components-gui/dialog/dialog.component';
import { DataProviderInstanceProperties } from '../../model-acct/data-provider-instance-properties';
import { SelectComponent } from '../../components-gui/select/select.component';
import { CardData } from '../../components-gui/cards-list/card-data';
import { newReportingDataSet, ReportingDataSet } from '../../model-acct/reporting-data-set';
import { TableColumnDirective, TableComponent } from '../../components-gui/table/table.component';
import { isEmptyString } from '../../utils-reusalbe/string-utils';

export interface DataProviderInstanceWithDataproviderReference extends DataProviderInstance  {
  dataProvider : DataProvider
}

export interface DataProviderCardData extends CardData {
  dataProvider : DataProvider
}

export interface DataProviderPropertyDataTypeCardData extends CardData {
  dataProviderProprtyDataType : DataProviderPropertyDataType
}

export interface MandatoryCardData extends CardData {
  value : boolean
}

export interface DataProviderParameterSelectedOption {
  parameterDataType : DataProviderPropertyDataTypeCardData,
  mandatory : MandatoryCardData
}

export interface DataProviderInstancePropertiesWithDataproviderRefernce extends DataProviderInstanceProperties {
  dataProvider? : DataProvider
  dataProviderInstanceUUID? : string
}

@Component({
  selector: 'app-reports',
  imports: [
    ItemsManagerComponent,
    ItemsManagerNewItemFormDirective,
    InputComponent,
    SelectComponent,
    ButtonComponent,
    DialogComponent,
    TableComponent,
    TableColumnDirective
  ],
  templateUrl: './reports.component.html',
  styleUrl: './reports.component.less'
})
export class ReportsComponent implements OnInit {

  /**
   * Reference to the item that's selected within the list of data provider instances
   */
  dataProviderInstancesListSelectedItem! : DataProviderInstancePropertiesWithDataproviderRefernce

  /**
   * A set of available data providers to be wrapped into data provier instances
   */
  dataProviders! : DataProvider[]

  /**
   * The set of available data providers mapped by their UUIDs, for easy access
   */
  dataProvidersByDataProviderUUID! : Map<string, DataProvider>

  /**
   * Card data array for the data providers select control
   */
  dataProviderSelectOptions! : DataProviderCardData[]

  /**
   * Represents the selected card in the data provider select control
   */
  dataProviderSelectedOption? : DataProviderCardData

  /**
   * Array of all possible data provider porperty data types, for the data type selection box
   */
  dataProviderPropertyDataTypeSelectOptions : DataProviderPropertyDataTypeCardData[] = 
    allDataProviderPropertyDataTypes().map(dataType => ({
      title: "",
      text: DataProviderPropertyDataType[dataType],
      dataProviderProprtyDataType: dataType
    }))

  dataProviderPropertyMandatorySelectOptions : MandatoryCardData[] = [
    {
      title: "",
      text: "Mandatory",
      value: true
    },
    {
      title: "",
      text: "Optional",
      value: false
    }
  ]

  /**
   * Array that holds the selected option for each of the runtime parameters
   */
  dataProviderPropertySelectedOptions : DataProviderParameterSelectedOption[] = []

  /**
   * Controls the visibility of the data table dialog
   */
  dataTableDialogVisible : boolean = false

  /**
   * Controls the visibility of the runtime parameters dialog
   */
  runtimeParametersDialogVisible : boolean = false

  /**
   * Data set fetched from the back-end, to be displayed in the data table dialog
   */
  fetchedDataSet : ReportingDataSet = newReportingDataSet()

  /**
   * An array that contains the names of the columns of the fetched data set - populated after the data set is fetched
   */
  fetchedDataSetColumnNames : string[] = []

  /**
   * An array that contains the data of the fetched data set - populated after the data set is fetched
   */
  fetchedDataSetData : string[][] = []

  /**
   * This event emitter tells the data table to reload its data
   */
  dataTableReloadTrigger : EventEmitter<void> = new EventEmitter<void>()

  /**
   * Contains properties to be shown in the runtime parameters dialog
   */
  runtimeParametersDialogData : DataProviderInstanceProperty[] = []

  /**
   * The UUID of the data provider instance selected for running
   */
  runtimeParametersDialogDataProviderInstanceUUID : string = ""

  constructor(
    private reportingService : ReportingService
  ){}

  ngOnInit(): void {
    this.loadDataProviders().subscribe()
  }

  private loadDataProviders() : Observable<void> {
    return new Observable<void>(subscriber => {
      this.reportingService.findAllDataProviders().subscribe({
        next: dataProviders => {
          this.dataProviders = dataProviders

          this.dataProvidersByDataProviderUUID =
            new Map(
              this.dataProviders.map(dataProvider => [dataProvider.uuid ?? "", dataProvider])
            )

          this.dataProviderSelectOptions =
            dataProviders.map(dataProvider => ({
              title: dataProvider.humanReadableName,
              text: dataProvider.description,
              dataProvider: dataProvider
            })
          )

          complete(subscriber, undefined)
        },
        error: err => {
          // TODO: toast
          console.log(err)
        }
      })
    })
  }

  /**
   * Produces the list of data provider instances for the item manager
   */
  dataProviderInstancesListProducer : (() => Observable<ItemsManagerDataSet>) = () => {
    // Wait until the dataProviders are fetched
    return waitForCondition(() => isDefined(this.dataProviders))
      .pipe(
        // Once the data providers have been fetched, go ahead and fetch the data provider instances
        switchMap(() => 
          this.reportingService.findAllDataProviderInstances()
            .pipe(
              // Cast to data provider instances with data provider
              map(dataProviderInstances => dataProviderInstances as DataProviderInstanceWithDataproviderReference[]),

              // Add the data provider references
              map(dataProviderInstances => {
                dataProviderInstances.forEach(dataProviderInstance => {
                  const dataProvider : DataProvider | undefined = 
                    this.dataProvidersByDataProviderUUID.get(dataProviderInstance.dataProviderUUID)

                  if (dataProvider) {
                    dataProviderInstance.dataProvider = dataProvider
                  }
                })

                return dataProviderInstances
            })
          )
        )
      )
  }

  /**
   * Initializes a new, unsaved, data provider instance for the items manager
   */
  newDataProviderInstanceSupplier : (() => DataProviderInstancePropertiesWithDataproviderRefernce) = () => {
    this.dataProviderInstancesListSelectedItem = {
      dataProviderUUID: "",
      instanceName: "",
      instanceProperties: [],
      runtimeParameters: []
    }
    
    return this.dataProviderInstancesListSelectedItem
  }

  /**
   * Loads the details of the data provider instance and adds them to the item being edited
   * before the edit dialog appears
   */
  editedDataProviderInstanceTransformingMapper : ((item:DataProviderInstancePropertiesWithDataproviderRefernce) => Observable<DataProviderInstancePropertiesWithDataproviderRefernce>) =
    (item:DataProviderInstancePropertiesWithDataproviderRefernce) => {
        return new Observable<DataProviderInstancePropertiesWithDataproviderRefernce>(subscriber => {
          if (item.dataProviderInstanceUUID) {
            this.reportingService.findDataProviderInstanceDetails(item.dataProviderInstanceUUID).subscribe({
              next: data => {
                const dataWithDpRef = data as DataProviderInstancePropertiesWithDataproviderRefernce
                dataWithDpRef.dataProviderInstanceUUID = item.dataProviderInstanceUUID
                dataWithDpRef.dataProvider = this.dataProvidersByDataProviderUUID.get(dataWithDpRef.dataProviderUUID)
                dataWithDpRef.instanceName = item.dataProviderInstanceName ?? item.instanceName
                dataWithDpRef.dataProviderUUID = item.dataProviderUUID
                this.prepareSelectedDataProviderInstanceEditorProperties(dataWithDpRef)
                complete(subscriber, dataWithDpRef)
              },
              error: err => {
                // TODO: toast
                console.log(err)
              }
            })
          } else {
            complete(subscriber, {
              instanceName: "",
              dataProviderUUID: item.dataProviderUUID,
              instanceProperties: [],
              runtimeParameters: []
            })
          }
        })
    }

  /**
   * Saves a data provider instance for the items manager
   */
  dataProviderInstanceSavingConsumer : ((item:DataProviderInstancePropertiesWithDataproviderRefernce) => Observable<void>) =
    (item:DataProviderInstancePropertiesWithDataproviderRefernce) => {
      item.instanceProperties = this.dataProviderInstancesListSelectedItem.instanceProperties

      return errorConsumingObservableTransform(
        this.reportingService.saveDataProviderInstance(
          item,
          item.dataProviderInstanceUUID
        ),
        () => {},
        err => {
          // TODO: toast
          console.log(err)
        }
      )
  }

  /**
   * Deletes a data provider instance for the items manager
   */
  dataProviderInstanceDeletionConsumer : ((item:ItemsManagerDataItem<DataProviderInstanceWithDataproviderReference>) => Observable<void>) =
    (item:ItemsManagerDataItem<DataProviderInstanceWithDataproviderReference>) => errorConsumingObservableOperation(
      this.reportingService.deleteDataProviderInstance(item),
      err => {
        // TODO: toast
        console.log(err)
      }
    )

  /**
   * Validates a data provider instnace for the items manager, before saving
   */
  dataProviderInstanceValidator : ((item:DataProviderInstancePropertiesWithDataproviderRefernce) => boolean) =
    (item:DataProviderInstancePropertiesWithDataproviderRefernce) => {
      if (item) {
        return (
          isDefined(item.instanceName) &&
          isDefined(item.dataProvider) &&
          this.isInstancePropertiesArrayCorrect(item) &&
          this.isRuntimeParametersArrayCorrect(item)
        )
      }


      return false
    }
  isInstancePropertiesArrayCorrect(item:DataProviderInstancePropertiesWithDataproviderRefernce) : boolean {
    // If there are no instance properties defined by the data provider, then there is nothing to validate
    if (!(item.dataProvider?.instanceProperties)) {
      return true
    }

    return(
      // Data provider must have instance properties
      (item.dataProvider?.instanceProperties) &&

      // The number of instance properties defined for the instance must be the one required by the data provider
      (item.instanceProperties.length == item.dataProvider.instanceProperties.length) &&

      // The instance must define instance properties with the exact names as the ones specified by the data provider
      (
        item.dataProvider.instanceProperties
          .filter(dpInstProp => 
            item.instanceProperties.filter(iInstProp => iInstProp.propertyName == dpInstProp.name).length != 1
          )
          .length == 0
      ) &&
     
      // All instance properties must have values
      (
        item.instanceProperties
        .filter(instanceProperty => isEmptyString(instanceProperty.propertyValue))
        .length == 0
      )
    ) || false
  }

  isRuntimeParametersArrayCorrect(item:DataProviderInstancePropertiesWithDataproviderRefernce) : boolean {
    // Get the runtime parameters specified by the data provider instance
    const instanceParameters = item.runtimeParameters

    // If the data provider specifies runtime parameters, then make sure that all are specified
    if (item.dataProvider?.parameters) {
      if (
        // The number of runtime parameters defined by the instance must be greater than or equal to
        // the number of runtime parameters defined by the data provider
        (item.dataProvider.parameters.length > (instanceParameters?.length ?? 0)) ||

        // Each runtime parameter specified by the data provider must be found in the instance
        // with exactly the same data type
        (
          item.dataProvider.parameters
            .filter(dpParam => 
              instanceParameters?.filter(iParam => 
                iParam.parameterName == dpParam.name &&
                iParam.parameterDataType == dpParam.dataType
              ).length != 1
            )
            .length > 0
        )
      ) {
        return false
      }
    }

    // Every runtime parameter specified by the instance must have a name, a data type and a mandatory flag
    return (
      !instanceParameters || instanceParameters.length == 0 ||
      (
        instanceParameters.filter(param => 
          !isDefined(param.mandatory) ||
          !isDefined(param.parameterDataType) ||
          isEmptyString(param.parameterName)
        ).length == 0
      )
    )
  }

  dataProviderInstanceRunAction : ItemsManagerCardAction = 
    (dataProviderInstance:ItemsManagerDataItem<DataProviderInstanceWithDataproviderReference>) => {
      // If the data provider instance has an UUID then go ahead and run
      if (dataProviderInstance.dataProviderInstanceUUID) {
        // Fetch the data provider instance details
        this.reportingService.findDataProviderInstanceDetails(dataProviderInstance.dataProviderInstanceUUID)
          .subscribe({
            next: (dataProviderInstanceDetails:DataProviderInstanceProperties) => {
              // Once the data provider instance details have been fetched, check for runtime parameters
              // If there are runtime parameters, then show the runtime parameters dialog
              if (
                dataProviderInstanceDetails.runtimeParameters &&
                dataProviderInstanceDetails.runtimeParameters.length > 0
              ) {
                // Prepare the runtime parameters
                this.prepareRuntimeParametersDialogData(dataProviderInstanceDetails.runtimeParameters)

                // Store the UUID of the data provider instance selected for running
                this.runtimeParametersDialogDataProviderInstanceUUID = 
                  dataProviderInstance.dataProviderInstanceUUID ?? ""

                // Show the runtime parameters dialog
                this.showRuntimeParametersDialog()
              }
              // If there are no runtime parametes, then proceed straight to fetching the data
              else {
                this.runDataProviderInstance(dataProviderInstance.dataProviderInstanceUUID ?? "")
              }
            },
            error: err => {
              // TODO: toast
              console.log(err)
            }
          })
      }
      // If the data provider instance does not have an UUID, it means there's something wrong with it
      else {
        throw new Error("Data provider instance does not have an UUID")
      }
    }

  prepareRuntimeParametersDialogData(runtimeParameters:DataProviderInstanceRuntimeParameter[]) : void {
    this.runtimeParametersDialogData =
      runtimeParameters.map(runtimeParameter => ({
        propertyName: runtimeParameter.parameterName,
        propertyValue: runtimeParameter.parameterDefaultValue
      }))
  }

  runDataProviderInstance(dataProviderInstanceUUID:string, parameters?:Map<string, string>) : void {
    // Run the data provider instance
    this.reportingService.fetchDataProviderInstanceDataSet(
      dataProviderInstanceUUID,
      parameters
    ).subscribe({
      // Upon successful run, store the data set and show the data table dialog
      next: (dataSet:ReportingDataSet) => {
        this.storeDataSet(dataSet)
        this.hideRuntimeParametersDialog()
        this.showDataTableDialog()
      },
      error: err => {
        // TODO: toast
        console.log(err)
      }
    })
  }

  /**
   * Extracts the dashboard name for the item manager
   */
  dataProviderInstanceCardTitleExtractor : ItemsManagerCardPropertyExtractor =
    (item:DataProviderInstanceWithDataproviderReference) => item.dataProviderInstanceName

  /**
   * Extracts dashboard's description
   */
  dataProviderInstanceCardTextExtractor : ItemsManagerCardPropertyExtractor = 
    (item:DataProviderInstanceWithDataproviderReference) => 
      item.dataProvider.humanReadableName + ": " + item.dataProvider.description

  /**
   * Triggered by selecting a data provider in the data provider instance editing dialog
   */
  onSelectedDataProviderChange : ((event:CardData|undefined) => void) = (event:CardData|undefined) => {
    const dataProviderSelectEvent : DataProviderCardData = event as DataProviderCardData
    this.dataProviderSelectedOption = this.identifyDataProviderSelectOption(dataProviderSelectEvent.dataProvider.uuid)

    if (dataProviderSelectEvent.dataProvider) {
      // Set the data provider
      this.dataProviderInstancesListSelectedItem.dataProvider = dataProviderSelectEvent.dataProvider
      this.dataProviderInstancesListSelectedItem.dataProviderUUID = dataProviderSelectEvent.dataProvider.uuid ?? ""

      // Prepare the instance properties
      this.prepareDataProviderInstanceProperties(dataProviderSelectEvent.dataProvider)  

      // Prepare the runtime parameters
      this.dataProviderInstancesListSelectedItem.runtimeParameters = []
      this.dataProviderPropertySelectedOptions = []
    }
  }

  /**
   * Triggered by clicking the "Run" button near the data provider selection box in the data provider instance
   * editor dialog
   */
  onRunSelectedDataProviderButtonClick : (() => void) = () => {
    // Get the selected data provider
    const selectedDataProvider = this.dataProviderSelectedOption?.dataProvider

    // If there is a data provider selected and it has an UUID, then proceed
    if (selectedDataProvider?.uuid) {
      const properties : Map<string,string> = new Map<string,string>()
      this.dataProviderInstancesListSelectedItem.instanceProperties.forEach(a => properties.set(a.propertyName, a.propertyValue))

      const parameters : Map<string,string> = new Map<string,string>()
      this.dataProviderInstancesListSelectedItem.runtimeParameters.forEach(a => parameters.set(a.parameterName, a.parameterDefaultValue))

      this.fetchDataProviderData(
        selectedDataProvider.uuid,
        properties,
        parameters
      ).subscribe({
        next: () => {
          this.showDataTableDialog()
        },
        error: err => {
          // TODO: toast
          console.log(err)
        }
      })
    }
  }

  onNewDataProviderInstance : (() => void) = () => {
    delete this.dataProviderSelectedOption
  }

  onDataTableDialogVisibilityChange(visibility:boolean) {
    this.dataTableDialogVisible = visibility
  }

  onRuntimeParametersDialogVisibilityChange(visibility:boolean) {
    this.runtimeParametersDialogVisible = visibility
  }

  onAddRuntimeParameterButtonClick() {
    this.dataProviderInstancesListSelectedItem.runtimeParameters.push({
      mandatory: false,
      parameterName: "",
      parameterDataType: DataProviderPropertyDataType.STRING,
      parameterDefaultValue: ""
    })

    this.dataProviderPropertySelectedOptions.push(this.newDataProviderPropertySelectedOptions())
  }

  onDelRuntimeParameterButtonClick(paramIndex:number) {
    this.dataProviderInstancesListSelectedItem.runtimeParameters.splice(paramIndex,1)
  }

  onSelectedDataProviderPropertyTypeChange(paramIndex:number, event:CardData|undefined) : void {
    // Cast the event to the proper card data type
    const dataTypeCardData : DataProviderPropertyDataTypeCardData = event as DataProviderPropertyDataTypeCardData

    // Get the information from the card data event and put it into the runtime parameter
    this.dataProviderInstancesListSelectedItem.runtimeParameters[paramIndex].parameterDataType = 
      dataTypeCardData.dataProviderProprtyDataType

    // Assign the selected option
    this.dataProviderPropertySelectedOptions[paramIndex].parameterDataType = dataTypeCardData
  }

  onSelectedDataProviderPropertyMandatoryChange(paramIndex:number, event:CardData|undefined) : void {
    // Cast the event to the proper card data type
    const mandatoryCardData : MandatoryCardData = event as MandatoryCardData

    // Get the information from the card data event and put it into the runtime parameter
    this.dataProviderInstancesListSelectedItem.runtimeParameters[paramIndex].mandatory = 
      mandatoryCardData.value

    // Assign the selected option
    this.dataProviderPropertySelectedOptions[paramIndex].mandatory = mandatoryCardData
  }

  onRunSelectedDataProviderInstanceButtonClick() : void {
    // Prepare the runtime parameters map
    const runtimeParameters : Map<string,string> = new Map<string,string>()
    this.runtimeParametersDialogData.forEach(param => {
      runtimeParameters.set(param.propertyName, param.propertyValue)
    })

    // Start running the data provider instance
    this.runDataProviderInstance(
      this.runtimeParametersDialogDataProviderInstanceUUID,
      runtimeParameters
    )
  }

  newDataProviderPropertySelectedOptions() : DataProviderParameterSelectedOption {
    return {
      parameterDataType: this.dataProviderPropertyDataTypeSelectOptions[0],
      mandatory: this.dataProviderPropertyMandatorySelectOptions[0]
    }
    
    
  }

  isDataProviderSelected() : boolean {
    if (this.dataProviderSelectedOption) {
      return true
    }

    return false
  }

  /**
   * Re-initializes the properties used by the data provider instance editor dialog
   * @param dataProviderInstance the selected data provider instance
   */
  prepareSelectedDataProviderInstanceEditorProperties(dataProviderInstance:DataProviderInstancePropertiesWithDataproviderRefernce) : void {
    // Prepare the selected data provider
    this.dataProviderSelectedOption = this.identifyDataProviderSelectOption(dataProviderInstance.dataProvider?.uuid)

    if (dataProviderInstance.dataProvider) {
      // Prepare the instance properties
      this.prepareDataProviderInstanceProperties(
        dataProviderInstance.dataProvider,
        dataProviderInstance.instanceProperties
      )

      // Prepare the runtime parameters
      if (dataProviderInstance.runtimeParameters) {
        this.prepareDataProviderInstanceRuntimeParameters(dataProviderInstance.runtimeParameters)
      }
      
    }
  }

  identifyDataProviderSelectOption(dataProviderUUID?:string) : DataProviderCardData {
    return this.dataProviderSelectOptions.filter(o => o.dataProvider.uuid == dataProviderUUID)[0]
  }

  prepareDataProviderParametersDialog(dataProvider : DataProvider) {
    this.dataProviderInstancesListSelectedItem.runtimeParameters = []
    dataProvider.parameters.forEach(parameter => {
      this.dataProviderInstancesListSelectedItem.runtimeParameters.push({
        parameterName: parameter.name,
        parameterDefaultValue: "",
        parameterDataType: DataProviderPropertyDataType.STRING,
        mandatory: false
      })
    })
  }

  prepareDataProviderInstanceProperties(
    dataProvider : DataProvider,
    instanceProperties? : DataProviderInstanceProperty[]
  ) {
    this.dataProviderInstancesListSelectedItem.instanceProperties = []
    dataProvider.instanceProperties.forEach(instanceProperty => {
      let propertyValue : string = ""
      if (instanceProperties) {
        const ipSubset : DataProviderInstanceProperty[] = instanceProperties.filter(ip => ip.propertyName == instanceProperty.name)
        if (ipSubset) {
          propertyValue = ipSubset[0]?.propertyValue ?? ""
        }
      }

      this.dataProviderInstancesListSelectedItem.instanceProperties.push({
        propertyName: instanceProperty.name,
        propertyValue: propertyValue
      })
    })
  }

  prepareDataProviderInstanceRuntimeParameters(runtimeParameters : DataProviderInstanceRuntimeParameter[]) {
    this.dataProviderInstancesListSelectedItem.runtimeParameters = runtimeParameters

    let paramIndex = 0
    for (let param of this.dataProviderInstancesListSelectedItem.runtimeParameters) {
      this.dataProviderPropertySelectedOptions.push({
        parameterDataType: this.dataProviderPropertyDataTypeSelectOptions.filter(t => t.dataProviderProprtyDataType == param.parameterDataType)[0],
        mandatory: this.dataProviderPropertyMandatorySelectOptions.filter(m => m.value == param.mandatory)[0]
      })

      paramIndex++
    }
  }

  fetchDataProviderData(
    dataProviderUUID:string,
    instanceProperties?: Map<string,string>,
    runtimeParameters?:Map<string,string>
  ) : Observable<void> {
    return new Observable<void>(subscriber => {
      this.reportingService
        .fetchDataProviderDataSet(dataProviderUUID, instanceProperties, runtimeParameters)
        .subscribe({
          next: dataSet => {
            this.storeDataSet(dataSet)
            complete(subscriber, undefined)
          },
          error: err => {
            // TODO: toast
            console.log(err)
          }
        })
    })
  }

  storeDataSet(dataSet:ReportingDataSet) : void {
    this.fetchedDataSet = dataSet
    this.fetchedDataSetData = this.fetchedDataSet.data
    this.fetchedDataSetColumnNames = this.extractDataSetColumnNames(this.fetchedDataSet)
  }

  extractDataSetColumnNames(dataSet:ReportingDataSet) : string[] {
    return dataSet.columns.map(col => col.name)
  }

  showDataTableDialog() {
    this.dataTableDialogVisible = true
    setTimeout(() => this.dataTableReloadTrigger.emit(), 100)
  }

  hideDataTableDialog() {
    this.dataTableDialogVisible = false
  }

  isDataTableDialogVisible() : boolean {
    return this.dataTableDialogVisible
  }

  showRuntimeParametersDialog() {
    this.runtimeParametersDialogVisible = true
  }

  hideRuntimeParametersDialog() {
    this.runtimeParametersDialogVisible = false
  }

  isRuntimeParametersDialogVisible() : boolean {
    return this.runtimeParametersDialogVisible
  }

  getFetchedDataSetData() : string[][] {
    return this.fetchedDataSetData
  }

  getFetchedDataSetColumnNames() : string[] {
    return this.fetchedDataSetColumnNames
  }

}

