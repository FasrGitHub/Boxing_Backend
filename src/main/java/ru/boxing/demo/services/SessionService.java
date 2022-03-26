package ru.boxing.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;

@Service
public class SessionService {
    private HashMap<String, WebSocketSession> sessions = new HashMap<>();

    public void addSession(String username, WebSocketSession session) {
        sessions.put(username, session);
    }

    public void removeSession(String username) {
        sessions.remove(username);
    }

    public WebSocketSession getSession(String username) {
        return sessions.get(username);
    }
}
