package org.sopt.sopkathon_server.global.exception;

import lombok.Getter;
import org.sopt.sopkathon_server.global.response.error.ErrorCode;
import org.sopt.sopkathon_server.global.response.error.ErrorType;

@Getter
public class BaseException extends RuntimeException {

    private final ErrorType errorCode;

    public BaseException(ErrorType errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BaseException(ErrorCode errorCode, String detail) {
        super(errorCode.getMessage() + " → " + detail);
        this.errorCode = errorCode;
    }
}
