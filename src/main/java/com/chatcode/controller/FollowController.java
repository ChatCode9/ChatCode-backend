package com.chatcode.controller;

import com.chatcode.config.auth.LoginUser;
import com.chatcode.dto.BaseResponseDto;
import com.chatcode.dto.avatar.AvatarResponse;
import com.chatcode.service.FollowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Follow API", description = "팔로우 관련 API")
@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/avatars/{avatarId}/follow")
    @Operation(summary = "팔로우", description = "현재 로그인한 유저가 특정 아바타를 팔로우합니다. (인증된 사용자만 접근 가능)")
    @ApiResponse(responseCode = "201", description = "팔로우 성공")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BaseResponseDto<String>> follow(@PathVariable Long avatarId,
                                                          @AuthenticationPrincipal LoginUser loginUser) {
        followService.follow(loginUser.getAvatarId(), avatarId);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.CREATED.value(), "", "success"));
    }

    @PostMapping("/avatars/{avatarId}/unfollow")
    @Operation(summary = "언팔로우", description = "현재 로그인한 유저가 특정 아바타를 언팔로우합니다. (인증된 사용자만 접근 가능)")
    @ApiResponse(responseCode = "200", description = "언팔로우 성공")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BaseResponseDto<String>> unfollow(@PathVariable Long avatarId,
                                                            @AuthenticationPrincipal LoginUser loginUser) {
        followService.unfollow(loginUser.getAvatarId(), avatarId);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK.value(), "", "success"));
    }

    @GetMapping("/avatars/{avatarId}/followers")
    @Operation(summary = "팔로워 수 조회", description = "특정 아바타의 팔로워 수를 조회합니다. (누구나 접근 가능)")
    @ApiResponse(responseCode = "200", description = "팔로워 수 조회 성공")
    public ResponseEntity<BaseResponseDto<Long>> getNumberOfFollowers(@PathVariable Long avatarId) {
        Long followers = followService.getNumberOfFollowers(avatarId);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK.value(), followers, "success"));
    }

    @GetMapping("/avatars/{avatarId}/followings")
    @Operation(summary = "팔로잉 수 조회", description = "특정 아바타의 팔로잉 수를 조회합니다. (누구나 접근 가능)")
    @ApiResponse(responseCode = "200", description = "팔로잉 수 조회 성공")
    public ResponseEntity<BaseResponseDto<Long>> getNumberOfFollowings(@PathVariable Long avatarId) {
        Long followings = followService.getNumberOfFollowings(avatarId);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK.value(), followings, "success"));
    }

    @GetMapping("/avatars/{avatarId}/followers/profiles")
    @Operation(summary = "팔로워 프로필 조회", description = "특정 아바타의 팔로워들의 프로필 리스트를 조회합니다. (누구나 접근 가능)")
    @ApiResponse(responseCode = "200", description = "팔로워 프로필 조회 성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = BaseResponseDto.class,
                            example = "{\n  \"statusCode\": 200,\n  \"message\": \"success\",\n  \"data\": [\n    {\n      \"id\": 1,\n      \"activityPoint\": 100,\n      \"nickname\": \"user1\",\n      \"picture\": \"http://example.com/avatar1.jpg\",\n      \"content\": \"안녕하세요\"\n    },\n    {\n      \"id\": 2,\n      \"activityPoint\": 150,\n      \"nickname\": \"user2\",\n      \"picture\": \"http://example.com/avatar2.jpg\",\n      \"content\": null\n    }\n  ]\n}"
                    )
            )
    )
    public ResponseEntity<BaseResponseDto<List<AvatarResponse>>> getFollowersProfiles(@PathVariable Long avatarId) {
        List<AvatarResponse> followersProfiles = followService.getFollowersProfiles(avatarId);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK.value(), followersProfiles, "success"));
    }

    @GetMapping("/avatars/{avatarId}/followings/profiles")
    @Operation(summary = "팔로잉 프로필 조회", description = "특정 아바타의 팔로잉들의 프로필 리스트를 조회합니다. (누구나 접근 가능)")
    @ApiResponse(responseCode = "200", description = "팔로잉 프로필 조회 성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = BaseResponseDto.class,
                            example = "{\n  \"statusCode\": 200,\n  \"message\": \"success\",\n  \"data\": [\n    {\n      \"id\": 3,\n      \"activityPoint\": 120,\n      \"nickname\": \"user3\",\n      \"picture\": \"http://example.com/avatar3.jpg\",\n      \"content\": \"반갑습니다\"\n    },\n    {\n      \"id\": 4,\n      \"activityPoint\": 80,\n      \"nickname\": \"user4\",\n      \"picture\": \"http://example.com/avatar4.jpg\",\n      \"content\": null\n    }\n  ]\n}"
                    )
            )
    )
    public ResponseEntity<BaseResponseDto<List<AvatarResponse>>> getFollowingsProfiles(@PathVariable Long avatarId) {
        List<AvatarResponse> followingsProfiles = followService.getFollowingsProfiles(avatarId);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK.value(), followingsProfiles, "success"));
    }

}
