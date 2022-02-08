package org.comps.errors;


import java.util.Map;

public class AppException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public static String DEFAULT_ERR_CODE = "SERVER_ERROR";

    protected int httpCode = 500;
    protected String errorCode = DEFAULT_ERR_CODE;
    protected Map<String, Object> errorResponse;

    protected AppException() {

    }

    protected AppException(Throwable e) {
        super(e);
        if (e instanceof AppException)
            this.setStackTrace(new StackTraceElement[0]);
    }

    protected AppException(String message) {
        this(message, null);
    }

    protected AppException(String message, Throwable e) {
        super(message, e);
        if (e instanceof AppException)
            this.setStackTrace(new StackTraceElement[0]);
    }

    protected AppException(String errorCode, String message, Throwable e) {
        this(message, e);
        if (e instanceof AppException) {
            this.errorCode = ((AppException) e).getErrorCode();
            this.httpCode = ((AppException) e).getHttpCode();
        } else {
            this.errorCode = errorCode;
        }
    }

    protected AppException(int httpCode, String errorCode, String message, Throwable e) {
        this(errorCode, message, e);
        if (!(e instanceof AppException)) {
            this.httpCode = httpCode;
        }
    }

    public AppException(int httpCode, String errorCode, String message) {
        this(message);
        this.httpCode = httpCode;
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public String getWrappedMessage() {
        if (getCause() instanceof AppException) {
            return ((AppException) getCause()).getWrappedMessage();
        } else {
            return getMessage();
        }
    }

    public Map<String, Object> getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(Map<String, Object> errorResponse) {
        this.errorResponse = errorResponse;
    }

    @Deprecated
    public static AppException forErrorCode(String errorCode, String errorMessage) {
        return new AppException(errorCode, errorMessage, null);
    }

    @Deprecated
    public static AppException forErrorCode(String errorCode, Exception e) {
        return e instanceof AppException ? (AppException) e : new AppException(errorCode, null, e);
    }

    @Deprecated
    public static AppException forErrorCode(String errorCode, String errorMessage, Exception e) {
        return e instanceof AppException ? (AppException) e : new AppException(errorCode, errorMessage, e);
    }

    @Deprecated
    public static AppException forException(Throwable e) {
        return e instanceof AppException ? (AppException) e : new AppException(e);
    }

    @Deprecated
    public static AppException forException(String message) {
        return new AppException(message);
    }

    @Deprecated
    public static AppException forException(String message, Throwable e) {
        return e instanceof AppException ? (AppException) e : new AppException(message, e);
    }

    @Deprecated
    public static AppException withMessage(String errorMessage) {
        return new AppException(errorMessage);
    }

    @Override
    public String toString() {
        return "AppException{" +
                "httpCode=" + httpCode +
                "message=" + getMessage() +
                ", errorCode='" + errorCode + '\'' +
                ", errorResponse=" + errorResponse +
                '}';
    }
}