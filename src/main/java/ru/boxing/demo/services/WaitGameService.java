package ru.boxing.demo.services;

import org.springframework.stereotype.Service;

@Service
public class WaitGameService {
    private String username = null;

    public void addToQueue(String username) {
        this.username = username;
    }

    public void clearQueue() {
        this.username = null;
    }

    public String getUsername() {
        return this.username;
    }

    public int getQueue() {
        return username != null ? 1 : 0;
    }
}
