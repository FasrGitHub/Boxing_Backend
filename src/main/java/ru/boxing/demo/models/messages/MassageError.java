package ru.boxing.demo.models.messages;

public class MassageError {
    private String type;
    private int requestId;
    public final boolean error;
    private String errorMessage;

    public MassageError(String type, int requestId, String errorMessage) {
        this.type = type;
        this.requestId = requestId;
        this.error = true;
        this.errorMessage = errorMessage;
    }

    public MassageError(String errorMessage) {
        this.error = true;
        this.errorMessage = errorMessage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
