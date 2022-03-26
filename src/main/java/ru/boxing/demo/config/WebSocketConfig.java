package ru.boxing.demo.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.PerConnectionWebSocketHandler;
import ru.boxing.demo.controller.MyHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final BeanFactory beanFactory;

    public WebSocketConfig(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(), "/websocket");
    }

    @Bean
    public WebSocketHandler myHandler() {
        PerConnectionWebSocketHandler perConnectionWebSocketHandler = new PerConnectionWebSocketHandler(MyHandler.class);
        perConnectionWebSocketHandler.setBeanFactory(beanFactory);

        return perConnectionWebSocketHandler;
    }
}