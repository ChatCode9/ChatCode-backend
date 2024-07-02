package com.chatcode.controller;

import com.chatcode.config.auth.LoginUser;
import com.chatcode.dto.BaseResponseDto;
import com.chatcode.dto.tag.InterestTagRequest.InterestTagIdRequest;
import com.chatcode.dto.tag.InterestTagRequest.InterestTagNameRequest;
import com.chatcode.dto.tag.InterestTagRequest.InterestTagRenameRequest;
import com.chatcode.dto.tag.InterestTagResponse;
import com.chatcode.service.AvatarService;
import com.chatcode.service.InterestTagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Interest Tag API", description = "관심 태그 관련 API")
@RequiredArgsConstructor
@RestController
public class InterestTagController {

    private final InterestTagService interestTagService;
    private final AvatarService avatarService;

    @GetMapping("/avatars/interest-tags")
    @Operation(summary = "관심 태그 목록 조회", description = "모든 관심 태그 목록을 조회합니다. (누구나 접근 가능)")
    @ApiResponse(responseCode = "200", description = "관심 태그 목록 조회 성공")
    public ResponseEntity<BaseResponseDto<List<InterestTagResponse>>> getAll() {
        List<InterestTagResponse> responseBody = interestTagService.getAllInterestTags();
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK.value(), responseBody, "success"));
    }

    @GetMapping("/avatars/{avatarId}/interest-tags")
    @Operation(summary = "USER의 관심 태그 목록 조회", description = "USER의 관심 태그 목록을 조회합니다. (누구나 접근 가능)")
    @ApiResponse(responseCode = "200", description = "USER의 관심 태그 목록 조회 성공")
    public ResponseEntity<BaseResponseDto<List<InterestTagResponse>>> get(@PathVariable("avatarId") Long avatarId) {
        List<InterestTagResponse> responseBody = avatarService.getInterestTags(avatarId);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK.value(), responseBody, "success"));
    }

    @PostMapping("/avatars/interest-tags")
    @Operation(summary = "USER의 관심 태그 추가(로그인 필요)", description = "USER의 관심 태그를 덮어씌웁니다. 기존에 등록된 유저의 태그들은 모두 삭제되고 새롭게 요청 들어온 태그들로 업데이트됩니다. (본인만 접근 가능)")
    @ApiResponse(responseCode = "201", description = "USER의 관심 태그 추가 성공")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BaseResponseDto<InterestTagResponse>> addUserTag(@RequestBody List<InterestTagIdRequest> params,
                                                                    @AuthenticationPrincipal LoginUser loginUser) {
        avatarService.addInterestTags(params, loginUser.getAvatarId());
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseDto<>(HttpStatus.CREATED.value(), null, "success"));
    }

    @DeleteMapping("/avatars/interest-tags/{interestTagId}")
    @Operation(summary = "USER의 관심 태그 삭제", description = "현재 로그인한 유저가 본인의 관심 태그를 삭제합니다. (본인만 접근 가능)")
    @ApiResponse(responseCode = "200", description = "USER의 관심 태그 삭제 성공")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BaseResponseDto<InterestTagResponse>> delete(@PathVariable Long interestTagId,
                                                                       @AuthenticationPrincipal LoginUser loginUser) {
        avatarService.deleteInterestTags(interestTagId, loginUser.getAvatarId());
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseDto<>(HttpStatus.OK.value(), null, "success"));
    }

    @PostMapping("/admin/interest-tags")
    @Operation(summary = "관심 태그 추가", description = "관심 태그를 추가합니다. (관리자만 접근 가능)")
    @ApiResponse(responseCode = "201", description = "관심 태그 추가 성공")
    public ResponseEntity<BaseResponseDto<List<InterestTagResponse>>> add(@Valid @RequestBody List<InterestTagNameRequest> params,
                                                                          BindingResult bindingResult) {
        List<InterestTagResponse> responseBody = interestTagService.addInterestTags(params);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseDto<>(HttpStatus.CREATED.value(), responseBody, "success"));
    }

    @DeleteMapping("/admin/interest-tags/{interestTagId}")
    @Operation(summary = "관심 태그 삭제", description = "관심 태그를 삭제합니다. (관리자만 접근 가능)")
    @ApiResponse(responseCode = "200", description = "관심 태그 삭제 성공")
    public ResponseEntity<BaseResponseDto<Void>> delete(@PathVariable Long interestTagId) {
        interestTagService.deleteInterestTags(interestTagId);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseDto<>(HttpStatus.OK.value(), null, "success"));
    }

    @PutMapping("/admin/interest-tags")
    @Operation(summary = "관심 태그 수정", description = "관심 태그를 수정합니다. (관리자만 접근 가능)")
    @ApiResponse(responseCode = "200", description = "관심 태그 수정 성공")
    public ResponseEntity<BaseResponseDto<List<InterestTagResponse>>> update(@Valid @RequestBody List<InterestTagRenameRequest> params,
                                                                             BindingResult bindingResult) {
        List<InterestTagResponse> responseBody = interestTagService.updateInterestTags(params);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseDto<>(HttpStatus.OK.value(), responseBody, "success"));
    }
}