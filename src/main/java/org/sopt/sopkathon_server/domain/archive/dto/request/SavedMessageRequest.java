package org.sopt.sopkathon_server.domain.archive.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SavedMessageRequest (
        @Schema(description = "저장하고 싶은 message의 id입니다.", example = "1")
        @NotNull
        Long messageId,

        @Schema(description = "저장하기 위한 비밀번호입니다.", example = "1234")
        @NotBlank
        String password
) {
}
