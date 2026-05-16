package org.sopt.sopkathon_server.domain.message.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.sopkathon_server.domain.message.dto.request.MessageCreateRequest;
import org.sopt.sopkathon_server.domain.message.dto.response.MessageCreateResponse;
import org.sopt.sopkathon_server.domain.message.service.MessageService;
import org.sopt.sopkathon_server.global.response.CommonApiResponse;
import org.sopt.sopkathon_server.global.response.success.SuccessCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Message", description = "메시지 API")
@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @MessageCreateApi
    @PostMapping
    public ResponseEntity<CommonApiResponse<MessageCreateResponse>> createMessage(
            @RequestBody @Valid MessageCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonApiResponse.success(SuccessCode.CREATED, messageService.createMessage(request)));
    }
}