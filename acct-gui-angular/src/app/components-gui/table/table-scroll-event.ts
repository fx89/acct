/**
 * Defines the scroll direction
 * - UP   : scroll slider went up
 * - DOWN : scroll slider went down
 */
export enum TableScrollDirection {
    UP,
    DOWN
}

/**
 * Provides information about a scroll action executed on a table
 * - direction    : the direction in which the scroll slider has gone
 * - sliderPosPct : the position at which the scroll slider currently resides,
 *                  between 0 (the beginning of the scroll bar) and 1 (the end of the scroll bar)
 */
export interface TableScrollEvent {
    direction : TableScrollDirection,
    sliderPosPct : number
}