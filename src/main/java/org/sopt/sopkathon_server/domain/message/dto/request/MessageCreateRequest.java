package org.sopt.sopkathon_server.domain.message.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record MessageCreateRequest(

        @NotBlank(message = "보내는 사람 이니셜은 필수입니다.")
        String senderInitial,

        @NotBlank(message = "받는 사람 이니셜은 필수입니다.")
        String receiverInitial,

        @NotBlank(message = "내용은 필수입니다.")
        @Pattern(regexp = "^[가-힣ㄱ-ㅎㅏ-ㅣ\\s]+$", message = "한국어만 입력 가능합니다.")
        @Size(max = 50, message = "50자 이하로 입력해주세요.")
        String content
) {
}