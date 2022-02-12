package com.desolatetimelines.util.httpclient.exception;

public class EmptyHttpResponseException extends HttpClientUtilsException {

    public EmptyHttpResponseException() {
        super();
    }

    public EmptyHttpResponseException(String message) {
        super(message);
    }

    public EmptyHttpResponseException(String message, Throwable cause) {
        super(message, cause);
    }

}
