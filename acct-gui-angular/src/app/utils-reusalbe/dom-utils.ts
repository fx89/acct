
/**
 * Looks up the element with the given element id and returns the DOMRect of this element
 * 
 * @param elementId the given element id
 */
export const getElementRect = function(elementId : string) : DOMRect | undefined {
    const element : HTMLElement | null = document.getElementById(elementId)
    const rect : DOMRect | undefined = element?.getBoundingClientRect()
    return rect;
}