
/**
 * Describes the readable properties of a workspace in the ACCT ecosystem
 */
export interface Workspace {
    /**
     * The unique identifier of the workspace in the ACCT ecosystem
     */
    workspaceUUID? : string

    /**
     * The human-readable name of the workspace
     */
    workspaceName : string

    /**
     * The human-readable description of the workspace
     */
    workspaceDescription : string

    /**
     * The UUID of the icon used when displaying the workspace
     */
    workspaceIconUUID : string

    /**
     * The UUID of the workspace' default currency
     */
    defaultCurrencyUUID : string
}

/**
 * Extends the Workspace with the imageData property, which contains the
 * Base64-encoded image and its meta-data, ready to be displayed by the browser
 */
export interface IconifiedWorkspace extends Workspace {
    imageData? : string
}