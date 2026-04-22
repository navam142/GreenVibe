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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final ImageService imageService;

    // Used by Admins to add new items to the store
    @Transactional
    public ProductResponseDto create(ProductRequestDto request, MultipartFile imageFile) {
        // 1. Verify the category exists
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", request.getCategoryId()));

        // 2. Upload image if provided
        if (imageFile == null || imageFile.isEmpty()) {
            throw new IllegalArgumentException("Product image is required");
        }
        String imageUrl = imageService.uploadProductImage(imageFile);

        // 3. Map DTO to Entity and set the category
        Product product = productMapper.toEntity(request, category);
        product.setImageUrl(imageUrl);

        // 4. Save to database
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    // Used by customers to view the store catalog
    public List<ProductResponseDto> getAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .toList();
    }

    // Used by customers when they click on a specific product for more details
    public ProductResponseDto getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        return productMapper.toDto(product);
    }

    public String deleteById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        productRepository.delete(product);
        return "Product deleted successfully";
    }
}
