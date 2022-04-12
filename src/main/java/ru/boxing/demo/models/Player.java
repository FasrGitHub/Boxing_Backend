package ru.boxing.demo.models;

public class Player {
    private int kick;
    private int block;
    private int score;
    private String username;
    private boolean hit;
    private boolean winner;

    public Player(int kick, int block, int score, String username, boolean hit, boolean winner) {
        this.kick = kick;
        this.block = block;
        this.score = score;
        this.username = username;
        this.hit = hit;
        this.winner = winner;
    }

    public int getKick() {
        return kick;
    }

    public void setKick(int kick) {
        this.kick = kick;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }
}
