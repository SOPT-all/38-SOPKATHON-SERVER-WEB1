package org.sopt.sopkathon_server.domain.message.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.sopt.sopkathon_server.global.response.CommonApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "메시지 생성", description = "익명 메시지를 생성합니다.")
@ApiResponses({
        @ApiResponse(responseCode = "201", description = "메시지 생성 성공",
                content = @Content(schema = @Schema(implementation = CommonApiResponse.class))),
        @ApiResponse(responseCode = "400", description = "유효성 검증 실패",
                content = @Content(schema = @Schema(implementation = CommonApiResponse.class)))
})
public @interface MessageCreateApi {
}