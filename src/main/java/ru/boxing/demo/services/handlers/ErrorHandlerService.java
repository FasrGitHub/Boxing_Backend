package ru.boxing.demo.services.handlers;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import ru.boxing.demo.services.MessageService;

import java.io.IOException;

@Service
public class ErrorHandlerService {
    private final MessageService messageService;

    public ErrorHandlerService(MessageService messageService) {
        this.messageService = messageService;
    }

    public void unexpectedError(WebSocketSession session, TextMessage message, Exception e) throws IOException {
        String unexpectedError = "Непредвиденная ошибка :" + e;

        messageService.sendErrorMassage(session, message , unexpectedError);

        System.err.println("Непредвиденная ошибка " + e);
    }
}
