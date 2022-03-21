package ru.boxing.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.boxing.demo.helpers.StringGenerator;
import ru.boxing.demo.models.Token;
import ru.boxing.demo.models.User;
import ru.boxing.demo.services.UserTokenService;

@Controller
public class ClientController {
    private final String password = "123456";
    private final StringGenerator stringGenerator;
    private final UserTokenService userTokenService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public ClientController(UserTokenService userTokenService) {
        this.stringGenerator = new StringGenerator();
        this.userTokenService = userTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity initialization(@RequestBody User user) {
        if(user.getPassword().equals(password)) {
            if (userTokenService.checkForUser(user.getUsername())){
                userTokenService.deleteUser(user.getUsername());
            }

            Token token = new Token(stringGenerator.generate());

            userTokenService.createUser(user, token);

            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            Error error = new Error("Password mismatch");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR); // model error + status 500
        }
    }

    @MessageMapping("/websocket")
    public void wsMessage() {
        System.out.println("123");

        simpMessagingTemplate.convertAndSend("hui");
    }

    @SubscribeMapping("/websocket")
    public String wsSubscribe() {
        return "hui";
    }
}
