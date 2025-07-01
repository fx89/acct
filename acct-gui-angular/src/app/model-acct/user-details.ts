import { GroupInfo } from "./group-info"

/**
 * Details of a given user, including a set of groups that the user is part of
 */
export interface UserDetails {

    /**
     * The unique identifier of the user, a V4 UUID
     */
    userUUID : string

    /**
     * The login ID of the user
     */
    userLoginName : string

    /**
     * The human-readable name of the user
     */
    userName : string

    /**
     * The workspace where the user lands upon login
     */
    defaultWorkspaceUUID : string

    /**
     * The unique identifier of the user's icon, a V4 UUID
     */
    userIconUUID : string

    /**
     * A  set of {@link GroupInfo group info records} for the groups that the user is part of
     */
    userGroups : GroupInfo[]

}