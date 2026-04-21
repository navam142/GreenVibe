package com.app.greenvibe.service;

import com.app.greenvibe.dto.request.ProductRequestDto;
import com.app.greenvibe.dto.response.ProductResponseDto;
import com.app.greenvibe.entity.Category;
import com.app.greenvibe.entity.Product;
import com.app.greenvibe.exception.ResourceNotFoundException;
import com.app.greenvibe.mapper.ProductMapper;
import com.app.greenvibe.repository.CategoryRepository;
import com.app.greenvibe.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    // Used by Admins to add new items to the store
    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto request) {
        // 1. Verify the category exists
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", request.getCategoryId()));

        // 2. Map DTO to Entity and set the category
        Product product = productMapper.toEntity(request, category);

        // 3. Save to database
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    // Used by customers to view the store catalog
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .toList();
    }

    // Used by customers when they click on a specific product for more details
    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        return productMapper.toDto(product);
    }
}
