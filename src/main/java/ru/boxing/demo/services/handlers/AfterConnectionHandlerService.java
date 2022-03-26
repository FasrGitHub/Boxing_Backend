package ru.boxing.demo.services.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import ru.boxing.demo.models.*;
import ru.boxing.demo.models.messages.MessageGame;
import ru.boxing.demo.models.messages.MessageInit;
import ru.boxing.demo.models.messages.MessageWait;
import ru.boxing.demo.services.SendMessageService;
import ru.boxing.demo.services.SessionService;
import ru.boxing.demo.services.UserTokenService;
import ru.boxing.demo.services.WaitGameService;

import java.io.IOException;

@Service
public class AfterConnectionHandlerService {

    private final UserTokenService userTokenService;
    private final WaitGameService waitGameService;
    private final SessionService sessionService;
    private final SendMessageService sendMessageService;
    private final ObjectMapper mapper = new ObjectMapper();

    public AfterConnectionHandlerService(
            UserTokenService userTokenService,
            WaitGameService waitGameService,
            SessionService sessionService,
            SendMessageService sendMessageService
    ) {
        this.userTokenService = userTokenService;
        this.waitGameService = waitGameService;
        this.sessionService = sessionService;
        this.sendMessageService = sendMessageService;
    }

    public void processingRequest(WebSocketSession session, TextMessage message) throws IOException {
        Query query = mapper.readValue(message.getPayload(), Query.class);
        String username = userTokenService.getUsernameBySession(session);
        boolean isAuthorized = username != null;

        if (query.getAction().equals("join") && isAuthorized) {
            // Пока что только 1 пользователь может быть в очереди
            // В будущем можно переделать, если за очереди будет отвечать отдельный сервер и т.п.
            requestJoin(session, message);
        }else if (query.getAction().equals("undo_join") && isAuthorized) {
            requestUndoJoin(session);
        }else if (!isAuthorized) {
            session.close();
        }
    }

    private void requestJoin(WebSocketSession session, TextMessage message) throws IOException {
        Query query = mapper.readValue(message.getPayload(), Query.class);
        String username = userTokenService.getUsernameBySession(session);

        if(waitGameService.getUsername() != null) {
            String enemyName = waitGameService.getUsername();
            Player mine = new Player(0, 0, 0, username, false, false);
            Player enemy = new Player(0, 0, 0, enemyName, false, false);
            Game mineGame = new Game(
                    1,
                    false,
                    false,
                    mine,
                    enemy,
                    0,
                    0
            );
            Game enemyGame = new Game(
                    1,
                    false,
                    false,
                    enemy,
                    mine,
                    0,
                    0
            );

            MessageGame messageForMe = new MessageGame(mineGame, username);
            MessageGame messageForEnemy = new MessageGame(enemyGame, enemyName);

            sendMessageService.sendMessage(session, messageForMe);
            sendMessageService.sendMessage(sessionService.getSession(enemyName), messageForEnemy);

            waitGameService.clearQueue();
        } else {
            waitGameService.addToQueue(username);

            MessageWait messageWait = new MessageWait(waitGameService.getQueue(), username);
            sendMessageService.sendMessage(session, messageWait);

            SuccessfulResponse successfulResponse = new SuccessfulResponse(query.getId());
            sendMessageService.sendMessage(session, successfulResponse);
        }
    }

    private void requestUndoJoin(WebSocketSession session) throws IOException {
        String username = userTokenService.getUsernameBySession(session);

        if(waitGameService.getUsername().equals(username)){
            waitGameService.clearQueue();
        }
        MessageInit messageInit = new MessageInit(waitGameService.getQueue(),username);
        sendMessageService.sendMessage(session, messageInit);
    }
}