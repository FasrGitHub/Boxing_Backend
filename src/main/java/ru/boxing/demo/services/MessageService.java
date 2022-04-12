package ru.boxing.demo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import ru.boxing.demo.models.Game;
import ru.boxing.demo.models.Query;
import ru.boxing.demo.models.messages.*;

import java.io.IOException;

@Service
public class MessageService {
    private final ObjectMapper mapper = new ObjectMapper();
    private final WaitGameService waitGameService;

    public MessageService(WaitGameService waitGameService) {
        this.waitGameService = waitGameService;
    }

    public void sendInitMessage(WebSocketSession session, String username) throws IOException {
        MessageStateInit messageStateInit = new MessageStateInit(waitGameService.getQueue(), username);
        sendMessage(session, messageStateInit);
    }

    public void sendWaitMessage(WebSocketSession session, String username) throws IOException {
        MessageStateWait messageStateWait = new MessageStateWait(waitGameService.getQueue(), username);
        sendMessage(session, messageStateWait);
    }

    public void sendGameMessage(WebSocketSession session, Game game, String username) throws IOException {
        MessageStateGame messageStateGame = new MessageStateGame(game, username);
        sendMessage(session, messageStateGame);
    }

    public void sendErrorMassage(
            WebSocketSession session,
            TextMessage message,
            String errorMessage
    ) throws IOException{
        Query query = mapper.readValue(message.getPayload(), Query.class);
        MassageResponseError massageError = new MassageResponseError(query.getId(),errorMessage);
        sendMessage(session, massageError);
    }

    public void sendSuccessfulMassage(WebSocketSession session, TextMessage message) throws IOException {
        Query query = mapper.readValue(message.getPayload(), Query.class);
        MassageResponseSuccessful massageResponseSuccessful = new MassageResponseSuccessful(query.getId());
        sendMessage(session, massageResponseSuccessful);
    }

    private void sendMessage(WebSocketSession session, Object object) throws IOException {
        TextMessage textMessage = new TextMessage(mapper.writeValueAsString(object));
        session.sendMessage(textMessage);
    }
}
