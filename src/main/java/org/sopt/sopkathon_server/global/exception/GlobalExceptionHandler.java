package org.sopt.sopkathon_server.global.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.sopt.sopkathon_server.global.response.CommonApiResponse;
import org.sopt.sopkathon_server.global.response.error.ErrorCode;
import org.sopt.sopkathon_server.global.response.error.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 커스텀 예외 처리
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<CommonApiResponse<Void>> handleBaseException(BaseException e) {
        ErrorType errorType = e.getErrorCode();
        log.warn("[BaseException] code={}, message={}", errorType.getCode(), errorType.getMessage());
        return ResponseEntity
                .status(errorType.getStatus())
                .body(CommonApiResponse.fail(errorType));
    }

    // @Valid 검증 실패 (필드별 에러 메시지 상세 반환)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        Map<String, String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        fieldError -> fieldError.getDefaultMessage() != null
                                ? fieldError.getDefaultMessage() : "유효하지 않은 값입니다",
                        (existing, newValue) -> existing + ", " + newValue
                ));
        log.warn("[Validation] {}", errors);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(CommonApiResponse.fail(ErrorCode.INVALID_INPUT, errors));
    }

    // 바인딩 실패 처리
    @ExceptionHandler(BindException.class)
    public ResponseEntity<CommonApiResponse<Void>> handleBindException(BindException e) {
        log.warn("[BindException] {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(CommonApiResponse.fail(ErrorCode.INVALID_INPUT));
    }

    // 파라미터 타입 불일치
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<CommonApiResponse<Void>> handleTypeMismatch(
            MethodArgumentTypeMismatchException e) {
        log.warn("[TypeMismatch] param={}", e.getName());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(CommonApiResponse.fail(ErrorCode.INVALID_FORMAT));
    }

    // 필수 파라미터 누락
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<CommonApiResponse<Void>> handleMissingParameter(
            MissingServletRequestParameterException e) {
        log.warn("[MissingParam] param={}", e.getParameterName());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(CommonApiResponse.fail(ErrorCode.INVALID_INPUT));
    }

    // JSON 파싱 실패 (enum, 날짜 형식 등 상세 필드 정보 포함)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CommonApiResponse<Map<String, String>>> handleMessageNotReadableException(
            HttpMessageNotReadableException e) {
        Map<String, String> errorDetails = new HashMap<>();
        if (e.getCause() instanceof InvalidFormatException invalidFormatException) {
            String fieldName = invalidFormatException.getPath().stream()
                    .map(JsonMappingException.Reference::getFieldName)
                    .collect(Collectors.joining("."));
            log.warn("[InvalidFormat] field='{}', value={}, targetType={}",
                    fieldName, invalidFormatException.getValue(),
                    invalidFormatException.getTargetType().getSimpleName());
            errorDetails.put(fieldName, "올바른 형식이 아닙니다");
        } else {
            log.warn("[NotReadable] {}", e.getMessage());
            errorDetails.put("body", "요청 데이터를 읽을 수 없습니다");
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(CommonApiResponse.fail(ErrorCode.INVALID_FORMAT, errorDetails));
    }

    // 잘못된 URL 요청
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<CommonApiResponse<Void>> handleNoHandler(NoHandlerFoundException e) {
        log.warn("[NoHandler] {}", e.getRequestURL());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(CommonApiResponse.fail(ErrorCode.NOT_FOUND));
    }

    // 지원하지 않는 HTTP 메서드
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<CommonApiResponse<Void>> handleMethodNotAllowed(
            HttpRequestMethodNotSupportedException e) {
        log.warn("[MethodNotAllowed] {}", e.getMethod());
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(CommonApiResponse.fail(ErrorCode.METHOD_NOT_ALLOWED));
    }

    // IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CommonApiResponse<Map<String, String>>> handleIllegalArgumentException(
            IllegalArgumentException e) {
        log.warn("[IllegalArgument] {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(CommonApiResponse.fail(ErrorCode.INVALID_INPUT, Map.of("message", e.getMessage())));
    }

    // 그 외 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonApiResponse<Void>> handleException(Exception e) {
        log.error("[UnhandledException] {}", e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(CommonApiResponse.fail(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}