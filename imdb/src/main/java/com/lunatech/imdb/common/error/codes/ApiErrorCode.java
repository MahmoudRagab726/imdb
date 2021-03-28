package com.lunatech.imdb.common.error.codes;

import com.lunatech.imdb.common.error.ApiError;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 9/16/2020 by OLE
 */
public enum ApiErrorCode implements IApiErrorCode {


    SERVICE_UNAVAILABLE(1, HttpStatus.INTERNAL_SERVER_ERROR,"The service is temporarily unavailable"),

    NAME_NOT_FOUND(2, HttpStatus.NOT_FOUND,"Resource not found"),

    MISSING_QUERY_PARAM(3, HttpStatus.BAD_REQUEST, "Missing query-string parameter"),

    INVALID_QUERY_PARAM_VALUE(4, HttpStatus.BAD_REQUEST, "Invalid query-string parameter value");


    @Getter
    private final int code;

    @Getter
    private final HttpStatus httpStatus;

    @Getter
    private final String message;


    ApiErrorCode(int code, HttpStatus httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public ApiError toResponseEntity(String details) {
        return new ApiError(code, name(), message + ": " + details);
    }

    @Override
    public ApiError toResponseEntity() {
        return new ApiError(code, name(), message);
    }
}
