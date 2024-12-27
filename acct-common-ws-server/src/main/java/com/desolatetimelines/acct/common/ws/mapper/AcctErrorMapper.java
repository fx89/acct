package com.desolatetimelines.acct.common.ws.mapper;

import com.desolatetimelines.acct.common.exception.AcctException;
import com.desolatetimelines.acct.common.ws.model.AcctError;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides mapping methods for the {@link AcctError} tyoe
 */
public abstract class AcctErrorMapper {

    public static AcctError fromAcctException(AcctException acctException) {
        return
            new AcctError(
                acctException.getErrorCode(),
                acctException.getParameters()
            );
    }

    public static AcctError fromHandlerMethodValidationException(
        HandlerMethodValidationException validationException,
        String errorCode
    ) {
        final Map<String, String> parameters = new HashMap<>(validationException.getValueResults().size());
        validationException.getValueResults().forEach(validationResult ->
            parameters.put(
                validationResult.getMethodParameter().getParameter().getName(),
                validationResult.getArgument().toString()
            )
        );

        return new AcctError(errorCode, parameters);
    }

}
