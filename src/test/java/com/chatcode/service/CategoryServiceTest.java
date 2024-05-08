package com.chatcode.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.chatcode.domain.entity.Category;
import com.chatcode.dto.category.CategoryRequest.CategoryCreateRequest;
import com.chatcode.dto.category.CategoryRequest.CategoryUpdateNameRequest;
import com.chatcode.dto.category.CategoryResponse;
import com.chatcode.repository.category.CategoryReadRepository;
import com.chatcode.repository.category.CategoryWriteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;
    @Mock
    private CategoryWriteRepository categoryWriteRepository;
    @Mock
    private CategoryReadRepository categoryReadRepository;
    @Spy
    private ObjectMapper om;

    @Test
    void createNewCategory_test() {
        // given
        CategoryCreateRequest categoryCreateRequest = new CategoryCreateRequest();
        categoryCreateRequest.setName("test");

        // stub
        when(categoryReadRepository.count()).thenReturn(0);
        when(categoryWriteRepository.save(any())).thenReturn(Category.of("test", 0));

        // when
        CategoryResponse result = categoryService.createNewCategory(categoryCreateRequest);

        // then
        assertEquals(result.getName(), "test");
        assertEquals(result.getSortOrder(), 0);
    }

    @Test
    void updateCategoryName() {
        // given
        CategoryUpdateNameRequest categoryUpdateNameRequest = new CategoryUpdateNameRequest();

        categoryUpdateNameRequest.setName("test_2");

        // stub
        when(categoryReadRepository.findById(1L)).thenReturn(java.util.Optional.of(Category.of("test", 1)));
        when(categoryWriteRepository.save(any())).thenReturn(Category.of("test_2", 1));

        // when
        CategoryResponse result = categoryService.updateCategoryName(1L, categoryUpdateNameRequest);

        // then
        assertEquals(result.getName(), "test_2");
        assertEquals(result.getSortOrder(), 1);
    }

}