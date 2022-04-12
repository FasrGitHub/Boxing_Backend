package ru.boxing.demo.models;

public class Game implements Cloneable {
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

    public void setRound(int round) {
        this.round = round;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isRoundCompleted() {
        return roundCompleted;
    }

    public void setRoundCompleted(boolean roundCompleted) {
        this.roundCompleted = roundCompleted;
    }

    public Player getMine() {
        return mine;
    }

    public void setMine(Player player) {
        mine = player;
    }

    public Player getEnemy() {
        return enemy;
    }

    public void setEnemy(Player player) {
        enemy = player;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public long getTimeoutPassed() {
        return timeoutPassed;
    }

    public void setTimeoutPassed(long timeoutPassed) {
        this.timeoutPassed = timeoutPassed;
    }

    @Override
    public Game clone() {
        try {
            return (Game) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
