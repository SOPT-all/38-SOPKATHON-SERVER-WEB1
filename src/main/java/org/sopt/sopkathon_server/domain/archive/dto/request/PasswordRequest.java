package org.sopt.sopkathon_server.domain.archive.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record PasswordRequest(
        @Schema(description = "사용자의 비밀번호입니다", example = "1234")
        @NotBlank
        String password
) {
}
