import { Observable } from "rxjs"
import { complete, newObservable } from "../utils-reusalbe/rxjs-utils"
import { mergeArrays } from "../utils-reusalbe/array-utils"
import { AcctPage } from "../model-acct/acct-page"

/**
 * Manages page loading and tracking, makes sure the data set is coherent
 * and handles resetting the data set.
 * 
 */
export class RecordsManager<T,K> {
  /**
   * Contains the current page
   */
  private records: T[] = []

  /**
   * The number of the last loaded page
   */
  private lastLoadedPageNumber : number = -1

  /**
   * If this page has been reached, then no more scrolling is possible until a reset is performed
   */
  private lastAvailablePageNumber? : number

  constructor(
    private pageSize : number,
    private pageLoadingObservableSupplier : (pageNumber:number, pageSize:number) => Observable<AcctPage<T>>,
    private recordKeyExtractor : (record:T) => K
  ) {

  }

  public reset() : void {
    this.lastLoadedPageNumber = -1
    delete this.lastAvailablePageNumber
    this.records = []
  }

  public loadNextPage() : Observable<void> {
    // If the last loaded page is the last available page, then do not load anything
    if (this.areAllPagesLoaded()) {
      return newObservable(undefined)
    }

    // If the last loaded page is not the last available page, then load the next page
    return new Observable<void>(subscriber => {
      // Compute the page number to load as the last loaded page number plus 1
      const pageNumberToLoad : number = this.lastLoadedPageNumber + 1

      // Start loading the page
      this.pageLoadingObservableSupplier(pageNumberToLoad, this.pageSize).subscribe({
        // When loading is finished
        next: page => {
          // Add the records to the page
          this.records = mergeArrays(this.records, page.data, this.recordKeyExtractor)

          // Set the last available page number
          this.lastAvailablePageNumber = page.page.totalPages - 1

          // The content is now loaded, so set the last loaded page number
          this.lastLoadedPageNumber = pageNumberToLoad

          // Notify the subscriber that the job is finished
          complete(subscriber, undefined)
        },
        // Errors are sent up the pipe
        error: err => subscriber.error(err)
      })
    })
  }

  public getRecords() : T[] {
    return this.records
  }

  /**
   * Returns true if the last loaded page is the last available page
   */
  public areAllPagesLoaded() : boolean {
    return (this.lastLoadedPageNumber == this.lastAvailablePageNumber)
  }
}