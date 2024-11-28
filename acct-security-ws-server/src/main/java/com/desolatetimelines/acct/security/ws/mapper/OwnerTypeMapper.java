package com.desolatetimelines.acct.security.ws.mapper;

import com.desolatetimelines.acct.security.ws.endpoint.model.OwnerType;

/**
 * Provides mapper methods for the {@link OwnerType} object
 */
public class OwnerTypeMapper {

    public static com.desolatetimelines.acct.security.model.OwnerType toDataLayerOwnerType(OwnerType ownerType) {
        if (ownerType == OwnerType.PUBLIC) {
            return com.desolatetimelines.acct.security.model.OwnerType.PUBLIC;
        }

        if (ownerType == OwnerType.USER) {
            return com.desolatetimelines.acct.security.model.OwnerType.USER;
        }

        if (ownerType == OwnerType.GROUP) {
            return com.desolatetimelines.acct.security.model.OwnerType.GROUP;
        }

        throw new IllegalArgumentException("The given owner type is not supported");
    }

}
