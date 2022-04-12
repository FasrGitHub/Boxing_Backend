package ru.boxing.demo.services.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import ru.boxing.demo.models.*;
import ru.boxing.demo.services.*;

import java.io.IOException;

@Service
public class ActionHandlerService {

    private final UserTokenService userTokenService;
    private final WaitGameService waitGameService;
    private final SessionService sessionService;
    private final MessageService messageService;
    private final GameService gameService;
    private final ObjectMapper mapper = new ObjectMapper();

    public ActionHandlerService(
            UserTokenService userTokenService,
            WaitGameService waitGameService,
            SessionService sessionService,
            MessageService messageService,
            GameService gameService
    ) {
        this.userTokenService = userTokenService;
        this.waitGameService = waitGameService;
        this.sessionService = sessionService;
        this.messageService = messageService;
        this.gameService = gameService;
    }

    public void processingRequest(WebSocketSession session, TextMessage message) throws IOException, InterruptedException {
        Query query = mapper.readValue(message.getPayload(), Query.class);
        String username = userTokenService.getUsernameBySession(session);
        boolean isAuthorized = username != null;

        if (!isAuthorized) {
            session.close();
        }else if (query.getAction().equals("join")) {
            // Пока что только 1 пользователь может быть в очереди
            // В будущем можно переделать, если за очереди будет отвечать отдельный сервер и т.п.
            requestJoin(session, message);
        }else if (query.getAction().equals("undo_join")) {
            requestUndoJoin(session, message);
        }else if (query.getAction().equals("game_set_block")) {
            requestGameSetBlock(session, message);
        }else if (query.getAction().equals("game_set_kick")) {
            requestGameSetKick(session,message);
        }
    }

    private void requestJoin(WebSocketSession session, TextMessage message) throws IOException, InterruptedException {
        String username = userTokenService.getUsernameBySession(session);

        if(waitGameService.getUsername() != null) {
            String enemyName = waitGameService.getUsername();

            gameService.startGame(username, enemyName, session, sessionService.getSession(enemyName));
            waitGameService.clearQueue();
        } else {
            waitGameService.addToQueue(username);

            messageService.sendWaitMessage(session, username);

            messageService.sendSuccessfulMassage(session, message);
        }
    }

    private void requestUndoJoin(WebSocketSession session, TextMessage message) throws IOException {
        String username = userTokenService.getUsernameBySession(session);

        if(waitGameService.getUsername().equals(username)){
            waitGameService.clearQueue();
        }
        messageService.sendInitMessage(session, username);

        messageService.sendSuccessfulMassage(session, message);
    }

    private void requestGameSetBlock(WebSocketSession session, TextMessage message) throws IOException {
        Query query = mapper.readValue(message.getPayload(), Query.class);
        gameService.setBlockInTheGameSession(query.getBlock(), userTokenService.getUsernameBySession(session));
        messageService.sendSuccessfulMassage(session, message);
    }

    private void requestGameSetKick(WebSocketSession session, TextMessage message) throws IOException {
        Query query = mapper.readValue(message.getPayload(), Query.class);
        gameService.setKickInTheGameSession(query.getKick(), userTokenService.getUsernameBySession(session));
        messageService.sendSuccessfulMassage(session, message);
    }
}