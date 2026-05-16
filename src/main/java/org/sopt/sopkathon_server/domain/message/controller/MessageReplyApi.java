package org.sopt.sopkathon_server.domain.message.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.sopt.sopkathon_server.global.response.CommonApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "메시지 답장", description = "특정 메시지에 답장을 보냅니다. messageId는 원본 메시지 ID여야 합니다. (답장 메시지에는 답장 불가)")
@RequestBody(content = @Content(examples = @ExampleObject(value = """
        {
          "senderInitial": "YJ",
          "receiverInitial": "JH",
          "content": "나도 보고싶었어~"
        }
        """)))
@ApiResponses({
        @ApiResponse(responseCode = "201", description = "답장 생성 성공",
                content = @Content(schema = @Schema(implementation = CommonApiResponse.class))),
        @ApiResponse(responseCode = "400", description = "답장에는 답장할 수 없음",
                content = @Content(schema = @Schema(implementation = CommonApiResponse.class))),
        @ApiResponse(responseCode = "404", description = "원본 메시지를 찾을 수 없음",
                content = @Content(schema = @Schema(implementation = CommonApiResponse.class)))
})
public @interface MessageReplyApi {
}
