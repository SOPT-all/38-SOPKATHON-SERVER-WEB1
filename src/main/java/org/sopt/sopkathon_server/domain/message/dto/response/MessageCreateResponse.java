package org.sopt.sopkathon_server.domain.message.dto.response;

public record MessageCreateResponse(
        Long messageId,
        String viewUrl
) {
}