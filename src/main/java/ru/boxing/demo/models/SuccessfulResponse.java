package ru.boxing.demo.models;

public class SuccessfulResponse {
    private final String type;
    private int requestId;
    private final String data;

    public SuccessfulResponse(int requestId) {
        this.type = "response";
        this.requestId = requestId;
        this.data = "OK";
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

    public String getData() {
        return data;
    }
}
