package com.cts.pmt.task.exception;

public class TaskException extends Exception {

    private static final long serialVersionUID = 1L;
    private String message;

    public TaskException(String message) {
        super(message);
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }

}
