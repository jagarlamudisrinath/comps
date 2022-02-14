package org.comps.errors;

import org.springframework.util.StringUtils;

import java.util.Map;

public class StorageException extends AppException {

    private static String DEFAULT_ERROR_CODE = "STORAGE_ERROR";

    public StorageException(String message){
        super(400, DEFAULT_ERROR_CODE, message);
    }

    public StorageException(String message, Throwable e){
        super(400, DEFAULT_ERROR_CODE, message, e);
    }

    public StorageException(String errorCode, String message, Throwable e, Map<String,Object> response){
        super(400, StringUtils.hasText(errorCode) ? errorCode : DEFAULT_ERROR_CODE, message,e);
        super.errorResponse = response;
        this.errorCode = StringUtils.hasText(this.errorCode) ? this.errorCode : DEFAULT_ERROR_CODE;
    }
}