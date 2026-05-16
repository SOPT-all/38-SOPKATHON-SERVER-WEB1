package org.sopt.sopkathon_server.domain.message.dto.response;

import org.sopt.sopkathon_server.domain.message.entity.Message;

import java.time.LocalDateTime;

public record MessageGetResponse(
        String senderInitial,
        String receiverInitial,
        String content,
        LocalDateTime createdAt
) {
    public static MessageGetResponse from(Message message) {
        return new MessageGetResponse(
                message.getSenderInitial(),
                message.getReceiverInitial(),
                message.getContent(),
                message.getCreatedAt()
        );
    }
}