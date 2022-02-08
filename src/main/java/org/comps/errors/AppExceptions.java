package org.comps.errors;

import java.util.Map;

public class AppExceptions {

    public static AppException badRequest(Throwable e) {
        return new BadRequestException(getWrappedErrCode(e), getWrappedMessage(e), e, null);
    }

    public static AppException badRequest(String message) {
        return new BadRequestException(null, message, null, null);
    }

    public static AppException badRequest(String message, Throwable e) {
        return new BadRequestException(getWrappedErrCode(e), message, e, null);
    }

    public static AppException badRequest(String errorCode, String message) {
        return new BadRequestException(errorCode, message, null, null);
    }

    public static AppException badRequest(String errorCode, String message, Throwable e) {
        return new BadRequestException(errorCode, message, e, null);
    }

    public static AppException badRequest(String errorCode, String message, Throwable e, Map<String, Object> errorResponse) {
        return new BadRequestException(errorCode, message, e, errorResponse);
    }

    public static AppException forbiddenAccess(Throwable e) {
        return new ForbiddenAccessException(getWrappedErrCode(e), getWrappedMessage(e), e);
    }

    public static AppException forbiddenAccess(String message) {
        return new ForbiddenAccessException(null, message, null);
    }

    public static AppException forbiddenAccess(String message, Throwable e) {
        return new ForbiddenAccessException(getWrappedErrCode(e), message, e);
    }

    public static AppException forbiddenAccess(String errorCode, String message) {
        return new ForbiddenAccessException(errorCode, message, null);
    }

    public static AppException forbiddenAccess(String errorCode, String message, Throwable e) {
        return new ForbiddenAccessException(errorCode, message, e);
    }

    public static AppException serverError(Throwable e) {
        return new InternalServerError(getWrappedErrCode(e), getWrappedMessage(e), e);
    }

    public static AppException serverError(String message) {
        return new InternalServerError(null, message, null);
    }

    public static AppException serverError(String message, Throwable e) {
        return new InternalServerError(getWrappedErrCode(e), message, e);
    }

    public static AppException serverError(String errorCode, String message) {
        return new InternalServerError(errorCode, message, null);
    }

    public static AppException serverError(String errorCode, String message, Throwable e) {
        return new InternalServerError(errorCode, message, e);
    }

    public static AppException resourceNotFound(Throwable e) {
        return new ResourceNotFoundException(getWrappedErrCode(e), getWrappedMessage(e), e);
    }

    public static AppException resourceNotFound(String message) {
        return new ResourceNotFoundException(null, message, null);
    }

    public static AppException resourceNotFound(String message, Throwable e) {
        return new ResourceNotFoundException(getWrappedErrCode(e), message, e);
    }

    public static AppException resourceNotFound(String errorCode, String message) {
        return new ResourceNotFoundException(errorCode, message, null);
    }

    public static AppException resourceNotFound(String errorCode, String message, Throwable e) {
        return new ResourceNotFoundException(errorCode, message, e);
    }

    public static AppException exception(int httpCode, String errorCode, String message) {
        return new AppException(httpCode, errorCode, message);
    }

    private static String getWrappedMessage(Throwable e) {
        return e instanceof AppException ? ((AppException) e).getWrappedMessage() : e.getMessage();
    }

    private static String getWrappedErrCode(Throwable e) {
        return e instanceof AppException ? ((AppException) e).getErrorCode() : null;
    }
}