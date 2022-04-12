package ru.boxing.demo.models.messages;

public class MassageResponseSuccessful {
    private final String type;
    private int requestId;
    private final String data;

    public MassageResponseSuccessful(int requestId) {
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
