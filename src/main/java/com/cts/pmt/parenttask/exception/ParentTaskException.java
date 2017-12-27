package com.cts.pmt.parenttask.exception;

public class ParentTaskException extends Exception {

    private static final long serialVersionUID = 1L;
    private String message;

    public ParentTaskException(String message) {
        super(message);
        this.message = message;
    }


    public ParentTaskException() {
        super();
    }

    @Override
    public String getMessage() {
        return message;
    }

}
