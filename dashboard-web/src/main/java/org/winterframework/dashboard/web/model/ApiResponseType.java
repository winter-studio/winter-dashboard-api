package org.winterframework.dashboard.web.model;

public enum ApiResponseType {
    SUCCESS("success"),
    FAILURE("failure"),
    ERROR("error");

    private final String value;

    ApiResponseType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
