package com.desolatetimelines.acct.workspace.privilegesprovider.model;

/**
 * Enumerates the types of operations that need to be secured: <ul>
 * <li><b>SAVE</b> = creating a new resource or updating an existing resource</li>
 * <li><b>READ</b> = reading a resource</li>
 * <li><b>DELETE</b> = deleting a resource</li>
 * </ul>
 */
public enum WorkspaceServiceOperation {
    SAVE,
    READ,
    DELETE
}
