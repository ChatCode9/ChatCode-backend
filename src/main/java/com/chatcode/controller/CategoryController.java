package com.chatcode.controller;

import com.chatcode.dto.BaseResponseDto;
import com.chatcode.dto.category.CategoryRequest.CategoryCreateRequest;
import com.chatcode.dto.category.CategoryRequest.CategoryUpdateNameRequest;
import com.chatcode.dto.category.CategoryRequest.CategoryUpdateOrderRequest;
import com.chatcode.dto.category.CategoryResponse;
import com.chatcode.service.CategoryService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<BaseResponseDto<List<CategoryResponse>>> getAll() {
        List<CategoryResponse> responseBody = categoryService.getAllCategories();
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK.value(), responseBody, "success"));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<BaseResponseDto<CategoryResponse>> getOne(@PathVariable Long categoryId) {
        CategoryResponse responseBody = categoryService.getOneCategory(categoryId);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK.value(), responseBody, "success"));
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<BaseResponseDto<CategoryResponse>> create(@Valid @RequestBody CategoryCreateRequest params) {
        CategoryResponse responseBody = categoryService.createNewCategory(params);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.CREATED.value(), responseBody, "success"));
    }

    @PutMapping("/{categoryId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<BaseResponseDto<CategoryResponse>> updateName(@PathVariable Long categoryId,
                                                                        @Valid @RequestBody CategoryUpdateNameRequest params) {
        CategoryResponse responseBody = categoryService.updateCategoryName(categoryId, params);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK.value(), responseBody, "success"));
    }

    @PutMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<BaseResponseDto<List<CategoryResponse>>> updateOrders(
            @Valid @RequestBody CategoryUpdateOrderRequest params) {
        List<CategoryResponse> responseBody = categoryService.updateCategoryOrders(params);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK.value(), responseBody, "success"));
    }

    @DeleteMapping("/{categoryId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<BaseResponseDto<Void>> delete(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK.value(), null, "success"));
    }

}
