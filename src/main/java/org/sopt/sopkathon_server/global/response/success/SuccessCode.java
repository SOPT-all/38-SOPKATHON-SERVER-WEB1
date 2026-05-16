package org.sopt.sopkathon_server.global.response.success;

import lombok.Getter;

@Getter
public enum SuccessCode implements SuccessType {
    // 공통 응답 코드
    SUCCESS(200, "CMN_200", "요청이 성공했습니다."),
    CREATED(201, "CMN_201", "생성되었습니다.");

    private final int status;
    private final String code;
    private final String message;

    SuccessCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
