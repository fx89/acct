package com.desolatetimelines.acct.common.ws.controlleradvice;

import com.desolatetimelines.acct.common.exception.AcctException;
import com.desolatetimelines.acct.common.service.AbstractErrorCodesRegistryService;
import com.desolatetimelines.acct.common.ws.exception.AcctJwtException;
import com.desolatetimelines.acct.common.ws.mapper.AcctErrorMapper;
import com.desolatetimelines.acct.common.ws.model.AcctError;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.Collections;

/**
 * Creates a {@link ResponseEntity response entity} containing information
 * that the front-end can use about the exception that has occurred
 */
@ControllerAdvice
@SuppressWarnings("unused")
public class AcctHttpErrorHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AbstractErrorCodesRegistryService errors;

    public AcctHttpErrorHandler(AbstractErrorCodesRegistryService errors) {
        this.errors = errors;
    }

    @ExceptionHandler
    public ResponseEntity<Object> mapExceptionToHttpResponse(Exception e, HttpServletResponse response) {
        // Write the error to the debug log
        logger.debug("An exception has occurred", e);

        // Map the exception to an HTTP status code
        final HttpStatusCode statusCode =
            HttpStatusCode.valueOf(mapExceptionToHttpStatusCode(e, response.getStatus()));

        // Copy the headers
        final MultiValueMap<String, String> headers = new HttpHeaders();
        response.getHeaderNames().forEach(headerName ->
            headers.add(headerName, response.getHeader(headerName))
        );

        // Map the exception to an AcctError object
        final AcctError acctError = mapExceptionToAcctError(e);

        // Create and return the response entity
        return new ResponseEntity<>(acctError, headers, statusCode);
    }

    // 400 bad request
    // 401 unauthorized - unauthenticated
    // 403 forbidden
    // 404 not found
    // 405 method not allowed - no controller mapping, though that usually gives a not found
    // 500 internal error
    private int mapExceptionToHttpStatusCode(Exception e, int fallbackCode) {
        // If this is an ACCT exception then get the code from there
        if (e instanceof AcctException acctException) {
            return mapAcctExceptionToAcctStatusCode(acctException);
        }

        // If this is a Jakarta validation exception then map to illegal argument
        if (e instanceof HandlerMethodValidationException) {
            return HttpStatus.BAD_REQUEST.value();
        }

        // If this is an AcctJwtException then map to forbidden
        if (e instanceof AcctJwtException) {
            return HttpStatus.FORBIDDEN.value();
        }

        // If this is an AuthorizationDeniedException then map to forbidden
        if (e instanceof AuthorizationDeniedException) {
            return HttpStatus.FORBIDDEN.value();
        }

        // If none of the above then map to the fallback code
        return fallbackCode;
    }

    private int mapAcctExceptionToAcctStatusCode(AcctException acctException) {
        // Forbidden maps to forbidden
        if (acctException.isForbiddenException()) {
            return HttpStatus.FORBIDDEN.value();
        }

        // Not found maps to not found
        if (acctException.isNotFoundException()) {
            return HttpStatus.NOT_FOUND.value();
        }

        // Bad parameter maps to bad request
        if (acctException.isBadParameterException()) {
            return HttpStatus.BAD_REQUEST.value();
        }

        // Everything else maps to internal server error
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    private AcctError mapExceptionToAcctError(Exception e) {
        // If this is an ACCT exception then get the error details from the ACCT exception
        if (e instanceof AcctException acctException) {
            return AcctErrorMapper.fromAcctException(acctException);
        }

        // If this is a Jakarta validation exception then return an ACCT error having the
        // invalid input error code and the message of the Jakarta exception
        if (e instanceof HandlerMethodValidationException validationException) {
            return AcctErrorMapper.fromHandlerMethodValidationException(validationException, errors.VALIDATION_BAD_PARAM);
        }

        // If this is an AcctJwtException then return the "missing credentials" security error code
        if (e instanceof AcctJwtException) {
            return
                new AcctError(
                    errors.MISSING_CREDENTIALS,
                    Collections.emptyMap()
                );
        }

        // If this is an AuthorizationDeniedException then return the "missing grants" security error code
        if (e instanceof AuthorizationDeniedException) {
            return
                new AcctError(
                    errors.MISSING_GRANTS,
                    Collections.emptyMap()
                );
        }

        // If nothing else, map to the unknown error
        return new AcctError(errors.GENERIC_UNKNOWN, Collections.emptyMap());
    }

}
