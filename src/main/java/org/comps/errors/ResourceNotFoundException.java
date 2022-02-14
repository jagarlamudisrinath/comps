package org.comps.errors;

import org.springframework.util.StringUtils;

public class ResourceNotFoundException extends AppException {

    private static String DEFAULT_ERROR_CODE = "RESOURCE_NOT_FOUND";

    public ResourceNotFoundException(String message){
        super(404,DEFAULT_ERROR_CODE, message);
        this.errorCode = StringUtils.hasText(this.errorCode) ? this.errorCode : DEFAULT_ERROR_CODE;
    }

    public ResourceNotFoundException(String message, Throwable e){
        super(404,DEFAULT_ERROR_CODE, message, e);
        this.errorCode = StringUtils.hasText(this.errorCode) ? this.errorCode : DEFAULT_ERROR_CODE;
    }

    public ResourceNotFoundException(String errorCode, String message, Throwable e){
        super(404,errorCode, message,e);
        this.errorCode = StringUtils.hasText(this.errorCode) ? this.errorCode : DEFAULT_ERROR_CODE;
    }
}
