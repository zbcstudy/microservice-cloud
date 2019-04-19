package com.wondertek.springcloud.security.controller;

/**
 * Created by win on 2019/4/19.
 */
public class AuthenticationException extends RuntimeException {

    public AuthenticationException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
