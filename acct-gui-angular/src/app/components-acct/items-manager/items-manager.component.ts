import { AfterContentInit, AfterViewChecked, AfterViewInit, Component, ContentChildren, Directive, EventEmitter, input, InputSignal, OnInit, Output, QueryList, TemplateRef } from '@angular/core';
import {v4 as uuidv4} from 'uuid';
import { CardsListComponent } from '../../components-gui/cards-list/cards-list.component';
import { CardData } from '../../components-gui/cards-list/card-data';
import { Observable } from 'rxjs';
import { ButtonComponent } from '../../components-gui/button/button.component';
import { MsgboxComponent } from '../../components-gui/msgbox/msgbox.component';
import { MsgboxType } from '../../components-gui/msgbox/msgbox-type';
import { DialogComponent } from '../../components-gui/dialog/dialog.component';
import { CommonModule } from '@angular/common';

/**
 * Generic item type for the items in the data set managed by the items manager
 */
export type ItemsManagerDataItem<T> = T

/**
 * Defnies the data set managed by the items manager
 */
export type ItemsManagerDataSet = ItemsManagerDataItem<any>[]

/**
 * Defines a mapper function that has the purpose of extracting a card property
 * required for populating cards in the cards list component of the items manager
 */
export type ItemsManagerCardPropertyExtractor = (item:ItemsManagerDataItem<any>) => string

/**
 * Extends the CardData type with the represented item
 */
interface ItemCardData extends CardData {
  item : ItemsManagerDataItem<any>
}

@Directive({ selector: '[itemsManagerNewItemForm]'})
export class ItemsManagerNewItemFormDirective {
  constructor(public templateRef: TemplateRef<any>) { }
}

@Component({
  selector: 'app-items-manager',
  imports: [
    CardsListComponent,
    ButtonComponent,
    MsgboxComponent,
    DialogComponent,
    CommonModule
  ],
  templateUrl: './items-manager.component.html',
  styleUrl: './items-manager.component.less'
})
export class ItemsManagerComponent implements OnInit, AfterContentInit {

  /**
   * The ID of the component is unique in the page
   */
  public id = uuidv4()

  // Properties
  width           : InputSignal<string> = input("380px")
  height          : InputSignal<string> = input("400px")
  cardWidth       : InputSignal<string> = input("348px")
  cardHeight      : InputSignal<string> = input("50px")
  cardSpacing     : InputSignal<string> = input("5px")
  addDialogWidth  : InputSignal<string> = input("500px")
  addDialogHeight : InputSignal<string> = input("300px")

  // Message box types
  itemDeletionConfirmationMessageBoxType : MsgboxType = MsgboxType.YES_NO

  // Force reload event
  forceReloadEventEmitter : InputSignal<EventEmitter<void>> = input(new EventEmitter<void>())

  // Functions
  dataSetProducerFunction         : () => Observable<ItemsManagerDataSet> = () => new Observable<ItemsManagerDataSet>()
  cardImageRefExtractorFunction   : ItemsManagerCardPropertyExtractor = () => ""
  cardTitleExtractorFunction      : ItemsManagerCardPropertyExtractor = () => ""
  cardTextExtractorFunction       : ItemsManagerCardPropertyExtractor = () => ""
  itemDeletionFunction            : ((item:ItemsManagerDataItem<any>) => Observable<void>) = () => new Observable<void>()
  newItemSupplierFunction         : () => ItemsManagerDataItem<any> = () => {}
  itemPropertiesValidatorFunction : ((item:ItemsManagerDataItem<any>) => boolean) = () => true
  itemSavingFunction              : (item:ItemsManagerDataItem<any>) => Observable<void> = () => new Observable<void>

  /**
   * When called, this function produces an observable for the data set to be displayed by the items manager
   */
  dataSetProducer : InputSignal<() => Observable<ItemsManagerDataSet>> = input.required()

  /**
   * Mapper that extracts the src of the image to be displayed on the card that represents one item in the
   * data set. If not specified, then the card will not be rendering the image.
   */
  cardImageRefExtractor : InputSignal<ItemsManagerCardPropertyExtractor | undefined> = input()

  /**
   * Mapper that extracts the title of the card that represents one item in the data set. If not specified,
   * then the card will not be rendering the title.
   */
  cardTitleExtractor : InputSignal<ItemsManagerCardPropertyExtractor | undefined> = input()

  /**
   * Mapper that extracts the text of the card that represents one item in the data set. If not specified,
   * then the card will not be rendering the text.
   */
  cardTextExtractor : InputSignal<ItemsManagerCardPropertyExtractor | undefined> = input()

  /**
   * Consumer that deletes a given item and returns an observable that tells the items manager when the
   * deletion was successful
   */
  itemDeletionConsumer : InputSignal<((item:ItemsManagerDataItem<any>) => Observable<void>)> = input.required()

  /**
   * Supplier that initializes a new item for the new item creation form
   */
  newItemSupplier : InputSignal<() => ItemsManagerDataItem<any>> = input.required()

  /**
   * Consumer that saves the new item initialized using the the newItemInitializationRunnable
   * and returns an observable to let the items manager know when the saving operation is complete
   */
  itemSavingConsumer : InputSignal<(item:ItemsManagerDataItem<any>) => Observable<void>> = input.required()

  /**
   * Mapper that tells the items manager if a given item is valid for saving
   */
  itemPropertiesValidator : InputSignal<(item:ItemsManagerDataItem<any>) => boolean> = input.required()


  /**
   * The data set
   */
  dataSet : ItemCardData[] = []

  /**
   * The selected item
   */
  selection : InputSignal<ItemsManagerDataItem<any>> = input()

  /**
   * The new item
   */
  newItem : ItemsManagerDataItem<any>

  // Events
  @Output() selectionChange : EventEmitter<ItemsManagerDataItem<any>> = new EventEmitter<ItemsManagerDataItem<any>>()
  @Output() onSelectionChanged : EventEmitter<ItemsManagerDataItem<any>> = new EventEmitter<ItemsManagerDataItem<any>>()

  // Content children
  @ContentChildren(ItemsManagerNewItemFormDirective)
  private newItemFormDirectives? : QueryList<ItemsManagerNewItemFormDirective>

  // Internal properties
  private widthStr           : string = ""
  private heightStr          : string = ""
  private cardWidthStr       : string = ""
  private cardHeightStr      : string = ""
  private cardSpacingStr     : string = ""
  private addDialogWidthStr  : string = ""
  private addDialogHeightStr : string = ""

  private selectedCard?  : ItemCardData

  newItemFormTemplateRef! : TemplateRef<any>

  // Dialog visibility switches
  itemDeletionConfirmationMessageBoxVisible : boolean = false
  newItemCreationDialogVisible : boolean = false

  ngOnInit() : void {
    this.initProperties()
    this.initFunctions()
    this.initDataSet()
    this.initForceReloadEventHandler()
  }

  ngAfterContentInit(): void {
    this.initNewItemFormTemplateRef()
  }

  private initProperties() : void {
    this.widthStr = this.width()
    this.heightStr = this.height()
    this.cardWidthStr = this.cardWidth()
    this.cardHeightStr = this.cardHeight()
    this.cardSpacingStr = this.cardSpacing()
    this.addDialogWidthStr = this.addDialogWidth()
    this.addDialogHeightStr = this.addDialogHeight()
  }

  private initFunctions() : void {
    this.dataSetProducerFunction         = this.dataSetProducer()
    this.cardImageRefExtractorFunction   = this.cardImageRefExtractor() ?? (() => "")
    this.cardTitleExtractorFunction      = this.cardTitleExtractor() ?? (() => "")
    this.cardTextExtractorFunction       = this.cardTextExtractor() ?? (() => "")
    this.itemDeletionFunction            = this.itemDeletionConsumer()
    this.newItemSupplierFunction         = this.newItemSupplier()
    this.itemSavingFunction              = this.itemSavingConsumer()
    this.itemPropertiesValidatorFunction = this.itemPropertiesValidator()
  }

  private initNewItemFormTemplateRef() : void {
    const templateRef : TemplateRef<any> | undefined = this.newItemFormDirectives?.get(0)?.templateRef

    if (templateRef) {
      this.newItemFormTemplateRef = templateRef
    }
  }

  private initDataSet() : void {
    this.dataSetProducerFunction().subscribe({
      next: data => {
        if (data) {
          this.createCards(data)
        }
      },
      error: err => {
        // TODO: Toast
        console.log(err)
      }
    })
  }

  private initForceReloadEventHandler() : void {
    this.forceReloadEventEmitter().asObservable().subscribe(() => this.initDataSet())
  }

  private createCards(data:ItemsManagerDataSet) : void {
    // Reset the data set
    this.dataSet = []

    // Populate the data set with cards created from the items in the data array
    data.forEach(item => {
      this.dataSet.push({
        item     : item,
        title    : this.cardTitleExtractorFunction(item),
        text     : this.cardTextExtractorFunction(item),
        imageRef : this.cardImageRefExtractorFunction(item)
      })
    })
  }

  onCardSelected(card:CardData) : void {
    this.selectedCard = card as ItemCardData;
    this.selectionChange.emit(this.selectedCard.item)
    this.onSelectionChanged.emit(this.selectedCard.imageRef)
  }

  onCreateNewButtonClick() : void {
    this.newItem = this.newItemSupplierFunction()
    this.showNewItemCreationDialog()
  }

  onDeleteSelectedButtonClick() : void {
    if (this.selectedCard) {
      this.showItemDeletionConfirmationMessageBox()
    }
  }

  onItemDeletionConfirmation() : void {
    if (this.selectedCard) {
      this.itemDeletionFunction(this.selectedCard?.item).subscribe({
        next: () => {
          this.initDataSet()
        },
        error: err => {
          // TODO: Toast
          console.log(err)
        }
      })
    }
  }

  onNewItemSaveButtonClick() : void {
    this.itemSavingFunction(this.newItem).subscribe({
      next: () => {
        this.initDataSet()
        this.hideNewItemCreationDialog()
      },
      error: err => {
        // TODO: Toast
        console.log(err)
      }
    })
  }

  onNewItemCancelButtonClick() : void {
    this.hideNewItemCreationDialog()
  }

  private showItemDeletionConfirmationMessageBox() : void {
    this.itemDeletionConfirmationMessageBoxVisible = true
  }

  private showNewItemCreationDialog() : void {
    this.newItemCreationDialogVisible = true
  }

  private hideNewItemCreationDialog() : void {
    this.newItemCreationDialogVisible = false
  }

  isDeleteSelectedButtonEnabled() : boolean {
    if (this.selectedCard) {
      return true
    }

    return false
  }

  isItemDeletionConfirmationMessageBoxVisible() : boolean {
    return this.itemDeletionConfirmationMessageBoxVisible
  }

  isNewItemFormTemplateDefined() : boolean {
    if (this.newItemFormTemplateRef) {
      return true
    }

    return false
  }

  isNewItemValid(item:ItemsManagerDataItem<any>) : boolean {
    return this.itemPropertiesValidatorFunction(item)
  }

  getWidth() : string {
    return this.widthStr
  }

  getHeight() : string {
    return this.heightStr
  }

  getCardWidth() : string {
    return this.cardWidthStr
  }

  getCardHeight() : string {
    return this.cardHeightStr
  }

  getCardSpacing() : string {
    return this.cardSpacingStr
  }

  getAddDialogWidth() : string {
    return this.addDialogWidthStr
  }

  getAddDialogHeight() : string {
    return this.addDialogHeightStr
  }
  
}
