package com.app.greenvibe.mapper;

import com.app.greenvibe.dto.request.ProductRequestDto;
import com.app.greenvibe.dto.response.ProductResponseDto;
import com.app.greenvibe.entity.Category;
import com.app.greenvibe.entity.Product;
import org.springframework.stereotype.Component;

/**
 * Maps between Product entity and its DTOs.
 * Category is resolved by the service layer (fetched from DB by categoryId)
 * and passed in — the mapper never touches a repository.
 */
@Component
public class ProductMapper {

    /**
     * @param dto      the incoming product request
     * @param category the Category entity resolved in the service layer
     */
    public Product toEntity(ProductRequestDto dto, Category category) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setStockQuantity(dto.getStockQuantity());
        product.setCategory(category);
        return product;
    }

    public ProductResponseDto toDto(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .imageUrl(product.getImageUrl())
                .categoryName(product.getCategory() != null
                        ? product.getCategory().getName()
                        : null)
                .build();
    }
}