/**
 * Enumerates the combinations of buttons available for the message box component.
 * Possible values:
 * - OK_ONLY
 * - OK_CANCEL
 * - YES_NO
 * - YES_NO_CANCEL
 */
export enum MsgboxType {
    /**
     * Only the OK button is shown.
     * When the OK button is clicked, the onAffirmativeResponse event is triggered.
     */
    OK_ONLY = "OK_ONLY",

    /**
     * The OK and CANCEL buttons are shown.
     * When the OK button is clicked, the onAffirmativeResponse event is triggered.
     * When the CANCEL button is clicked, nothing happens.
     */
    OK_CANCEL = "OK_CANCEL",

    /**
     * The YES and NO buttons are shown.
     * When the YES button is clicked, the onAffirmativeResponse event is triggered.
     * When the NO button is clicked, the onNegativeResponse event is triggered.
     */
    YES_NO = "YES_NO",

    /**
     * The YES, NO and CANCEL buttons are shown.
     * When the YES button is clicked, the onAffirmativeResponse event is triggered.
     * When the NO button is clicked, the onNegativeResponse event is triggered.
     * When the CANCEL button is clicked, nothing happens.
     */
    YES_NO_CANCEL = "YES_NO_CANCEL"
}