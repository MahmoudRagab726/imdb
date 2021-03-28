package com.lunatech.imdb.common.error.exceptions;

import com.lunatech.imdb.common.error.codes.ApiErrorCode;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 3/6/2021 by OLE
 */
public abstract class ServiceException extends RuntimeException {

    ServiceException(String message) {
        super(message);
    }

    ServiceException() {
        super();
    }

    public abstract ApiErrorCode getApiErrorCode();
}
