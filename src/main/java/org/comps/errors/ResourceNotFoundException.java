package org.comps.errors;

import org.springframework.util.StringUtils;

public class ResourceNotFoundException extends AppException {

    private static String DEFAULT_ERROR_CODE = "RESOURCE_NOT_FOUND";

    public ResourceNotFoundException(String errorCode, String message, Throwable e){
        super(404,errorCode, message,e);
        this.errorCode = StringUtils.hasText(this.errorCode) ? this.errorCode : DEFAULT_ERROR_CODE;
    }
}
