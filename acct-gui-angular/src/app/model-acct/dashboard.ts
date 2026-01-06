
/**
 * Describes the readable properties of a dashboard in the ACCT ecosystem
 */
export interface Dashboard {
    /**
     * The unique identifier of the dashboard in the ACCT ecosystem
     */
    dashboardUUID? : string

    /**
     * The human-readable name of the dashboard
     */
    dashboardName : string

    /**
     * The human-readable description of the dashboard
     */
    dashboardDescription : string

    /**
     * The UUID of the icon used when displaying the dashboard
     */
    dashboardIconUUID : string
}

/**
 * Extends the Dashboard with the imageData property, which contains the
 * Base64-encoded image and its meta-data, ready to be displayed by the browser
 */
export interface IconifiedDashboard extends Dashboard {
    imageData : string
}