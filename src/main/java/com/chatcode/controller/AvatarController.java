package com.chatcode.controller;

import com.chatcode.config.auth.LoginUser;
import com.chatcode.dto.BaseResponseDto;
import com.chatcode.dto.avatar.AvatarRequest.AvatarUpdateRequest;
import com.chatcode.dto.avatar.AvatarResponse;
import com.chatcode.service.AvatarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Avatar API", description = "아바타 관련 API")
@RestController
@RequestMapping("/avatars")
@RequiredArgsConstructor
public class AvatarController {

    private final AvatarService avatarService;

    @GetMapping("")
    @Operation(summary = "아바타 목록 조회", description = "모든 아바타 목록을 조회합니다. (누구나 접근 가능)")
    @ApiResponse(
            responseCode = "200",
            description = "아바타 목록 조회 성공",
            content = @Content(
                    examples = @ExampleObject(
                            value = "{\"code\": 200, \"data\": [{\"id\": 1, \"activityPoint\": 100, \"nickname\": \"user1\", \"picture\": \"http://example.com/avatar1.jpg\", \"content\": \"안녕하세요\"}, {\"id\": 2, \"activityPoint\": 200, \"nickname\": \"user2\", \"picture\": \"http://example.com/avatar2.jpg\", \"content\": \"안녕하세요\"}], \"message\": \"success\"}"
                    )
            )
    )
    public ResponseEntity<BaseResponseDto<List<AvatarResponse>>> getAll() {
        List<AvatarResponse> responseBody = avatarService.getAllAvatars();
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK.value(), responseBody, "success"));
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "내 아바타 조회", description = "내 아바타를 조회합니다. (본인만 접근 가능)")
    @ApiResponse(
            responseCode = "200",
            description = "내 아바타 조회 성공",
            content = @Content(
                    examples = @ExampleObject(
                            value = "{\"code\": 200, \"data\": {\"id\": 1, \"activityPoint\": 100, \"nickname\": \"user1\", \"picture\": \"http://example.com/avatar1.jpg\", \"content\": \"안녕하세요\"}, \"message\": \"success\"}"
                    )
            )
    )
    public ResponseEntity<BaseResponseDto<AvatarResponse>> getMyAvatar(@AuthenticationPrincipal LoginUser loginUser) {
        AvatarResponse responseBody = avatarService.getOneAvatar(loginUser.getAvatarId());
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK.value(), responseBody, "success"));
    }

    @GetMapping("/{avatarId}")
    @Operation(summary = "아바타 상세 조회", description = "특정 아바타를 상세 조회합니다. (누구나 접근 가능)")
    @ApiResponse(
            responseCode = "200",
            description = "아바타 상세 조회 성공",
            content = @Content(
                    examples = @ExampleObject(
                            value = "{\"code\": 200, \"data\": {\"id\": 1, \"activityPoint\": 100, \"nickname\": \"user1\", \"picture\": \"http://example.com/avatar1.jpg\", \"content\": \"안녕하세요\"}, \"message\": \"success\"}"
                    )
            )
    )
    public ResponseEntity<BaseResponseDto<AvatarResponse>> getOne(@PathVariable Long avatarId) {
        AvatarResponse responseBody = avatarService.getOneAvatar(avatarId);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK.value(), responseBody, "success"));
    }

    @PutMapping("/{avatarId}")
//    @PreAuthorize("isAuthenticated() and #avatarId == principal.id")
    @Operation(summary = "아바타 수정", description = "특정 아바타를 수정합니다. (본인만 접근 가능)")
    @ApiResponse(
            responseCode = "200",
            description = "아바타 수정 성공",
            content = @Content(
                    examples = @ExampleObject(
                            value = "{\"code\": 200, \"data\": {\"id\": 1, \"activityPoint\": 150, \"nickname\": \"user1\", \"picture\": \"http://example.com/avatar1.jpg\", \"content\": \"안녕하세요, 수정되었습니다.\"}, \"message\": \"success\"}"
                    )
            )
    )
    public ResponseEntity<BaseResponseDto<AvatarResponse>> update(
            @PathVariable Long avatarId,
            @RequestBody AvatarUpdateRequest params
    ) {
        AvatarResponse responseBody = avatarService.updateAvatar(avatarId, params);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK.value(), responseBody, "success"));
    }
}
