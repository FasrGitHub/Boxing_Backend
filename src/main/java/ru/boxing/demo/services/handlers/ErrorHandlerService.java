package ru.boxing.demo.services.handlers;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;
import ru.boxing.demo.models.BadAnswer;
import ru.boxing.demo.services.SendMessageService;

import java.io.IOException;

@Service
public class ErrorHandlerService {
    private final SendMessageService sendMessageService;

    public ErrorHandlerService(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    public void unexpectedError(WebSocketSession session, Exception e) throws IOException {
        BadAnswer badAnswer = new BadAnswer("Непредвиденная ошибка :" + e);
        sendMessageService.sendMessage(session, badAnswer);
        System.err.println("Непредвиденная ошибка " + e);
    }
}
