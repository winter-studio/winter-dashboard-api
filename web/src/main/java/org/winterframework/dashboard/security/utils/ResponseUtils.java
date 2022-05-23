package org.winterframework.dashboard.security.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.winterframework.dashboard.web.model.ApiResponse;

public class ResponseUtils {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String error(String message) {
        ApiResponse<Void> res = ApiResponse.error(message);
        try {
            return objectMapper.writeValueAsString(res);
        } catch (JsonProcessingException e) {
            //退化成自己拼接
            return ("""
                    {"message":" + ${message} "}
                    """);
        }
    }

}
