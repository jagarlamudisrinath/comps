package org.comps.errors;

import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * The HyperText Transfer Protocol (HTTP) 422 Unprocessable Entity response
 * status code indicates that the server understands the content type of the
 * request entity, and the syntax of the request entity is correct, but it
 * was unable to process the contained instructions.
 */
public class UnProcessableRequestException extends AppException {

    private static String DEFAULT_ERROR_CODE = "UN_PROCESSABLE_REQUEST";

    public UnProcessableRequestException(String errorCode, String message, Throwable e, Map<String,Object> response){
        super(422,errorCode, message,e);
        super.errorResponse = response;
        this.errorCode = StringUtils.hasText(this.errorCode) ? this.errorCode : DEFAULT_ERROR_CODE;
    }
}
