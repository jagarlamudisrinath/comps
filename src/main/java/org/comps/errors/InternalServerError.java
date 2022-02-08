package org.comps.errors;

import org.springframework.util.StringUtils;

/**
 * The HyperText Transfer Protocol (HTTP) 500 Internal Server Error server error response
 * code indicates that the server encountered an unexpected condition that prevented it
 * from fulfilling the request.
 *
 * This error response is a generic "catch-all" response. Usually, this indicates the server
 * cannot find a better 5xx error code to response. Sometimes, server administrators log error
 * responses like the 500 status code with more details about the request to prevent the error
 * from happening again in the future.
 */
public class InternalServerError extends AppException {

    private static String DEFAULT_ERROR_CODE = "SERVER_ERROR";

    public InternalServerError(String errorCode, String message, Throwable e){
        super(500,errorCode, message,e);
        this.errorCode = StringUtils.hasText(this.errorCode) ? this.errorCode : DEFAULT_ERROR_CODE;
    }
}
