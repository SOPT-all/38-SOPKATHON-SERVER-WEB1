package org.sopt.sopkathon_server.global.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.sopt.sopkathon_server.global.response.CommonApiResponse;
import org.sopt.sopkathon_server.global.response.success.SuccessCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Health", description = "서버 상태 확인 API")
@RestController
@RequestMapping("/api/v1")
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<CommonApiResponse<Void>> health() {
        return ResponseEntity.ok(CommonApiResponse.success(SuccessCode.SUCCESS));
    }
}