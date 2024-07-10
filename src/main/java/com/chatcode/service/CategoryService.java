package com.chatcode.service;

import static com.chatcode.handler.exception.ExceptionCode.INVALID_CATEGORY_ORDER_SIZE;
import static com.chatcode.handler.exception.ExceptionCode.NOT_FOUND_CATEGORY_ID;

import com.chatcode.domain.entity.Category;
import com.chatcode.dto.category.CategoryRequest.CategoryCreateRequest;
import com.chatcode.dto.category.CategoryRequest.CategoryUpdateNameRequest;
import com.chatcode.dto.category.CategoryRequest.CategoryUpdateOrderRequest;
import com.chatcode.dto.category.CategoryResponse;
import com.chatcode.handler.exception.category.CategoryOrderException;
import com.chatcode.handler.exception.common.ContentNotFoundException;
import com.chatcode.repository.category.CategoryReadRepository;
import com.chatcode.repository.category.CategoryWriteRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryReadRepository categoryReadRepository;
    private final CategoryWriteRepository categoryWriteRepository;

    @Transactional
    public CategoryResponse createNewCategory(CategoryCreateRequest params) {
        Category category = Category.of(params.getName(), categoryReadRepository.count());
        categoryWriteRepository.save(category);
        return CategoryResponse.of(category);
    }

    @Transactional
    public CategoryResponse updateCategoryName(Long categoryId, CategoryUpdateNameRequest params) {

        Category category = categoryReadRepository.findById(categoryId)
                .orElseThrow(() -> new ContentNotFoundException(NOT_FOUND_CATEGORY_ID));

        if (params.getName() != null) {
            category.setName(params.getName());
        }
        categoryWriteRepository.save(category);
        return CategoryResponse.of(category);
    }

    @Transactional
    public List<CategoryResponse> updateCategoryOrders(CategoryUpdateOrderRequest params) {

        if (isInvalidOrderSize(params.getOrders())) {
            throw new CategoryOrderException(INVALID_CATEGORY_ORDER_SIZE,
                    "total category size: " + categoryReadRepository.count());
        }
        for (Long param : params.getOrders()) {
            Category category = categoryReadRepository.findById(param)
                    .orElseThrow(() -> new ContentNotFoundException(NOT_FOUND_CATEGORY_ID));
            category.setSortOrder(params.getOrders().indexOf(param));
            categoryWriteRepository.save(category);
        }
        return categoryReadRepository.findAll().stream()
                .map(CategoryResponse::of)
                .toList();
    }

    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryReadRepository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException(NOT_FOUND_CATEGORY_ID));
        categoryWriteRepository.delete(category);
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> getAllCategories() {
        return categoryReadRepository.findAll().stream()
                .map(CategoryResponse::of)
                .toList();
    }

    @Transactional(readOnly = true)
    public CategoryResponse getOneCategory(Long id) {
        Category category = categoryReadRepository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException(NOT_FOUND_CATEGORY_ID));
        return CategoryResponse.of(category);
    }

    private boolean isInvalidOrderSize(List<Long> ids) {
        return ids.size() != categoryReadRepository.count();
    }

}
