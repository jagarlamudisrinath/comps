package org.comps.errors;

import org.springframework.util.StringUtils;

/**
 *The HTTP 401 Unauthorized client error status response code indicates that the
 * request has not been applied because it lacks valid authentication credentials
 * for the target resource.
 *
 */
public class UnauthorizedException extends AppException {

    private static String DEFAULT_ERROR_CODE = "UNAUTHORIZED_ACCESS";

    public UnauthorizedException(String errorCode, String message, Throwable e){
        super(401,errorCode, message,e);
        this.errorCode = StringUtils.hasText(this.errorCode) ? this.errorCode : DEFAULT_ERROR_CODE;
    }
}
