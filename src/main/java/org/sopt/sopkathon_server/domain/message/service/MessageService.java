package org.sopt.sopkathon_server.domain.message.service;

import lombok.RequiredArgsConstructor;
import org.sopt.sopkathon_server.domain.message.dto.request.MessageCreateRequest;
import org.sopt.sopkathon_server.domain.message.dto.response.MessageCreateResponse;
import org.sopt.sopkathon_server.domain.message.entity.Message;
import org.sopt.sopkathon_server.domain.message.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    @Value("${app.base-url}")
    private String baseUrl;

    @Transactional
    public MessageCreateResponse createMessage(MessageCreateRequest request) {
        Message message = Message.builder()
                .senderInitial(request.senderInitial())
                .receiverInitial(request.receiverInitial())
                .content(request.content())
                .build();

        Message saved = messageRepository.save(message);
        String viewUrl = baseUrl + "/m/" + saved.getId();

        return new MessageCreateResponse(saved.getId(), viewUrl);
    }
}