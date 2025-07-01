import { Workspace } from "./workspace";

/**
 * A response object that contains the details of all workspaces accessible to a given owner,
 * grouped into 3 categories: user workspaces, group workspaces and public workspaces.
 */
export interface WorkspaceCollections {

    /**
     * A collection of workspaces directly-accessible to the owner
     */
    userWorkspaces : Workspace[]

    /**
     * A collection of workspaces owned by a group that the owner is part of
     */
    groupWorkspaces : Workspace[]

    /**
     * A collection of workspaces that are publicly accessible
     */
    publicWorkspaces : Workspace[]
}