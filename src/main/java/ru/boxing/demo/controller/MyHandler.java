package ru.boxing.demo.controller;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.boxing.demo.services.handlers.ActionHandlerService;
import ru.boxing.demo.services.handlers.ConnectHandlerService;
import ru.boxing.demo.services.handlers.ErrorHandlerService;

import java.io.IOException;

public class MyHandler extends TextWebSocketHandler {
    private final ConnectHandlerService connectHandlerService;
    private final ActionHandlerService actionHandlerService;
    private final ErrorHandlerService errorHandlerService;

    public MyHandler(
            ConnectHandlerService connectHandlerService,
            ActionHandlerService actionHandlerService,
            ErrorHandlerService errorHandlerService
    ) {
        this.connectHandlerService = connectHandlerService;
        this.actionHandlerService = actionHandlerService;
        this.errorHandlerService = errorHandlerService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);

        connectHandlerService.onConnect(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        try {
            actionHandlerService.processingRequest(session, message);
        } catch (Exception e){
            errorHandlerService.unexpectedError(session, message, e);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);

        connectHandlerService.onDisconnect(session);
    }
}
