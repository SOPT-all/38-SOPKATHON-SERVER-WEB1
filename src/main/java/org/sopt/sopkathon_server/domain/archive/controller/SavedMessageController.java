package org.sopt.sopkathon_server.domain.archive.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.sopkathon_server.domain.archive.dto.request.PasswordRequest;
import org.sopt.sopkathon_server.domain.archive.dto.request.SavedMessageRequest;
import org.sopt.sopkathon_server.domain.archive.dto.response.SavedMessageResponse;
import org.sopt.sopkathon_server.domain.archive.service.SavedMessageService;
import org.sopt.sopkathon_server.global.response.CommonApiResponse;
import org.sopt.sopkathon_server.global.response.success.SuccessCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Archives", description = "아카이브 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/archives")
public class SavedMessageController {

    private final SavedMessageService savedMessageService;

    // 아카이브 메시지 저장
    @PostMapping
    @SavedMessageApiResponses.CreateSavedMessage
    public ResponseEntity<CommonApiResponse<Long>> createSavedMessage(
            @Valid @RequestBody
            SavedMessageRequest savedMessageRequest
    ) {
        Long response = savedMessageService.createSavedMessage(savedMessageRequest.messageId(),
                savedMessageRequest.password());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CommonApiResponse.success(SuccessCode.CREATED, response));
    }

    // 아카이브 전체 조회
    @GetMapping
    @SavedMessageApiResponses.GetAllSavedMessages
    public ResponseEntity<CommonApiResponse<List<SavedMessageResponse>>> getAllSavedMessages() {
        List<SavedMessageResponse> responses = savedMessageService.getAllSavedMessages();

        return ResponseEntity.ok(
                CommonApiResponse.success(SuccessCode.SUCCESS, responses)
        );
    }

   // 아카이브 단건 조회
    @PostMapping("/{savedMessageId}")
    @SavedMessageApiResponses.GetSavedMessage
    public ResponseEntity<CommonApiResponse<SavedMessageResponse>> getSavedMessage(
            @Parameter(description = "조회할 저장된 메시지의 id", example = "1")
            @PathVariable Long savedMessageId,

            @Valid @RequestBody
            PasswordRequest request
    ) {
        SavedMessageResponse response = savedMessageService.getSavedMessage(savedMessageId, request.password());

        return ResponseEntity.ok(
                CommonApiResponse.success(SuccessCode.SUCCESS, response)
        );
    }

    // 저장된 메시지 삭제
    @PostMapping("/{savedMessageId}/delete")
    @SavedMessageApiResponses.DeleteSavedMessage
    public ResponseEntity<CommonApiResponse<Void>> deleteSavedMessage(
            @Parameter(description = "삭제할 저장된 메시지의 id", example = "1")
            @PathVariable Long savedMessageId,

            @Valid @RequestBody PasswordRequest request
    ) {
        savedMessageService.deleteSavedMessage(savedMessageId, request.password());

        return ResponseEntity.ok(
                CommonApiResponse.success(SuccessCode.SUCCESS, null)
        );
    }
}
