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
@Operation(summary = "메시지 단건 조회", description = "메시지 ID로 메시지를 조회합니다.")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "메시지 조회 성공",
                content = @Content(schema = @Schema(implementation = CommonApiResponse.class))),
        @ApiResponse(responseCode = "404", description = "메시지를 찾을 수 없음",
                content = @Content(schema = @Schema(implementation = CommonApiResponse.class)))
})
public @interface MessageGetApi {
}