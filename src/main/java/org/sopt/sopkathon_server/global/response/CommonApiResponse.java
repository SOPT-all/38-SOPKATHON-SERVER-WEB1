package org.sopt.sopkathon_server.global.response;

import org.sopt.sopkathon_server.global.response.error.ErrorType;
import org.sopt.sopkathon_server.global.response.success.SuccessType;

public record CommonApiResponse<T>(
        String code,
        String message,
        T data
) {
    public static <T> CommonApiResponse<T> success(SuccessType successType, T data) {
        return new CommonApiResponse<>(successType.getCode(), successType.getMessage(), data);
    }

    public static CommonApiResponse<Void> success(SuccessType successType) {
        return new CommonApiResponse<>(successType.getCode(), successType.getMessage(), null);
    }

    public static CommonApiResponse<Void> fail(ErrorType errorType) {
        return new CommonApiResponse<>(errorType.getCode(), errorType.getMessage(), null);
    }

    public static <T> CommonApiResponse<T> fail(ErrorType errorType, T data) {
        return new CommonApiResponse<>(errorType.getCode(), errorType.getMessage(), data);
    }
}