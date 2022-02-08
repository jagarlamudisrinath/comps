package org.comps.errors;

import org.springframework.util.StringUtils;

/**
 * The HTTP 403 Forbidden client error status response code indicates that the
 * server understood the request but refuses to authorize it.
 *
 * This status is similar to 401, but in this case, re-authenticating will make
 * no difference. The access is permanently forbidden and tied to the application
 * logic, such as insufficient rights to a resource.
 */
public class ForbiddenAccessException extends AppException {

    private ForbiddenAccessException(){
    }

    private static String DEFAULT_ERROR_CODE = "FORBIDDEN_ACCESS";

    public ForbiddenAccessException(String errorCode, String message, Throwable e){
        super(403,errorCode, message,e);
        this.errorCode = StringUtils.hasText(this.errorCode) ? this.errorCode : DEFAULT_ERROR_CODE;
    }
}
