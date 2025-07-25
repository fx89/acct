import { OnChanges, Component, EventEmitter, input, InputSignal, Output, OnInit } from '@angular/core';
import { CatalogService } from '../../services-acct/catalog.service';
import { IconProperties } from '../../model-acct/icon-properties';
import {v4 as uuidv4} from 'uuid';
import { DataScrollerComponent, DataScrollerFilter, DataScrollerItemDirective, DataScrollerPageRequest, DataScrollerPageResponse } from '../../components-gui/data-scroller/data-scroller.component';
import { Observable } from 'rxjs';
import { CardData } from '../../components-gui/cards-list/card-data';
import { IconQueryParams } from '../../model-acct/icon-query-params';
import { PictureFrameComponent } from '../../components-gui/picture-frame/picture-frame.component';
import { ButtonComponent } from '../../components-gui/button/button.component';
import { DialogComponent } from '../../components-gui/dialog/dialog.component';
import { InputComponent } from '../../components-gui/input/input.component';
import { ItemsManagerCardPropertyExtractor, ItemsManagerComponent, ItemsManagerDataItem, ItemsManagerDataSet, ItemsManagerNewItemFormDirective } from '../items-manager/items-manager.component';

/**
 * Contains both properties (meta-data) and the base64-encoded bytes of the icon
 */
interface IconData {
  /**
   * The properties (meta-data)
   */
  iconProperties : IconProperties,

  /**
   * The base64-encoded bytes of the icon
   */
  iconBase64 : string
}

/**
 * Container used for passing the icon category name to and from the items manager
 */
interface IconCategoryNameContainer {
  iconCategoryName : string
}

/**
 * The size of the page if not configured
 */
const DEFAULT_PAGE_SIZE : number = 30

@Component({
  selector: 'app-icons-manager',
  imports: [
    DataScrollerComponent,
    PictureFrameComponent,
    DataScrollerItemDirective,
    ButtonComponent,
    DialogComponent,
    InputComponent,
    ItemsManagerComponent,
    ItemsManagerNewItemFormDirective
  ],
  templateUrl: './icons-manager.component.html',
  styleUrl: './icons-manager.component.less'
})
export class IconsManagerComponent implements OnChanges, OnInit {

  /**
   * The ID of the component is unique in the page
   */
  public id = uuidv4()

  // Settings
  allowUpload           : InputSignal<boolean> = input(false)
  allowDelete           : InputSignal<boolean> = input(false)
  allowManageCategories : InputSignal<boolean> = input(false)
  pageSize              : InputSignal<number>  = input(DEFAULT_PAGE_SIZE)

  // Properties
  width         : InputSignal<string>  = input("400px")
  height        : InputSignal<string>  = input("300px")
  iconWidth     : InputSignal<string>  = input("198px")
  iconHeight    : InputSignal<string>  = input("198px")


  // Private properties
  uploadAllowed      : boolean = false
  deleteAllowed      : boolean = false
  widthStr           : string = ""
  heightStr          : string = ""
  iconWidthStr       : string = ""
  iconHeightStr      : string = ""
  selectAllowed      : boolean = false
  multiSelectAllowed : boolean = false
  pageSizeNum        : number = DEFAULT_PAGE_SIZE

  // Dialog visibility switches
  iconUploadDialogVisible : boolean = false
  iconCategoriesManagerVisible : boolean = false

  // Picture upload dialog-related properties
  newPictureName     : string = ""
  newPictureMimeType : string = ""
  newPictureContent  : string = ""

  // Icon category creation-related properties
  newIconCategoryName : string = ""
 
  /**
   * The selected icons category (if any)
   */
  private selectedIconCategoryName : string = ""

  /**
   * Filters definition for the data-scroller
   */
  dataScrollerFilters : DataScrollerFilter[] = [
    {
      filterName: "Category",
      possibleValueCards: []
    }
  ]

  /**
   * Triggers the reloading of the data scroller
   */
  dataScrollerReloadEventEmitter : EventEmitter<void> = new EventEmitter<void>


  // Events
  @Output() selectionChange : EventEmitter<IconProperties> = new EventEmitter<IconProperties>()



  constructor(private catalogService : CatalogService) {

  }


  ngOnInit() : void {
    this.uploadAllowed = this.allowUpload()
    this.deleteAllowed = this.allowDelete()
    this.widthStr = this.width()
    this.heightStr = this.height()
    this.iconWidthStr = this.iconWidth()
    this.iconHeightStr = this.iconHeight()
    this.selectAllowed = true
    this.multiSelectAllowed = this.allowDelete()
    this.pageSizeNum = this.pageSize()
    this.initDataScrollerFilters()
  }

  ngOnChanges() : void {
    
  }

  iconCategoriesListProducer : () => Observable<ItemsManagerDataSet> =
    () => this.catalogService.findIconCategories()

  iconCategoryCardTextExtractor : ItemsManagerCardPropertyExtractor = (iconCategoryName:string) => iconCategoryName

  iconCategoriesListSelectedItem : string = ""

  iconCategoryDeletionConsumer : ((item:ItemsManagerDataItem<string>) => Observable<void>) =
    (iconCategoryName:string) => {
      return new Observable<void>(subscriber => {
        // Delete the icon category
        this.catalogService.deleteIconCategory(iconCategoryName).subscribe({
          // Re-initialize the data scroller filters upon completion
          next: () => {
            this.initDataScrollerFilters()
            subscriber.next()
            subscriber.complete()
          },
          error: err => subscriber.error(err)
        })
      })
    }

  newIconCategorySupplier : (() => IconCategoryNameContainer) = () => {
    return {
      iconCategoryName: ""
    }
  }

  iconCategorySavingConsumer : ((iconCategoryNameContainer:IconCategoryNameContainer) => Observable<void>) =
    (iconCategoryNameContainer:IconCategoryNameContainer) => {
      return new Observable<void>(subscriber => {
        // Create the icon category
        this.catalogService.createIconCategory(iconCategoryNameContainer.iconCategoryName).subscribe({
          // Re-initialize the data scroller filters upon completion
          next: () => {
            this.initDataScrollerFilters()
            subscriber.next()
            subscriber.complete()
          }, 
          error: err => subscriber.error(err)
        })
      })
    }

  iconCategoryValidator : ((iconCategoryNameContainer:IconCategoryNameContainer) => boolean) =
    (iconCategoryNameContainer:IconCategoryNameContainer) => iconCategoryNameContainer?.iconCategoryName != ""

  /**
   * Retrieve the icons category from the catalog service.
   * Upon retrieval, populate the categories filter.
   */
  private initDataScrollerFilters() : void {
    // Init the manage button
    if (this.allowManageCategories()) {
      this.dataScrollerFilters[0].manageButtonIconRef = "button-icons/list.png"
      this.dataScrollerFilters[0].manageButtonAction = () => this.showIconCategoriesManager()
    }

    // Init the icon categories list
    this.initIconCategoriesList()
  }

  private initIconCategoriesList() : void {
    this.catalogService.findIconCategories().subscribe({
      next: iconCategoryNames => {
        this.dataScrollerFilters[0].possibleValueCards = 
          iconCategoryNames.map(iconCategoryName => <CardData>{
            title: iconCategoryName,
            text: iconCategoryName
          })
      },
      error: err => {
        // TODO: Toast
      }
    })
  }

  dataScrollerPageRequestCallback : ((pageRequest:DataScrollerPageRequest) => Observable<DataScrollerPageResponse<IconData>>) =
    (pageRequest:DataScrollerPageRequest) => new Observable<DataScrollerPageResponse<IconData>>(subscriber => {
      // Reset the selected icon category name
      this.selectedIconCategoryName = ""

      // Prepare the query parameters
      const iconQueryParams : IconQueryParams = {
        iconCategoryName: undefined,
        iconNamePattern: undefined
      }

      // If the icon category name is selected, then add it to the query parameters and
      // set the selected icon category name
      if (pageRequest.appliedFiltersValues[0].selectedCardData) {
        iconQueryParams.iconCategoryName = pageRequest.appliedFiltersValues[0].selectedCardData.text
        this.selectedIconCategoryName = iconQueryParams.iconCategoryName
      }

      // If the search box is populated, then add the value to the query parameters
      if (pageRequest.searchBoxValue && pageRequest.searchBoxValue != "") {
        iconQueryParams.iconNamePattern = pageRequest.searchBoxValue
      }

      // Find the page of icons
      this.catalogService.findIcons({
        queryParameters : iconQueryParams,
        pageNumber : pageRequest.pageNumber,
        pageSize : pageRequest.pageSize
      })
      .subscribe({
        next : iconsPage => {
          // Initialize the data array
          const data : IconData[] = []

          // Push each of the loaded icon properties to the newly initialized array
          iconsPage.data.forEach(iconProperties => data.push({
            iconProperties : iconProperties,
            iconBase64 : ""
          }))

          // For each of the elements in the data array, load the icon bytes and assign them to the icon
          data.forEach(iconData => {
            // Load the icon bytes from the catalog service
            this.catalogService.loadIconBytesBase64(iconData.iconProperties.iconUUID).subscribe({
              // Upon successful load, link the bytes into the data element
              next: iconBase64 => {
                iconData.iconBase64 = iconBase64
              },
              error: err => {
                console.log(err)
                // TODO: Toast
              }
            })
          })

          // In any case, announce that the new page data is ready,
          // as the image content will be updated on the go
          subscriber.next({
            dataSetSize: iconsPage.page.totalElements,
            pageData: data
          })
        },
        error: err => {
          console.log(err)
          // TODO: Toast
        }
      })
    })

  dataScrollerElementUniqueKeyFunction : (element:IconData) => string =
    (element:IconData) => element.iconProperties.iconUUID

  dataScrollerSelectionChange(selectedIcons : IconData[]) : void {
    if (selectedIcons.length > 0) {
      this.selectionChange.emit(selectedIcons[0].iconProperties)
    }
  }

  onAddNewIconButtonClick() : void {
    this.showIconUploadDialog()
  }

  onDeleteSelectedIconsButtonClick() : void {
    // TODO: implement
  }

  onPictureFileSelected(event : Event) : void {
    const input = event.target as HTMLInputElement
    const file = input.files?.[0]

    if (file) {
      // Create a file reader for the picture file
      const reader = new FileReader();

      // Set up the file reader to process the loaded file when the loading finishes
      reader.onload = () => {
        // Read the uploaded picture's data and meta-data
        this.newPictureMimeType = file.type
        this.newPictureContent = reader.result as string
      };

      // Tell the reader to start reading te file
      reader.readAsDataURL(file);
    }
  }

  onNewPictureUploadButtonClick() : void {
    this.catalogService.createIcon({
      iconCategoryName : this.selectedIconCategoryName,
      iconName         : this.newPictureName,
      iconMimeType     : this.newPictureMimeType,
      iconBase64       : this.newPictureContent
    })
    .subscribe({
      next: () => {
        // Reload the content of the data scroller
        this.dataScrollerReloadEventEmitter.emit()

        // Clear the data
        this.clearSelectedPicture()

        // Hide this dialog
        this.hideIconUploadDialog()
      },
      error: err => {
        // TODO: toast
        console.log(err)
      }
    })
  }

  onNewPictureUploadCancelButtonClick() : void {
    this.clearSelectedPicture()
    this.hideIconUploadDialog()
  }

  onIconCategoryCardsSelectionChange(card : CardData) : void {

  }

  private showIconUploadDialog() : void {
    this.iconUploadDialogVisible = true
  }

  private hideIconUploadDialog() : void {
    this.iconUploadDialogVisible = false
  }

  private showIconCategoriesManager() : void {
    this.iconCategoriesManagerVisible = true
  }

  private clearSelectedPicture() : void {
    this.newPictureContent = ""
    this.newPictureMimeType = ""
    this.newPictureName = ""
  }

  getWidth() : string {
    return this.widthStr
  }

  getHeight() : string {
    return this.heightStr
  }

  getIconWidth() : string {
    return this.iconWidthStr
  }

  getIconHeight() : string {
    return this.iconHeightStr
  }

  getPageSize() : number {
    return this.pageSizeNum
  }

  getDataScrollerFilters() : DataScrollerFilter[] {
    return this.dataScrollerFilters
  }

  isSelectAllowed() : boolean {
    return this.selectAllowed
  }

  isMultiSelectAllowed() : boolean {
    return this.multiSelectAllowed
  }

  isUploadAllowed() : boolean {
    return this.uploadAllowed
  }

  isDeleteAllowed() : boolean {
    return this.deleteAllowed
  }

  isIconsCategorySelected() : boolean {
    return this.selectedIconCategoryName != ""
  }

  isIconUploadDialogVisible() : boolean {
    return this.iconUploadDialogVisible
  }

  isIconCategoriesManagerVisible() : boolean {
    return this.iconCategoriesManagerVisible
  }

  isNewPictureNameValid() : boolean {
    return this.newPictureName != ""
  }

  isNewPictureContentValid() : boolean {
    if (this.newPictureContent && this.newPictureContent != "") {
      return true
    }

    return false
  }

  isNewPictureSavingAllowed() : boolean {
    return this.isNewPictureNameValid() && this.isNewPictureContentValid()
  }

}