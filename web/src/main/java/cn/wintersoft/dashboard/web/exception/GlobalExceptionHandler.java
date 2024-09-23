package cn.wintersoft.dashboard.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import cn.wintersoft.dashboard.web.model.ApiRes;
import cn.wintersoft.dashboard.web.model.ApiResCodes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ApiErrorException.class)
    public ApiRes<Void> exceptionHandler(HttpServletResponse response, ApiErrorException e) {
        if (e.getHttpStatus() != null) {
            response.setStatus(e.getHttpStatus().value());
        } else {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return ApiRes.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = ApiFailureException.class)
    public ApiRes<Void> exceptionHandler(HttpServletRequest req, ApiFailureException e) {
        return ApiRes.failure(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiRes<Void> exceptionHandler(HttpServletRequest req, AccessDeniedException e) {
        return ApiRes.error(ApiResCodes.Error.PERMISSION_DECLINE, "没有权限");
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiRes<Void> exceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e) {
        return buildArgumentErrorResult(e);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ApiRes<Void> exceptionHandler(HttpServletRequest req, HttpMessageNotReadableException e) {
        return ApiRes.errorMessage(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ApiRes<Void> exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("Exception Caught", e);
        return ApiRes.errorMessage("请求异常");
    }

    private ApiRes<Void> buildArgumentErrorResult(BindingResult bindingResult) {
        List<ObjectError> fieldErrors = bindingResult.getAllErrors();
        StringBuilder errors = new StringBuilder();

        for (ObjectError fieldError : fieldErrors) {
            errors.append(fieldError.getDefaultMessage()).append(";");
        }

        return ApiRes.failureMessage(errors.toString());
    }
}
