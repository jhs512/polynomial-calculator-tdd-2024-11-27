package com.ll.exception;

// 로직이 의도와 다르게 잘 못 동작하는 경우 발생
public class LogicException extends RuntimeException {
    public LogicException(String message) {
        super(message);
    }
}
