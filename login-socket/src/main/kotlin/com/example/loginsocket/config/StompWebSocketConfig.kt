package com.example.loginsocket.config

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class StompWebSocketConfig : WebSocketMessageBrokerConfigurer {
    override fun registerStompEndpoints(stompEndpointRegistry: StompEndpointRegistry) {
        // 웹소켓 핸드셰이크 커넥션 경로
        stompEndpointRegistry.addEndpoint("/socket")
            .setAllowedOrigins("*")
        /*.setAllowedOrigins("http://localhost:8080", "https://localhost:8080")
        .withSockJS()*/
    }

    override fun configureMessageBroker(messageBrokerRegistry: MessageBrokerRegistry) {
        // 아래 경로로 시작하는 STOMP 메세지의 Destination 헤더는 @MessageMapping으로 라우팅
//        messageBrokerRegistry.setApplicationDestinationPrefixes("/topic")
        messageBrokerRegistry.setApplicationDestinationPrefixes("/pub")
        // 내장된 메세지 브로커를 이용, Client에게 Subscribe, Broadcasting 기능 제공
        //  /sub 로 시작하는 destination 헤더를 가진 메시지를 라우팅
        messageBrokerRegistry.enableSimpleBroker("/sub")
    }
}
