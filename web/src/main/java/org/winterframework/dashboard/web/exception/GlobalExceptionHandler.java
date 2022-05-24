package org.winterframework.dashboard.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.winterframework.dashboard.web.model.APIResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public APIResponse<Void> exceptionHandler(HttpServletRequest req, ApiException e) {
        return APIResponse.failure(e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public APIResponse<Void> exceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e) {
        return buildArgumentErrorResult(e);
    }

    @ExceptionHandler(value = Exception.class)
    public APIResponse<Void> exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("Exception Caught", e);
        return APIResponse.error("请求异常");
    }

    private APIResponse<Void> buildArgumentErrorResult(BindingResult bindingResult) {
        List<ObjectError> fieldErrors = bindingResult.getAllErrors();
        StringBuilder errors = new StringBuilder();

        for (ObjectError fieldError : fieldErrors) {
            errors.append(fieldError.getDefaultMessage()).append(";");
        }

        return APIResponse.failure(errors.toString());
    }
}
