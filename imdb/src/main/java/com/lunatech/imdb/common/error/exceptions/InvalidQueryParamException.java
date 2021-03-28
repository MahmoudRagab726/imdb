package com.lunatech.imdb.common.error.exceptions;

import com.lunatech.imdb.common.error.codes.ApiErrorCode;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 3/6/2021 by OLE
 */
public class InvalidQueryParamException extends ServiceException {

    public InvalidQueryParamException(String message) {
        super(message);
    }

    @Override
    public ApiErrorCode getApiErrorCode() {
        return ApiErrorCode.INVALID_QUERY_PARAM_VALUE;
    }
}
