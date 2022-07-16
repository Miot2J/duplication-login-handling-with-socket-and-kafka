package com.example.loginsocket.controller

import com.example.loginsocket.dto.LoginRequest
import com.example.loginsocket.dto.MessageRequest
import com.example.loginsocket.service.SendMessageService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Controller
@Tag(name = "Socket Login", description = "소켓을 통한 로그인")
@RequestMapping
class SocketController(
    private val sendMessageService: SendMessageService,
    // 특정 브로커로 Message 전달 하는 객체
    private val simpMessagingTemplate: SimpMessagingTemplate
) {
    // Client가 SEND할 수 있는 경로
    // StompWebSocketConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
    // "/topic/login"
    @MessageMapping("login")
    fun enter() {
        val request = LoginRequest("jason", "pass", "")
        request.message = "${request.id} 로그인 완료"

        Thread.sleep(1000)
        simpMessagingTemplate.convertAndSend("/sub/login/${request.id}", request)
    }

    // "/topic/login-message"
    @MessageMapping("/login-message")
    fun message(request: MessageRequest) {
        simpMessagingTemplate.convertAndSend("/sub/login/${request.requestUserId}", request)
    }
}
