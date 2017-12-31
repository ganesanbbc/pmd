package com.cts.pmt.user.exception;

public class ErrorResponse {

    private int errorCode;
    private String errorMessage;

    public ErrorResponse(int errorCode, String errorMessage) {

        this.setErrorCode(errorCode);
        this.setErrorMessage(errorMessage);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
