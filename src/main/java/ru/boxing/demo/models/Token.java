package ru.boxing.demo.models;

public class Token {
    private String token;

    public Token() {
    }

    @Override
    public String toString() {
        return  token;
    }

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
