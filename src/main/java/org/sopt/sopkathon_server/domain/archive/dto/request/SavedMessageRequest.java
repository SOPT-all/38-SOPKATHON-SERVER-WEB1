package org.sopt.sopkathon_server.domain.archive.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SavedMessageRequest (
        @NotNull
        Long messageId,
        @NotBlank
        String password
) {
}
