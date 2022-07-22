package cn.wintersoft.dashboard.web.exception;

import org.springframework.http.HttpStatus;
import cn.wintersoft.dashboard.web.model.ApiResCodes;

public class ApiErrorException extends RuntimeException {

    private final int code;
    private final HttpStatus httpStatus;

    public ApiErrorException(String message) {
        this(ApiResCodes.Error.COMMON, message, null, null);
    }

    public ApiErrorException(int code, String message) {
        this(code, message, null, null);
    }

    public ApiErrorException(int code, String message, HttpStatus httpStatus) {
        this(code, message, null, httpStatus);
    }

    public ApiErrorException(int code, String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public int getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
