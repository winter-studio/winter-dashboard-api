package org.winterframework.dashboard.web.exception;

import org.winterframework.dashboard.web.model.ApiResCodes;

public class ApiFailureException extends RuntimeException {

    private final int code;

    public ApiFailureException(String message) {
        this(ApiResCodes.Failure.COMMON, message);
    }

    public ApiFailureException(int code, String message) {
        this(code, message, null);
    }

    public ApiFailureException(String message, Throwable cause) {
        this(ApiResCodes.Failure.COMMON, message, null);
    }

    public ApiFailureException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
