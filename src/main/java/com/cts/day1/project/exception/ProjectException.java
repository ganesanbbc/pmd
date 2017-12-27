package com.cts.day1.project.exception;

public class ProjectException extends Exception {

    private static final long serialVersionUID = 1L;
    private String message;

    public ProjectException(String message) {
        super(message);
        this.message = message;
    }


    public ProjectException() {
        super();
    }

    @Override
    public String getMessage() {
        return message;
    }

}
