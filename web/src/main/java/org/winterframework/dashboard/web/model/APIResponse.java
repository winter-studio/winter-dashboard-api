package org.winterframework.dashboard.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class APIResponse<T> {
    private int code;
    private String message;
    private T data;
    private ApiResponseType type;

    public APIResponse(int code, String message, T data, ApiResponseType type) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.type = type;
    }

    public static <T> APIResponse<T> success() {
        return success(null);
    }

    public static <T> APIResponse<T> success(String message) {
        return success(ApiResponseCodes.OK, message);
    }

    public static <T> APIResponse<T> success(int code, String message) {
        return success(code, message, null);
    }

    public static <T> APIResponse<T> success(T data) {
        return success(ApiResponseCodes.OK, null, data);
    }
    public static <T> APIResponse<T> success(String message, T data) {
        return success(ApiResponseCodes.OK, message, data);
    }

    public static <T> APIResponse<T> success(int code, String message, T data) {
        return new APIResponse<>(code, message, data, ApiResponseType.SUCCESS);
    }

    public static <T> APIResponse<T> failure() {
        return failure(null);
    }

    public static <T> APIResponse<T> failure(String message) {
        return failure(ApiResponseCodes.FAILED, message);
    }

    public static <T> APIResponse<T> failure(int code, String message) {
        return failure(code, message, null);
    }

    public static <T> APIResponse<T> failure(String message, T data) {
        return failure(ApiResponseCodes.FAILED, message, data);
    }

    public static <T> APIResponse<T> failure(int code, String message, T data) {
        return new APIResponse<>(code, message, data, ApiResponseType.FAILURE);
    }


    public static <T> APIResponse<T> error() {
        return error(null);
    }

    public static <T> APIResponse<T> error(String message) {
        return error(ApiResponseCodes.INTERNAL_ERROR, message);
    }

    public static <T> APIResponse<T> error(int code, String message) {
        return error(code, message, null);
    }

    public static <T> APIResponse<T> error(String message, T data) {
        return error(ApiResponseCodes.INTERNAL_ERROR, message, data);
    }

    public static <T> APIResponse<T> error(int code, String message, T data) {
        return new APIResponse<>(code, message, data, ApiResponseType.ERROR);
    }
}
