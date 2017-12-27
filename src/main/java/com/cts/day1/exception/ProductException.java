package com.cts.day1.exception;

public class ProductException extends Exception {

    private static final long serialVersionUID = 1L;
    private String message;

    public ProductException(String message) {
        super(message);
        this.message = message;
    }


    public ProductException() {
        super();
    }

    @Override
    public String getMessage() {
        return message;
    }

}
