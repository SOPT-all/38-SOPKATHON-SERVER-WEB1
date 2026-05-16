package org.sopt.sopkathon_server.domain.message.service;

import lombok.RequiredArgsConstructor;
import org.sopt.sopkathon_server.domain.message.dto.request.MessageCreateRequest;
import org.sopt.sopkathon_server.domain.message.dto.response.MessageCreateResponse;
import org.sopt.sopkathon_server.domain.message.dto.response.MessageGetResponse;
import org.sopt.sopkathon_server.domain.message.entity.Message;
import org.sopt.sopkathon_server.domain.message.repository.MessageRepository;
import org.sopt.sopkathon_server.global.exception.BaseException;
import org.sopt.sopkathon_server.global.response.error.ErrorCode;
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

    @Transactional
    public MessageCreateResponse replyMessage(Long parentMessageId, MessageCreateRequest request) {
        Message parent = messageRepository.findById(parentMessageId)
                .orElseThrow(() -> new BaseException(ErrorCode.MESSAGE_NOT_FOUND));

        Message message = Message.builder()
                .senderInitial(request.senderInitial())
                .receiverInitial(request.receiverInitial())
                .content(request.content())
                .parent(parent)
                .build();

        Message saved = messageRepository.save(message);
        String viewUrl = baseUrl + "/m/" + saved.getId();

        return new MessageCreateResponse(saved.getId(), viewUrl);
    }

    @Transactional(readOnly = true)
    public MessageGetResponse getMessage(Long messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new BaseException(ErrorCode.MESSAGE_NOT_FOUND));
        return MessageGetResponse.from(message);
    }
}