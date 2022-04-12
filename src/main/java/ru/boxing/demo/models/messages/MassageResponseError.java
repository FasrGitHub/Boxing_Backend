package ru.boxing.demo.models.messages;

public class MassageResponseError {
    private final String type = "response";
    private int requestId;
    private final boolean error = true;
    private String errorMessage;

    public MassageResponseError(int requestId, String errorMessage) {
        this.requestId = requestId;
        this.errorMessage = errorMessage;
    }

    public boolean isError() {
        return error;
    }

    public String getType() {
        return type;
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
}
