package com.app.greenvibe.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {
    @NotBlank
    private String name;

    @NotNull
    @Positive
    private BigDecimal price;
    @NotNull @Positive
    private int stockQuantity;

    private String description;

    @NotNull
    private Long categoryId;
}
