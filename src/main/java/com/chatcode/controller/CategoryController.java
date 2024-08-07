package com.chatcode.controller;

import com.chatcode.dto.BaseResponseDto;
import com.chatcode.dto.category.CategoryRequest.CategoryCreateRequest;
import com.chatcode.dto.category.CategoryRequest.CategoryUpdateNameRequest;
import com.chatcode.dto.category.CategoryRequest.CategoryUpdateOrderRequest;
import com.chatcode.dto.category.CategoryResponse;
import com.chatcode.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "카테고리 API", description = "카테고리 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("")
    @Operation(summary = "카테고리 목록 조회", description = "모든 카테고리 목록을 조회하는 API (누구나 접근 가능) <br />")
    @ApiResponse(
            responseCode = "200",
            description = "카테고리 목록 조회 성공",
            content = @Content(
                    examples = @ExampleObject(
                            value = "{\"code\": 200, \"data\": [{\"id\": 1, \"name\": \"카테고리1\", \"sortOrder\": 1}, {\"id\": 2, \"name\": \"카테고리2\", \"sortOrder\": 2}], \"message\": \"success\"}"
                    )
            )
    )
    public ResponseEntity<BaseResponseDto<List<CategoryResponse>>> getAll() {
        List<CategoryResponse> responseBody = categoryService.getAllCategories();
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK.value(), responseBody, "success"));
    }

    @GetMapping("/{categoryId}")
    @Operation(summary = "카테고리 상세 조회", description = "특정 카테고리 상세를 조회하는 API (누구나 접근 가능) <br />")
    @ApiResponse(
            responseCode = "200",
            description = "카테고리 상세 조회 성공",
            content = @Content(
                    examples = @ExampleObject(
                            value = "{\"code\": 200, \"data\": {\"id\": 1, \"name\": \"카테고리1\", \"sortOrder\": 1}, \"message\": \"success\"}"
                    )
            )
    )
    public ResponseEntity<BaseResponseDto<CategoryResponse>> getOne(@PathVariable Long categoryId) {
        CategoryResponse responseBody = categoryService.getOneCategory(categoryId);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK.value(), responseBody, "success"));
    }

    @PostMapping("")
    @Operation(summary = "카테고리 생성", description = "새로운 카테고리를 생성하는 API (관리자 권한 필요) <br />")
    @ApiResponse(
            responseCode = "201",
            description = "카테고리 생성 성공",
            content = @Content(
                    examples = @ExampleObject(
                            value = "{\"code\": 201, \"data\": {\"id\": 1, \"name\": \"카테고리1\", \"sortOrder\": 1}, \"message\": \"success\"}"
                    )
            )
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<BaseResponseDto<CategoryResponse>> create(@Valid @RequestBody CategoryCreateRequest params,
                                                                    BindingResult bindingResult) {
        CategoryResponse responseBody = categoryService.createNewCategory(params);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.CREATED.value(), responseBody, "success"));
    }

    @PutMapping("/{categoryId}/name")
    @Operation(summary = "카테고리 이름 수정", description = "특정 카테고리의 이름을 수정하는 API (관리자 권한 필요) <br />")
    @ApiResponse(
            responseCode = "200",
            description = "카테고리 이름 수정 성공",
            content = @Content(
                    examples = @ExampleObject(
                            value = "{\"code\": 200, \"data\": {\"id\": 1, \"name\": \"수정된 카테고리\", \"sortOrder\": 1}, \"message\": \"success\"}"
                    )
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "잘못된 category ID에 대한 요청",
            content = @Content(
                    examples = @ExampleObject(
                            value = "{\"code\": 400, \"data\": null, \"message\": \"잘못된 category ID\"}"
                    )
            )
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<BaseResponseDto<CategoryResponse>> updateName(@PathVariable Long categoryId,
                                                                        @Valid @RequestBody CategoryUpdateNameRequest params,
                                                                        BindingResult bindingResult) {
        CategoryResponse responseBody = categoryService.updateCategoryName(categoryId, params);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK.value(), responseBody, "success"));
    }

    @PutMapping("/order")
    @Operation(summary = "카테고리 순서 수정", description = "카테고리의 순서를 수정하는 API (관리자 권한 필요) <br />")
    @ApiResponse(
            responseCode = "200",
            description = "카테고리 순서 수정 성공",
            content = @Content(
                    examples = @ExampleObject(
                            value = "{\"code\": 200, \"data\": [{\"id\": 1, \"name\": \"카테고리1\", \"sortOrder\": 2}, {\"id\": 2, \"name\": \"카테고리2\", \"sortOrder\": 1}], \"message\": \"success\"}"
                    )
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "잘못된 category ID에 대한 요청 (누락, 중복 등)",
            content = @Content(
                    examples = @ExampleObject(
                            value = "{\"code\": 400, \"data\": null, \"message\": \"잘못된 category ID\"}"
                    )
            )
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<BaseResponseDto<List<CategoryResponse>>> updateOrders(
            @Valid @RequestBody CategoryUpdateOrderRequest params,
            BindingResult bindingResult
    ) {
        List<CategoryResponse> responseBody = categoryService.updateCategoryOrders(params);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK.value(), responseBody, "success"));
    }

    @DeleteMapping("/{categoryId}")
    @Operation(summary = "카테고리 삭제", description = "특정 카테고리를 삭제하는 API (관리자 권한 필요) <br />")
    @ApiResponse(
            responseCode = "200",
            description = "카테고리 삭제 성공",
            content = @Content(
                    examples = @ExampleObject(
                            value = "{\"code\": 200, \"data\": null, \"message\": \"success\"}"
                    )
            )
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<BaseResponseDto<Void>> delete(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK.value(), null, "success"));
    }

}
