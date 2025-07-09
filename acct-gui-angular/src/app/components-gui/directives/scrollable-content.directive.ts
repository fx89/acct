import { Directive, ElementRef, EventEmitter, HostListener, inject, input, InputSignal, Output } from '@angular/core';
import { DelayedTrigger } from '../../utils-reusalbe/delayed-trigger';

/**
 * Defines the scroll direction
 * - UP   : scroll slider went up
 * - DOWN : scroll slider went down
 */
export enum ScrollDirection {
    UP = "UP",
    DOWN = "DOWN"
}

/**
 * Provides information about a scroll action executed on a scrollable item
 * - direction    : the direction in which the scroll slider has gone
 * - sliderPosPct : the position at which the scroll slider currently resides,
 *                  between 0 (the beginning of the scroll bar) and 1 (the end of the scroll bar)
 */
export interface ScrollEvent {
    direction : ScrollDirection,
    sliderPosPct : number
}

@Directive({
  selector: '[appScrollableContent]',
  outputs: ['contentScroll']
})
export class ScrollableContentDirective {

  /**
   * The element on which the directive has been set
   */
  private element = inject(ElementRef)

  /**
   * The scroll event that is triggered every time the scroll position has changed significantly
   */
  @Output() contentScroll : EventEmitter<ScrollEvent> = new EventEmitter<ScrollEvent>

  /**
   * The HTML element that contains the scrollable content
   */
  private scrollableContentElement : HTMLElement | undefined = undefined
 
  /**
   * Delayed trigger for the scroll event
   */
  scrollEventTrigger : DelayedTrigger = new DelayedTrigger(250, () => this.fireScrollEvent())

  /**
   * State of the scrollable body
   */
  private previousScrollPct : number = 0

  constructor() { }

  ngAfterContentInit(): void {
    this.scrollableContentElement = this.element.nativeElement
    //this.scrollableContentElement?.addEventListener("scroll", (event) => this.onContentScroll(event))
  }

  @HostListener('scroll')
  onContentScroll(event:Event) : void {
      if (this.scrollableContentElement) {
        this.scrollEventTrigger.fire()
      }
    }
  
    fireScrollEvent() : void {
      if (this.scrollableContentElement) {
        // Get the current scroll position
        const currentScrollPct : number =
          this.scrollableContentElement.scrollTop / 
          (this.scrollableContentElement.scrollHeight - this.scrollableContentElement.clientHeight)
  
        // Compute the scroll direction by comparing the current scrill position to the previous scroll position
        const scrollDirection : ScrollDirection =
          this.previousScrollPct < currentScrollPct
            ? ScrollDirection.DOWN
            : ScrollDirection.UP
  
        // Store the state
        this.previousScrollPct = currentScrollPct
  
        // Fire the event
        this.contentScroll.emit({
          direction: scrollDirection,
          sliderPosPct: currentScrollPct
        })
      }
      
    }

}
