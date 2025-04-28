/**
 * Defines the minimal data set for a tab to be used with the tabs component
 */
export interface TabData {
    text: string;
    imageRef: string;
    onSelect?: () => void;
    onDeselect?: () => void;
}