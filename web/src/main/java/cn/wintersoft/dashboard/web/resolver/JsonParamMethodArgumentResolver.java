package cn.wintersoft.dashboard.web.resolver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import cn.wintersoft.dashboard.web.exception.ApiErrorException;
import cn.wintersoft.dashboard.web.utils.Jackson;

import jakarta.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class JsonParamMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(JsonParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        String contentType = Objects.requireNonNull(servletRequest).getContentType();

        if (contentType == null || !contentType.contains(MediaType.APPLICATION_JSON_VALUE)) {
            throw new ApiErrorException("@JsonParam only supports application/json");
        }

        if (HttpMethod.GET.name().equalsIgnoreCase(servletRequest.getMethod())) {
            throw new ApiErrorException("@JsonParam works only with non-GET method");
        }
        try {
            return bindRequestParams(parameter, servletRequest);
        } catch (Exception e) {
            log.error("Failed to bind request params", e);
            throw new ApiErrorException("参数处理错误");
        }
    }

    private Object bindRequestParams(MethodParameter parameter, HttpServletRequest servletRequest) throws Exception {
        JsonParam jsonParam = parameter.getParameterAnnotation(JsonParam.class);

        Class<?> parameterType = parameter.getParameterType();
        String requestBody = getRequestBody(servletRequest);
        ObjectMapper objectMapper = Jackson.get();
        JsonNode jsonNode = objectMapper.readTree(requestBody);
        if (jsonNode == null) {
            throw new ApiErrorException("Invalid request body");
        }

        assert jsonParam != null;
        String value = jsonParam.value();
        if (value.isBlank()) {
            value = parameter.getParameterName();
        }
        String[] jsonPaths = value.split("\\.");
        for (String jsonPath : jsonPaths) {
            jsonNode = jsonNode.get(jsonPath);
            if (jsonNode == null) {
                return null;
            }
        }

        if (jsonNode.isNull()) {
            return null;
        }

        return objectMapper.treeToValue(jsonNode, parameterType);
    }

    /**
     * 获取请求body
     *
     * @param servletRequest request
     * @return 请求body
     */
    private String getRequestBody(HttpServletRequest servletRequest) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = servletRequest.getReader();
            char[] buf = new char[1024];
            int length;
            while ((length = reader.read(buf)) != -1) {
                stringBuilder.append(buf, 0, length);
            }
        } catch (IOException e) {
            log.error("读取流异常", e);
            throw new ApiErrorException("参数错误");
        }
        return stringBuilder.toString();
    }
}
