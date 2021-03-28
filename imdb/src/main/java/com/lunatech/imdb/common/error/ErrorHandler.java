package com.lunatech.imdb.common.error;

import com.lunatech.imdb.common.error.codes.ApiErrorCode;
import com.lunatech.imdb.common.error.exceptions.ServiceException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 3/6/2021 by OLE
 */

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiErrorCode errorCode = ApiErrorCode.MISSING_QUERY_PARAM;
        return new ResponseEntity<>(errorCode.toResponseEntity(), errorCode.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiErrorCode errorCode = ApiErrorCode.INVALID_QUERY_PARAM_VALUE;
        String detailedMessage = ex.getMessage();
        return new ResponseEntity<>(errorCode.toResponseEntity(detailedMessage), errorCode.getHttpStatus());
    }

    @ExceptionHandler(ServiceException.class)
    public final ResponseEntity<ApiError> handleServiceException(ServiceException exception) {
        ApiErrorCode errorCode = exception.getApiErrorCode();
        String detailedMessage = exception.getMessage();
        return new ResponseEntity<>(errorCode.toResponseEntity(detailedMessage), errorCode.getHttpStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ApiError> handleConstraintViolationException(ConstraintViolationException exception) {
        ApiErrorCode errorCode = ApiErrorCode.INVALID_QUERY_PARAM_VALUE;
        String detailedMessage = exception.getMessage();
        return new ResponseEntity<>(errorCode.toResponseEntity(detailedMessage), errorCode.getHttpStatus());
    }

}
