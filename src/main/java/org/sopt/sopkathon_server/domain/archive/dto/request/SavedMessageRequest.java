package org.sopt.sopkathon_server.domain.archive.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SavedMessageRequest (
        @NotBlank
        Long messageId,
        @NotBlank
        String password
) {
}
