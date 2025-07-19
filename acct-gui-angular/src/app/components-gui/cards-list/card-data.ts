/**
 * Defines the properties of a card displayed within a cards list component
 */
export interface CardData {
    title: string;
    text: string;
    imageRef?: string;
    onClick?: () => void;
  }