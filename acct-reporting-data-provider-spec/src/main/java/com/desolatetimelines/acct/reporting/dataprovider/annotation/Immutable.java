package com.desolatetimelines.acct.reporting.dataprovider.annotation;

import java.lang.annotation.*;

/**
 * Specifies that the annotated element is, by contract, not allowed to change.
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.PARAMETER})
public @interface Immutable {
}
