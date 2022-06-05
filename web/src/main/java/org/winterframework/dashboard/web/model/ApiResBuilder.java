package org.winterframework.dashboard.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ApiResBuilder<T> {
    private final boolean succeeded;
    private final ApiResData<T> whenSuccess = new ApiResData<>(ApiResCodes.Ok.COMMON, "请求成功", null);
    private final ApiResData<T> whenFailure = new ApiResData<>(ApiResCodes.Failure.COMMON, "请求失败", null);

    private ApiResData<T> current;

    public ApiResBuilder(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public ApiResBuilder<T> successThen() {
        this.current = whenSuccess;
        return this;
    }

    public ApiResBuilder<T> failureThen() {
        this.current = whenFailure;
        return this;
    }

    public ApiResBuilder<T> code(int code) {
        this.current.setCode(code);
        return this;
    }

    public ApiResBuilder<T> message(String message) {
        this.current.setMessage(message);
        return this;
    }

    public ApiResBuilder<T> data(T data) {
        this.current.setData(data);
        return this;
    }

    public ApiRes<T> get() {
        if (succeeded) {
            return new ApiRes<>(this.whenSuccess.code, this.whenSuccess.message, this.whenSuccess.data,
                    ApiResType.SUCCESS);
        } else {
            return new ApiRes<>(this.whenFailure.code, this.whenFailure.message, this.whenFailure.data,
                    ApiResType.FAILURE);
        }
    }


    @Data
    @AllArgsConstructor
    public static class ApiResData<T> {
        private int code;
        private String message;
        private T data;
    }
}
