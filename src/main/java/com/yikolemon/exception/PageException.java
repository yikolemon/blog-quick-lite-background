package com.yikolemon.exception;

/**
 * @author yikolemon
 * @date 2023/8/20 21:57
 * @description
 */
public class PageException extends RuntimeException{

    public PageException() {
    }

    public PageException(String message) {
        super(message);
    }

    public PageException(String message, Throwable cause) {
        super(message, cause);
    }

    public PageException(Throwable cause) {
        super(cause);
    }

    public PageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
