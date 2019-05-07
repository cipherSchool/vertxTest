package com.example.demo.common.model;


public class Failure extends RuntimeException {
    private int code;
    public Failure() {
        super();
        code = 500;
    }
    public Failure(String message) {
        super(message);
        code = 500;
    }
    public Failure(Throwable throwable) {
        super(throwable);
        code = 500;
    }
    public Failure(int failureCode) {
        code = failureCode;
        initCause(new RuntimeException());
    }
    public Failure(String message, Throwable throwable) {
        super(message, throwable);
        code = 500;
    }
    public Failure(String message, int failureCode) {
        super(message);
        code = failureCode;
    }
    public Failure(Throwable throwable, int failureCode) {
        super(throwable);
        code = failureCode;
    }
    public Failure(String message, Throwable throwable, int failureCode) {
        super(message, throwable);
        code = failureCode;
    }
    public int getCode() {
        return code;
    }
    public static Failure failure(Throwable throwable) {
        if (throwable instanceof Failure) {
            return (Failure) throwable;
        }
        if(throwable.getMessage() == null) {
            return new Failure("No message provided", throwable.getCause());
        }
        return new Failure(throwable.getMessage(), throwable.getCause());
    }
}
