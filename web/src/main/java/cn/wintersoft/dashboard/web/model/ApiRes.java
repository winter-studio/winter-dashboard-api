package cn.wintersoft.dashboard.web.model;

import cn.wintersoft.dashboard.web.utils.LocaleMessage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Schema(description = "API返回结果")
@Data
@NoArgsConstructor
public class ApiRes<T> implements Serializable {
    @Schema(description = "状态码")
    @JsonView(ApiResView.class)
    protected int code;
    @Schema(description = "消息")
    @JsonView(ApiResView.class)
    protected String message;
    @Schema(description = "数据")
    @JsonView(ApiResView.class)
    protected T data;
    @Schema(description = "结果类型")
    @JsonView(ApiResView.class)
    protected ApiResType type;

    @JsonIgnore
    private ApiResBuilder<T> apiResBuilder;

    public ApiRes(int code, String message, T data, ApiResType type) {
        this.code = code;
        this.message = LocaleMessage.get(message);
        this.data = data;
        this.type = type;
    }

    public static <T> ApiRes<T> success() {
        return success(null, null);
    }

    public static <T> ApiRes<T> successMessage(String message) {
        return success(ApiResCodes.Ok.COMMON, message);
    }

    public static <T> ApiRes<T> success(int code, String message) {
        return success(code, message, null);
    }

    public static <T> ApiRes<T> success(T data) {
        return success(ApiResCodes.Ok.COMMON, null, data);
    }

    public static <T> ApiRes<T> success(String message, T data) {
        return success(ApiResCodes.Ok.COMMON, message, data);
    }

    public static <T> ApiRes<T> success(int code, String message, T data) {
        return new ApiRes<>(code, message, data, ApiResType.SUCCESS);
    }

    public static <T> ApiRes<T> failure() {
        return failure(null, null);
    }

    public static <T> ApiRes<T> failureMessage(String message) {
        return failure(ApiResCodes.Failure.COMMON, message);
    }

    public static <T> ApiRes<T> failure(int code, String message) {
        return failure(code, message, null);
    }

    public static <T> ApiRes<T> failure(String message, T data) {
        return failure(ApiResCodes.Failure.COMMON, message, data);
    }

    public static <T> ApiRes<T> failure(int code, String message, T data) {
        return new ApiRes<>(code, message, data, ApiResType.FAILURE);
    }


    public static <T> ApiRes<T> error() {
        return error(null, null);
    }

    public static <T> ApiRes<T> errorMessage(String message) {
        return error(ApiResCodes.Error.COMMON, message);
    }

    public static <T> ApiRes<T> error(int code, String message) {
        return error(code, message, null);
    }

    public static <T> ApiRes<T> error(String message, T data) {
        return error(ApiResCodes.Error.COMMON, message, data);
    }

    public static <T> ApiRes<T> error(int code, String message, T data) {
        return new ApiRes<>(code, message, data, ApiResType.ERROR);
    }

    public static <T> ApiResBuilder<T> baseOn(boolean succeeded) {
        return new ApiResBuilder<T>(succeeded);
    }

}
