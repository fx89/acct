
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

/**
 * Returns true if the referenced string is a number, or false if it's not
 */
export function isNumber(string: any) : boolean {
    const number = parseFloat(string)
    return !(isNaN(number))
}

/**
 * Returns true if the keyboard key referenced by the keyboard event is in
 * one of the lists of special keys defind in the key categories described
 * [here](https://developer.mozilla.org/en-US/docs/Web/API/UI_Events/Keyboard_event_key_values):
 * - Modifier keys
 * - Navigation keys
 * - Whitespace keys (excluding space)
 * - Editing keys
 */
export function isSpecialKey(event:KeyboardEvent) : boolean {
    // Get the key name
    const key : string = event.key

    // Check on the key name
    return (
      // Modifier keys
      key == "Alt" ||
      key == "AltGraph" ||
      key == "CapsLock" ||
      key == "Control" ||
      key == "Fn" ||
      key == "FnLock" ||
      key == "Hyper" ||
      key == "Meta" ||
      key == "NumLock" ||
      key == "OS" ||
      key == "ScrollLock" ||
      key == "Shift" ||
      key == "Super" ||
      key == "Symbol" ||
      key == "SymbolLock" ||

      // Navigation keys
      key == "Home" || 
      key == "End" ||
      key == "PageUp" ||
      key == "PageDown" ||
      key == "ArrowUp" ||
      key == "ArrowDown" ||
      key == "ArrowLeft" ||
      key == "ArrowRight" ||

      // Whitespace keys
      key == "Tab" || 
      key == "Enter" ||

      // Editing keys
      key == "Backspace" ||
      key == "Clear" ||
      key == "Copy" ||
      key == "CrSel" ||
      key == "Cut" ||
      key == "Delete" ||
      key == "EraseEof" ||
      key == "ExSel" ||
      key == "Insert" ||
      key == "Paste" ||
      key == "Redo" ||
      key == "Undo"
    )
}