package ru.boxing.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;
import ru.boxing.demo.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class UserTokenService {
    private final HashMap<String,String> users = new HashMap<>();

    public void createUser(User user, String token) {
        users.put(user.getUsername(), token);
    }

    public boolean checkUser(String username) {
        return users.containsKey(username);
    }

    public void deleteUser(String username) {
        users.remove(username);
    }

    public String getUsernameBySession(WebSocketSession session) {
        String str = String.valueOf(session.getUri());
        return searchByToken(str.substring(36));
    }

    private String searchByToken(String token) {
        Set<Map.Entry<String,String>> entrySet=users.entrySet();

        for (Map.Entry<String,String> pair : entrySet) {
            if (token.equals(pair.getValue())) {
                return pair.getKey();
            }
        }
        return null;
    }
}
