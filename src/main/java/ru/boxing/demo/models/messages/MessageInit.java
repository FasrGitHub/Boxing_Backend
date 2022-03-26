package ru.boxing.demo.models.messages;

public class MessageInit {
    private final String type = "state";
    private final String state = "init";
    private String username;
    private int waitingCount;

    public MessageInit(int waitingCount, String username) {
        this.waitingCount = waitingCount;
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public int getWaitingCount() {
        return waitingCount;
    }

    public void setWaitingCount(int waitingCount) {
        this.waitingCount = waitingCount;
    }

    public String getState() {
        return state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
