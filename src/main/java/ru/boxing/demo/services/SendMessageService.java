package ru.boxing.demo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Service
public class SendMessageService {
    private final ObjectMapper mapper = new ObjectMapper();

    public void sendMessage(WebSocketSession session, Object object) throws IOException {
        TextMessage textMessage = new TextMessage(mapper.writeValueAsString(object));
        session.sendMessage(textMessage);
    }
}
