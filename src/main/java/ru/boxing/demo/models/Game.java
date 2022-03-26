package ru.boxing.demo.models;

public class Game {
    private int round;
    private boolean completed;
    private boolean roundCompleted;

    private Player mine;
    private Player enemy;

    private long timeout;
    private long timeoutPassed;

    public Game(int round, boolean completed, boolean roundCompleted, Player mine, Player enemy, long timeout, long timeoutPassed) {
        this.round = round;
        this.completed = completed;
        this.roundCompleted = roundCompleted;
        this.mine = mine;
        this.enemy = enemy;
        this.timeout = timeout;
        this.timeoutPassed = timeoutPassed;
    }

    public int getRound() {
        return this.round;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public boolean isRoundCompleted() {
        return roundCompleted;
    }

    public Player getMine() {
        return mine;
    }

    public Player getEnemy() {
        return enemy;
    }

    public long getTimeout() {
        return timeout;
    }

    public long getTimeoutPassed() {
        return timeoutPassed;
    }
}
