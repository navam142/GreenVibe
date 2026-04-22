package com.app.greenvibe.mapper;

import com.app.greenvibe.dto.request.CategoryRequestDto;
import com.app.greenvibe.dto.response.CategoryResponseDto;
import com.app.greenvibe.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryRequestDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return category;
    }

    public CategoryResponseDto toDto(Category category) {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }
}
