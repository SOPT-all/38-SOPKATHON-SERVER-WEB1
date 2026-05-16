package org.sopt.sopkathon_server.domain.archive.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.ErrorResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public final class SavedMessageApiResponses {
    private SavedMessageApiResponses() {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "메시지 저장",
            description = "메시지를 저장합니다"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "아카이브 메시지 저장 성공"),
            @ApiResponse(
                    responseCode = "404",
                    description = "메시지가 없을 때",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public @interface CreateSavedMessage {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "저장된 메시지 전체 조회",
            description = "저장된 메시지를 전체 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "저장 메시지 전체 조회 성공"),
    })
    public @interface GetAllSavedMessages {}


    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "저장된 메시지 단건 조회",
            description = "저장된 메시지를 단건 조회합니다. 비밀번호를 입력해야합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "저장 메시지 단건 조회 성공"),
            @ApiResponse(
                    responseCode = "401",
                    description = "비밀번호 틀렸을 때",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "저장메시지가 없을 때",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public @interface GetSavedMessage {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "메시지 삭제",
            description = "저장된 메시지를 삭제합니다. 비밀번호를 입력해야합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "저장 메시지 삭제 성공"),
            @ApiResponse(
                    responseCode = "401",
                    description = "비밀번호 틀렸을 때",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "저장메시지가 없을 때",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public @interface DeleteSavedMessage {}
}

