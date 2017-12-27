package com.cts.day1.user.exception;

public class UserException extends Exception {

    private static final long serialVersionUID = 1L;
    private String message;

    public UserException(String message) {
        super(message);
        this.message = message;
    }


    public UserException() {
        super();
    }

    @Override
    public String getMessage() {
        return message;
    }

}
