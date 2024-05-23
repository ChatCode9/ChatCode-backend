package com.chatcode.controller;

import com.chatcode.config.auth.LoginUser;
import com.chatcode.dto.BaseResponseDto;
import com.chatcode.dto.avatar.AvatarResponse;
import com.chatcode.service.FollowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/avatars/{avatarId}/follow")
    @Operation(summary = "팔로우", description = "특정 아바타를 팔로우합니다. (인증된 사용자만 접근 가능)")
    @ApiResponse(responseCode = "201", description = "팔로우 성공")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BaseResponseDto<String>> follow(@PathVariable Long avatarId,
                                                          @AuthenticationPrincipal LoginUser loginUser) {
        followService.follow(loginUser.getAvatarId(), avatarId);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.CREATED.value(), "", "success"));
    }

    @PostMapping("/avatars/{avatarId}/unfollow")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BaseResponseDto<String>> unfollow(@PathVariable Long avatarId,
                                                            @AuthenticationPrincipal LoginUser loginUser) {
        followService.unfollow(loginUser.getAvatarId(), avatarId);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK.value(), "", "success"));
    }

    @GetMapping("/avatars/{avatarId}/followers")
    public ResponseEntity<BaseResponseDto<Long>> getNumberOfFollowers(@PathVariable Long avatarId) {
        Long followers = followService.getNumberOfFollowers(avatarId);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK.value(), followers, "success"));
    }

    @GetMapping("/avatars/{avatarId}/followings")
    public ResponseEntity<BaseResponseDto<Long>> getNumberOfFollowings(@PathVariable Long avatarId) {
        Long followings = followService.getNumberOfFollowings(avatarId);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK.value(), followings, "success"));
    }

    @GetMapping("/avatars/{avatarId}/followers/profiles")
    public ResponseEntity<BaseResponseDto<List<AvatarResponse>>> getFollowersProfiles(@PathVariable Long avatarId) {
        List<AvatarResponse> followersProfiles = followService.getFollowersProfiles(avatarId);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK.value(), followersProfiles, "success"));
    }

    @GetMapping("/avatars/{avatarId}/followings/profiles")
    public ResponseEntity<BaseResponseDto<List<AvatarResponse>>> getFollowingsProfiles(@PathVariable Long avatarId) {
        List<AvatarResponse> followingsProfiles = followService.getFollowingsProfiles(avatarId);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK.value(), followingsProfiles, "success"));
    }

}
