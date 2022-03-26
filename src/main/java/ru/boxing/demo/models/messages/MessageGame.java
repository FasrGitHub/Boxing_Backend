package ru.boxing.demo.models.messages;

import ru.boxing.demo.models.Game;

public class MessageGame {
    private final String type = "state";
    private final String state = "game";
    private Game game;
    private String username;

    public MessageGame(Game game, String username) {
        this.game = game;
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public String getState() {
        return state;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
