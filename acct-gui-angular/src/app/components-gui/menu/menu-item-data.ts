/**
 * Defines the minimal data set for a menu item to be used with the app menu
 */
export interface MenuItemData {
    text: string;
    imageRef: string;
    onSelect?: () => void;
    onDeselect?: () => void;
}