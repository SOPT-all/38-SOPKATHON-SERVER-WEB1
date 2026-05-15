package org.sopt.sopkathon_server.global.response.error;

import lombok.Getter;

@Getter
public enum ErrorCode implements ErrorType {

    // 공통 에러
    // 400 Bad Request
    BAD_REQUEST(400,"CMN_400", "잘못된 요청입니다."),

    INVALID_INPUT(400, "CMN_400_1", "입력값이 올바르지 않습니다."),
    MISSING_PARAMETER(400, "CMN_400_2", "필수 파라미터가 누락되었습니다."),
    INVALID_FORMAT(400, "CMN_400_3", "요청 데이터 형식이 올바르지 않습니다."),

    // 404 Not Found
    NOT_FOUND(404, "CMN_404", "요청한 리소스를 찾을 수 없습니다."),

    // 405 Method Not Allowed
    METHOD_NOT_ALLOWED(405, "CMN_405", "허용되지 않은 HTTP 메서드입니다."),

    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR(500,"CMN_500", "서버 내부 오류가 발생했습니다");



    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message ) {
        this.status = status;
        this.code = code;
        this.message = message;

    }
}
