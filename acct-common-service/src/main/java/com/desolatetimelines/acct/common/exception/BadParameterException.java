package com.desolatetimelines.acct.common.exception;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@link AcctException ACCT exception} classes decorated with this annotation
 * are likely to occur in case one or more arguments provided to a service method
 * are missing or illegal
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BadParameterException {
}
