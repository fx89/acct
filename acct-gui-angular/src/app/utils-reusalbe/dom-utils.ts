
/**
 * Looks up the element with the given element id and returns the DOMRect of this element.
 * 
 * @param elementId the given element id
 */
export const getElementRect = function(elementId : string) : DOMRect | undefined {
    const element : HTMLElement | null = document.getElementById(elementId)
    const rect : DOMRect | undefined = element?.getBoundingClientRect()
    return rect;
}

/**
 * Looks up the element with the given element id and returns the DOMRect of this element.
 * If not found, then an error with the given error message is thrown.
 * 
 * @param elementId    the given element id
 * @param errorMessage the given error message
 */
export function getElementRectOrThrow(elementId:string, errorMessage:string) : DOMRect {
    const elementRect : DOMRect | undefined = getElementRect(elementId)

    if (elementRect) {
        return elementRect
    }

    throw new Error(errorMessage)
}

/**
 * Looks up the element with the given element id. If not found, then an error with the
 * given error message is thrown.
 * 
 * @param elementId    the given element id
 * @param errorMessage the given error message
 */
export function getElementOrThrow(elementId:string, errorMessage:string) : HTMLElement {
    const element : HTMLElement | null = document.getElementById(elementId)

    if (element) {
      return element
    }

    throw new Error(errorMessage)
}

/**
 * A type that represents a position on a 2D surface such as the HTML document
 */
export type Point2D = { left:number, top:number }

/**
 * Computes the scroll position of the window/document
 */
export function findScrollPosition() : Point2D {
    const doc : HTMLElement = document.documentElement;

    return {
        left : (window.pageXOffset || doc.scrollLeft) - (doc.clientLeft || 0),
        top  : (window.pageYOffset || doc.scrollTop )  - (doc.clientTop || 0)
    }

    
}