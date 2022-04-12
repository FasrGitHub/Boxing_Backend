package ru.boxing.demo.services.handlers;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;
import ru.boxing.demo.services.MessageService;
import ru.boxing.demo.services.SessionService;
import ru.boxing.demo.services.UserTokenService;
import ru.boxing.demo.services.WaitGameService;

import java.io.IOException;
import java.util.Objects;

@Service
public class ConnectHandlerService {
    private final UserTokenService userTokenService;
    private final SessionService sessionService;
    private final WaitGameService waitGameService;
    private final MessageService messageService;

    public ConnectHandlerService(
            UserTokenService userTokenService,
            SessionService sessionService,
            WaitGameService waitGameService,
            MessageService messageService) {
        this.userTokenService = userTokenService;
        this.sessionService = sessionService;
        this.waitGameService = waitGameService;
        this.messageService = messageService;
    }

    public void onConnect(WebSocketSession session) throws IOException {
        String username = userTokenService.getUsernameBySession(session);
        boolean isAuthorized = username != null;

        if (isAuthorized) {
            sessionService.addSession(username, session);

            messageService.sendInitMessage(session, username);
        } else {
            session.close();
        }
    }

    public void onDisconnect(WebSocketSession session) {
        String username = userTokenService.getUsernameBySession(session);

        if(Objects.equals(waitGameService.getUsername(), username)){
            sessionService.removeSession(username);
            waitGameService.clearQueue();
        }
    }
}
