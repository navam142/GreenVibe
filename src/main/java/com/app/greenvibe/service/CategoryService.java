package com.app.greenvibe.service;

import com.app.greenvibe.dto.request.CategoryRequestDto;
import com.app.greenvibe.dto.response.CategoryResponseDto;
import com.app.greenvibe.entity.Category;
import com.app.greenvibe.exception.ResourceNotFoundException;
import com.app.greenvibe.mapper.CategoryMapper;
import com.app.greenvibe.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryResponseDto create(CategoryRequestDto request) {
        Category category =  categoryMapper.toEntity(request);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    public List<CategoryResponseDto> getAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    public Optional<CategoryResponseDto> getById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDto);
    }

    public String deleteById(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return "Category with id " + id + " deleted successfully.";
        } else {
            throw new ResourceNotFoundException("Category", "id", id);
        }
    }
}
