package com.lunatech.imdb.common.error.codes;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 9/16/2020 by OLE
 */

import com.lunatech.imdb.common.error.ApiError;
import org.springframework.http.HttpStatus;

public interface IApiErrorCode {

    HttpStatus getHttpStatus();

    ApiError toResponseEntity();

    ApiError toResponseEntity(String detailedMessage);

}
