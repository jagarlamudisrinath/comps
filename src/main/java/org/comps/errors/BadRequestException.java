package org.comps.errors;

import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * The HyperText Transfer Protocol (HTTP) 400 Bad Request response status
 * code indicates that the server cannot or will not process the request
 * due to something that is perceived to be a client error (e.g., malformed
 * request syntax, invalid request message framing, or deceptive request routing).
 */

public class BadRequestException extends AppException {

    private static String DEFAULT_ERROR_CODE = "BAD_REQUEST";

    public BadRequestException(String message){
        super(400, DEFAULT_ERROR_CODE, message);
    }

    public BadRequestException(String errorCode, String message, Throwable e, Map<String,Object> response){
        super(400, StringUtils.hasText(errorCode) ? errorCode : DEFAULT_ERROR_CODE, message,e);
        super.errorResponse = response;
        this.errorCode = StringUtils.hasText(this.errorCode) ? this.errorCode : DEFAULT_ERROR_CODE;
    }
}