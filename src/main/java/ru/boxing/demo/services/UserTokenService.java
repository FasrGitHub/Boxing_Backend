package ru.boxing.demo.services;

import org.springframework.stereotype.Service;
import ru.boxing.demo.models.Token;
import ru.boxing.demo.models.User;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserTokenService {
    private final HashMap<String,Token> users = new HashMap<>();

    public void createUser(User user, Token token) {
        users.put(user.getUsername(), token);
    }

    public boolean checkForUser(String username) {
        return users.containsKey(username);
    }

    public void deleteUser(String username) {
        users.remove(username);
    }
}
