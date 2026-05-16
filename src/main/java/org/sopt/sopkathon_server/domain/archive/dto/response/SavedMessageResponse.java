package org.sopt.sopkathon_server.domain.archive.dto.response;

import org.sopt.sopkathon_server.domain.archive.entity.SavedMessage;

import java.time.LocalDateTime;

public record SavedMessageResponse(
        Long savedMessageId,
        String content,
        String senderInitial,
        String receiverInitial,
        LocalDateTime createdAt
) {
    public static SavedMessageResponse from(SavedMessage savedMessage) {
        return new SavedMessageResponse(
                savedMessage.getId(),
                savedMessage.getMessage().getContent(),
                savedMessage.getSenderInitial(),
                savedMessage.getReceiverInitial(),
                savedMessage.getCreatedAt()
        );
    }
}
