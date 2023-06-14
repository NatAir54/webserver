package com.studying.webserver.exceptions;

public class MethodNotAllowedException extends RuntimeException {
    public MethodNotAllowedException(String s) {
        super(s);
    }
}
