package com.desolatetimelines.acct.common.exception;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@link AcctException ACCT exception} classes decorated with this annotation
 * are likely to occur in case a service method determines that certain resources
 * or operations should not be accessed in certain circumstances
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ForbiddenException {
}
