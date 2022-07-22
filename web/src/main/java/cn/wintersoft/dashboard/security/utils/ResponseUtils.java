package cn.wintersoft.dashboard.security.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cn.wintersoft.dashboard.web.model.ApiRes;

public class ResponseUtils {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String error(String message) {
        ApiRes<Void> res = ApiRes.errorMessage(message);
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
