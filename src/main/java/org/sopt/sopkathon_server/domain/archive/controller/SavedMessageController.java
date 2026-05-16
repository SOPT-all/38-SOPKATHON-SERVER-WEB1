package org.sopt.sopkathon_server.domain.archive.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.sopkathon_server.domain.archive.dto.request.PasswordRequest;
import org.sopt.sopkathon_server.domain.archive.dto.request.SavedMessageRequest;
import org.sopt.sopkathon_server.domain.archive.dto.response.SavedMessageResponse;
import org.sopt.sopkathon_server.domain.archive.service.SavedMessageService;
import org.sopt.sopkathon_server.global.response.CommonApiResponse;
import org.sopt.sopkathon_server.global.response.success.SuccessCode;
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
    public ResponseEntity<CommonApiResponse<Long>> createSavedMessage(
            @Valid
            @RequestBody
            SavedMessageRequest savedMessageRequest
    ) {
        Long response = savedMessageService.createSavedMessage(savedMessageRequest.messageId(),
                savedMessageRequest.password());

        return ResponseEntity.ok(
                CommonApiResponse.success(SuccessCode.SUCCESS, response)
        );
    }

    // 아카이브 전체 조회
    @GetMapping
    public ResponseEntity<CommonApiResponse<List<SavedMessageResponse>>> getAllSavedMessages() {
        List<SavedMessageResponse> responses = savedMessageService.getAllSavedMessages();

        return ResponseEntity.ok(
                CommonApiResponse.success(SuccessCode.SUCCESS, responses)
        );
    }

   // 아카이브 단건 조회
    @GetMapping("/{savedMessageId}")
    public ResponseEntity<CommonApiResponse<SavedMessageResponse>> getSavedMessage(
            @PathVariable Long savedMessageId,

            @Valid
            @RequestBody
            PasswordRequest request
    ) {
        SavedMessageResponse response = savedMessageService.getSavedMessage(savedMessageId, request.password());

        return ResponseEntity.ok(
                CommonApiResponse.success(SuccessCode.SUCCESS, response)
        );
    }

}
