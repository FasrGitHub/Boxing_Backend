package ru.boxing.demo.models;

public class BadAnswer {
    private String type;
    private int requestId;
    private boolean error;
    private String errorMessage;

    public BadAnswer() {
    }

    public BadAnswer(int requestId, String errorMessage) {
        this.type = "response";
        this.requestId = requestId;
        this.error = true;
        this.errorMessage = errorMessage;
    }

    public BadAnswer(String errorMessage) {
        this.type = "response";
        this.error = true;
        this.errorMessage = errorMessage;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getType() {
        return type;
    }

    public boolean isError() {
        return error;
    }
}
