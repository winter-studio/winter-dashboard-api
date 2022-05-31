package org.winterframework.dashboard.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.winterframework.dashboard.web.model.ApiRes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ApiRes<Void> exceptionHandler(HttpServletRequest req, ApiException e) {
        return ApiRes.failure(e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiRes<Void> exceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e) {
        return buildArgumentErrorResult(e);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ApiRes<Void> exceptionHandler(HttpServletRequest req, HttpMessageNotReadableException e) {
        return ApiRes.error(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ApiRes<Void> exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("Exception Caught", e);
        return ApiRes.error("请求异常");
    }

    private ApiRes<Void> buildArgumentErrorResult(BindingResult bindingResult) {
        List<ObjectError> fieldErrors = bindingResult.getAllErrors();
        StringBuilder errors = new StringBuilder();

        for (ObjectError fieldError : fieldErrors) {
            errors.append(fieldError.getDefaultMessage()).append(";");
        }

        return ApiRes.failure(errors.toString());
    }
}
