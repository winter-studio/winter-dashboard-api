package org.winterframework.dashboard.web.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ApiResType {
    SUCCESS,
    FAILURE,
    ERROR;

    @JsonValue
    public String forJackson() {
        return name().toLowerCase();
    }
}
