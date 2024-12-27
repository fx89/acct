package com.desolatetimelines.acct.common.model;

import java.util.Collection;

/**
 * Represents an ACCT service that throws {@link ErrorCode errors} registered within its own
 * {@link com.desolatetimelines.acct.common.service.AbstractErrorCodesRegistryService registry}
 *
 * @param errorThrowingServiceNumber An integer that uniquely identifies the service within the ACCT ecosystem
 * @param errorCategories            A collection of {@link ErrorCategory error categories} grouping the registered errors
 */
public record ErrorThrowingServiceDescription(
    Integer errorThrowingServiceNumber,
    Collection<ErrorCategory> errorCategories
) {
}
